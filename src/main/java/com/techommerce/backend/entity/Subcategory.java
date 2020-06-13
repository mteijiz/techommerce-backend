package com.techommerce.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
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
	@NotNull
	@Size(min = 1, max = 10)
	@NotBlank
	private String subcategoryCode;

	@Column(unique = true)
	@NotNull
	@Size(min = 1, max = 15)
	@NotBlank
	private String subcategoryName;

	@Size(max = 50)
	private String subcategoryDescription;

	@NotNull
	private Boolean subcategoryState;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public Subcategory(@Valid AddSubcategoryRequest subcategoryRequest) {
		// TODO Auto-generated constructor stub
		this.subcategoryCode = subcategoryRequest.getSubcategoryCode();
		this.subcategoryName = subcategoryRequest.getSubcategoryName();
		this.subcategoryDescription = subcategoryRequest.getSubcategoryDescription();
		this.subcategoryState = true;
		this.category = subcategoryRequest.getCategory();
	}

	public Subcategory(@Valid UpdateSubcategoryRequest subcategoryRequest) {
		// TODO Auto-generated constructor stub
		this.subcategoryCode = subcategoryRequest.getSubcategoryCode();
		this.subcategoryId = subcategoryRequest.getSubcategoryId();
		this.subcategoryName = subcategoryRequest.getSubcategoryName();
		this.subcategoryDescription = subcategoryRequest.getSubcategoryDescription();
		this.subcategoryState = subcategoryRequest.getSubcategoryState();
		this.category = subcategoryRequest.getCategory();
	}

}
