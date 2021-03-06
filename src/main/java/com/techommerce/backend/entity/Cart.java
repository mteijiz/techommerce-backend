package com.techommerce.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carts", schema = "ecommerce")
@Data
@NoArgsConstructor
public class Cart {
	
	@Id
	private String userId;
	
	@NotNull
	@Min(0)
	private Float totalAmount;
	
	@NotNull
	@Min(0)
	private Integer quantityOfProduct;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.PERSIST)
	private List<CartDetails> cartDetails = new ArrayList<>();
	
	public Cart(String id) {
		this.userId = id;
		this.totalAmount = new Float(0.0);
		this.quantityOfProduct = 0;
	}
	
}
