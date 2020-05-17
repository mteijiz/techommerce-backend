package com.techommerce.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.request.AddCategoryRequest;
import com.techommerce.backend.response.CategoryResponse;
import com.techommerce.backend.service.CategoryService;

@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add")
	public ResponseEntity<?> addCategory(@Valid @RequestBody AddCategoryRequest categoryRequest) {
		Category categoryToAdd = new Category(categoryRequest);
		Category categoryAdded = categoryService.addCategory(categoryToAdd);
		CategoryResponse categoryResponse = new CategoryResponse(categoryAdded);
		return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
	}

}
