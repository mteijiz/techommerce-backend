package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.repository.SubcategoryRepository;
import com.techommerce.backend.request.SearchByCategoryRequest;
import com.techommerce.backend.response.SubcategoryResponse;
import com.techommerce.backend.service.ProductService;
import com.techommerce.backend.service.SubcategoryService;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	@Autowired
	private ProductService productService;

	@Override
	public Subcategory add(Subcategory subcategoryToAdd) {
		// TODO Auto-generated method stub
		subcategoryCodeAndNameToUpperCase(subcategoryToAdd);
		Subcategory subcategoryAdded = subcategoryRepository.save(subcategoryToAdd);
		return subcategoryAdded;
	}

	private void subcategoryCodeAndNameToUpperCase(Subcategory subcategory) {
		// TODO Auto-generated method stub
		subcategory.setSubcategoryCode(subcategory.getSubcategoryCode().toUpperCase());
		subcategory.setSubcategoryName(subcategory.getSubcategoryName().toUpperCase());
	}

	@Override
	public List<Subcategory> getAll() {
		// TODO Auto-generated method stub
		List<Subcategory> subcategoriesList = subcategoryRepository.findAll();
		return subcategoriesList;
	}

	@Override
	public List<SubcategoryResponse> buildSubcategoryResponseList(List<Subcategory> subcategoriesList) {
		// TODO Auto-generated method stub
		List<SubcategoryResponse> subcategoryResponsesList = new ArrayList<SubcategoryResponse>();
		for (Subcategory subcategory : subcategoriesList) {
			SubcategoryResponse auxSubcategory = new SubcategoryResponse(subcategory);
			subcategoryResponsesList.add(auxSubcategory);
		}
		return subcategoryResponsesList;
	}

	@Override
	public Subcategory updateState(Subcategory subcategoryToUpdateState) {
		// TODO Auto-generated method stub
		if (subcategoryToUpdateState.getSubcategoryState()) {
			subcategoryToUpdateState.setSubcategoryState(false);
			productService.changingActiveStateOfProductsBelongToCategory(subcategoryToUpdateState);
		}
		else {
			subcategoryToUpdateState.setSubcategoryState(true);
			productService.changingInactiveStateOfProductsBelongToCategory(subcategoryToUpdateState);
		}
		Subcategory subcategoryStateUpdated = subcategoryRepository.save(subcategoryToUpdateState);
		return subcategoryStateUpdated;
	}

	@Override
	public Subcategory updateSubcategory(Subcategory subcategoryToUpdate) {
		// TODO Auto-generated method stub
		subcategoryCodeAndNameToUpperCase(subcategoryToUpdate);
		Subcategory subcategoryUpdated = subcategoryRepository.save(subcategoryToUpdate);
		return subcategoryUpdated;
	}

	@Override
	public void changingActiveStateOfSubcategoriesBelongToCategory(Category category) {
		// TODO Auto-generated method stub
		List<Subcategory> subcategoryListOfCategory = getSubcategoryByCategory(category);
		for (Subcategory subcategory : subcategoryListOfCategory) {
			if (subcategory.getSubcategoryState())
				subcategory.setSubcategoryState(false);
			subcategoryRepository.save(subcategory);
		}
	}

	public List<Subcategory> getSubcategoryByCategory(Category category) {
		List<Subcategory> subcategoryListOfCategory = subcategoryRepository.findByCategory(category);
		return subcategoryListOfCategory;
	}

	@Override
	public void changingInactiveStateOfSubcategoriesBelongToCategory(Category category) {
		List<Subcategory> subcategoryListOfCategory = getSubcategoryByCategory(category);
		for (Subcategory subcategory : subcategoryListOfCategory) {
			if (!subcategory.getSubcategoryState())
				subcategory.setSubcategoryState(true);
			subcategoryRepository.save(subcategory);
		}
	}

	

}
