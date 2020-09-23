package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.PurchaseOrder;
import com.techommerce.backend.entity.PurchaseOrderDetails;
import com.techommerce.backend.exception.EmptyOrderListException;
import com.techommerce.backend.repository.PurchaseOrderDetailsRepository;
import com.techommerce.backend.repository.PurchaseOrderRepository;
import com.techommerce.backend.response.PurchaseOrderResponse;
import com.techommerce.backend.response.PurchaseOrderDetailsResponse;
import com.techommerce.backend.service.ProductService;
import com.techommerce.backend.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private PurchaseOrderDetailsRepository purchaseOrderDetailsRepository;

	@Autowired
	private ProductService productService;

	@Override
	public List<PurchaseOrderDetails> purchaseProducts(List<PurchaseOrderDetails> orderDetails) {
		List<PurchaseOrderDetails> productsAdded = new ArrayList<>();
		for (PurchaseOrderDetails productDetails : orderDetails) {
			PurchaseOrderDetails puchaseSaved = purchaseOrderDetailsRepository.save(productDetails);
			productsAdded.add(puchaseSaved);
		}
		return productsAdded;
	}

	@Override
	public PurchaseOrder createOrder(Cart cart) {
		PurchaseOrder order = new PurchaseOrder(cart);
		PurchaseOrder orderSaved = purchaseOrderRepository.save(order);
		return orderSaved;
	}

	@Override
	public List<PurchaseOrderDetailsResponse> buildPurchaseOrderDetailsResponseList(
			List<PurchaseOrderDetails> addedPurchaseProducts) {
		List<PurchaseOrderDetailsResponse> purchaseList = new ArrayList<>();
		for (PurchaseOrderDetails orderDetail : addedPurchaseProducts) {
			PurchaseOrderDetailsResponse orderResponse = new PurchaseOrderDetailsResponse(orderDetail);
			purchaseList.add(orderResponse);
		}
		return purchaseList;
	}

	@Override
	public List<PurchaseOrderDetails> setPurchaseOrderDetails(List<CartDetails> details, PurchaseOrder purchaseOrder) {
		List<PurchaseOrderDetails> purchaseOrderDetails = details.stream()
				.map(detail -> new PurchaseOrderDetails(detail, purchaseOrder)).collect(Collectors.toList());
		return purchaseOrderDetails;
	}

	@Override
	public List<PurchaseOrder> getAllOrders(String name) {
		List<PurchaseOrder> orders = purchaseOrderRepository.findAllByUserId(name);
		return orders;
	}

	@Override
	public List<PurchaseOrderResponse> buildPurchaseOrderResponseList(List<PurchaseOrder> orders) {
		checkIfPurchaseOrderListIsEmpty(orders);

		List<PurchaseOrderResponse> ordersResponse = new ArrayList<>();
		for (PurchaseOrder order : orders) {
			List<PurchaseOrderDetailsResponse> orderDetailsResponse = order.getDetails().stream()
					.map(detail -> new PurchaseOrderDetailsResponse(detail,
							productService.buildProductResponse(detail.getProduct())))
					.collect(Collectors.toList());
			PurchaseOrderResponse orderResponse = new PurchaseOrderResponse(order, orderDetailsResponse);
			ordersResponse.add(orderResponse);
		}
		return ordersResponse;
	}

	@Override
	public void checkIfPurchaseOrderListIsEmpty(List<PurchaseOrder> orders) {
		if (orders.isEmpty())
			throw new EmptyOrderListException("No se ha realizado ninguna compra");
	}

	@Override
	public List<PurchaseOrder> getAllOrders() {
		List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
		return orders;
	}

	@Override
	public PurchaseOrder getOrderById(Long orderId) {
		PurchaseOrder order = purchaseOrderRepository.findById(orderId).get();
		return order;
	}

	@Override
	public PurchaseOrder changeStatusToReady(PurchaseOrder order) {
		order.getDetails().stream().forEach(detail -> detail.setStatus(true));
		order.setStatus(true);
		purchaseOrderRepository.save(order);
		return order;
	}

	@Override
	public PurchaseOrder changeStatusToDetail(PurchaseOrder order, Long detailId) {
		order.getDetails().stream().forEach(detail -> {
			if (detailId.equals(detail.getPurchaseOrderDetailsId())) {
				detail.setStatus(!detail.getStatus());
			}
		});
		if(order.getDetails().stream().filter(detail -> !detail.getStatus()).findAny().isPresent())
			order.setStatus(false);
		else
			order.setStatus(true);
		purchaseOrderRepository.save(order);
		return order;
	}

}
