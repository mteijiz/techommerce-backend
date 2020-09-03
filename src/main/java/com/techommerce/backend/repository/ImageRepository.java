package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;

public interface ImageRepository extends JpaRepository<Image, Long>{

	List<Image> findByProduct(Product product);

	@Query(value = "select i from Image i where i.product = ?1 and i.isMainImage = false")
	List<Image> findByProductAndIsNotMain(Product product);
	
	@Query(value = "select i from Image i where i.product = ?1 and i.isMainImage = true")
	List<Image> findByProductAndIsMain(Product product);

}
