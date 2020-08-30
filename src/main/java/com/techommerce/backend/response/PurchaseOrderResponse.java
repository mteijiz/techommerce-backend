package com.techommerce.backend.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

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
	private String ccNumberFourLastDigits;
	private List<PurchaseOrderDetailsResponse> orderDetails = new ArrayList<>();
	
	public PurchaseOrderResponse(PurchaseOrder order) {
		this.purchaseOrderId = order.getPurchaseOrderId();
		this.userId = order.getUserId();
		this.total = order.getTotal();
		this.quantity = order.getQuantity();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		this.purchaseDate = format.format(order.getPurchaseDate());
		this.ccNumberFourLastDigits = order.getCcNumber().substring(order.getCcNumber().length()-4);
	}
	
	public PurchaseOrderResponse(PurchaseOrder order, List<PurchaseOrderDetailsResponse> orderDetailsResponse) {
		this.purchaseOrderId = order.getPurchaseOrderId();
		this.userId = order.getUserId();
		this.total = order.getTotal();
		this.quantity = order.getQuantity();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		this.purchaseDate = format.format(order.getPurchaseDate());
		this.ccNumberFourLastDigits = order.getCcNumber().substring(order.getCcNumber().length()-4);
		this.orderDetails = orderDetailsResponse;
	}
}
