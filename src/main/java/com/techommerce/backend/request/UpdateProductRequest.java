package com.techommerce.backend.request;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProductRequest {

	private Long productId;
	private String productCode;
	private String productName;
	private String productDescription;
	private Float productPrice;
	private Integer productQuantity;
	private Float productTotalPoints;
	private Float productRate;
	private Integer productQuantityOfVotes;
	private Brand productBrand;
	private Category productCategory;
	private Subcategory productSubcategory;
	private Boolean productState;

}
