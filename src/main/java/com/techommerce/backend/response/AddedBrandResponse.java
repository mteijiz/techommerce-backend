package com.techommerce.backend.response;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.state.BrandState;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddedBrandResponse {

	private Long brandId;
	private String brandCode;
	private String brandName;
	private String brandDescription;
	private BrandState brandState;

	public AddedBrandResponse(Brand brandAdded) {
		this.brandId = brandAdded.getBrandId();
		this.brandCode = brandAdded.getBrandCode();
		this.brandName = brandAdded.getBrandName();
		this.brandDescription = brandAdded.getBrandDescription();
		this.brandState = brandAdded.getBrandState();
	}

}
