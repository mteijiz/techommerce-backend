package com.techommerce.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;

import com.techommerce.backend.entity.state.BrandState;
import com.techommerce.backend.request.AddBrandRequest;
import com.techommerce.backend.request.UpdateBrandRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brands", schema = "ecommerce")
@Data
@NoArgsConstructor
@SQLDelete(sql="UPDATE ecommerce.brands SET brand_state = 'INACTIVE' WHERE brand_id = ?")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brandId;

	@Column(unique = true)
	@NotNull
	@Size(min = 1, max = 10)
	@NotBlank
	private String brandCode;

	@Column(unique = true)
	@NotNull
	@Size(min = 1, max = 15)
	@NotBlank
	private String brandName;

	@Size(max = 50)
	private String brandDescription;

	@NotNull
	@Enumerated(EnumType.STRING)
	private BrandState brandState;

	public Brand(@Valid AddBrandRequest brandRequest) {
		this.brandCode = brandRequest.getBrandCode();
		this.brandName = brandRequest.getBrandName();
		this.brandDescription = brandRequest.getBrandDescription();
		this.brandState = BrandState.valueOf("ACTIVE");
	}

	public Brand(@Valid UpdateBrandRequest brandRequest) {
		this.brandId = brandRequest.getBrandId();
		this.brandCode = brandRequest.getBrandCode();
		this.brandName = brandRequest.getBrandName();
		this.brandDescription = brandRequest.getBrandDescription();
		this.brandState = brandRequest.getBrandState();
	}

}
