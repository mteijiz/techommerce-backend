package com.techommerce.backend.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddProductRequest {

	private String productCode;
	private String productName;
	private String productDescription;
	private Float productPrice;
	private Integer productQuantity;
	private Brand productBrand;
	private Category category;
	private Subcategory productSubcategory;
	private Boolean productState;
	
}
