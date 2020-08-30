package com.techommerce.backend.request;

import lombok.Data;

@Data
public class AddBrandRequest {

	private String brandCode;
	private String brandName;
	private String brandDescription;
	private Boolean brandState;

}
