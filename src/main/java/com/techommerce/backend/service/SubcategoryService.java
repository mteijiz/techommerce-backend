package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.response.SubcategoryResponse;

public interface SubcategoryService {

	Subcategory addNewSubcategory(Subcategory subcategoryToAdd);

	List<Subcategory> getAllSubcategories();

	List<SubcategoryResponse> buildSubcategoryResponseList(List<Subcategory> subcategoriesList);

	Subcategory updateSubcategory(Subcategory subcategoryToUpdate);

	List<Subcategory> getSubcategoryByCategory(Category category);

	List<Subcategory> getActiveSubcategories();
	
	void subcategoryCodeAndNameToUpperCase(Subcategory subcategory);
	
	void checkIfSubcategoryListIsEmpty(List<Subcategory> subcategoriesList);

}
