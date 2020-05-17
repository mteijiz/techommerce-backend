package com.techommerce.backend.response;

import com.techommerce.backend.entity.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResponse {

	private Long categoryId;
	private String categoryCode;
	private String categoryName;
	private String categoryDescription;
	private Boolean categoryState;

	public CategoryResponse(Category categoryAdded) {
		// TODO Auto-generated constructor stub
		this.categoryId = categoryAdded.getCategoryId();
		this.categoryCode = categoryAdded.getCategoryCode();
		this.categoryName = categoryAdded.getCategoryName();
		this.categoryDescription = categoryAdded.getCategoryDescription();
		this.categoryState = categoryAdded.getCategoryState();
	}
}
