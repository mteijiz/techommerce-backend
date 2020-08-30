package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{

	@Query(value = "select * from ecommerce.purchase_order where user_id = ?1", nativeQuery = true)
	List<PurchaseOrder> findAllByUserId(String userId);

}
