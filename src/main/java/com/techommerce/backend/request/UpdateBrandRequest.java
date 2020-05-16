package com.techommerce.backend.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateBrandRequest {

	private Long brandId;

	@NotNull
	@Size(min = 1, max = 10)
	@NotBlank
	private String brandCode;

	@NotNull
	@Size(min = 1, max = 15)
	@NotBlank
	private String brandName;

	@Size(max = 50)
	private String brandDescription;

	@NotNull
	private Boolean brandState;

}
