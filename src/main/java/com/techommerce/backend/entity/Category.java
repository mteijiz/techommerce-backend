package com.techommerce.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	@NotNull(message="La categoría tiene que tener un código")
	@Size(min = 1, max = 10, message="El codigo de la categoría tiene que estar entre 1 y 15 caracteres")
	@NotBlank(message="El código de la categoría no puede estar en blanco")
	private String categoryCode;

	@Column(unique = true)
	@NotNull(message="La categoría tiene que tener un nombre")
	@Size(min = 1, max = 15, message="El nombre de la categoría tiene que estar entre 1 y 15 caracteres")
	@NotBlank(message="El nombre de la categoría no puede estar en blanco")
	private String categoryName;

	@Size(max = 500, message="La descripción de la categoría tiene que ser menor de 500 caracteres")
	private String categoryDescription;

	@NotNull(message="La categoría tiene que tener un estado definido")
	private Boolean categoryState;
	
	@OneToMany(mappedBy = "category", fetch=FetchType.LAZY)
	private List<Subcategory> subcategories = new ArrayList<>();


	public Category(AddCategoryRequest request) {
		this.categoryCode = request.getCategoryCode();
		this.categoryName = request.getCategoryName();
		this.categoryDescription = request.getCategoryDescription();
		this.categoryState = request.getCategoryState();
	}

	public Category(UpdateCategoryRequest request) {
		this.categoryId = request.getCategoryId();
		this.categoryCode = request.getCategoryCode();
		this.categoryName = request.getCategoryName();
		this.categoryDescription = request.getCategoryDescription();
		this.categoryState = request.getCategoryState();
	}

}
