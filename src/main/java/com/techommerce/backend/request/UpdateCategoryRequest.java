package com.techommerce.backend.request;

import lombok.Data;

@Data
public class UpdateCategoryRequest {

	private Long categoryId;
	private String categoryCode;
	private String categoryName;
	private String categoryDescription;
	private Boolean categoryState;

}
