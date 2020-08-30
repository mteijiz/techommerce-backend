package com.techommerce.backend.response;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDetailResponse {

	private Long cartDetailId;
	private Float unitPrice;
	private Float totalPrice;
	private Integer quantity;
	private ProductResponse product;
	private Boolean state;
	
	public CartDetailResponse(CartDetails cartDetails, ProductResponse productResponse) {
		this.cartDetailId = cartDetails.getCartDetailId();
		this.unitPrice = cartDetails.getUnitPrice();
		this.quantity = cartDetails.getQuantity();
		this.totalPrice = cartDetails.getTotalPrice();
		this.product = productResponse;
		this.state = cartDetails.getState();
	}
	
	public CartDetailResponse(CartDetails cartDetails) {
		this.cartDetailId = cartDetails.getCartDetailId();
		this.unitPrice = cartDetails.getUnitPrice();
		this.quantity = cartDetails.getQuantity();
		this.totalPrice = cartDetails.getTotalPrice();
		this.product = new ProductResponse(cartDetails.getProduct());
		this.state = cartDetails.getState();
	}

}
