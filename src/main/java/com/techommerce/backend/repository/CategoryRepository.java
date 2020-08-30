package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "select * from ecommerce.categories where category_state = true", nativeQuery = true)
	List<Category> findActiveBrands();

}
