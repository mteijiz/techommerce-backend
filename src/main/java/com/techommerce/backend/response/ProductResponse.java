package com.techommerce.backend.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.service.ImageService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {

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
	private List<ImageResponse> productImage = new ArrayList<>();

	public ProductResponse(Product product, List<ImageResponse> imagesResponse) {
		this.productId = product.getProductId();
		this.productCode = product.getProductCode();
		this.productName = product.getProductName();
		this.productDescription = product.getProductDescription();
		this.productPrice = product.getProductPrice();
		this.productQuantity = product.getProductQuantity();
		this.productTotalPoints = product.getProductTotalPoints();
		this.productRate = product.getProductRate();
		this.productQuantityOfVotes = product.getProductQuantityOfVotes();
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
		this.productTotalPoints = product.getProductTotalPoints();
		this.productRate = product.getProductRate();
		this.productQuantityOfVotes = product.getProductQuantityOfVotes();
		this.productBrand = product.getProductBrand();
		this.productCategory = new CategoryWithoutSubcategoriesResponse(product.getProductCategory());
		this.productSubcategory = new SubcategoryWithoutCategoryResponse(product.getProductSubcategory());
		this.productState = product.getProductState();
	}	
	
}
