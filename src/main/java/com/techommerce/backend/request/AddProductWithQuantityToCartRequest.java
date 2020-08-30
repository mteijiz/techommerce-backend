package com.techommerce.backend.request;

import javax.validation.constraints.Min;

import com.techommerce.backend.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddProductWithQuantityToCartRequest {

	private Product product;
	
	@Min(0)
	private Integer quantity;
	
}
