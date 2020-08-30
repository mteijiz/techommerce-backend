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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techommerce.backend.request.AddProductWithQuantityToCartRequest;
import com.techommerce.backend.request.DeleteCartProductRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_details", schema = "ecommerce")
@Data
@NoArgsConstructor
public class CartDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartDetailId;
	
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
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnoreProperties("cartDetails")
	private Cart cart;
	
	@NotNull
	private Boolean state;
	
	public CartDetails(Product product, Cart cart) {
		// TODO Auto-generated constructor stub
		this.unitPrice = product.getProductPrice();
		this.quantity = new Integer(1);
		this.totalPrice = new Float(product.getProductPrice() * 1);
		this.product = product;
		this.cart = cart;
		this.state = true;
	}

	public CartDetails(@Valid AddProductWithQuantityToCartRequest cartDetails, Cart cart) {
		this.unitPrice = cartDetails.getProduct().getProductPrice();
		this.quantity = new Integer(cartDetails.getQuantity());
		this.totalPrice = new Float(cartDetails.getProduct().getProductPrice() * cartDetails.getQuantity());
		this.product = cartDetails.getProduct();
		this.cart = cart;
		this.state = true;
	}

	public CartDetails(@Valid AddProductWithQuantityToCartRequest cartDetails) {
		this.unitPrice = cartDetails.getProduct().getProductPrice();
		this.quantity = new Integer(cartDetails.getQuantity());
		this.totalPrice = new Float(cartDetails.getProduct().getProductPrice() * cartDetails.getQuantity());
		this.product = cartDetails.getProduct();
		this.state = true;
	}

	
}