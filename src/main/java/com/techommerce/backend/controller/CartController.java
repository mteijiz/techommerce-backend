package com.techommerce.backend.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.request.AddProductWithQuantityToCartRequest;
import com.techommerce.backend.request.UpdateQuantityOfProductInACartRequest;
import com.techommerce.backend.response.CartResponse;
import com.techommerce.backend.service.CartService;
import com.techommerce.backend.service.KeycloakService;

@RestController
@RequestMapping("carts")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private KeycloakService keycloakService;
	
	@PostMapping("/addWithQuantity")
	@RolesAllowed("user")
	public ResponseEntity<?> addToCartWithQuantity(@RequestBody AddProductWithQuantityToCartRequest cartDetails){
		KeycloakPrincipal<KeycloakSecurityContext> keycloakToken = keycloakService.getJwtToken();
		Cart cart = cartService.getCartOfUser(keycloakToken.getName());
		@Valid CartDetails detail = new CartDetails(cartDetails);
		Cart updatedCart = cartService.addProductDetailToCart(cart, detail);
		CartResponse cartResponse = cartService.buildCartResponse(updatedCart);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	@GetMapping("/get")
	@RolesAllowed("user")
	public ResponseEntity<?> getUserCart(){
		KeycloakPrincipal<KeycloakSecurityContext> keycloakToken = keycloakService.getJwtToken();
		Cart cart = cartService.getCartOfUser(keycloakToken.getName());;
		CartResponse cartResponse = cartService.buildCartResponse(cart);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteFromCart/{cartProductId}")
	@RolesAllowed("user")
	public ResponseEntity<?> deleteFromCart(@PathVariable Long cartProductId){
		CartDetails detail = cartService.getCartDetailById(cartProductId); 
		cartService.deleteCartProduct(cartProductId);
		cartService.substractQuantityFromCart(detail);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("updateQuantityOfAProductInACart")
	@RolesAllowed("user")
	public ResponseEntity<?> updateQuantityOfAProductInACart(@RequestBody UpdateQuantityOfProductInACartRequest request){
		KeycloakPrincipal<KeycloakSecurityContext> keycloakToken = keycloakService.getJwtToken();
		Cart cart = cartService.getCartOfUser(keycloakToken.getName());
		CartDetails detail = cartService.updateCartDetail(request);
		cartService.updateCartTotals(cart, detail);
		cartService.updateCart(cart);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
