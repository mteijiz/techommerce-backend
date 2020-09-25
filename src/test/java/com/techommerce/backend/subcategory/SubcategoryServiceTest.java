package com.techommerce.backend.subcategory;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.exception.AddingSubcategoryException;
import com.techommerce.backend.exception.EmptySubcategoryListException;
import com.techommerce.backend.exception.UpdatingSubcategoryException;
import com.techommerce.backend.repository.SubcategoryRepository;
import com.techommerce.backend.response.SubcategoryResponse;
import com.techommerce.backend.serviceImpl.SubcategoryServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SubcategoryServiceTest {

	@Mock
	private SubcategoryRepository subcategoryRepository;

	@InjectMocks
	private SubcategoryServiceImpl subcategoryService;

	@Test
	public void testAddNewSubcategorySuccessfully() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		Subcategory subcategoryToReturn = new Subcategory();
		subcategoryToReturn.setSubcategoryId(new Long(1));
		subcategoryToReturn.setSubcategoryCode("ACODE");
		subcategoryToReturn.setSubcategoryName("ANAME");
		subcategoryToReturn.setSubcategoryDescription("a description");
		subcategoryToReturn.setSubcategoryState(true);
		subcategoryToReturn.setCategory(aCategory);
		when(subcategoryRepository.save(aSubcategory)).thenReturn(subcategoryToReturn);
		Subcategory subcategoryAdded = subcategoryService.addNewSubcategory(aSubcategory);
		assertEquals(aSubcategory.getSubcategoryCode(), subcategoryAdded.getSubcategoryCode());
		assertEquals(aSubcategory.getSubcategoryName(), subcategoryAdded.getSubcategoryName());
		assertEquals(aSubcategory.getSubcategoryDescription(), subcategoryAdded.getSubcategoryDescription());
		assertEquals(aSubcategory.getSubcategoryState(), subcategoryAdded.getSubcategoryState());
		assertNotNull(subcategoryAdded.getSubcategoryId());
		Assert.isInstanceOf(Long.class, subcategoryAdded.getSubcategoryId());
		assertTrue(aSubcategory.getCategory().getCategoryId().equals(subcategoryAdded.getCategory().getCategoryId()));
		assertEquals(aSubcategory.getCategory().getCategoryCode(), subcategoryAdded.getCategory().getCategoryCode());
		assertEquals(aSubcategory.getCategory().getCategoryName(), subcategoryAdded.getCategory().getCategoryName());
		assertEquals(aSubcategory.getCategory().getCategoryDescription(),
				subcategoryAdded.getCategory().getCategoryDescription());
		assertEquals(aSubcategory.getCategory().getCategoryState(), subcategoryAdded.getCategory().getCategoryState());
		verify(subcategoryRepository, times(1)).save(aSubcategory);
	}

	@Test
	public void testAddNewSubcategoryUpperCaseCodeAndName() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		when(subcategoryRepository.save(aSubcategory)).thenReturn(aSubcategory);
		Subcategory subcategoryAdded = subcategoryService.addNewSubcategory(aSubcategory);
		assertEquals("acode".toUpperCase(), aSubcategory.getSubcategoryCode());
		assertEquals("aname".toUpperCase(), aSubcategory.getSubcategoryName());
		verify(subcategoryRepository, times(1)).save(aSubcategory);
	}

	@Test
	public void testAddNewBrandThrowAddingSubcategoryException() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		when(subcategoryRepository.save(aSubcategory)).thenThrow(DataIntegrityViolationException.class);
		try {
			Subcategory subcategoryAdded = subcategoryService.addNewSubcategory(aSubcategory);
		} catch (AddingSubcategoryException e) {
			assertEquals("Hubo un problema creadno la subcategoría", e.getMessage());
			verify(subcategoryRepository, times(1)).save(aSubcategory);
		}
	}

	@Test
	public void testGetAllSubcategoriesAndListHasSubcategories() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		Subcategory anotherSubcategory = new Subcategory();
		anotherSubcategory.setSubcategoryCode("aCode");
		anotherSubcategory.setSubcategoryName("aName");
		anotherSubcategory.setSubcategoryDescription("a description");
		anotherSubcategory.setSubcategoryState(true);
		anotherSubcategory.setCategory(aCategory);
		List<Subcategory> subcategories = new ArrayList<>();
		subcategories.add(aSubcategory);
		subcategories.add(anotherSubcategory);
		when(subcategoryRepository.findAll()).thenReturn(subcategories);
		List<Subcategory> subcategoriesList = subcategoryService.getAllSubcategories();
		assertNotNull(subcategoriesList);
		assertFalse(subcategoriesList.isEmpty());
		assertTrue(subcategoriesList.size() > 0);
		verify(subcategoryRepository, times(1)).findAll();
	}

	@Test
	public void testGetAllSubcategoriesAndListDoesNotHasSubcategories() {
		List<Subcategory> subcategories = new ArrayList<>();
		when(subcategoryRepository.findAll()).thenReturn(subcategories);
		List<Subcategory> subcategoriesList = subcategoryService.getAllSubcategories();
		assertNotNull(subcategoriesList);
		assertTrue(subcategoriesList.isEmpty());
		verify(subcategoryRepository, times(1)).findAll();
	}

	@Test
	public void testBuildSubcategoriesResponseListWithSubcategories() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		List<Subcategory> subcategories = new ArrayList<>();
		subcategories.add(aSubcategory);
		List<SubcategoryResponse> subcategoriesList = subcategoryService.buildSubcategoryResponseList(subcategories);
		assertNotNull(subcategoriesList);
		assertFalse(subcategoriesList.isEmpty());
		assertTrue(subcategoriesList.size() > 0);
		assertTrue(subcategories.get(0).getSubcategoryId().equals(subcategoriesList.get(0).getSubcategoryId()));
		assertEquals(subcategories.get(0).getSubcategoryCode(), subcategoriesList.get(0).getSubcategoryCode());
		assertEquals(subcategories.get(0).getSubcategoryName(), subcategoriesList.get(0).getSubcategoryName());
		assertEquals(subcategories.get(0).getSubcategoryDescription(),
				subcategoriesList.get(0).getSubcategoryDescription());
		assertEquals(subcategories.get(0).getSubcategoryState(), subcategoriesList.get(0).getSubcategoryState());
		assertEquals(subcategories.get(0).getCategory().getCategoryCode(),
				subcategoriesList.get(0).getCategory().getCategoryCode());
		assertEquals(subcategories.get(0).getCategory().getCategoryName(),
				subcategoriesList.get(0).getCategory().getCategoryName());
		assertEquals(subcategories.get(0).getCategory().getCategoryState(),
				subcategoriesList.get(0).getCategory().getCategoryState());
		assertEquals(subcategories.get(0).getCategory().getCategoryDescription(),
				subcategoriesList.get(0).getCategory().getCategoryDescription());
		assertTrue(subcategories.get(0).getCategory().getCategoryId()
				.equals(subcategoriesList.get(0).getCategory().getCategoryId()));
	}

	@Test
	public void testBuildSubcategoriesResponseListThrowsEmptySubcategoryListException() {
		List<Subcategory> subcategories = new ArrayList<>();
		try {
			List<SubcategoryResponse> subcategoriesList = subcategoryService
					.buildSubcategoryResponseList(subcategories);
		} catch (EmptySubcategoryListException e) {
			assertEquals("No hay subcategorías cargadas", e.getMessage());
		}
	}

	@Test
	public void testUpdateSubcategorySuccessfully() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		Subcategory subcategoryToReturn = new Subcategory();
		subcategoryToReturn.setSubcategoryId(new Long(1));
		subcategoryToReturn.setSubcategoryCode("ACODE");
		subcategoryToReturn.setSubcategoryName("ANAME");
		subcategoryToReturn.setSubcategoryDescription("a description");
		subcategoryToReturn.setSubcategoryState(true);
		subcategoryToReturn.setCategory(aCategory);
		when(subcategoryRepository.save(aSubcategory)).thenReturn(subcategoryToReturn);
		Subcategory subcategoryAdded = subcategoryService.updateSubcategory(aSubcategory);
		assertEquals(aSubcategory.getSubcategoryCode(), subcategoryAdded.getSubcategoryCode());
		assertEquals(aSubcategory.getSubcategoryName(), subcategoryAdded.getSubcategoryName());
		assertEquals(aSubcategory.getSubcategoryDescription(), subcategoryAdded.getSubcategoryDescription());
		assertEquals(aSubcategory.getSubcategoryState(), subcategoryAdded.getSubcategoryState());
		assertTrue(aSubcategory.getSubcategoryId().equals(subcategoryAdded.getSubcategoryId()));
		assertTrue(aSubcategory.getCategory().getCategoryId().equals(subcategoryAdded.getCategory().getCategoryId()));
		assertEquals(aSubcategory.getCategory().getCategoryCode(), subcategoryAdded.getCategory().getCategoryCode());
		assertEquals(aSubcategory.getCategory().getCategoryName(), subcategoryAdded.getCategory().getCategoryName());
		assertEquals(aSubcategory.getCategory().getCategoryDescription(),
				subcategoryAdded.getCategory().getCategoryDescription());
		assertEquals(aSubcategory.getCategory().getCategoryState(), subcategoryAdded.getCategory().getCategoryState());
		verify(subcategoryRepository, times(1)).save(aSubcategory);
	}

	@Test
	public void testUpdateSubcategoryUpperCaseCodeAndName() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		when(subcategoryRepository.save(aSubcategory)).thenReturn(aSubcategory);
		Subcategory subcategoryAdded = subcategoryService.updateSubcategory(aSubcategory);
		assertEquals("acode".toUpperCase(), aSubcategory.getSubcategoryCode());
		assertEquals("aname".toUpperCase(), aSubcategory.getSubcategoryName());
		verify(subcategoryRepository, times(1)).save(aSubcategory);
	}

	@Test
	public void testUpdateBrandThrowAddingSubcategoryException() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		when(subcategoryRepository.save(aSubcategory)).thenThrow(DataIntegrityViolationException.class);
		try {
			Subcategory subcategoryAdded = subcategoryService.updateSubcategory(aSubcategory);
		} catch (UpdatingSubcategoryException e) {
			assertEquals("Hubo un problema actualizando la subcategoría", e.getMessage());
			verify(subcategoryRepository, times(1)).save(aSubcategory);
		}
	}

	@Test
	public void testGetActiveSubcategoriesWithSubcategoriesInTheList() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		Subcategory anotherSubcategory = new Subcategory();
		anotherSubcategory.setSubcategoryId(new Long(1));
		anotherSubcategory.setSubcategoryCode("aCode");
		anotherSubcategory.setSubcategoryName("aName");
		anotherSubcategory.setSubcategoryDescription("a description");
		anotherSubcategory.setSubcategoryState(true);
		anotherSubcategory.setCategory(aCategory);
		List<Subcategory> subcategories = new ArrayList<>();
		subcategories.add(aSubcategory);
		subcategories.add(anotherSubcategory);
		when(subcategoryRepository.findActiveSubcategories(Sort.by("subcategoryName"))).thenReturn(subcategories);
		List<Subcategory> subcategoriesList = subcategoryService.getActiveSubcategories();
		assertFalse(subcategoriesList.isEmpty());
		assertTrue(subcategoriesList.size() > 0);
		subcategoriesList.stream().forEach(subcategory -> assertTrue(subcategory.getSubcategoryState()));
	}

	@Test
	public void testUpperCaseSubcategoryCodeAndName() {
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		subcategoryService.subcategoryCodeAndNameToUpperCase(aSubcategory);
		assertEquals("aCode".toUpperCase(), aSubcategory.getSubcategoryCode());
		assertEquals("aName".toUpperCase(), aSubcategory.getSubcategoryName());
	}

	@Test
	public void testCheckIfSubcategoryListIsEmptyAndIsNotEmpty() {
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		List<Subcategory> subcategories = new ArrayList<>();
		subcategories.add(aSubcategory);
		subcategoryService.checkIfSubcategoryListIsEmpty(subcategories);
		assertFalse(subcategories.isEmpty());
		assertTrue(subcategories.size() > 0);
	}

	@Test
	public void testCheckIfSubcategoryListIsEmptyAndIsEmpty() {
		List<Subcategory> subcategories = new ArrayList<>();
		try {
			subcategoryService.checkIfSubcategoryListIsEmpty(subcategories);
		} catch (EmptySubcategoryListException e) {
			assertEquals("No hay subcategorías cargadas", e.getMessage());
		}
	}
	
	@Test
	public void testGetSubcategoryByCategory() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Subcategory aSubcategory = new Subcategory();
		aSubcategory.setSubcategoryId(new Long(1));
		aSubcategory.setSubcategoryCode("aCode");
		aSubcategory.setSubcategoryName("aName");
		aSubcategory.setSubcategoryDescription("a description");
		aSubcategory.setSubcategoryState(true);
		aSubcategory.setCategory(aCategory);
		List<Subcategory> subcategories = new ArrayList<>();
		subcategories.add(aSubcategory);
		when(subcategoryRepository.findByCategory(aCategory, Sort.by("subcategoryName"))).thenReturn(subcategories);
		assertNotNull(subcategories);
		assertFalse(subcategories.isEmpty());
		assertTrue(subcategories.size()>0);
	}
}
