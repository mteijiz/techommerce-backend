package com.techommerce.backend.response;

import com.techommerce.backend.entity.Brand;

public class BrandResponse {

	private Long brandId;
	private String brandCode;
	private String brandName;
	private String brandDescription;
	private Boolean brandStatus;

	public BrandResponse(Brand brand) {
		super();
		this.brandId = brand.getBrandId();
		this.brandCode = brand.getBrandCode();
		this.brandName = brand.getBrandName();
		this.brandDescription = brand.getBrandDescription();
		this.brandStatus = brand.getStatus();
	}

	public BrandResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
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

	public Boolean getBrandStatus() {
		return brandStatus;
	}

	public void setBrandStatus(Boolean brandStatus) {
		this.brandStatus = brandStatus;
	}

}
