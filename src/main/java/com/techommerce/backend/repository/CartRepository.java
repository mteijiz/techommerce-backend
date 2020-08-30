package com.techommerce.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techommerce.backend.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, String>{

}
