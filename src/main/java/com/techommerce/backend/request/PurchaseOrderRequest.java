package com.techommerce.backend.request;

import java.util.ArrayList;
import java.util.List;

import com.techommerce.backend.entity.CartDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrderRequest {
	private String userId;
	private Long purchaseOrder;
	private Float total;
	
}
