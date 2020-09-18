package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{

	@Query(value = "select p from PurchaseOrder p where p.userId = ?1")
	List<PurchaseOrder> findAllByUserId(String userId);

}
