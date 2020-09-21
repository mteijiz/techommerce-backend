package com.techommerce.backend.request;

import java.util.ArrayList;
import java.util.List;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterRequest {

	private List<Brand> brands = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();
	private List<Subcategory> subcategories = new ArrayList<>();
	private Float minPrice;
	private Float maxPrice;
	
}
