package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByProductCategory(Category category);

	List<Product> findByProductSubcategory(Subcategory subcategory);

	List<Product> findByProductBrand(Brand brand);

	@Query(value = "select * from ecommerce.products where product_state = true",  nativeQuery = true)
	List<Product> findAllByStatus();


}
