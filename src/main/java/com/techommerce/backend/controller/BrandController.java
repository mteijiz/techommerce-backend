package com.techommerce.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.techommerce.backend.response.AddedBrandResponse;
import com.techommerce.backend.response.BrandResponse;
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
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllBrand(){
		List<Brand> brandsList = brandService.getAllBrands();
		List<BrandResponse> brandResponsesList = brandService.buildBrandsResponseList(brandsList);
		return new ResponseEntity<List<BrandResponse>>(brandResponsesList, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{brandId}")
	public ResponseEntity<?> deleteBrandById(@PathVariable Long brandId){
		brandService.deleteBrandById(brandId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateBrand(@Valid @RequestBody UpdateBrandRequest brandRequest){
		Brand brandToUpdate = new Brand(brandRequest);
		Brand brandUpdated = brandService.updateBrand(brandToUpdate);
		BrandResponse brandUpdatedResponse = new BrandResponse(brandUpdated);
		return new ResponseEntity<BrandResponse>(brandUpdatedResponse, HttpStatus.OK);
	}
	
	@PutMapping("/changeStatus")
	public ResponseEntity<?> changeStatus(@Valid @RequestBody UpdateBrandRequest brandRequest){
		Brand brandToChangeStatus = new Brand(brandRequest);
		Brand brandWithChangedStatus = brandService.changeStatusOfBrand(brandToChangeStatus);
		BrandResponse brandChanged = new BrandResponse(brandWithChangedStatus);
		return new ResponseEntity<BrandResponse>(brandChanged, HttpStatus.OK);
	}

}
