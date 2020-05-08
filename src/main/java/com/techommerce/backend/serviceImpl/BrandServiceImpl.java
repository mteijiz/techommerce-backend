package com.techommerce.backend.serviceImpl;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.repository.BrandRepository;
import com.techommerce.backend.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand addBrand(Brand brandToAdd) {
		Brand brandCreated = brandRepository.save(brandToAdd);
		return brandCreated;
	}

}
