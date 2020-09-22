package com.techommerce.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.PurchaseOrder;
import com.techommerce.backend.entity.PurchaseOrderDetails;
import com.techommerce.backend.request.CartRequest;
import com.techommerce.backend.request.PurchaseOrderRequest;
import com.techommerce.backend.response.PurchaseOrderResponse;
import com.techommerce.backend.response.PurchaseOrderDetailsResponse;
import com.techommerce.backend.service.CartService;
import com.techommerce.backend.service.KeycloakService;
import com.techommerce.backend.service.ProductService;
import com.techommerce.backend.service.PurchaseOrderService;

import ch.qos.logback.classic.net.SyslogAppender;


@RestController
@RequestMapping("purchase")
@CrossOrigin(origins = "http://localhost:4200")
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private KeycloakService keycloakService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/add")
	@RolesAllowed("user")
	public ResponseEntity<?> addPurchaseOfAUser() {
		KeycloakPrincipal<KeycloakSecurityContext> keycloakToken = keycloakService.getJwtToken();
		Cart cart = cartService.getCartOfUser(keycloakToken.getName());
		List<CartDetails> cartDetailsList = cartService.getAllProductsOfACart(cart);
		productService.substractProductQuantity(cartDetailsList);
		PurchaseOrder purchaseOrder = purchaseOrderService.createOrder(cart);
		cartService.deleteProductsOfAUser(cart);
		List<PurchaseOrderDetails> orderDetails = purchaseOrderService.setPurchaseOrderDetails(cartDetailsList, purchaseOrder);
		List<PurchaseOrderDetails> addedPurchaseProducts = purchaseOrderService.purchaseProducts(orderDetails);
		List<PurchaseOrderDetailsResponse> productResponseList = purchaseOrderService.buildPurchaseOrderDetailsResponseList(addedPurchaseProducts);
		return new ResponseEntity<List<PurchaseOrderDetailsResponse>>(productResponseList, HttpStatus.OK);
	}
	
	@GetMapping("/getPurchaseDetails")
	@RolesAllowed("user")
	public ResponseEntity<?> getOrderDetailsOfAUser(@RequestBody PurchaseOrderRequest orderRequest){
		PurchaseOrder order = new PurchaseOrder(orderRequest);
		List<PurchaseOrderDetails> orderDetails = purchaseOrderService.getOrderDetaislOfAUser(order);
		List<PurchaseOrderDetailsResponse> orderDetailsResponse = purchaseOrderService.buildPurchaseOrderDetailsResponseList(orderDetails);
		return new ResponseEntity<List<PurchaseOrderDetailsResponse>>(orderDetailsResponse, HttpStatus.OK);
	}
	
	@GetMapping("getOrders")
	@RolesAllowed("user")
	public ResponseEntity<?> getAllOrdersOfAUser(){
		KeycloakPrincipal<KeycloakSecurityContext> keycloakToken = keycloakService.getJwtToken();
		List<PurchaseOrder> orders = purchaseOrderService.getAllOrders(keycloakToken.getName());
		List<PurchaseOrderResponse> ordersResponse = purchaseOrderService.buildPurchaseOrderResponseList(orders);
		return new ResponseEntity<List<PurchaseOrderResponse>>(ordersResponse, HttpStatus.OK);
	}
	
	@GetMapping("getAllOrders")
	@RolesAllowed("admin")
	public ResponseEntity<?> getAllOrders(){
		List<PurchaseOrder> orders = purchaseOrderService.getAllOrders();
		List<PurchaseOrderResponse> ordersResponse = purchaseOrderService.buildPurchaseOrderResponseList(orders);
		return new ResponseEntity<List<PurchaseOrderResponse>>(ordersResponse, HttpStatus.OK);
	}
	
	@GetMapping("changeStatus/{orderId}")
	@RolesAllowed("admin")
	public ResponseEntity<?> changeStatusToOrder(@PathVariable Long orderId){
		PurchaseOrder order = purchaseOrderService.getOrderById(orderId);
		PurchaseOrder updatedOrder = purchaseOrderService.changeStatusToReady(order);
		List<PurchaseOrderDetailsResponse> orderDetailsResponse = updatedOrder.getDetails().stream().map(detail -> new PurchaseOrderDetailsResponse(detail, productService.buildProductResponse(detail.getProduct()))).collect(Collectors.toList());
		PurchaseOrderResponse orderResponse = new PurchaseOrderResponse(updatedOrder, orderDetailsResponse);
		return new ResponseEntity<PurchaseOrderResponse>(orderResponse, HttpStatus.OK);
	}
	
	@GetMapping("changeStatus/{orderId}/{detailId}")
	@RolesAllowed("admin")
	public ResponseEntity<?> changeStatusToDetailOfAnOrder(@PathVariable Long orderId, @PathVariable Long detailId){
		System.out.println(orderId + " " + detailId);
		PurchaseOrder order = purchaseOrderService.getOrderById(orderId);
		PurchaseOrder updatedOrder = purchaseOrderService.changeStatusToDetail(order, detailId);
		List<PurchaseOrderDetailsResponse> orderDetailsResponse = updatedOrder.getDetails().stream().map(detail -> new PurchaseOrderDetailsResponse(detail, productService.buildProductResponse(detail.getProduct()))).collect(Collectors.toList());
		PurchaseOrderResponse orderResponse = new PurchaseOrderResponse(updatedOrder, orderDetailsResponse);
		return new ResponseEntity<PurchaseOrderResponse>(orderResponse, HttpStatus.OK);
	}
}
