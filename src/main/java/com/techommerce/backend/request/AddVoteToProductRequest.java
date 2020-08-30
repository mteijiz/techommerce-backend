package com.techommerce.backend.request;

import com.techommerce.backend.entity.Product;

import lombok.Data;

@Data
public class AddVoteToProductRequest {

	private Product product;
	private Float vote;
	
}
