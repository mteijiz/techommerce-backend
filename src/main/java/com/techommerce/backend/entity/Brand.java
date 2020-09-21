package com.techommerce.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techommerce.backend.request.AddBrandRequest;
import com.techommerce.backend.request.UpdateBrandRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brands", schema = "ecommerce")
@Data
@NoArgsConstructor
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brandId;

	@Column(unique = true)
	@NotNull(message="La marca tiene que tener un código")
	@Size(min = 1, max = 10, message="El código de la marca tiene que estar entre 1 y 10 caracteres")
	@NotBlank(message="El código de la marca no puede estar en blanco")
	private String brandCode;

	@Column(unique = true)
	@NotNull(message="La marca tiene que tener un nombre")
	@Size(min = 1, max = 15, message="El nombre de la marca tiene que estar entre 1 y 15 caracteres")
	@NotBlank(message="El nombre de la marca no puede estar en blanco")
	private String brandName;

	@Size(max = 500, message="La descripción de la marca tiene que ser menor de 500 caracteres")
	private String brandDescription;

	@NotNull(message="La marca tiene que tener un estado definido")
	private Boolean brandState;

	public Brand(AddBrandRequest request) {
		this.brandCode = request.getBrandCode();
		this.brandName = request.getBrandName();
		this.brandDescription = request.getBrandDescription();
		this.brandState = request.getBrandState();
	}

	public Brand(UpdateBrandRequest request) {
		this.brandId = request.getBrandId();
		this.brandCode = request.getBrandCode();
		this.brandName = request.getBrandName();
		this.brandDescription = request.getBrandDescription();
		this.brandState = request.getBrandState();
	}

	public Brand(Long brandId,
			@NotNull(message = "La marca tiene que tener un código") @Size(min = 1, max = 10, message = "El código de la marca tiene que estar entre 1 y 10 caracteres") @NotBlank(message = "El código de la marca no puede estar en blanco") String brandCode,
			@NotNull(message = "La marca tiene que tener un nombre") @Size(min = 1, max = 15, message = "El nombre de la marca tiene que estar entre 1 y 15 caracteres") @NotBlank(message = "El nombre de la marca no puede estar en blanco") String brandName,
			@Size(max = 500, message = "La descripción de la marca tiene que ser menor de 500 caracteres") String brandDescription,
			@NotNull(message = "La marca tiene que tener un estado definido") Boolean brandState) {
		super();
		this.brandId = brandId;
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.brandDescription = brandDescription;
		this.brandState = brandState;
	}
	
	

}
