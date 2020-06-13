package com.techommerce.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_products", schema = "ecommerce")
@Data
@NoArgsConstructor
public class CartProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartProductId;
	
	@Column(nullable = false)
	@NotNull
	@Min(0)
	private Float unitPrice;
	
	@Column(nullable = false)
	@NotNull
	@Min(0)
	private Float totalPrice;
	
	@Column(nullable = false)
	@NotNull
	@Min(0)
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;
	
	@NotNull
	private Boolean state;
	
}
