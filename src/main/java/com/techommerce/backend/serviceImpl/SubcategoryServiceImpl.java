package com.techommerce.backend.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.exception.AddingSubcategoryException;
import com.techommerce.backend.exception.EmptySubcategoryListException;
import com.techommerce.backend.exception.UpdatingSubcategoryException;
import com.techommerce.backend.repository.SubcategoryRepository;
import com.techommerce.backend.response.SubcategoryResponse;
import com.techommerce.backend.service.SubcategoryService;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Override
	public Subcategory addNewSubcategory(Subcategory subcategoryToAdd) {
		try {
			Subcategory subcategoryAdded = subcategoryRepository.save(subcategoryToAdd);
			return subcategoryAdded;
		} catch (DataIntegrityViolationException e) {
			throw new AddingSubcategoryException("Hubo un problema creadno la subcategoría", e);
		}
	}

	@Override
	public List<Subcategory> getAllSubcategories() {
		List<Subcategory> subcategoriesList = subcategoryRepository.findAll(Sort.by("subcategoryName"));
		return subcategoriesList;
	}

	@Override
	public List<SubcategoryResponse> buildSubcategoryResponseList(List<Subcategory> subcategories) {
		checkIfSubcategoryListIsEmpty(subcategories);
		List<SubcategoryResponse> subcategoriesResponses = subcategories.stream()
				.map(subcategory -> new SubcategoryResponse(subcategory)).collect(Collectors.toList());
		return subcategoriesResponses;
	}

	@Override
	public Subcategory updateSubcategory(Subcategory subcategoryToUpdate) {
		subcategoryCodeAndNameToUpperCase(subcategoryToUpdate);
		try {
			Subcategory subcategoryUpdated = subcategoryRepository.save(subcategoryToUpdate);
			return subcategoryUpdated;
		} catch (DataIntegrityViolationException e) {
			throw new UpdatingSubcategoryException("Hubo un problema actualizando la subcategoría", e);
		}
	}

	@Override
	public List<Subcategory> getActiveSubcategories() {
		List<Subcategory> subcategories = subcategoryRepository.findActiveSubcategories(Sort.by("subcategoryName")).stream()
				.filter(subcategory -> subcategory.getCategory().getCategoryState()).collect(Collectors.toList());
		return subcategories;
	}

	@Override
	public void subcategoryCodeAndNameToUpperCase(Subcategory subcategory) {
		subcategory.setSubcategoryCode(subcategory.getSubcategoryCode().toUpperCase());
		subcategory.setSubcategoryName(subcategory.getSubcategoryName().toUpperCase());
	}

	@Override
	public void checkIfSubcategoryListIsEmpty(List<Subcategory> subcategoriesList) {
		if (subcategoriesList.isEmpty())
			throw new EmptySubcategoryListException("No hay subcategorías cargadas");
	}

	@Override
	public List<Subcategory> getSubcategoryByCategory(Category category) {
		List<Subcategory> subcategoryListOfCategory = subcategoryRepository.findByCategory(category, Sort.by("subcategoryName"));
		return subcategoryListOfCategory;
	}

}
