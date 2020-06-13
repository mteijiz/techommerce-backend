package com.techommerce.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techommerce.backend.request.AddCategoryRequest;
import com.techommerce.backend.request.SearchByCategoryRequest;
import com.techommerce.backend.request.UpdateCategoryRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories", schema = "ecommerce")
@Data
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@Column(unique = true)
	@NotNull
	@Size(min = 1, max = 10)
	@NotBlank
	private String categoryCode;

	@Column(unique = true)
	@NotNull
	@Size(min = 1, max = 15)
	@NotBlank
	private String categoryName;

	@Size(max = 50)
	private String categoryDescription;

	@NotNull
	private Boolean categoryState;


	public Category(@Valid AddCategoryRequest categoryRequest) {
		this.categoryCode = categoryRequest.getCategoryCode();
		this.categoryName = categoryRequest.getCategoryName();
		this.categoryDescription = categoryRequest.getCategoryDescription();
		this.categoryState = true;
	}

	public Category(@Valid UpdateCategoryRequest categoryRequest) {
		this.categoryId = categoryRequest.getCategoryId();
		this.categoryCode = categoryRequest.getCategoryCode();
		this.categoryName = categoryRequest.getCategoryName();
		this.categoryDescription = categoryRequest.getCategoryDescription();
		this.categoryState = categoryRequest.getCategoryState();
	}

	public Category(@Valid SearchByCategoryRequest categoryRequest) {
		// TODO Auto-generated constructor stub
		this.categoryId = categoryRequest.getCategoryId();
		this.categoryCode = categoryRequest.getCategoryCode();
		this.categoryName = categoryRequest.getCategoryName();
		this.categoryDescription = categoryRequest.getCategoryDescription();
		this.categoryState = categoryRequest.getCategoryState();
	}


}
