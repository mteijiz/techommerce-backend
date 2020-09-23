package com.techommerce.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techommerce.backend.entity.PurchaseOrderDetails;

public interface PurchaseOrderDetailsRepository extends JpaRepository<PurchaseOrderDetails, Long>{

}
