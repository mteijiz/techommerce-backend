package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.repository.BrandRepository;
import com.techommerce.backend.response.BrandResponse;
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

	@Override
	public List<Brand> getAllBrands() {
		List<Brand> brandsList = brandRepository.findAll();
		return brandsList;
	}

	@Override
	public List<BrandResponse> buildBrandsResponseList(List<Brand> brandsList) {
		// checkIsBrandListEmpty(brandsList);
		List<BrandResponse> brandsResponseList = new ArrayList<BrandResponse>();
		for (Brand brand : brandsList) {
			BrandResponse brandResponseToAdd = new BrandResponse(brand);
			brandsResponseList.add(brandResponseToAdd);
		}
		return brandsResponseList;
	}

}
