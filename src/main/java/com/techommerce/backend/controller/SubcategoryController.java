package com.techommerce.backend.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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
	@RolesAllowed("admin")
	public ResponseEntity<?> addSubCategory(@RequestBody AddSubcategoryRequest subcategoryRequest){
		@Valid Subcategory subcategoryToAdd = new Subcategory(subcategoryRequest);
		subcategoryService.subcategoryCodeAndNameToUpperCase(subcategoryToAdd);
		Subcategory subcategoryAdded = subcategoryService.addNewSubcategory(subcategoryToAdd);
		SubcategoryResponse subcategoryResponse = new SubcategoryResponse(subcategoryAdded);
		return new ResponseEntity<SubcategoryResponse>(subcategoryResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	@RolesAllowed("admin")
	public ResponseEntity<?> getAllSubcategories(){
		List<Subcategory> subcategoriesList = subcategoryService.getAllSubcategories();
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
	
	@PutMapping("/update")
	@RolesAllowed("admin")
	public ResponseEntity<?> updateSubcategory(@RequestBody UpdateSubcategoryRequest subcategoryRequest){
		@Valid Subcategory subcategoryToUpdate = new Subcategory(subcategoryRequest);
		Subcategory subcategoryUpdated = subcategoryService.updateSubcategory(subcategoryToUpdate);
		SubcategoryResponse subcategoryUpdatedResponse = new SubcategoryResponse(subcategoryUpdated);
		return new ResponseEntity<SubcategoryResponse>(subcategoryUpdatedResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getActive")
	public ResponseEntity<?> getActiveSubcategories(){
		List<Subcategory> subcategoriesList = subcategoryService.getActiveSubcategories();
		List<SubcategoryResponse> subcategoriesResponseList = subcategoryService.buildSubcategoryResponseList(subcategoriesList);
		return new ResponseEntity<List<SubcategoryResponse>>(subcategoriesResponseList, HttpStatus.OK);
	}
	
}
