package com.techommerce.backend.service;

import java.util.List;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.response.SubcategoryResponse;

public interface SubcategoryService {

	Subcategory add(Subcategory subcategoryToAdd);

	List<Subcategory> getAll();

	List<SubcategoryResponse> buildSubcategoryResponseList(List<Subcategory> subcategoriesList);

	Subcategory updateState(Subcategory subcategoryToUpdateState);

	Subcategory updateSubcategory(Subcategory subcategoryToUpdate);

	void changingActiveStateOfSubcategoriesBelongToCategory(Category categoryToUpdateState);

	void changingInactiveStateOfSubcategoriesBelongToCategory(Category categoryToUpdateState);

	List<Subcategory> getSubcategoryByCategory(Category category);

}
