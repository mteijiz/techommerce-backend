package com.techommerce.backend.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;
import com.techommerce.backend.request.PurchaseOrderRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_order", schema = "ecommerce")
@Data
@NoArgsConstructor
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long purchaseOrderId;
	
	@NotNull
	@Column(nullable=false)
	private String userId;
	
	@NotNull
	@Min(0)
	private Float total;
	
	@NotNull
	@Min(0)
	private Integer quantity;
	
	@NotNull
	private Date purchaseDate;
	
	@NotNull
	private Boolean status;
	
	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
	private List<PurchaseOrderDetails> details = new ArrayList<>();
	
	public PurchaseOrder(Cart cart) {
		this.userId = cart.getUserId();
		this.total = cart.getTotalAmount();
		this.quantity = cart.getQuantityOfProduct();  
		this.purchaseDate = new Date();
		this.status = false;
	}

	public PurchaseOrder(PurchaseOrderRequest orderRequest) {
		this.purchaseOrderId = orderRequest.getPurchaseOrder();
		this.userId = orderRequest.getUserId();
		this.total = orderRequest.getTotal();
	}
	
}
