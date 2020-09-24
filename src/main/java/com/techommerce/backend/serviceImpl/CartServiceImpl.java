package com.techommerce.backend.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.exception.EmptyCartException;
import com.techommerce.backend.repository.CartDetailsRepository;
import com.techommerce.backend.repository.CartRepository;
import com.techommerce.backend.request.UpdateQuantityOfProductInACartRequest;
import com.techommerce.backend.response.CartDetailResponse;
import com.techommerce.backend.response.CartResponse;
import com.techommerce.backend.service.CartService;
import com.techommerce.backend.service.ProductService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartDetailsRepository cartDetailsRepository;

	@Autowired
	private ProductService productService;

	@Override
	public Cart createNewCartForUser(String id) {
		Cart cart = new Cart(id);
		Cart cartCreated = cartRepository.save(cart);
		return cartCreated;
	}

	@Override
	@Transactional
	public Cart addProductDetailToCart(Cart cart, CartDetails detail) {
		Optional<CartDetails> cartDetailOptional = cart.getCartDetails().stream()
				.filter(cartDetail -> cartDetail.getProduct().getProductId().equals(detail.getProduct().getProductId()))
				.findFirst();
		if (cartDetailOptional.isPresent()) {
			cart.getCartDetails().remove(cartDetailOptional.get());
			substractTotalPriceFromCart(cart, cartDetailOptional.get());
			updateCartWithExistingProduct(cart, detail, cartDetailOptional.get());
			updateDetail(cartDetailOptional.get(), detail);
			cart.getCartDetails().add(cartDetailOptional.get());
		} else {
			updateCart(cart, detail);
			detail.setCart(cart);
			CartDetails detailSaved = cartDetailsRepository.saveAndFlush(detail);
			cart.getCartDetails().add(detailSaved);
		}
		System.out.println(cart.getTotalAmount());
		Cart newCart = cartRepository.save(cart);
		return newCart;
	}

	@Override
	public void substractTotalPriceFromCart(Cart cart, CartDetails cartDetails) {
		cart.setTotalAmount(cart.getTotalAmount() - cartDetails.getTotalPrice());		
	}

	@Override
	public void updateCart(Cart cart, CartDetails detail) {
		cart.setQuantityOfProduct(cart.getQuantityOfProduct() + detail.getQuantity());
		cart.setTotalAmount(cart.getTotalAmount() + detail.getQuantity() * detail.getProduct().getProductPrice());
	}
	
	@Override
	public void updateCartWithExistingProduct(Cart cart, CartDetails detail, CartDetails actualDetail) {
		cart.setQuantityOfProduct(cart.getQuantityOfProduct() + detail.getQuantity());
		cart.setTotalAmount(cart.getTotalAmount() + (detail.getQuantity() + actualDetail.getQuantity()) * detail.getProduct().getProductPrice());
	}

	@Override
	public void updateDetail(CartDetails cartDetail, CartDetails detail) {
		cartDetail.setQuantity(cartDetail.getQuantity() + detail.getQuantity());
		cartDetail.setUnitPrice(detail.getProduct().getProductPrice());
		cartDetail.setTotalPrice(cartDetail.getQuantity() * detail.getProduct().getProductPrice());
	}

	@Override
	public List<CartDetails> getAllProductsOfACart(Cart cart) {
		List<CartDetails> cartProductsList = cartDetailsRepository.findByCart(cart);
		return cartProductsList;
	}

	@Override
	public Cart getCartOfUser(String id) {
		if (!cartRepository.existsById(id)) {
			Cart existingCart = createNewCartForUser(id);
			return existingCart;
		}
		Cart newCart = cartRepository.findById(id).get();
		return newCart;
	}

	@Override
	public void deleteCartProduct(Long cartProductId) {
		cartDetailsRepository.deleteById(cartProductId);
	}

	@Override
	public void substractQuantityFromCart(CartDetails detail) {
		Cart cart = detail.getCart();
		cart.setQuantityOfProduct(cart.getQuantityOfProduct() - detail.getQuantity());
		cart.setTotalAmount(cart.getTotalAmount() - detail.getTotalPrice());
		cartRepository.save(cart);
	}

	@Override
	public CartDetails getCartDetailById(Long cartProductId) {
		CartDetails details = cartDetailsRepository.findById(cartProductId).get();
		return details;
	}

	@Override
	public void deleteProductsOfAUser(Cart cart) {
		List<CartDetails> cartDetails = getAllProductsOfACart(cart);
		cartDetails.stream().forEach(detail -> cartDetailsRepository.delete(detail));
		cart.setTotalAmount(new Float(0));
		cart.setQuantityOfProduct(new Integer(0));
		cartRepository.save(cart);
	}

	@Override
	public CartResponse buildCartResponse(Cart cart) {
		checkIfCartIsEmpty(cart);
		List<CartDetailResponse> detailsResponse = cart.getCartDetails().stream()
				.map(detail -> new CartDetailResponse(detail, productService.buildProductResponse(detail.getProduct()), productService.checkIfProductIsActiveOrInactive(detail.getProduct())))
				.collect(Collectors.toList());
		CartResponse cartResponse = new CartResponse(cart, detailsResponse);
		return cartResponse;
	}

	@Override
	public void checkIfCartIsEmpty(Cart cart) {
		if(cart.getCartDetails().isEmpty())
			throw new EmptyCartException("No se agregaron productos al carrito");
	}

	@Override
	public void updateCart(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public void updateCartTotals(Cart cart, CartDetails detail) {
		cart.getCartDetails().removeIf(detailOfCart -> detailOfCart.getCartDetailId().equals(detail.getCartDetailId()));
		cart.getCartDetails().add(detail);
		float total = 0;
		int quantity = 0;
		for (CartDetails cartDetail : cart.getCartDetails()) {
			total = total + cartDetail.getTotalPrice();
			quantity = quantity + cartDetail.getQuantity();
		}
		cart.setTotalAmount(new Float(total));
		cart.setQuantityOfProduct(new Integer(quantity));
	}

	@Override
	public CartDetails updateCartDetail(UpdateQuantityOfProductInACartRequest request) {
		CartDetails detail = cartDetailsRepository.findById(request.getCartDetail().getCartDetailId()).get();
		detail.setQuantity(request.getNewQuantity());
		detail.setUnitPrice(detail.getProduct().getProductPrice());
		detail.setTotalPrice(request.getNewQuantity() * detail.getProduct().getProductPrice());
		return detail;
	}

}
