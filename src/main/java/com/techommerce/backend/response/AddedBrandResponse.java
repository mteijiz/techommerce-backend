package com.techommerce.backend.response;

import com.techommerce.backend.entity.Brand;

public class AddedBrandResponse {

	private Long brandId;
	private String brandCode;
	private String brandName;
	private String brandDescription;
	private Boolean status;

	public AddedBrandResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddedBrandResponse(Brand brandAdded) {
		// TODO Auto-generated constructor stub
		this.brandId = brandAdded.getBrandId();
		this.brandCode = brandAdded.getBrandCode();
		this.brandName = brandAdded.getBrandName();
		this.brandDescription = brandAdded.getBrandDescription();
		this.status = brandAdded.getStatus();
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
