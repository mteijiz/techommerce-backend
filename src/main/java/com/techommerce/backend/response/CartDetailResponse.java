package com.techommerce.backend.response;

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
		this.unitPrice = (float) ((float) Math.round(cartDetails.getUnitPrice() * 100.0)/100.0);
		this.quantity = cartDetails.getQuantity();
		this.totalPrice = (float) ((float) Math.round(cartDetails.getTotalPrice() * 100.0)/100.0);
		this.product = productResponse;
	}
	
	public CartDetailResponse(CartDetails cartDetails, ProductResponse productResponse, boolean active) {
		this.cartDetailId = cartDetails.getCartDetailId();
		this.unitPrice = (float) ((float) Math.round(cartDetails.getUnitPrice() * 100.0)/100.0);
		this.quantity = cartDetails.getQuantity();
		this.totalPrice = (float) ((float) Math.round(cartDetails.getTotalPrice() * 100.0)/100.0);
		this.product = productResponse;
		this.state = active;
	}

}
