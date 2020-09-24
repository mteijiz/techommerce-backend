package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value = "select p from Product p where p.productState = true")
	List<Product> findAllByStatus(Sort sort);

	@Query(value = "select p from Product p where p.productBrand in :brands and p.productCategory in :categories and p.productSubcategory in :subcategories")
	List<Product> findProductInBrand(@Param("brands") List<Brand> brands, @Param("categories") List<Category> categories, @Param("subcategories") List<Subcategory> subcategories);


}
