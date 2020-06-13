package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.response.ProductResponse;

public interface ProductService {

	Product addProduct(Product productToAdd);

	List<Product> getAllProducts();

	List<ProductResponse> buildProductResponseList(List<Product> productsList);

	Product updateProductState(Product productToUpdate);

	Product updateProduct(Product productToUpdate);

	void changingActiveStateOfProductsBelongToCategory(Category categoryToUpdateState);

	void changingInactiveStateOfProductsBelongToCategory(Category categoryToUpdateState);

	void changingInactiveStateOfProductsBelongToCategory(Subcategory subcategoryToUpdateState);

	void changingActiveStateOfProductsBelongToCategory(Subcategory subcategoryToUpdateState);

	Product searchProductById(Long productId);

}
