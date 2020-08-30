package com.techommerce.backend.request;

import com.techommerce.backend.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewMainImageRequest {

	private Product product;
	private Long imageId;
	
}
