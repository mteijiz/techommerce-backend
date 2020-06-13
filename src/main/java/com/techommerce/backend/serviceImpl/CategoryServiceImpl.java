package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.exception.EmptyCategoryListException;
import com.techommerce.backend.repository.CategoryRepository;
import com.techommerce.backend.response.CategoryResponse;
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
		Category categoryAdded = categoryRepository.save(categoryToAdd);
		return categoryAdded;
	}

	private void categoryCodaAndNameToUpperCase(Category categoryToAdd) {
		categoryToAdd.setCategoryCode(categoryToAdd.getCategoryCode().toUpperCase());
		categoryToAdd.setCategoryName(categoryToAdd.getCategoryName().toUpperCase());
	}

	@Override
	public List<Category> getAll() {
		List<Category> categoryList = categoryRepository.findAll();
		return categoryList;
	}

	@Override
	public List<CategoryResponse> buildCategoryResponseList(List<Category> categoryList) {
		checkIfCategoryListIsEmpty(categoryList);
		List<CategoryResponse> categoryListResponse = new ArrayList<CategoryResponse>();
		for (Category category : categoryList) {
			CategoryResponse auxCategoryResponse = new CategoryResponse(category);
			categoryListResponse.add(auxCategoryResponse);
		}
		return categoryListResponse;
	}

	private void checkIfCategoryListIsEmpty(List<Category> categoryList) {
		if (categoryList.isEmpty())
			throw new EmptyCategoryListException("There are no categories");
	}

	@Override
	public Category updateCategory(Category categoryToUpdate) {
		categoryCodaAndNameToUpperCase(categoryToUpdate);
		Category categoryUpdated = categoryRepository.save(categoryToUpdate);
		return categoryUpdated;
	}

	@Override
	public Category updateCategoryState(Category categoryToUpdateState) {
		if (categoryToUpdateState.getCategoryState()) {
			categoryToUpdateState.setCategoryState(false);
			subcategoryService.changingActiveStateOfSubcategoriesBelongToCategory(categoryToUpdateState);
			productService.changingActiveStateOfProductsBelongToCategory(categoryToUpdateState);
		}
		else {
			categoryToUpdateState.setCategoryState(true);
			subcategoryService.changingInactiveStateOfSubcategoriesBelongToCategory(categoryToUpdateState);
			productService.changingInactiveStateOfProductsBelongToCategory(categoryToUpdateState);
		}
		Category categoryUpdated = categoryRepository.save(categoryToUpdateState);
		return categoryUpdated;
	}

	@Override
	public Category searchCategoryById(Long categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(categoryId).get();
		return category;
	}

}
