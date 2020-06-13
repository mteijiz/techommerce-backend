package com.techommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;

public interface ImageRepository extends JpaRepository<Image, Long>{

	List<Image> findByProduct(Product product);

}
