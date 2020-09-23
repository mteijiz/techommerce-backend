package com.techommerce.backend.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.exception.AddingCategoryException;
import com.techommerce.backend.exception.EmptyCategoryListException;
import com.techommerce.backend.exception.NotExistingCategoryException;
import com.techommerce.backend.exception.UpdatingCategoryException;
import com.techommerce.backend.repository.CategoryRepository;
import com.techommerce.backend.response.CategoryWithoutSubcategoriesResponse;
import com.techommerce.backend.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category categoryToAdd) {
		try {
			Category categoryAdded = categoryRepository.save(categoryToAdd);
			return categoryAdded;
		} catch (DataIntegrityViolationException e) {
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
		try {
			Category categoryUpdated = categoryRepository.save(categoryToUpdate);
			return categoryUpdated;
		} catch (DataIntegrityViolationException e) {
			throw new UpdatingCategoryException("Hubo un problema actualizando la categoría", e);
		}
	}

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

	@Override
	public void categoryCodaAndNameToUpperCase(Category category) {
		category.setCategoryCode(category.getCategoryCode().toUpperCase());
		category.setCategoryName(category.getCategoryName().toUpperCase());
	}

	@Override
	public void checkIfCategoryListIsEmpty(List<Category> categories) {
		if (categories.isEmpty())
			throw new EmptyCategoryListException("No hay categorías cargadas");
	}
}
