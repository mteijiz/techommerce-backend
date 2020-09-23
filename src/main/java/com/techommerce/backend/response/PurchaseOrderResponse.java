package com.techommerce.backend.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.techommerce.backend.entity.PurchaseOrder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrderResponse {
	private Long purchaseOrderId;
	private String userId;
	private Float total;
	private Integer quantity;
	private String purchaseDate;
	private Boolean status;
	private List<PurchaseOrderDetailsResponse> orderDetails = new ArrayList<>();
	
	public PurchaseOrderResponse(PurchaseOrder order) {
		this.purchaseOrderId = order.getPurchaseOrderId();
		this.userId = order.getUserId();
		this.total = (float) ((float) Math.round(order.getTotal() * 100.0)/100.0);
		this.quantity = order.getQuantity();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		this.purchaseDate = format.format(order.getPurchaseDate());
	}
	
	public PurchaseOrderResponse(PurchaseOrder order, List<PurchaseOrderDetailsResponse> orderDetailsResponse) {
		this.purchaseOrderId = order.getPurchaseOrderId();
		this.userId = order.getUserId();
		this.total = (float) ((float) Math.round(order.getTotal() * 100.0)/100.0);;
		this.quantity = order.getQuantity();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		this.purchaseDate = format.format(order.getPurchaseDate());
		this.orderDetails = orderDetailsResponse;
		this.status = order.getStatus();
	}
}
