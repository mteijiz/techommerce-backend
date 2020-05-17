package com.techommerce.backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.repository.CategoryRepository;
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

}
