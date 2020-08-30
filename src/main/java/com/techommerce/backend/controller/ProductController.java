package com.techommerce.backend.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

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

import com.techommerce.backend.entity.Product;
import com.techommerce.backend.request.AddProductRequest;
import com.techommerce.backend.request.AddVoteToProductRequest;
import com.techommerce.backend.request.UpdateProductRequest;
import com.techommerce.backend.response.ProductResponse;
import com.techommerce.backend.service.ProductService;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "${spring.frontend.url}")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/add")
	@RolesAllowed("admin")
	public ResponseEntity<?> addProduct(@RequestBody AddProductRequest productRequest){
		@Valid Product productToAdd = new Product(productRequest);
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
	
	@GetMapping("/getActive")
	public ResponseEntity<?> getActiveProducts(){
		List<Product> productsList = productService.getActiveProducts();
		List<ProductResponse> productsResponseList = productService.buildProductResponseList(productsList);
		return new ResponseEntity<List<ProductResponse>>(productsResponseList, HttpStatus.OK);
	}
	
	@PutMapping("/updateState")
	@RolesAllowed("admin")
	public ResponseEntity<?> updateProductState(@Valid @RequestBody UpdateProductRequest productRequest){
		Product productToUpdate = new Product(productRequest);
		Product productUpdated = productService.updateProductState(productToUpdate);
		ProductResponse productUpdatedResponse = new ProductResponse(productUpdated);
		return new ResponseEntity<ProductResponse>(productUpdatedResponse, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	@RolesAllowed("admin")
	public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest productRequest){
		@Valid Product productToUpdate = new Product(productRequest);
		Product productUpdated = productService.updateProduct(productToUpdate);
		ProductResponse productUpdatedResponse = new ProductResponse(productUpdated);
		return new ResponseEntity<ProductResponse>(productUpdatedResponse, HttpStatus.OK);
	}
	
	@PutMapping("/addVote")
	public ResponseEntity<?> addVoteToProduct(@RequestBody @Valid AddVoteToProductRequest voteRequest){
		Product product = productService.updateProductRate(voteRequest);
		ProductResponse productResponse = new ProductResponse(product);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable Long productId){
		Product product = productService.searchProductById(productId);
		ProductResponse productResponse = productService.buildProductResponse(product);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}
}
