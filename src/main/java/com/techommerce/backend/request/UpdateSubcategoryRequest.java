package com.techommerce.backend.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techommerce.backend.entity.Category;

import lombok.Data;

@Data
public class UpdateSubcategoryRequest {

	@NotNull
	private Long subcategoryId;

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
	private Boolean subcategoryState;

	@NotNull
	private Category category;
}
