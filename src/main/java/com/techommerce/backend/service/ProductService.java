package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.response.ProductResponse;

public interface ProductService {

	Product addProduct(Product productToAdd);

	List<Product> getAllProducts();

	List<ProductResponse> buildProductResponseList(List<Product> productsList);

	Product updateProduct(Product productToUpdate);

	Product searchProductById(Long productId);

	List<Product> getActiveProducts();
	
	ProductResponse buildProductResponse(Product product);
	
	void productCodeAndNameUppercase(Product product);
	
	void checkIfProductQuantityIsLowerThanZero(Product product);
	
	void addOrSubstractQuantityFromProduct(Product product, Integer quantityToAddOrSubstract);

	void substractProductQuantity(List<CartDetails> cartDetailsList);
	
	boolean checkIfProductIsActiveOrInactive(Product product);

	List<Product> getProductsByFilter(List<Brand> brands, List<Category> categories, List<Subcategory> subcategories);
	
	void checkIfProductListIsEmpty(List<Product> productsList);
	
	void removeQuantityOfProductsFromDetails(CartDetails detail);
	
	void checkIfQuantityProductHasQuantityLowerThanZero(Product product, Integer quantityToSubstract);

}
