package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.response.BrandResponse;

public interface BrandService {

	public abstract Brand addBrand(Brand brandToAdd);

	public abstract List<Brand> getAllBrands();

	public abstract List<BrandResponse> buildBrandsResponseList(List<Brand> brandsList);

	public abstract Brand updateBrand(Brand brandToUpdate);

	public abstract List<Brand> getActiveBrands();
	
	public abstract void checkIsBrandListEmpty(List<Brand> brandsList);
	
	public abstract void setBrandCodeAndNameToUppercase(Brand brand);
	
}
