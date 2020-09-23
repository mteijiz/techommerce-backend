package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long>{

	@Query(value="select c from CartDetails c where c.cart = ?1")
	List<CartDetails> findByCart(Cart cart);

}
