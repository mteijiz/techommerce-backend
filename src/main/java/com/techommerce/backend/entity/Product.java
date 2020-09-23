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
	@NotNull(message="El producto tiene que tener un código")
	@NotBlank(message="El código del producto no puede estar en blanco")
	@Size(min = 1, max = 10, message="El código del producto tiene que estar entre 1 y 10 caracteres")
	private String productCode;

	@Column(unique = true)
	@NotNull(message="El producto tiene que tener un nombre")
	@NotBlank(message="El nombre del producto no puede estar en blanco")
	@Size(min = 1, max = 15, message="El nombre del producto tiene que estar entre 1 y 15 caracteres")
	private String productName;

	@Size(max = 1000, message="La descripción del producto tiene que ser menor de 1000 caracteres")
	private String productDescription;

	@Column(nullable = false)
	@NotNull(message="El producto debe tener un precio")
	@Min(0)
	private Float productPrice;

	@Column(nullable = false)
	@NotNull(message="El producto debe tener una cantidad asignada")
	@Min(0)
	private Integer productQuantity;

	@NotNull(message="El producto debe tener un estado")
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

	public Product(AddProductRequest productRequest) {
		this.productCode = productRequest.getProductCode();
		this.productName = productRequest.getProductName();
		this.productDescription = productRequest.getProductDescription();
		this.productPrice = productRequest.getProductPrice();
		this.productQuantity = productRequest.getProductQuantity();
		this.productBrand = productRequest.getProductBrand();
		this.productCategory = productRequest.getProductSubcategory().getCategory();
		this.productSubcategory = productRequest.getProductSubcategory();
		this.productState = productRequest.getProductState();
	}

	public Product(UpdateProductRequest productRequest) {
		this.productId = productRequest.getProductId();
		this.productCode = productRequest.getProductCode();
		this.productName = productRequest.getProductName();
		this.productDescription = productRequest.getProductDescription();
		this.productPrice = productRequest.getProductPrice();
		this.productQuantity = productRequest.getProductQuantity();
		this.productBrand = productRequest.getProductBrand();
		this.productCategory = productRequest.getProductCategory();
		this.productSubcategory = productRequest.getProductSubcategory();
		this.productSubcategory.setCategory(productRequest.getProductCategory());
		this.productState = productRequest.getProductState();
	}
}
