package com.techommerce.backend.request;

import com.techommerce.backend.entity.CartDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateQuantityOfProductInACartRequest {

	private CartDetails cartDetail;
	private Integer newQuantity;
	
}
