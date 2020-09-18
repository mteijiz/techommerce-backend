package com.techommerce.backend.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.request.AddBrandRequest;
import com.techommerce.backend.request.UpdateBrandRequest;
import com.techommerce.backend.response.BrandResponse;
import com.techommerce.backend.service.BrandService;

@RestController
@RequestMapping("brands")
@CrossOrigin(origins = "http://localhost:4200")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@PostMapping("/add")
	@RolesAllowed("admin")
	public ResponseEntity<?> add(@RequestBody AddBrandRequest request) {
		@Valid Brand brandToAdd = new Brand(request);
		brandService.setBrandCodeAndNameToUppercase(brandToAdd);
		Brand brandAdded = brandService.addBrand(brandToAdd);
		BrandResponse brandAddedResponse = new BrandResponse(brandAdded);
		return new ResponseEntity<BrandResponse>(brandAddedResponse, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	@RolesAllowed("admin")
	public ResponseEntity<?> getAll() {
		List<Brand> brandsList = brandService.getAllBrands();
		List<BrandResponse> brandResponsesList = brandService.buildBrandsResponseList(brandsList);
		return new ResponseEntity<List<BrandResponse>>(brandResponsesList, HttpStatus.OK);
	}

	@PutMapping("/update")
	@RolesAllowed("admin")
	public ResponseEntity<?> updateBrand(@RequestBody UpdateBrandRequest request) {
		@Valid Brand brandToUpdate = new Brand(request);
		brandService.setBrandCodeAndNameToUppercase(brandToUpdate);
		Brand brandUpdated = brandService.updateBrand(brandToUpdate);
		BrandResponse brandUpdatedResponse = new BrandResponse(brandUpdated);
		return new ResponseEntity<BrandResponse>(brandUpdatedResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getActive")
	public ResponseEntity<?> getActiveBrand() {
		List<Brand> brandsList = brandService.getActiveBrands();
		List<BrandResponse> brandResponsesList = brandService.buildBrandsResponseList(brandsList);
		return new ResponseEntity<List<BrandResponse>>(brandResponsesList, HttpStatus.OK);
	}

}
