package com.techommerce.backend.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCategoryRequest {

	@NotNull
	@Size(min = 1, max = 10)
	@NotBlank
	private String categoryCode;

	@NotNull
	@Size(min = 1, max = 15)
	@NotBlank
	private String categoryName;

	@Size(max = 50)
	private String categoryDescription;

}
