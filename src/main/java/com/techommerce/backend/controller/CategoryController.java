package com.techommerce.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.request.AddCategoryRequest;
import com.techommerce.backend.request.UpdateCategoryRequest;
import com.techommerce.backend.response.CategoryResponse;
import com.techommerce.backend.service.CategoryService;
import com.techommerce.backend.service.SubcategoryService;

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

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllCategories() {
		List<Category> categoryList = categoryService.getAll();
		List<CategoryResponse> categoryResponseList = categoryService.buildCategoryResponseList(categoryList);
		return new ResponseEntity<List<CategoryResponse>>(categoryResponseList, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody UpdateCategoryRequest categoryRequest) {
		Category categoryToUpdate = new Category(categoryRequest);
		Category categoryUpdated = categoryService.updateCategory(categoryToUpdate);
		CategoryResponse categoryResponse = new CategoryResponse(categoryUpdated);
		return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
	}

	@PutMapping("/updateState")
	public ResponseEntity<?> updateCategoryState(@RequestBody UpdateCategoryRequest categoryRequest) {
		Category categoryToUpdateState = new Category(categoryRequest);
		Category categoryUpdated = categoryService.updateCategoryState(categoryToUpdateState);
		CategoryResponse categoryResponse = new CategoryResponse(categoryUpdated);
		return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
	}

}
