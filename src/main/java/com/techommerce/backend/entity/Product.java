package com.techommerce.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techommerce.backend.request.AddProductRequest;
import com.techommerce.backend.request.UpdateProductRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products", schema = "ecommerce")
@Data
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@Column(unique = true)
	@NotNull
	@NotBlank
	@Size(min = 1, max = 10)
	private String productCode;

	@Column(unique = true)
	@NotNull
	@NotBlank
	@Size(min = 1, max = 15)
	private String productName;

	@Size(max = 50)
	private String productDescription;

	@Column(nullable = false)
	@NotNull
	@Min(0)
	private Float productPrice;

	@Column(nullable = false)
	@NotNull
	@Min(0)
	private Integer productQuantity;

	@Column(nullable = false)
	@Min(0)
	private Float productRate;

	@Column(nullable = false)
	private Integer productQuantityOfVotes;

	@NotNull
	private Boolean productState;

	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand productBrand;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category productCategory;

	@ManyToOne
	@JoinColumn(name = "subcategory_id", nullable = false)
	private Subcategory productSubcategory;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<Image> productImages = new ArrayList<>();

	public Product(@Valid AddProductRequest productRequest) {
		// TODO Auto-generated constructor stub
		this.productCode = productRequest.getProductCode();
		this.productName = productRequest.getProductName();
		this.productDescription = productRequest.getProductDescription();
		this.productQuantityOfVotes = new Integer(0);
		this.productRate = new Float(0);
		this.productPrice = productRequest.getProductPrice();
		this.productQuantity = productRequest.getProductQuantity();
		this.productBrand = productRequest.getProductBrand();
		this.productCategory = productRequest.getProductSubcategory().getCategory();
		this.productSubcategory = productRequest.getProductSubcategory();
		this.productState = true;
	}

	public Product(UpdateProductRequest productRequest) {
		// TODO Auto-generated constructor stub
		this.productId = productRequest.getProductId();
		this.productCode = productRequest.getProductCode();
		this.productName = productRequest.getProductName();
		this.productDescription = productRequest.getProductDescription();
		this.productQuantityOfVotes = productRequest.getProductQuantityOfVotes();
		this.productRate = productRequest.getProductRate();
		this.productPrice = productRequest.getProductPrice();
		this.productQuantity = productRequest.getProductQuantity();
		this.productBrand = productRequest.getProductBrand();
		this.productCategory = productRequest.getProductCategory();
		this.productSubcategory = productRequest.getProductSubcategory();
		this.productSubcategory.setCategory(productRequest.getProductCategory());
		this.productState = productRequest.getProductState();
	}
}
