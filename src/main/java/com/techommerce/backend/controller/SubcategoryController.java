package com.techommerce.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.request.AddSubcategoryRequest;
import com.techommerce.backend.request.SearchByCategoryRequest;
import com.techommerce.backend.request.UpdateSubcategoryRequest;
import com.techommerce.backend.response.SubcategoryResponse;
import com.techommerce.backend.service.CategoryService;
import com.techommerce.backend.service.SubcategoryService;

@RestController
@RequestMapping("subcategories")
@CrossOrigin(origins = "http://localhost:4200")
public class SubcategoryController {
	
	@Autowired
	private SubcategoryService subcategoryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addSubCategory(@Valid @RequestBody AddSubcategoryRequest subcategoryRequest){
		Subcategory subcategoryToAdd = new Subcategory(subcategoryRequest);
		Subcategory subcategoryAdded = subcategoryService.add(subcategoryToAdd);
		SubcategoryResponse subcategoryResponse = new SubcategoryResponse(subcategoryAdded);
		return new ResponseEntity<SubcategoryResponse>(subcategoryResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllSubcategories(){
		List<Subcategory> subcategoriesList = subcategoryService.getAll();
		List<SubcategoryResponse> subcategoriesResponseList = subcategoryService.buildSubcategoryResponseList(subcategoriesList);
		return new ResponseEntity<List<SubcategoryResponse>>(subcategoriesResponseList, HttpStatus.OK);
	}
	
	@GetMapping("/getByCategory/{categoryId}")
	public ResponseEntity<?> getSubcategoriesByCategory(@PathVariable Long categoryId){
		Category category = categoryService.searchCategoryById(categoryId);
		List<Subcategory> subcategoriesList = subcategoryService.getSubcategoryByCategory(category);
		List<SubcategoryResponse> subcategoryResponsesList = subcategoryService.buildSubcategoryResponseList(subcategoriesList);
		return new ResponseEntity<List<SubcategoryResponse>>(subcategoryResponsesList, HttpStatus.OK);
	}
	
	@PutMapping("/updateState")
	public ResponseEntity<?> updateSubcategoryState(@Valid @RequestBody UpdateSubcategoryRequest subcategoryRequest){
		Subcategory subcategoryToUpdateState = new Subcategory(subcategoryRequest);
		Subcategory subcategoryStateUpdated = subcategoryService.updateState(subcategoryToUpdateState);
		SubcategoryResponse subcategoryUpdatedResponse = new SubcategoryResponse(subcategoryStateUpdated);
		return new ResponseEntity<SubcategoryResponse>(subcategoryUpdatedResponse, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateSubcategory(@Valid @RequestBody UpdateSubcategoryRequest subcategoryRequest){
		Subcategory subcategoryToUpdate = new Subcategory(subcategoryRequest);
		Subcategory subcategoryUpdated = subcategoryService.updateSubcategory(subcategoryToUpdate);
		SubcategoryResponse subcategoryUpdatedResponse = new SubcategoryResponse(subcategoryUpdated);
		return new ResponseEntity<SubcategoryResponse>(subcategoryUpdatedResponse, HttpStatus.OK);
	}
	
}
