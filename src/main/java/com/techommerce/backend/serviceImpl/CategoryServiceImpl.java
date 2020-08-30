package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.exception.AddingCategoryException;
import com.techommerce.backend.exception.EmptyCategoryListException;
import com.techommerce.backend.exception.ExistingBrandException;
import com.techommerce.backend.exception.ExistingCategoryCodeException;
import com.techommerce.backend.exception.ExistingCategoryNameException;
import com.techommerce.backend.exception.NotExistingCategoryException;
import com.techommerce.backend.exception.UpdatingCategoryException;
import com.techommerce.backend.repository.CategoryRepository;
import com.techommerce.backend.response.CategoryWithoutSubcategoriesResponse;
import com.techommerce.backend.service.CategoryService;
import com.techommerce.backend.service.ProductService;
import com.techommerce.backend.service.SubcategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubcategoryService subcategoryService;

	@Autowired
	private ProductService productService;

	@Override
	public Category addCategory(Category categoryToAdd) {
		categoryCodaAndNameToUpperCase(categoryToAdd);
		try {
			Category categoryAdded = categoryRepository.save(categoryToAdd);
			return categoryAdded;
		} catch (DataIntegrityViolationException e) {
//			checkIfCategoryCodeExists(categoryToAdd, e);
//			checkIfCategoryNameExists(categoryToAdd, e);
			throw new AddingCategoryException("Hubo un problema creando la categoría", e);
		}
	}

	@Override
	public List<Category> getAllCategories() {
		List<Category> categoryList = categoryRepository.findAll();
		return categoryList;
	}

	@Override
	public List<CategoryWithoutSubcategoriesResponse> buildCategoryResponseList(List<Category> categories) {
		checkIfCategoryListIsEmpty(categories);
		List<CategoryWithoutSubcategoriesResponse> categoriesResponses = categories.stream()
				.map(category -> new CategoryWithoutSubcategoriesResponse(category)).collect(Collectors.toList());
		return categoriesResponses;
	}

	@Override
	public Category updateCategory(Category categoryToUpdate) {
		categoryCodaAndNameToUpperCase(categoryToUpdate);
		try {
			Category categoryUpdated = categoryRepository.save(categoryToUpdate);
			return categoryUpdated;
		} catch (DataIntegrityViolationException e) {
//			checkIfCategoryCodeExists(categoryToUpdate, e);
//			checkIfCategoryNameExists(categoryToUpdate, e);
			throw new UpdatingCategoryException("Hubo un problema actualizando la categoría", e);
		}
	}

//	@Override
//	public Category updateCategoryState(Category categoryToUpdateState) {
//		if (categoryToUpdateState.getCategoryState()) {
//			categoryToUpdateState.setCategoryState(false);
//			subcategoryService.changingActiveStateOfSubcategoriesBelongToCategory(categoryToUpdateState);
//			productService.changingActiveStateOfProductsBelongToCategory(categoryToUpdateState);
//		} else {
//			categoryToUpdateState.setCategoryState(true);
//			subcategoryService.changingInactiveStateOfSubcategoriesBelongToCategory(categoryToUpdateState);
//			productService.changingInactiveStateOfProductsBelongToCategory(categoryToUpdateState);
//		}
//		Category categoryUpdated = categoryRepository.save(categoryToUpdateState);
//		return categoryUpdated;
//	}

	@Override
	public Category searchCategoryById(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotExistingCategoryException("No se encontro una categoría con esta clave"));
		return category;
	}

	@Override
	public List<Category> getActiveBrands() {
		List<Category> categoryList = categoryRepository.findActiveBrands();
		return categoryList;
	}
	
	public void checkIfCategoryCodeExists(Category category, DataIntegrityViolationException e) {
		if (e.getCause().getCause().getMessage()
				.contains("(category_code)=(" + category.getCategoryCode() + ") already exists"))
			throw new ExistingCategoryCodeException(
					"The category code " + category.getCategoryCode() + " already exists", e);
	}

	public void checkIfCategoryNameExists(Category category, DataIntegrityViolationException e) {
		if (e.getCause().getCause().getMessage()
				.contains("(category_name)=(" + category.getCategoryName() + ") already exists"))
			throw new ExistingCategoryNameException(
					"The category name " + category.getCategoryName() + " already exists", e);
	}

	public void categoryCodaAndNameToUpperCase(Category category) {
		category.setCategoryCode(category.getCategoryCode().toUpperCase());
		category.setCategoryName(category.getCategoryName().toUpperCase());
	}

	public void checkIfCategoryListIsEmpty(List<Category> categories) {
		if (categories.isEmpty())
			throw new EmptyCategoryListException("No hay categorías cargadas");
	}
}
