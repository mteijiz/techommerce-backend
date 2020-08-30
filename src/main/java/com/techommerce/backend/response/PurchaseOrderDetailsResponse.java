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
	
	public PurchaseOrderDetailsResponse(PurchaseOrderDetails orderDetail) {
		this.purchaseOrderDetailsId = orderDetail.getPurchaseOrderDetailsId();
		this.unitPrice = orderDetail.getUnitPrice();
		this.totalPrice = orderDetail.getTotalPrice();
		this.product = new ProductResponse(orderDetail.getProduct());
		this.quantity = orderDetail.getQuantity();
	}

	public PurchaseOrderDetailsResponse(PurchaseOrderDetails detail, ProductResponse product) {
		this.purchaseOrderDetailsId = detail.getPurchaseOrderDetailsId();
		this.unitPrice = detail.getUnitPrice();
		this.totalPrice = detail.getTotalPrice();
		this.product = product;
		this.quantity = detail.getQuantity();
	}

}
