package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	@Query(value = "SELECT b FROM Brand b WHERE b.brandState = true")
	List<Brand> findActiveBrands();

}
