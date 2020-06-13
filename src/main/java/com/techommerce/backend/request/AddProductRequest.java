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

	@NotNull
	@NotBlank
	@Size(min = 1, max = 10)
	private String productCode;
	
	@NotNull
	@NotBlank
	@Size(min = 1, max = 15)
	private String productName;

	@Size(max = 50)
	private String productDescription;

	@NotNull
	@Min(0)
	private Float productPrice;

	@NotNull
	@Min(0)
	private Integer productQuantity;

	@NotNull
	private Brand productBrand;
	
	@NotNull
	private Subcategory productSubcategory;
	
}
