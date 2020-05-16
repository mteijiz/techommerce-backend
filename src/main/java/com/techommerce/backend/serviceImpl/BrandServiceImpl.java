package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.exception.EmptyBrandListException;
import com.techommerce.backend.exception.ExistingBrandException;
import com.techommerce.backend.exception.InvalidRequestBrandException;
import com.techommerce.backend.exception.NotExistingBrandException;
import com.techommerce.backend.repository.BrandRepository;
import com.techommerce.backend.response.BrandResponse;
import com.techommerce.backend.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand addBrand(Brand brandToAdd) {
		brandCodeAndNameUppercase(brandToAdd);
		try {
			Brand brandCreated = brandRepository.save(brandToAdd);
			return brandCreated;
		} catch (DataIntegrityViolationException e) {
			throw new ExistingBrandException(e.getMessage(), e);
		} catch (ConstraintViolationException e) {
			throw new InvalidRequestBrandException(e.getMessage(), e);
		}
	}

	@Override
	public List<Brand> getAllBrands() {
		List<Brand> brandsList = brandRepository.findAll();
		return brandsList;
	}

	@Override
	public List<BrandResponse> buildBrandsResponseList(List<Brand> brandsList) {
		checkIsBrandListEmpty(brandsList);
		List<BrandResponse> brandsResponseList = new ArrayList<BrandResponse>();
		for (Brand brand : brandsList) {
			BrandResponse brandResponseToAdd = new BrandResponse(brand);
			brandsResponseList.add(brandResponseToAdd);
		}
		return brandsResponseList;
	}

	public void checkIsBrandListEmpty(List<Brand> brandsList) {
		if (brandsList.isEmpty())
			throw new EmptyBrandListException("There are no brands");
	}

	@Override
	public void deleteBrandById(Long brandId) {
		try {
			brandRepository.deleteById(brandId);
		} catch (EmptyResultDataAccessException e) {
			throw new NotExistingBrandException(e.getMessage(), e);
		}
	}

	@Override
	public Brand updateBrand(Brand brandToUpdate) {
		brandCodeAndNameUppercase(brandToUpdate);
		Brand brandUpdated = brandRepository.save(brandToUpdate);
		return brandUpdated;
	}
	
	public void brandCodeAndNameUppercase(Brand brand) {
		brand.setBrandCode(brand.getBrandCode().toUpperCase());
		brand.setBrandName(brand.getBrandName().toUpperCase());
	}

	@Override
	public Brand changeStatusOfBrand(Brand brandToChangeStatus) {
		if(brandToChangeStatus.getBrandState()) 
			brandToChangeStatus.setBrandState(false);
		else 
			brandToChangeStatus.setBrandState(true);
		Brand brandChanged = brandRepository.save(brandToChangeStatus);
		return brandChanged;
	}

}
