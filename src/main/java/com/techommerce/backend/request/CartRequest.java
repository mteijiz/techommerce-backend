package com.techommerce.backend.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartRequest {
	private String userId;
	private Integer quantityOfProduct;
	private Float totalAmount;
	private Boolean state;
}
