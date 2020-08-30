package com.techommerce.backend.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCategoryRequest {

	private String categoryCode;
	private String categoryName;
	private String categoryDescription;
	private Boolean categoryState;

}
