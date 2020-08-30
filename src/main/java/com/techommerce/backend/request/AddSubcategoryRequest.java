package com.techommerce.backend.request;

import com.techommerce.backend.entity.Category;

import lombok.Data;

@Data
public class AddSubcategoryRequest {

	private String subcategoryCode;
	private String subcategoryName;
	private String subcategoryDescription;
	private Boolean subcategoryState;
	private Category category;

}
