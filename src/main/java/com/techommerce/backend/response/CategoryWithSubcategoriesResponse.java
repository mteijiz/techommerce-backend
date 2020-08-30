package com.techommerce.backend.response;

import java.util.List;
import java.util.stream.Collectors;

import com.techommerce.backend.entity.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryWithSubcategoriesResponse {
	private Long categoryId;
	private String categoryCode;
	private String categoryName;
	private String categoryDescription;
	private Boolean categoryState;
	private List<SubcategoryWithoutCategoryResponse> subcategories;

	public CategoryWithSubcategoriesResponse(Category category) {
		this.categoryId = category.getCategoryId();
		this.categoryCode = category.getCategoryCode();
		this.categoryName = category.getCategoryName();
		this.categoryDescription = category.getCategoryDescription();
		this.categoryState = category.getCategoryState();
		this.subcategories = category.getSubcategories().stream().map(subcategory -> new SubcategoryWithoutCategoryResponse(subcategory)).collect(Collectors.toList());
	}
}
