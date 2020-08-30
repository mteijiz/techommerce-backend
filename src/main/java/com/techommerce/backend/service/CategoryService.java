package com.techommerce.backend.service;

import java.util.List;

import javax.validation.Valid;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.request.UpdateCategoryRequest;
import com.techommerce.backend.response.CategoryWithoutSubcategoriesResponse;

public interface CategoryService {

	Category addCategory(Category categoryToAdd);

	List<Category> getAllCategories();

	List<CategoryWithoutSubcategoriesResponse> buildCategoryResponseList(List<Category> categoryList);

	Category updateCategory(Category categoryToUpdate);

	//Category updateCategoryState(Category categoryToUpdateState);

	Category searchCategoryById(Long categoryId);

	List<Category> getActiveBrands();

}
