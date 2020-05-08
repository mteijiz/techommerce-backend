package com.techommerce.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.request.AddBrandRequest;
import com.techommerce.backend.response.AddedBrandResponse;
import com.techommerce.backend.service.BrandService;

@RestController
@RequestMapping("brands")
@CrossOrigin(origins = "http://localhost:4200")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addBrand(@Valid @RequestBody AddBrandRequest brandRequest) {
		Brand brandToAdd = new Brand(brandRequest);
		Brand brandAdded = brandService.addBrand(brandToAdd);
		AddedBrandResponse brandAddedResponse = new AddedBrandResponse(brandAdded);
		return new ResponseEntity<AddedBrandResponse>(brandAddedResponse, HttpStatus.OK);
	}

}
