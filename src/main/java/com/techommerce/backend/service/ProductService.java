package com.techommerce.backend.service;

import java.util.List;

import javax.validation.Valid;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.request.AddVoteToProductRequest;
import com.techommerce.backend.response.ProductResponse;

public interface ProductService {

	Product addProduct(Product productToAdd);

	List<Product> getAllProducts();

	List<ProductResponse> buildProductResponseList(List<Product> productsList);

//	Product updateProductState(Product productToUpdate);

	Product updateProduct(Product productToUpdate);

//	void changingActiveStateOfProductsBelongToCategory(Category categoryToUpdateState);
//
//	void changingInactiveStateOfProductsBelongToCategory(Category categoryToUpdateState);
//
//	void changingInactiveStateOfProductsBelongToSubcategory(Subcategory subcategoryToUpdateState);
//
//	void changingActiveStateOfProductsBelongToSubcategory(Subcategory subcategoryToUpdateState);

	Product searchProductById(Long productId);

//	void changingActiveStateOfProductBelongToBrand(Brand brandToChangeStatus);
//
//	void changingInactiveStateOfProductBelongToBrand(Brand brandToChangeStatus);

	List<Product> getActiveProducts();

//	Product updateProductRate(@Valid AddVoteToProductRequest voteRequest);
	
	ProductResponse buildProductResponse(Product product);
	
	void productCodeAndNameUppercase(Product product);
	
	void checkIfProductQuantityIsLowerThanZero(Product product);
	
	void addOrSubstractQuantityFromProduct(Product product);

	void substractProductQuantity(List<CartDetails> cartDetailsList);

}
