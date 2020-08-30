package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;

public interface ImageRepository extends JpaRepository<Image, Long>{

	List<Image> findByProduct(Product product);

	@Query(value = "select * from ecommerce.product_images pi2 where product_id = ?1 and is_main_image = false", nativeQuery = true)
	List<Image> findByProductAndIsNotMain(Long productId);
	
	@Query(value = "select * from ecommerce.product_images pi2 where product_id = ?1 and is_main_image = true", nativeQuery = true)
	List<Image> findByProductAndIsMain(Long productId);

}
