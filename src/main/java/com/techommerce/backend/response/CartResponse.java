package com.techommerce.backend.response;

import java.util.List;

import com.techommerce.backend.entity.Cart;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartResponse {

	private String userId;
	private Integer quantityOfProduct;
	private Float totalAmount;
	private List<CartDetailResponse> details;
	
	public CartResponse(Cart cart, List<CartDetailResponse> details) {
		this.userId = cart.getUserId();
		this.quantityOfProduct = cart.getQuantityOfProduct();
		this.totalAmount = (float) ((float) Math.round(cart.getTotalAmount() * 100.0)/100.0);
		this.details = details;
	}
}
