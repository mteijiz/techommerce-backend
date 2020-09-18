package com.techommerce.backend.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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
import com.techommerce.backend.response.CategoryWithoutSubcategoriesResponse;
import com.techommerce.backend.service.CategoryService;

@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add")
	@RolesAllowed("admin")
	public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest request) {
		@Valid Category categoryToAdd = new Category(request);
		categoryService.categoryCodaAndNameToUpperCase(categoryToAdd);
		Category categoryAdded = categoryService.addCategory(categoryToAdd);
		CategoryWithoutSubcategoriesResponse categoryResponse = new CategoryWithoutSubcategoriesResponse(categoryAdded);
		return new ResponseEntity<CategoryWithoutSubcategoriesResponse>(categoryResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	@RolesAllowed("admin")
	public ResponseEntity<?> getAllCategories() {
		List<Category> categoryList = categoryService.getAllCategories();
		List<CategoryWithoutSubcategoriesResponse> categoryResponseList = categoryService.buildCategoryResponseList(categoryList);
		return new ResponseEntity<List<CategoryWithoutSubcategoriesResponse>>(categoryResponseList, HttpStatus.OK);
	}

	@PutMapping("/update")
	@RolesAllowed("admin")
	public ResponseEntity<?> updateCategory(@RequestBody UpdateCategoryRequest request) {
		@Valid Category categoryToUpdate = new Category(request);
		categoryService.categoryCodaAndNameToUpperCase(categoryToUpdate);
		Category categoryUpdated = categoryService.updateCategory(categoryToUpdate);
		CategoryWithoutSubcategoriesResponse categoryResponse = new CategoryWithoutSubcategoriesResponse(categoryUpdated);
		return new ResponseEntity<CategoryWithoutSubcategoriesResponse>(categoryResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getActive")
	public ResponseEntity<?> getActiveCategories() {
		List<Category> categoryList = categoryService.getActiveBrands();
		List<CategoryWithoutSubcategoriesResponse> categoryResponseList = categoryService.buildCategoryResponseList(categoryList);
		return new ResponseEntity<List<CategoryWithoutSubcategoriesResponse>>(categoryResponseList, HttpStatus.OK);
	}

}
