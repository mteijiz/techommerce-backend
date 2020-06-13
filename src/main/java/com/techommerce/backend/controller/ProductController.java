package com.techommerce.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techommerce.backend.entity.Product;
import com.techommerce.backend.request.AddProductRequest;
import com.techommerce.backend.request.UpdateProductRequest;
import com.techommerce.backend.response.ProductResponse;
import com.techommerce.backend.service.ProductService;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductRequest productRequest){
		Product productToAdd = new Product(productRequest);
		Product productAdded = productService.addProduct(productToAdd);
		ProductResponse productAddedResponse = new ProductResponse(productAdded);
		return new ResponseEntity<ProductResponse>(productAddedResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllProducts(){
		List<Product> productsList = productService.getAllProducts();
		List<ProductResponse> productsResponseList = productService.buildProductResponseList(productsList);
		return new ResponseEntity<List<ProductResponse>>(productsResponseList, HttpStatus.OK);
	}
	
	@PutMapping("/updateState")
	public ResponseEntity<?> updateProductState(@Valid @RequestBody UpdateProductRequest productRequest){
		Product productToUpdate = new Product(productRequest);
		Product productUpdated = productService.updateProductState(productToUpdate);
		ProductResponse productUpdatedResponse = new ProductResponse(productUpdated);
		return new ResponseEntity<ProductResponse>(productUpdatedResponse, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody UpdateProductRequest productRequest){
		Product productToUpdate = new Product(productRequest);
		Product productUpdated = productService.updateProduct(productToUpdate);
		ProductResponse productUpdatedResponse = new ProductResponse(productUpdated);
		return new ResponseEntity<ProductResponse>(productUpdatedResponse, HttpStatus.OK);
	}
	
}
