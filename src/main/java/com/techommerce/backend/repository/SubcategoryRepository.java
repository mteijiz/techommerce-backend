package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

	List<Subcategory> findByCategory(Category category);

	@Query(value = "select * from ecommerce.subcategories where subcategory_state = true;", nativeQuery = true)
	List<Subcategory> findActiveSubcategories();

}