package com.techommerce.backend.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class UpdateProductRequest {

	@NotNull
	private Long productId;

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

	@Min(0)
	private Float productRate;

	@Min(0)
	private Integer productQuantityOfVotes;

	@NotNull
	private Brand productBrand;

	@NotNull
	private Category productCategory;

	@NotNull
	private Subcategory productSubcategory;
	
	@NotNull
	private Boolean productState;

}
