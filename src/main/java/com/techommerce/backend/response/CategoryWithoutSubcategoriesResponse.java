package com.techommerce.backend.response;

import com.techommerce.backend.entity.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryWithoutSubcategoriesResponse {

	private Long categoryId;
	private String categoryCode;
	private String categoryName;
	private String categoryDescription;
	private Boolean categoryState;

	public CategoryWithoutSubcategoriesResponse(Category categoryAdded) {
		this.categoryId = categoryAdded.getCategoryId();
		this.categoryCode = categoryAdded.getCategoryCode();
		this.categoryName = categoryAdded.getCategoryName();
		this.categoryDescription = categoryAdded.getCategoryDescription();
		this.categoryState = categoryAdded.getCategoryState();
	}
}
