package com.techommerce.backend.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.Product;

import lombok.Data;

@Data
public class DeleteCartProductRequest {

	@NotNull
	private Long cartProductId;
	
	@NotNull
	@Min(0)
	private Float unitPrice;
	
	@NotNull
	@Min(0)
	private Float totalPrice;
	
	@NotNull
	@Min(0)
	private Integer quantity;
	
	@NotNull
	private Product productResponse;
	
	@NotNull
	private Cart cart;
	
	@NotNull
	private Boolean state;
	
}
