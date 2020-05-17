package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.response.CategoryResponse;

public interface CategoryService {

	Category addCategory(Category categoryToAdd);

	List<Category> getAll();

	List<CategoryResponse> buildCategoryResponseList(List<Category> categoryList);

}
