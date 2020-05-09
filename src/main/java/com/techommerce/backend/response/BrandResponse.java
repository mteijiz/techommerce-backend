package com.techommerce.backend.response;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.state.BrandState;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BrandResponse {

	private Long brandId;
	private String brandCode;
	private String brandName;
	private String brandDescription;
	private BrandState brandState;

	public BrandResponse(Brand brand) {
		super();
		this.brandId = brand.getBrandId();
		this.brandCode = brand.getBrandCode();
		this.brandName = brand.getBrandName();
		this.brandDescription = brand.getBrandDescription();
		this.brandState = brand.getBrandState();
	}

}
