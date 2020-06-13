package com.techommerce.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.request.CartRequest;
import com.techommerce.backend.service.CartService;

@RestController
@RequestMapping("cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addToCart(@RequestBody @Valid CartRequest cartRequest){
		//Inicializo los cart acá, pero creo que lo correcto sería cuando la persona se registra o agrega un item por primera vez
		cartService.createCart();
		return null;
	}
	
}
