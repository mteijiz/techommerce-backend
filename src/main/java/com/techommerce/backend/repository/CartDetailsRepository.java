package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.Product;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long>{

	List<CartDetails> findByCart(Cart cart);

	@Query(value="select c from CartDetails c where c.cart = ?1 and c.state = true")
	List<CartDetails> findByCartAndState(Cart cart);

}
