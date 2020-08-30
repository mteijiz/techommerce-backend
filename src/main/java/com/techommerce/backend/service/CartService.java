package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.PurchaseOrderDetails;
import com.techommerce.backend.request.UpdateQuantityOfProductInACartRequest;
import com.techommerce.backend.response.CartDetailResponse;
import com.techommerce.backend.response.CartResponse;
import com.techommerce.backend.response.PurchaseOrderDetailsResponse;

public interface CartService {

	Cart createNewCartForUser(String id);

	Cart addProductDetailToCart(Cart cart, CartDetails cartProduct);

	List<CartDetails> getAllProductsOfACart(Cart cart);

	Cart getCartOfUser(String id);

	void deleteCartProduct(Long cartProductId);
	
	List<CartDetailResponse> buildCartProductResponseList(List<CartDetails> cartProductsList);

	void deleteProductsOfAUser(Cart cart);

	CartResponse buildCartResponse(Cart cart);

	void updateCartAndDetail(Cart cart, UpdateQuantityOfProductInACartRequest request);
	
}
