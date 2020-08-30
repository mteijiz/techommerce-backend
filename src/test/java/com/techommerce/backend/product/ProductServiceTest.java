package com.techommerce.backend.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.exception.ExistingProductException;
import com.techommerce.backend.repository.ProductRepository;
import com.techommerce.backend.serviceImpl.ProductServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Test
	public void testAddNewProductSuccessfully() {
		Brand aBrand = new Brand();
		aBrand.setBrandId(new Long(1));
		aBrand.setBrandCode("ACODE");
		aBrand.setBrandName("ANAME");
		aBrand.setBrandDescription("test description");
		aBrand.setBrandState(true);
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("ACODE");
		aCategory.setCategoryName("ANAME");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("ACODE");
		aSubcategory.setSubcategoryName("ANAME");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		Product aProduct = new Product();
		aProduct.setProductCode("aCode");
		aProduct.setProductName("aName");
		aProduct.setProductDescription("a description");
		aProduct.setProductPrice(new Float(10.0));
		aProduct.setProductQuantity(new Integer(20));
		aProduct.setProductState(true);
		aProduct.setProductBrand(aBrand);
		aProduct.setProductCategory(aCategory);
		aProduct.setProductSubcategory(aSubcategory);
		Product aProductToReturn = new Product();
		aProductToReturn.setProductId(new Long(1));
		aProductToReturn.setProductCode("ACODE");
		aProductToReturn.setProductName("ANAME");
		aProductToReturn.setProductDescription("a description");
		aProductToReturn.setProductPrice(new Float(10.0));
		aProductToReturn.setProductQuantity(new Integer(20));
		aProductToReturn.setProductState(true);
		aProductToReturn.setProductBrand(aBrand);
		aProductToReturn.setProductCategory(aCategory);
		aProductToReturn.setProductSubcategory(aSubcategory);
		when(productRepository.save(aProduct)).thenReturn(aProductToReturn);
		Product productAdded = productService.addProduct(aProduct);
		assertEquals(aProduct.getProductCode(), productAdded.getProductCode());
		assertEquals(aProduct.getProductName(), productAdded.getProductName());
		assertEquals(aProduct.getProductDescription(), productAdded.getProductDescription());
		assertEquals(aProduct.getProductPrice(), productAdded.getProductPrice());
		assertEquals(aProduct.getProductQuantity(), productAdded.getProductQuantity());
		assertEquals(aProduct.getProductState(), productAdded.getProductState());
		assertNotNull(productAdded.getProductId());
		Assert.isInstanceOf(Long.class, productAdded.getProductId());
		assertTrue(productAdded.getProductBrand().getBrandId().equals(aProduct.getProductBrand().getBrandId()));
		assertEquals(aProduct.getProductBrand().getBrandCode(), productAdded.getProductBrand().getBrandCode());
		assertEquals(aProduct.getProductBrand().getBrandName(), productAdded.getProductBrand().getBrandName());
		assertEquals(aProduct.getProductBrand().getBrandDescription(), productAdded.getProductBrand().getBrandDescription());
		assertEquals(aProduct.getProductBrand().getBrandState(), productAdded.getProductBrand().getBrandState());
		assertTrue(productAdded.getProductCategory().getCategoryId().equals(aProduct.getProductCategory().getCategoryId()));
		assertEquals(aProduct.getProductCategory().getCategoryCode(), productAdded.getProductCategory().getCategoryCode());
		assertEquals(aProduct.getProductCategory().getCategoryName(), productAdded.getProductCategory().getCategoryName());
		assertEquals(aProduct.getProductCategory().getCategoryDescription(), productAdded.getProductCategory().getCategoryDescription());
		assertEquals(aProduct.getProductCategory().getCategoryState(), productAdded.getProductCategory().getCategoryState());
		assertTrue(productAdded.getProductSubcategory().getSubcategoryId().equals(aProduct.getProductSubcategory().getSubcategoryId()));
		assertEquals(aProduct.getProductSubcategory().getSubcategoryCode(), productAdded.getProductSubcategory().getSubcategoryCode());
		assertEquals(aProduct.getProductSubcategory().getSubcategoryName(), productAdded.getProductSubcategory().getSubcategoryName());
		assertEquals(aProduct.getProductSubcategory().getSubcategoryDescription(), productAdded.getProductSubcategory().getSubcategoryDescription());
		assertEquals(aProduct.getProductSubcategory().getSubcategoryState(), productAdded.getProductSubcategory().getSubcategoryState());
	}
	
	@Test
	public void testAddNewProductUpperCaseCodeAndName() {
		Product aProduct = new Product();
		aProduct.setProductCode("aCode");
		aProduct.setProductName("aName");
		when(productRepository.save(aProduct)).thenReturn(aProduct);
		Product productAdded = productService.addProduct(aProduct);
		assertEquals("aCode".toUpperCase(), productAdded.getProductCode());
		assertEquals("aName".toUpperCase(), productAdded.getProductName());
	}
	
	@Test
	public void testAddNewProductThrowExistingProductException() {
		Brand aBrand = new Brand();
		aBrand.setBrandId(new Long(1));
		aBrand.setBrandCode("ACODE");
		aBrand.setBrandName("ANAME");
		aBrand.setBrandDescription("test description");
		aBrand.setBrandState(true);
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("ACODE");
		aCategory.setCategoryName("ANAME");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("ACODE");
		aSubcategory.setSubcategoryName("ANAME");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		Product aProduct = new Product();
		aProduct.setProductCode("aCode");
		aProduct.setProductName("aName");
		aProduct.setProductDescription("a description");
		aProduct.setProductPrice(new Float(10.0));
		aProduct.setProductQuantity(new Integer(20));
		aProduct.setProductState(true);
		aProduct.setProductBrand(aBrand);
		aProduct.setProductCategory(aCategory);
		aProduct.setProductSubcategory(aSubcategory);
		when(productRepository.save(aProduct)).thenThrow(DataIntegrityViolationException.class);
		try {
			Product productAdded = productService.addProduct(aProduct);
		}
		catch(ExistingProductException e) {
			assertEquals("Hubo un problema creando el producto", e.getMessage());
			verify(productRepository, times(1)).save(aProduct);
		}
	}
	
	@Test
	public void testUpperCaseCodeAndNameOfAProduct() {
		Product aProduct = new Product();
		aProduct.setProductCode("aCode");
		aProduct.setProductName("aName");
		productService.productCodeAndNameUppercase(aProduct);
		assertEquals("aCode".toUpperCase(), aProduct.getProductCode());
		assertEquals("aName".toUpperCase(), aProduct.getProductName());
	}
	
	@Test
	public void testGetAllProductsAndListIsNotEmpty() {
		Brand aBrand = new Brand();
		aBrand.setBrandId(new Long(1));
		aBrand.setBrandCode("ACODE");
		aBrand.setBrandName("ANAME");
		aBrand.setBrandDescription("test description");
		aBrand.setBrandState(true);
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("ACODE");
		aCategory.setCategoryName("ANAME");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("ACODE");
		aSubcategory.setSubcategoryName("ANAME");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		Product aProduct = new Product();
		aProduct.setProductId(new Long(1));
		aProduct.setProductCode("aCode");
		aProduct.setProductName("aName");
		aProduct.setProductDescription("a description");
		aProduct.setProductPrice(new Float(10.0));
		aProduct.setProductQuantity(new Integer(20));
		aProduct.setProductState(true);
		aProduct.setProductBrand(aBrand);
		aProduct.setProductCategory(aCategory);
		aProduct.setProductSubcategory(aSubcategory);
		Product anotherProduct = new Product();
		anotherProduct.setProductId(new Long(2));
		anotherProduct.setProductCode("aCode");
		anotherProduct.setProductName("aName");
		anotherProduct.setProductDescription("a description");
		anotherProduct.setProductPrice(new Float(10.0));
		anotherProduct.setProductQuantity(new Integer(20));
		anotherProduct.setProductState(true);
		anotherProduct.setProductBrand(aBrand);
		anotherProduct.setProductCategory(aCategory);
		anotherProduct.setProductSubcategory(aSubcategory);
		List<Product> products = new ArrayList<>();
		products.add(aProduct);
		products.add(anotherProduct);
		when(productRepository.findAll()).thenReturn(products);
		List<Product> productsList = productService.getAllProducts();
		assertNotNull(productsList);
		assertFalse(productsList.isEmpty());
		assertTrue(productsList.size()>0);
		verify(productRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetAllProductsAndListIsEmpty() {
		List<Product> products = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(products);
		List<Product> productsList = productService.getAllProducts();
		assertNotNull(productsList);
		assertTrue(productsList.isEmpty());
		verify(productRepository, times(1)).findAll();
	}
	
	
}
