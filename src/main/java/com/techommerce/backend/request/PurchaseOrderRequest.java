package com.techommerce.backend.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrderRequest {
	private String userId;
	private Long purchaseOrder;
	private Float total;
	
}
