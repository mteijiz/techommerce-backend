package com.techommerce.backend.response;

import java.util.List;
import java.util.stream.Collectors;

import com.techommerce.backend.entity.Cart;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartResponse {

	private String userId;
	private Integer quantityOfProduct;
	private Float totalAmount;
	private Boolean state;
	private List<CartDetailResponse> details;
	
	public CartResponse(Cart cart, List<CartDetailResponse> details) {
		this.userId = cart.getUserId();
		this.quantityOfProduct = cart.getQuantityOfProduct();
		this.totalAmount = cart.getTotalAmount();
		this.state = cart.getState();
		this.details = details;
	}
}
