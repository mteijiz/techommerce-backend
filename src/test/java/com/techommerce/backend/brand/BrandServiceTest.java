package com.techommerce.backend.brand;

import static org.junit.Assert.*;
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
import com.techommerce.backend.exception.EmptyBrandListException;
import com.techommerce.backend.exception.ExistingBrandException;
import com.techommerce.backend.repository.BrandRepository;
import com.techommerce.backend.response.BrandResponse;
import com.techommerce.backend.serviceImpl.BrandServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BrandServiceTest {

	@Mock
	private BrandRepository brandRepository;

	@InjectMocks
	private BrandServiceImpl brandService;

	@Test
	public void testAddNewBrandSuccessfully() {
		Brand brandToAdd = new Brand();
		brandToAdd.setBrandCode("testcode");
		brandToAdd.setBrandName("test name");
		brandToAdd.setBrandDescription("test description");
		brandToAdd.setBrandState(true);
		Brand brandToReturnFromSave = new Brand();
		brandToReturnFromSave.setBrandId(new Long(1));
		brandToReturnFromSave.setBrandCode("testcode".toUpperCase());
		brandToReturnFromSave.setBrandName("test name".toUpperCase());
		brandToReturnFromSave.setBrandDescription("test description");
		brandToReturnFromSave.setBrandState(true);
		when(brandRepository.save(brandToAdd)).thenReturn(brandToReturnFromSave);
		Brand brandAdded = brandService.addBrand(brandToAdd);
		assertEquals(brandToAdd.getBrandCode().toUpperCase(), brandAdded.getBrandCode());
		assertEquals(brandToAdd.getBrandName().toUpperCase(), brandAdded.getBrandName());
		assertEquals(brandToAdd.getBrandDescription(), brandAdded.getBrandDescription());
		assertEquals(brandToAdd.getBrandState(), brandAdded.getBrandState());
		assertNotNull(brandAdded.getBrandId());
		Assert.isInstanceOf(Long.class, brandAdded.getBrandId());
		verify(brandRepository, times(1)).save(brandToAdd);
	}

	@Test
	public void testAddNewBrandUpperCaseCodeAndName() {
		Brand brandToAdd = new Brand();
		brandToAdd.setBrandCode("testcode");
		brandToAdd.setBrandName("test name");
		brandToAdd.setBrandDescription("test description");
		brandToAdd.setBrandState(true);
		when(brandRepository.save(brandToAdd)).thenReturn(brandToAdd);
		Brand brandAdded = brandService.addBrand(brandToAdd);
		assertEquals(brandToAdd.getBrandCode().toUpperCase(), brandAdded.getBrandCode());
		assertEquals(brandToAdd.getBrandName().toUpperCase(), brandAdded.getBrandName());
		verify(brandRepository, times(1)).save(brandToAdd);
	}

	@Test
	public void testAddNewBrandFailsAndThrowExistingBrandException() {
		Brand brandToAdd = new Brand();
		brandToAdd.setBrandCode("testcode");
		brandToAdd.setBrandName("test name");
		brandToAdd.setBrandDescription("test description");
		brandToAdd.setBrandState(true);
		when(brandRepository.save(brandToAdd)).thenThrow(DataIntegrityViolationException.class);
		try {
			brandService.addBrand(brandToAdd);
		} catch (ExistingBrandException e) {
			assertEquals("Hubo un problema creando la marca", e.getMessage());
		}
	}
	
	@Test
	public void testGetAllTheBrandsWithBrandsInsideAList() {
		Brand aBrand = new Brand();
		aBrand.setBrandId(new Long(1));
		aBrand.setBrandCode("testcode".toUpperCase());
		aBrand.setBrandName("test name".toUpperCase());
		aBrand.setBrandDescription("test description");
		aBrand.setBrandState(true);
		Brand anotherBrand = new Brand();
		anotherBrand.setBrandId(new Long(1));
		anotherBrand.setBrandCode("testcode".toUpperCase());
		anotherBrand.setBrandName("test name".toUpperCase());
		anotherBrand.setBrandDescription("test description");
		anotherBrand.setBrandState(true);
		List<Brand> brands = new ArrayList<>();
		brands.add(aBrand);
		brands.add(anotherBrand);
		when(brandRepository.findAll()).thenReturn(brands);
		List<Brand> brandsReturn = brandService.getAllBrands();
		assertFalse(brandsReturn.isEmpty());
		assertEquals(2, brandsReturn.size());
		verify(brandRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetAllBrandListIsEmpty() {
		List<Brand> brands = new ArrayList<>();
		when(brandRepository.findAll()).thenReturn(brands);
		List<Brand> brandsReturn = brandService.getAllBrands();
		assertTrue(brandsReturn.isEmpty());
		assertEquals(0, brandsReturn.size());
		verify(brandRepository, times(1)).findAll();
	}
	
	@Test
	public void testBuildBrandResponseListSuccessfully() {
		Brand aBrand = new Brand();
		aBrand.setBrandId(new Long(1));
		aBrand.setBrandCode("testcode".toUpperCase());
		aBrand.setBrandName("test name".toUpperCase());
		aBrand.setBrandDescription("test description");
		aBrand.setBrandState(true);
		List<Brand> brands = new ArrayList<>();
		brands.add(aBrand);
		List<BrandResponse> brandsResponse = brandService.buildBrandsResponseList(brands);
		assertFalse(brandsResponse.isEmpty());
		assertEquals(brands.get(0).getBrandCode(), brandsResponse.get(0).getBrandCode());
		assertEquals(brands.get(0).getBrandName(), brandsResponse.get(0).getBrandName());
		assertEquals(brands.get(0).getBrandDescription(), brandsResponse.get(0).getBrandDescription());
		assertEquals(brands.get(0).getBrandId(), brandsResponse.get(0).getBrandId());
		assertEquals(brands.get(0).getBrandState(), brandsResponse.get(0).getBrandState());
	}

	@Test
	public void testBuildBrandResponseListThrowEmptyBrandListException() {
		List<Brand> brands = new ArrayList<>();
		try {
			List<BrandResponse> brandsResponse = brandService.buildBrandsResponseList(brands);
		}
		catch(EmptyBrandListException e) {
			assertEquals("No hay marcas cargadas", e.getMessage());
		}
	}
	
	@Test
	public void testUpdateBrandSuccessfully() {
		Brand brandToUpdate = new Brand();
		brandToUpdate.setBrandId(new Long(1));
		brandToUpdate.setBrandCode("testcode");
		brandToUpdate.setBrandName("test name");
		brandToUpdate.setBrandDescription("test description");
		brandToUpdate.setBrandState(true);
		when(brandRepository.save(brandToUpdate)).thenReturn(brandToUpdate);
		Brand brandUpdated = brandService.updateBrand(brandToUpdate);
		assertEquals(brandToUpdate.getBrandCode().toUpperCase(), brandUpdated.getBrandCode());
		assertEquals(brandToUpdate.getBrandName().toUpperCase(), brandUpdated.getBrandName());
		assertEquals(brandToUpdate.getBrandDescription(), brandUpdated.getBrandDescription());
		assertEquals(brandToUpdate.getBrandState(), brandUpdated.getBrandState());
		assertEquals(brandToUpdate.getBrandId(), brandUpdated.getBrandId());
		verify(brandRepository, times(1)).save(brandToUpdate);
	}

	@Test
	public void testUpdateBrandUpperCaseCodeAndName() {
		Brand brandToUpdate = new Brand();
		brandToUpdate.setBrandId(new Long(1));
		brandToUpdate.setBrandCode("testcode");
		brandToUpdate.setBrandName("test name");
		brandToUpdate.setBrandDescription("test description");
		brandToUpdate.setBrandState(true);
		when(brandRepository.save(brandToUpdate)).thenReturn(brandToUpdate);
		Brand brandUpdated = brandService.updateBrand(brandToUpdate);
		assertEquals(brandToUpdate.getBrandCode().toUpperCase(), brandUpdated.getBrandCode());
		assertEquals(brandToUpdate.getBrandName().toUpperCase(), brandUpdated.getBrandName());
		verify(brandRepository, times(1)).save(brandToUpdate);
	}

	@Test
	public void testUpdateBrandFailsAndThrowExistingBrandException() {
		Brand brandToUpdate = new Brand();
		brandToUpdate.setBrandId(new Long(1));
		brandToUpdate.setBrandCode("testcode");
		brandToUpdate.setBrandName("test name");
		brandToUpdate.setBrandDescription("test description");
		brandToUpdate.setBrandState(true);
		when(brandRepository.save(brandToUpdate)).thenThrow(DataIntegrityViolationException.class);
		try {
			brandService.updateBrand(brandToUpdate);
		} catch (ExistingBrandException e) {
			assertEquals("Hubo un problema actualizando la marca", e.getMessage());
		}
	}
	
//	@Test
//	public void testChangeBrandStateToActive() {
//		Brand aBrand = new Brand();
//		aBrand.setBrandState(false);
//		when(brandRepository.save(aBrand)).thenReturn(aBrand);
//		Brand brand = brandService.changeStatusOfBrand(aBrand);
//		assertTrue(brand.getBrandState());
//	}
//	
//	@Test
//	public void testChangeBrandStateToInactive() {
//		Brand aBrand = new Brand();
//		aBrand.setBrandState(true);
//		when(brandRepository.save(aBrand)).thenReturn(aBrand);
//		Brand brand = brandService.changeStatusOfBrand(aBrand);
//		assertFalse(brand.getBrandState());
//	}
	
	@Test
	public void testGetActiveBrandsSuccessfully() {
		Brand aBrand = new Brand();
		aBrand.setBrandId(new Long(1));
		aBrand.setBrandCode("testcode".toUpperCase());
		aBrand.setBrandName("test name".toUpperCase());
		aBrand.setBrandDescription("test description");
		aBrand.setBrandState(true);
		Brand anotherBrand = new Brand();
		anotherBrand.setBrandId(new Long(1));
		anotherBrand.setBrandCode("testcode".toUpperCase());
		anotherBrand.setBrandName("test name".toUpperCase());
		anotherBrand.setBrandDescription("test description");
		anotherBrand.setBrandState(true);
		List<Brand> brands = new ArrayList<>();
		brands.add(aBrand);
		brands.add(anotherBrand);
		when(brandRepository.findActiveBrands()).thenReturn(brands);
		List<Brand> brandsReturn = brandService.getActiveBrands();
		assertFalse(brandsReturn.isEmpty());
		brandsReturn.stream().forEach(brand -> assertTrue(brand.getBrandState()));
	}
	
//	@Test
//	public void testGetOppositeBrandStateFromActiveToInactive() {
//		Brand aBrand = new Brand();
//		aBrand.setBrandState(false);
//		Boolean brandState = brandService.getOppositeStateOfABrand(aBrand);
//		assertTrue(brandState);
//	}
//	
//	@Test
//	public void testGetOppositeBrandStateFromInactiveToActive() {
//		Brand aBrand = new Brand();
//		aBrand.setBrandState(true);
//		Boolean brandState = brandService.getOppositeStateOfABrand(aBrand);
//		assertFalse(brandState);
//	}
	
	@Test
	public void testBrandNameAndCodeToUpperCase() {
		Brand aBrand = new Brand();
		aBrand.setBrandCode("code");
		aBrand.setBrandName("name");
		brandService.setBrandCodeAndNameToUppercase(aBrand);
		assertEquals("code".toUpperCase(), aBrand.getBrandCode());
		assertEquals("name".toUpperCase(), aBrand.getBrandName());
	}
	
	@Test
	public void testCheckIfAListWithBrandsIsEmptyAndBrandListIsNotEmpty() {
		Brand aBrand = new Brand();
		aBrand.setBrandId(new Long(1));
		aBrand.setBrandCode("testcode".toUpperCase());
		aBrand.setBrandName("test name".toUpperCase());
		aBrand.setBrandDescription("test description");
		aBrand.setBrandState(true);
		Brand anotherBrand = new Brand();
		anotherBrand.setBrandId(new Long(1));
		anotherBrand.setBrandCode("testcode".toUpperCase());
		anotherBrand.setBrandName("test name".toUpperCase());
		anotherBrand.setBrandDescription("test description");
		anotherBrand.setBrandState(true);
		List<Brand> brands = new ArrayList<>();
		brands.add(aBrand);
		brands.add(anotherBrand);
		brandService.checkIsBrandListEmpty(brands);
		assertFalse(brands.isEmpty());
	}
	
	@Test
	public void testCheckIfAListWithBrandsIsEmptyAndBrandListIsEmpty() {
		List<Brand> brands = new ArrayList<>();
		try{brandService.checkIsBrandListEmpty(brands);}
		catch(EmptyBrandListException e) {
			assertEquals("No hay marcas cargadas", e.getMessage());
		}
	}
}
