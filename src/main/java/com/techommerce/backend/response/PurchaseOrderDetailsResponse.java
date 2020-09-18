package com.techommerce.backend.response;

import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.PurchaseOrder;
import com.techommerce.backend.entity.PurchaseOrderDetails;

import lombok.Data;

@Data
public class PurchaseOrderDetailsResponse {

	private Long purchaseOrderDetailsId;
	private Float unitPrice;
	private Float totalPrice;
	private Integer quantity;
	private ProductResponse product;
	
	public PurchaseOrderDetailsResponse(PurchaseOrderDetails detail) {
		this.purchaseOrderDetailsId = detail.getPurchaseOrderDetailsId();
		this.unitPrice = (float) ((float) Math.round(detail.getUnitPrice() * 100.0)/100.0);
		this.totalPrice = (float) ((float) Math.round(detail.getTotalPrice() * 100.0)/100.0);
		this.product = new ProductResponse(detail.getProduct());
		this.quantity = detail.getQuantity();
	}

	public PurchaseOrderDetailsResponse(PurchaseOrderDetails detail, ProductResponse product) {
		this.purchaseOrderDetailsId = detail.getPurchaseOrderDetailsId();
		this.unitPrice = (float) ((float) Math.round(detail.getUnitPrice() * 100.0)/100.0);
		this.totalPrice = (float) ((float) Math.round(detail.getTotalPrice() * 100.0)/100.0);
		this.product = product;
		this.quantity = detail.getQuantity();
	}

}
