package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.exception.EmptyCategoryListException;
import com.techommerce.backend.repository.CategoryRepository;
import com.techommerce.backend.response.CategoryResponse;
import com.techommerce.backend.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category categoryToAdd) {
		// TODO Auto-generated method stub
		categoryCodaAndNameToUpperCase(categoryToAdd);
		Category categoryAdded = categoryRepository.save(categoryToAdd);
		return categoryAdded;
	}

	private void categoryCodaAndNameToUpperCase(Category categoryToAdd) {
		// TODO Auto-generated method stub
		categoryToAdd.setCategoryCode(categoryToAdd.getCategoryCode().toUpperCase());
		categoryToAdd.setCategoryName(categoryToAdd.getCategoryName().toUpperCase());
	}

	@Override
	public List<Category> getAll() {
		List<Category> categoryList = categoryRepository.findAll();
		return categoryList;
	}

	@Override
	public List<CategoryResponse> buildCategoryResponseList(List<Category> categoryList) {
		checkIfCategoryListIsEmpty(categoryList);
		List<CategoryResponse> categoryListResponse = new ArrayList<CategoryResponse>();
		for(Category category : categoryList) {
			CategoryResponse auxCategoryResponse = new CategoryResponse(category);
			categoryListResponse.add(auxCategoryResponse);
		}
		return categoryListResponse;
	}

	private void checkIfCategoryListIsEmpty(List<Category> categoryList) {
		// TODO Auto-generated method stub
		if(categoryList.isEmpty())
			throw new EmptyCategoryListException("There are no categories");
	}

}
