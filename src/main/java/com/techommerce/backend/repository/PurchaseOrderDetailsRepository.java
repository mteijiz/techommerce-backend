package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Cart;
import com.techommerce.backend.entity.PurchaseOrder;
import com.techommerce.backend.entity.PurchaseOrderDetails;

public interface PurchaseOrderDetailsRepository extends JpaRepository<PurchaseOrderDetails, Long>{

	@Query(value = "select p from PurchaseOrderDetails p where p.purchaseOrder = ?1", nativeQuery = true)
	List<PurchaseOrderDetails> findAllByOrder(PurchaseOrder order);

}
