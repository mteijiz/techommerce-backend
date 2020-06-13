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
	private Float productRate;
	private Integer productQuantityOfVotes;
	private Brand productBrand;
	private Category productCategory;
	private Subcategory productSubcategory;
	private Boolean productState;
	private List<ImageResponse> productImage = new ArrayList<>();

	public ProductResponse(Product product) {
		// TODO Auto-generated constructor stub
		this.productId = product.getProductId();
		this.productCode = product.getProductCode();
		this.productName = product.getProductName();
		this.productDescription = product.getProductDescription();
		this.productPrice = product.getProductPrice();
		this.productQuantity = product.getProductQuantity();
		this.productRate = product.getProductRate();
		this.productQuantityOfVotes = product.getProductQuantityOfVotes();
		this.productBrand = product.getProductBrand();
		this.productCategory = product.getProductCategory();
		Subcategory auxSubcategory = new Subcategory();
		auxSubcategory.setSubcategoryId(product.getProductSubcategory().getSubcategoryId());
		auxSubcategory.setSubcategoryCode(product.getProductCode());
		auxSubcategory.setSubcategoryDescription(product.getProductDescription());
		auxSubcategory.setSubcategoryName(product.getProductName());
		auxSubcategory.setSubcategoryState(product.getProductState());
		this.productSubcategory = auxSubcategory;
		//this.productImage = buildImageResponseList(product);
		this.productState = product.getProductState();
	}

	private List<ImageResponse> buildImageResponseList(Product productAdded) {
		// TODO Auto-generated method stub
		List<ImageResponse> images = new ArrayList<>();
		for(Image image : productAdded.getProductImages()) {
			ImageResponse auxImage = new ImageResponse(image);
			images.add(auxImage);
		}
		return images;
	}
	
	
}
