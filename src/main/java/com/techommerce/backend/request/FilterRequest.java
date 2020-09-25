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

	private List<Brand> brands;
	private List<Category> categories;
	private List<Subcategory> subcategories;
	private Float minPrice;
	private Float maxPrice;
	
}
