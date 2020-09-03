package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.response.CategoryWithoutSubcategoriesResponse;

public interface CategoryService {

	Category addCategory(Category categoryToAdd);

	List<Category> getAllCategories();

	List<CategoryWithoutSubcategoriesResponse> buildCategoryResponseList(List<Category> categoryList);

	Category updateCategory(Category categoryToUpdate);

	Category searchCategoryById(Long categoryId);

	List<Category> getActiveBrands();
	
	void categoryCodaAndNameToUpperCase(Category category);
	
	void checkIfCategoryListIsEmpty(List<Category> categories);

}
