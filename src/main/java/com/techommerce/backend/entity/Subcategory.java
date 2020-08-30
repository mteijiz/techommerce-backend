package com.techommerce.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techommerce.backend.request.AddSubcategoryRequest;
import com.techommerce.backend.request.UpdateSubcategoryRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subcategories", schema = "ecommerce")
@Data
@NoArgsConstructor
public class Subcategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subcategoryId;

	@Column(unique = true)
	@NotNull(message="El producto tiene que tener un código")
	@Size(min = 1, max = 10, message="El código del producto tiene que estar entre 1 y 10 caracteres")
	@NotBlank(message="El código del producto no puede estar en blanco")
	private String subcategoryCode;

	@Column(unique = true)
	@NotNull(message="El producto tiene que tener un nombre")
	@Size(min = 1, max = 15, message="El nombre del producto tiene que estar entre 1 y 15 caracteres")
	@NotBlank(message="El nombre del producto no puede estar en blanco")
	private String subcategoryName;

	@Size(max = 500, message="La descripción del producto tiene que tener menos de 500 caracteres")
	private String subcategoryDescription;

	@NotNull(message="El producto tiene que tener un estado definido")
	private Boolean subcategoryState;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public Subcategory(AddSubcategoryRequest request) {
		this.subcategoryCode = request.getSubcategoryCode();
		this.subcategoryName = request.getSubcategoryName();
		this.subcategoryDescription = request.getSubcategoryDescription();
		this.subcategoryState = request.getSubcategoryState();
		this.category = request.getCategory();
	}

	public Subcategory(UpdateSubcategoryRequest request) {
		this.subcategoryCode = request.getSubcategoryCode();
		this.subcategoryId = request.getSubcategoryId();
		this.subcategoryName = request.getSubcategoryName();
		this.subcategoryDescription = request.getSubcategoryDescription();
		this.subcategoryState = request.getSubcategoryState();
		this.category = request.getCategory();
	}

}
