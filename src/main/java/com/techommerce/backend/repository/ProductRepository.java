package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByProductCategory(Category category);

	List<Product> findByProductSubcategory(Subcategory subcategory);


}
