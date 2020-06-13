package com.techommerce.backend.response;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;

import lombok.Data;

@Data
public class SubcategoryResponse {

	
	private Long subcategoryId;
	private String subcategoryCode;
	private String subcategoryName;
	private String subcategoryDescription;
	private Boolean subcategoryState;
	private Category category;
	
	
	public SubcategoryResponse(Subcategory subcategory) {
		// TODO Auto-generated constructor stub
		this.subcategoryId = subcategory.getSubcategoryId();
		this.subcategoryName = subcategory.getSubcategoryName();
		this.subcategoryDescription = subcategory.getSubcategoryDescription();
		this.subcategoryCode = subcategory.getSubcategoryCode();
		this.subcategoryState = subcategory.getSubcategoryState();
		this.category = subcategory.getCategory();
	}
}
