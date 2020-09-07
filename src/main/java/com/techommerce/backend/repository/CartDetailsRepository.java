package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long>{

	List<CartDetails> findByCart(Cart cart);

	@Query(value="select * from ecommerce.cart_details cp where product_id = ?1 and user_id = ?2", nativeQuery=true)
	CartDetails findByProductAndUser(Long productId, String userId);

	@Query(value="select c from CartDetails c where c.cart = ?1 and c.state = true")
	List<CartDetails> findByCartAndState(Cart cart);

	@Query(value="delete from ecommerce.cart_details cp where user_id = ?1", nativeQuery=true)
	void deleteWithUserId(String userId);

}
