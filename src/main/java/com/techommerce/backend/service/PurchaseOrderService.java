package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.response.PurchaseOrderResponse;
import com.techommerce.backend.response.PurchaseOrderDetailsResponse;
import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.PurchaseOrder;
import com.techommerce.backend.entity.PurchaseOrderDetails;

public interface PurchaseOrderService {

	List<PurchaseOrderDetails> purchaseProducts(List<PurchaseOrderDetails> orderDetails);

	List<PurchaseOrderDetailsResponse> buildPurchaseOrderDetailsResponseList(List<PurchaseOrderDetails> orderDetails);

	PurchaseOrder createOrder(Cart cart);

	List<PurchaseOrderDetails> setPurchaseOrderDetails(List<CartDetails> cartDetailsList, PurchaseOrder purchaseOrder);

	List<PurchaseOrderDetails> getOrderDetaislOfAUser(PurchaseOrder order);

	List<PurchaseOrder> getAllOrders(String name);

	List<PurchaseOrderResponse> buildPurchaseOrderResponseList(List<PurchaseOrder> orders);

	List<PurchaseOrder> getAllOrders();

	PurchaseOrder getOrderById(Long orderId);

	PurchaseOrder changeStatusToReady(PurchaseOrder order);

	PurchaseOrder changeStatusToDetail(PurchaseOrder order, Long detailId);

	

}
