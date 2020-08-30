package com.techommerce.backend.response;

import java.util.ArrayList;
import java.util.List;

import com.techommerce.backend.entity.Brand;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductWithImagesResponse {

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
	private CategoryWithoutSubcategoriesResponse productCategory;
	private SubcategoryWithoutCategoryResponse productSubcategory;
	private Boolean productState;
	private ImageResponse productMainImage;
	private List<ImageResponse> productImage = new ArrayList<>();
	
}
