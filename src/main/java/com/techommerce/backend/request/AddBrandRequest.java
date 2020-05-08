package com.techommerce.backend.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddBrandRequest {

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

	public AddBrandRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddBrandRequest(@NotNull @Size(min = 1, max = 10) @NotBlank String brandCode,
			@NotNull @Size(min = 1, max = 15) @NotBlank String brandName, @Size(max = 30) String brandDescription) {
		super();
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.brandDescription = brandDescription;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}

}
