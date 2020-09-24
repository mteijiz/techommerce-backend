package com.techommerce.backend.response;

import com.techommerce.backend.entity.Cart;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartQuantityResponse {

	private Integer quantity;
	
	public CartQuantityResponse(Cart cart) {
		this.quantity = cart.getQuantityOfProduct();
	}
	
}
