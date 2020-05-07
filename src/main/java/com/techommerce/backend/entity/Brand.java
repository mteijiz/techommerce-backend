package com.techommerce.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "brands", schema = "ecommerce")
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brandId;
	
	@Column(unique = true)
	@NotNull
	@Size(min = 1, max=10)
	@NotBlank
	private String brandCode;
	
	@Column(unique = true)
	@NotNull
	@Size(min = 1, max=15)
	@NotBlank
	private String brandName;
	
	@Column(unique = true)
	@Size(max=30)
	private String brandDescription;
	
	@NotNull
	@AssertTrue
	private Boolean status;

	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Brand(@NotNull @Size(min = 1, max = 10) @NotBlank String brandCode,
			@NotNull @Size(min = 1, max = 15) @NotBlank String brandName, @Size(max = 30) String brandDescription,
			@NotNull @AssertTrue Boolean status) {
		super();
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.brandDescription = brandDescription;
		this.status = status;
	}

	public Brand(Long brandId, @NotNull @Size(min = 1, max = 10) @NotBlank String brandCode,
			@NotNull @Size(min = 1, max = 15) @NotBlank String brandName, @Size(max = 30) String brandDescription,
			@NotNull @AssertTrue Boolean status) {
		super();
		this.brandId = brandId;
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.brandDescription = brandDescription;
		this.status = status;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getBrandId() {
		return brandId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brandCode == null) ? 0 : brandCode.hashCode());
		result = prime * result + ((brandDescription == null) ? 0 : brandDescription.hashCode());
		result = prime * result + ((brandId == null) ? 0 : brandId.hashCode());
		result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		if (brandCode == null) {
			if (other.brandCode != null)
				return false;
		} else if (!brandCode.equals(other.brandCode))
			return false;
		if (brandDescription == null) {
			if (other.brandDescription != null)
				return false;
		} else if (!brandDescription.equals(other.brandDescription))
			return false;
		if (brandId == null) {
			if (other.brandId != null)
				return false;
		} else if (!brandId.equals(other.brandId))
			return false;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	

}
