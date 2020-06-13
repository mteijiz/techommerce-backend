package com.techommerce.backend.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techommerce.backend.entity.Category;

import lombok.Data;

@Data
public class AddSubcategoryRequest {

	@NotNull
	@Size(min = 1, max = 10)
	@NotBlank
	private String subcategoryCode;

	@NotNull
	@Size(min = 1, max = 15)
	@NotBlank
	private String subcategoryName;

	@Size(max = 50)
	private String subcategoryDescription;
	
	@NotNull
	private Category category;

}
