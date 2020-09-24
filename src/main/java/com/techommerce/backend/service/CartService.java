package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.request.UpdateQuantityOfProductInACartRequest;
import com.techommerce.backend.response.CartResponse;

public interface CartService {

	Cart createNewCartForUser(String id);

	Cart addProductDetailToCart(Cart cart, CartDetails cartProduct);

	List<CartDetails> getAllProductsOfACart(Cart cart);

	Cart getCartOfUser(String id);

	void deleteCartProduct(Long cartProductId);

	void deleteProductsOfAUser(Cart cart);

	CartResponse buildCartResponse(Cart cart);

	void updateCart(Cart cart);
	
	void updateDetail(CartDetails cartDetail, CartDetails detail);
	
	void updateCart(Cart cart, CartDetails detail);
	
	void updateCartWithExistingProduct(Cart cart, CartDetails detail, CartDetails actualDetail);
	
	void substractQuantityFromCart(CartDetails detail);
	
	CartDetails updateCartDetail(UpdateQuantityOfProductInACartRequest request);
	
	void updateCartTotals(Cart cart, CartDetails detail);
	
	CartDetails getCartDetailById(Long cartProductId);
	
	void substractTotalPriceFromCart(Cart cart, CartDetails cartDetails);
	
	public void checkIfCartIsEmpty(Cart cart);
	
}
