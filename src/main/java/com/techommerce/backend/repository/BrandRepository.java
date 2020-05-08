package com.techommerce.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techommerce.backend.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}
