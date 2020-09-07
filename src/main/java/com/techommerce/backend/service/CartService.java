package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.request.UpdateQuantityOfProductInACartRequest;
import com.techommerce.backend.response.CartDetailResponse;
import com.techommerce.backend.response.CartResponse;

public interface CartService {

	Cart createNewCartForUser(String id);

	Cart addProductDetailToCart(Cart cart, CartDetails cartProduct);

	List<CartDetails> getAllProductsOfACart(Cart cart);

	Cart getCartOfUser(String id);

	void deleteCartProduct(Long cartProductId);
	
	List<CartDetailResponse> buildCartProductResponseList(List<CartDetails> cartProductsList);

	void deleteProductsOfAUser(Cart cart);

	CartResponse buildCartResponse(Cart cart);

	void updateCart(Cart cart);
	
	void updateDetail(CartDetails cartDetail, CartDetails detail);
	
	void updateCart(Cart cart, CartDetails detail);
	
	void substractQuantityFromCart(Long cartProductId);
	
	CartDetails updateCartDetail(UpdateQuantityOfProductInACartRequest request);
	
	void updateCartTotals(Cart cart, CartDetails detail);
	
	void checkIfCartHasDetails(Cart cart);
	
}
