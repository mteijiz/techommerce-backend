package com.techommerce.backend.response;

import java.util.ArrayList;
import java.util.List;


import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
	
	private Long productId;
	private String productCode;
	private String productName;
	private String productDescription;
	private double productPrice;
	private Integer productQuantity;
	private Brand productBrand;
	private CategoryWithoutSubcategoriesResponse productCategory;
	private SubcategoryWithoutCategoryResponse productSubcategory;
	private Boolean productState;
	private List<ImageResponse> productImage = new ArrayList<>();

	public ProductResponse(Product product, List<ImageResponse> imagesResponse) {
		this.productId = product.getProductId();
		this.productCode = product.getProductCode();
		this.productName = product.getProductName();
		this.productDescription = product.getProductDescription();
		this.productPrice = (float) Math.round(product.getProductPrice() * 100.0)/100.0;
		this.productQuantity = product.getProductQuantity();
		this.productBrand = product.getProductBrand();
		this.productCategory = new CategoryWithoutSubcategoriesResponse(product.getProductCategory());
		this.productSubcategory = new SubcategoryWithoutCategoryResponse(product.getProductSubcategory());
		this.productState = product.getProductState();
		this.productImage = imagesResponse;
	}

	public ProductResponse(Product product) {
		this.productId = product.getProductId();
		this.productCode = product.getProductCode();
		this.productName = product.getProductName();
		this.productDescription = product.getProductDescription();
		this.productPrice = product.getProductPrice();
		this.productQuantity = product.getProductQuantity();
		this.productBrand = product.getProductBrand();
		this.productCategory = new CategoryWithoutSubcategoriesResponse(product.getProductCategory());
		this.productSubcategory = new SubcategoryWithoutCategoryResponse(product.getProductSubcategory());
		this.productState = product.getProductState();
	}	
	
}
