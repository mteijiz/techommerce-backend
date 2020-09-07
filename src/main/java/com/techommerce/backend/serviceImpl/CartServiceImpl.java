package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.PurchaseOrderDetails;
import com.techommerce.backend.exception.EmptyCartException;
import com.techommerce.backend.repository.CartDetailsRepository;
import com.techommerce.backend.repository.CartRepository;
import com.techommerce.backend.request.UpdateQuantityOfProductInACartRequest;
import com.techommerce.backend.response.CartDetailResponse;
import com.techommerce.backend.response.CartResponse;
import com.techommerce.backend.response.PurchaseOrderDetailsResponse;
import com.techommerce.backend.service.CartService;
import com.techommerce.backend.service.ImageService;
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
			updateCart(cart, detail);
			updateDetail(cartDetailOptional.get(), detail);
			cart.getCartDetails().add(cartDetailOptional.get());
		} else {
			updateCart(cart, detail);
			detail.setCart(cart);
			CartDetails detailSaved = cartDetailsRepository.saveAndFlush(detail);
			cart.getCartDetails().add(detailSaved);
		}
		Cart newCart = cartRepository.save(cart);
		return newCart;
//		updateUserCart(cartProduct);
//		checkAndUpdateProductInCart(cartProduct);
//		CartDetails cartProductAdded = cartDetailsRepository.save(cartProduct);
//		cartRepository.save(cartProduct.getCart());
//		return cartProductAdded;
	}

	@Override
	public void updateCart(Cart cart, CartDetails detail) {
		cart.setQuantityOfProduct(cart.getQuantityOfProduct() + detail.getQuantity());
		cart.setTotalAmount(cart.getTotalAmount() + detail.getTotalPrice());
	}

	@Override
	public void updateDetail(CartDetails cartDetail, CartDetails detail) {
		cartDetail.setQuantity(cartDetail.getQuantity() + detail.getQuantity());
		cartDetail.setTotalPrice(cartDetail.getTotalPrice() + detail.getTotalPrice());
	}

//	private void updateUserCart(CartDetails cartProduct) {
//		System.out.println("updateUserCart - actual en cart: " + cartProduct.getCart().getQuantityOfProduct() + " lo que se agrega: " + cartProduct.getQuantity());
//		System.out.println("updateUserCart - actual en cart: " + cartProduct.getCart().getTotalAmount() + " lo que se agrega: " + cartProduct.getTotalPrice());
//		cartProduct.getCart().setQuantityOfProduct(cartProduct.getCart().getQuantityOfProduct() + cartProduct.getQuantity());
//		cartProduct.getCart().setTotalAmount(cartProduct.getCart().getTotalAmount() + cartProduct.getTotalPrice());
//	}

//	private void checkAndUpdateProductInCart(CartDetails cartProduct) {
//		CartDetails productDatails = cartDetailsRepository.findByProductAndUser(cartProduct.getProduct().getProductId(), cartProduct.getCart().getUserId());
//		if(!cartProductList.isEmpty()) {
//			addProductToTheLine(cartProductList, cartProduct);
//			deleteCartProduct(cartProductList.get(0).getCartProductId());
//		}
//	}

//	private void addProductToTheLine(List<CartDetails> cartProductList, CartDetails cartProduct) {
//		cartProduct.setQuantity(cartProduct.getQuantity() + cartProductList.get(0).getQuantity());
//		cartProduct.setTotalPrice(cartProduct.getTotalPrice() + cartProductList.get(0).getTotalPrice());
//	}

	@Override
	public List<CartDetails> getAllProductsOfACart(Cart cart) {
		List<CartDetails> cartProductsList = cartDetailsRepository.findByCartAndState(cart);
		return cartProductsList;
	}

	@Override
	public List<CartDetailResponse> buildCartProductResponseList(List<CartDetails> cartProductsList) {
//		List<CartDetailResponse> cartProductResponseList = new ArrayList<CartDetailResponse>();
//		for (CartDetails cartProduct : cartProductsList) {
//			CartDetailResponse cartProductResponse = new CartDetailResponse(cartProduct,
//					productService.buildProductResponse(cartProduct.getProduct()));
//			cartProductResponseList.add(cartProductResponse);
//		}
		List<CartDetailResponse> cartProductResponseList = cartProductsList.stream()
				.map(detail -> new CartDetailResponse(detail, productService.buildProductResponse(detail.getProduct())))
				.collect(Collectors.toList());
		return cartProductResponseList;
	}

	@Override
	public Cart getCartOfUser(String id) {
		if (!cartRepository.existsById(id)) {
			Cart existingCart = createNewCartForUser(id);
			return existingCart;
		}
		Cart newCart = cartRepository.findById(id).get();
//		Cart newCart = cartRepository.findById(id).orElse(createNewCartForUser(id));
//		if(!newCart.getCartDetails().isEmpty())
//			System.out.println("\nLa lista de details tiene algo\n");
		return newCart;
	}

	@Override
	public void deleteCartProduct(Long cartProductId) {
		cartDetailsRepository.deleteById(cartProductId);
	}

	@Override
	public void substractQuantityFromCart(Long cartProductId) {
		CartDetails details = cartDetailsRepository.findById(cartProductId).get();
		Cart cart = details.getCart();
		cart.setQuantityOfProduct(cart.getQuantityOfProduct() - details.getQuantity());
		cart.setTotalAmount(cart.getTotalAmount() - details.getTotalPrice());
		cartRepository.save(cart);
	}

	@Override
	public void deleteProductsOfAUser(Cart cart) {
		List<CartDetails> cartDetails = getAllProductsOfACart(cart);
		cartDetails.stream().forEach(detail -> cartDetailsRepository.delete(detail));
//		for(CartDetails product : cartDetails) {
//			cartDetailsRepository.delete(product);
//		}
		// cartDetailsRepository.deleteWithUserId(cart.getUserId());
		cart.setTotalAmount(new Float(0));
		cart.setQuantityOfProduct(new Integer(0));
		cartRepository.save(cart);
	}

	@Override
	public CartResponse buildCartResponse(Cart cart) {
		//checkIfCartHasDetails(cart);
		List<CartDetailResponse> detailsResponse = cart.getCartDetails().stream()
				.map(detail -> new CartDetailResponse(detail, productService.buildProductResponse(detail.getProduct())))
				.collect(Collectors.toList());
		CartResponse cartResponse = new CartResponse(cart, detailsResponse);
		return cartResponse;
	}

	public void checkIfCartHasDetails(Cart cart) {
		if (cart.getCartDetails().isEmpty())
			throw new EmptyCartException("No se ha agregado ningún producto al carrito");
	}

	@Override
	public void updateCart(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public void updateCartTotals(Cart cart, CartDetails detail) {
//		for(CartDetails detailOfCart : cart.getCartDetails()) {
//			if(detail.getCartDetailId().equals(detailOfCart.getCartDetailId())) {
//				cart.getCartDetails().removeIf(filter)
//			}
//		}
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
		detail.setTotalPrice(request.getNewQuantity() * detail.getUnitPrice());
		return detail;
	}

}
