package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.repository.ProductRepository;
import com.techommerce.backend.response.ProductResponse;
import com.techommerce.backend.service.ImageService;
import com.techommerce.backend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ImageService imageService;

	@Override
	public Product addProduct(Product productToAdd) {
		// TODO Auto-generated method stub
		productCodeAndNameUppercase(productToAdd);
		Product productAdded = productRepository.save(productToAdd);
		return productAdded;
	}
	
	public void productCodeAndNameUppercase(Product product) {
		product.setProductCode(product.getProductCode().toUpperCase());
		product.setProductName(product.getProductName().toUpperCase());
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> productList = productRepository.findAll();
		return productList;
	}

	@Override
	public List<ProductResponse> buildProductResponseList(List<Product> productsList) {
		// TODO Auto-generated method stub
		List<ProductResponse> productResponseList = new ArrayList<ProductResponse>();
		for(Product product : productsList) {
			ProductResponse auxProductResponse = new ProductResponse(product);
			imageService.convertImageListToResponse(auxProductResponse, product);
			productResponseList.add(auxProductResponse);
		}
		return productResponseList;
	}

	@Override
	public Product updateProductState(Product productToUpdate) {
		// TODO Auto-generated method stub
		if(productToUpdate.getProductState())
			productToUpdate.setProductState(false);
		else
			productToUpdate.setProductState(true);
		Product productStateUpdated = productRepository.save(productToUpdate);
		return productStateUpdated;
	}

	@Override
	public Product updateProduct(Product productToUpdate) {
		// TODO Auto-generated method stub
		productCodeAndNameUppercase(productToUpdate);
		Product productUpdated = productRepository.save(productToUpdate);
		return productUpdated;
	}

	@Override
	public void changingActiveStateOfProductsBelongToCategory(Category category) {
		// TODO Auto-generated method stub
		List<Product> productsBelongToCategoryList = productRepository.findByProductCategory(category);
		for(Product product : productsBelongToCategoryList) {
			if(product.getProductState()) 
				product.setProductState(false);
			productRepository.save(product);
		}
	}

	@Override
	public void changingInactiveStateOfProductsBelongToCategory(Category category) {
		// TODO Auto-generated method stub
		List<Product> productsBelongToCategoryList = productRepository.findByProductCategory(category);
		for(Product product : productsBelongToCategoryList) {
			if(!product.getProductState()) 
				product.setProductState(true);
			productRepository.save(product);
		}
	}

	@Override
	public void changingInactiveStateOfProductsBelongToCategory(Subcategory subcategory) {
		// TODO Auto-generated method stub
		List<Product> productsBelongToCategoryList = productRepository.findByProductSubcategory(subcategory);
		for(Product product : productsBelongToCategoryList) {
			if(product.getProductState()) 
				product.setProductState(false);
			productRepository.save(product);
		}
	}

	@Override
	public void changingActiveStateOfProductsBelongToCategory(Subcategory subcategory) {
		// TODO Auto-generated method stub
		List<Product> productsBelongToCategoryList = productRepository.findByProductSubcategory(subcategory);
		for(Product product : productsBelongToCategoryList) {
			if(!product.getProductState()) 
				product.setProductState(true);
			productRepository.save(product);
		}
	}

	@Override
	public Product searchProductById(Long productId) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(productId).get();
		return product;
	}
}
