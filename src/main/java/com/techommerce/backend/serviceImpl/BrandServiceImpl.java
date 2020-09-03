package com.techommerce.backend.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.exception.EmptyBrandListException;
import com.techommerce.backend.exception.ExistingBrandException;
import com.techommerce.backend.repository.BrandRepository;
import com.techommerce.backend.response.BrandResponse;
import com.techommerce.backend.service.BrandService;
import com.techommerce.backend.service.ProductService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private ProductService productService;

	@Override
	public Brand addBrand(Brand brandToAdd) {
		try {
			Brand brandCreated = brandRepository.save(brandToAdd);
			return brandCreated;
		} catch (DataIntegrityViolationException e) {
//			checkIfBrandCodeExists(e, brandToAdd);
//			checkIfBrandNameExists(e, brandToAdd);
			throw new ExistingBrandException("Hubo un problema creando la marca", e);
		} 
	}

	@Override
	public List<Brand> getAllBrands() {
		List<Brand> brandsList = brandRepository.findAll();
		return brandsList;
	}

	@Override
	public List<BrandResponse> buildBrandsResponseList(List<Brand> brands) {
		checkIsBrandListEmpty(brands);
		List<BrandResponse> brandsResponses = brands.stream()
				.map(brand -> new BrandResponse(brand))
				.collect(Collectors.toList());
		return brandsResponses;
	}

	@Override
	public Brand updateBrand(Brand brandToUpdate) {
		try {
			Brand brandUpdated = brandRepository.save(brandToUpdate);
			return brandUpdated;
		} catch (DataIntegrityViolationException e) {
//			checkIfBrandCodeExists(e, brandToUpdate);
//			checkIfBrandNameExists(e, brandToUpdate);
			throw new ExistingBrandException("Hubo un problema actualizando la marca", e);
		} 
	}

	@Override
	public List<Brand> getActiveBrands() {
		List<Brand> brandsList = brandRepository.findActiveBrands();
		return brandsList;
	}
	
	@Override
	public void checkIsBrandListEmpty(List<Brand> brandsList) {
		if (brandsList.isEmpty())
			throw new EmptyBrandListException("No hay marcas cargadas");
	}
	
	@Override
	public void setBrandCodeAndNameToUppercase(Brand brand) {
		brand.setBrandCode(brand.getBrandCode().toUpperCase());
		brand.setBrandName(brand.getBrandName().toUpperCase());
	}
	
	public void checkIfBrandNameExists(DataIntegrityViolationException e, Brand brand) {
		if(e.getCause().getCause().getMessage().contains("(brand_name)=(" + brand.getBrandName() + ") already exists"))
			throw new ExistingBrandException("El nombre de la marca (" + brand.getBrandName() + ") ya existe", e);
	}
	
	public void checkIfBrandCodeExists(DataIntegrityViolationException e, Brand brand) {
		if(e.getCause().getCause().getMessage().contains("(brand_code)=(" + brand.getBrandCode() + ") already exists"))
			throw new ExistingBrandException("El código de la marca (" + brand.getBrandCode() + ") ya existe", e);
	}

}
