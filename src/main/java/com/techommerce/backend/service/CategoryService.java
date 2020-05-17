package com.techommerce.backend.service;

import java.util.List;

import javax.validation.Valid;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.request.UpdateCategoryRequest;
import com.techommerce.backend.response.CategoryResponse;

public interface CategoryService {

	Category addCategory(Category categoryToAdd);

	List<Category> getAll();

	List<CategoryResponse> buildCategoryResponseList(List<Category> categoryList);

	Category updateCategory(Category categoryToUpdate);

	Category updateCategoryState(Category categoryToUpdateState);

}
