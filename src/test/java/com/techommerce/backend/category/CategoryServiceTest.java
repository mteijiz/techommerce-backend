package com.techommerce.backend.category;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.techommerce.backend.entity.Category;
import com.techommerce.backend.exception.AddingCategoryException;
import com.techommerce.backend.exception.EmptyCategoryListException;
import com.techommerce.backend.exception.NotExistingCategoryException;
import com.techommerce.backend.exception.UpdatingCategoryException;
import com.techommerce.backend.repository.CategoryRepository;
import com.techommerce.backend.response.CategoryWithoutSubcategoriesResponse;
import com.techommerce.backend.serviceImpl.CategoryServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryServiceImpl categoryService;

	@Test
	public void testAddNewCategorySuccessfully() {
		Category categoryToAdd = new Category();
		categoryToAdd.setCategoryDescription("description");
		categoryToAdd.setCategoryState(true);
		categoryToAdd.setCategoryCode("code");
		categoryToAdd.setCategoryName("name");
		when(categoryRepository.save(categoryToAdd)).thenReturn(categoryToAdd);
		Category categoryToReturn = new Category();
		categoryToReturn.setCategoryCode("CODE");
		categoryToReturn.setCategoryName("NAME");
		categoryToReturn.setCategoryId(new Long(1));
		categoryToReturn.setCategoryState(true);
		categoryToReturn.setCategoryDescription("description");
		when(categoryRepository.save(categoryToAdd)).thenReturn(categoryToReturn);
		Category categoryAdded = categoryService.addCategory(categoryToAdd);
		assertEquals(categoryToAdd.getCategoryCode().toUpperCase(), categoryAdded.getCategoryCode());
		assertEquals(categoryToAdd.getCategoryName().toUpperCase(), categoryAdded.getCategoryName());
		assertEquals(categoryToAdd.getCategoryDescription(), categoryAdded.getCategoryDescription());
		assertEquals(categoryToAdd.getCategoryState(), categoryAdded.getCategoryState());
		assertNotNull(categoryAdded.getCategoryId());
		Assert.isInstanceOf(Long.class, categoryAdded.getCategoryId());
		verify(categoryRepository, times(1)).save(categoryToAdd);
	}

	@Test
	public void testAddNewCategoryUpperCaseNameAndCode() {
		Category categoryToAdd = new Category();
		categoryToAdd.setCategoryDescription("description");
		categoryToAdd.setCategoryState(true);
		categoryToAdd.setCategoryCode("code");
		categoryToAdd.setCategoryName("name");
		when(categoryRepository.save(categoryToAdd)).thenReturn(categoryToAdd);
		Category categoryAdded = categoryService.addCategory(categoryToAdd);
		assertEquals(categoryToAdd.getCategoryCode(), categoryAdded.getCategoryCode());
		assertEquals(categoryToAdd.getCategoryName(), categoryAdded.getCategoryName());
		verify(categoryRepository, times(1)).save(categoryToAdd);
	}

	@Test
	public void testAddNewCategoryThrowAddingCategoryException() {
		Category categoryToAdd = new Category();
		categoryToAdd.setCategoryDescription("description");
		categoryToAdd.setCategoryState(true);
		categoryToAdd.setCategoryCode("code");
		categoryToAdd.setCategoryName("name");
		when(categoryRepository.save(categoryToAdd)).thenThrow(DataIntegrityViolationException.class);
		try {
			Category categoryAdded = categoryService.addCategory(categoryToAdd);
		} catch (AddingCategoryException e) {
			assertEquals("Hubo un problema creando la categoría", e.getMessage());
			verify(categoryRepository, times(1)).save(categoryToAdd);
		}
	}

	@Test
	public void testGetAllCategories() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Category anotherCategory = new Category();
		anotherCategory.setCategoryId(new Long(2));
		anotherCategory.setCategoryCode("anothercode");
		anotherCategory.setCategoryName("anothername");
		anotherCategory.setCategoryDescription("another descrition");
		anotherCategory.setCategoryState(true);
		List<Category> categories = new ArrayList<>();
		categories.add(aCategory);
		categories.add(anotherCategory);
		when(categoryRepository.findAll()).thenReturn(categories);
		List<Category> categoriesList = categoryService.getAllCategories();
		assertNotNull(categoriesList);
		assertFalse(categoriesList.isEmpty());
		assertTrue(categoriesList.size() > 0);
		verify(categoryRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetAllCategoriesAndListIsEmpty() {
		List<Category> categories = new ArrayList<>();
		when(categoryRepository.findAll()).thenReturn(categories);
		List<Category> categoriesList = categoryService.getAllCategories();
		assertNotNull(categoriesList);
		assertTrue(categoriesList.isEmpty());
		verify(categoryRepository, times(1)).findAll();
	}

	@Test
	public void testBuildCategoryWithoutSubcategoriesResponseListSuccessfully() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		List<Category> categories = new ArrayList<>();
		categories.add(aCategory);
		List<CategoryWithoutSubcategoriesResponse> categoriesResponse = categoryService
				.buildCategoryResponseList(categories);
		assertNotNull(categoriesResponse);
		assertFalse(categoriesResponse.isEmpty());
		assertEquals(categories.get(0).getCategoryCode(), categoriesResponse.get(0).getCategoryCode());
		assertEquals(categories.get(0).getCategoryName(), categoriesResponse.get(0).getCategoryName());
		assertEquals(categories.get(0).getCategoryState(), categoriesResponse.get(0).getCategoryState());
		assertEquals(categories.get(0).getCategoryDescription(), categoriesResponse.get(0).getCategoryDescription());
		assertTrue(categories.get(0).getCategoryId().equals(categoriesResponse.get(0).getCategoryId()));
	}

	@Test
	public void testBuildCategoryWithoutSubcategoriesResponseListFail() {
		List<Category> categories = new ArrayList<>();
		try {
			List<CategoryWithoutSubcategoriesResponse> categoriesResponse = categoryService
					.buildCategoryResponseList(categories);
		} catch (EmptyCategoryListException e) {
			assertEquals("No hay categorías cargadas", e.getMessage());
		}
	}

	@Test
	public void testUpdateCategorySuccessfully() {
		Category categoryToUpdate = new Category();
		categoryToUpdate.setCategoryId(new Long(1));
		categoryToUpdate.setCategoryDescription("description");
		categoryToUpdate.setCategoryState(true);
		categoryToUpdate.setCategoryCode("code");
		categoryToUpdate.setCategoryName("name");
		when(categoryRepository.save(categoryToUpdate)).thenReturn(categoryToUpdate);
		Category categoryToReturn = new Category();
		categoryToReturn.setCategoryCode("CODE");
		categoryToReturn.setCategoryName("NAME");
		categoryToReturn.setCategoryId(new Long(1));
		categoryToReturn.setCategoryState(true);
		categoryToReturn.setCategoryDescription("description");
		when(categoryRepository.save(categoryToUpdate)).thenReturn(categoryToReturn);
		Category categoryUpdated = categoryService.updateCategory(categoryToUpdate);
		assertEquals(categoryToUpdate.getCategoryCode().toUpperCase(), categoryUpdated.getCategoryCode());
		assertEquals(categoryToUpdate.getCategoryName().toUpperCase(), categoryUpdated.getCategoryName());
		assertEquals(categoryToUpdate.getCategoryDescription(), categoryUpdated.getCategoryDescription());
		assertEquals(categoryToUpdate.getCategoryState(), categoryUpdated.getCategoryState());
		assertNotNull(categoryUpdated.getCategoryId());
		Assert.isInstanceOf(Long.class, categoryUpdated.getCategoryId());
		verify(categoryRepository, times(1)).save(categoryToUpdate);
	}

	@Test
	public void testUpdateCategoryUpperCaseNameAndCode() {
		Category categoryToUpdate = new Category();
		categoryToUpdate.setCategoryId(new Long(1));
		categoryToUpdate.setCategoryDescription("description");
		categoryToUpdate.setCategoryState(true);
		categoryToUpdate.setCategoryCode("code");
		categoryToUpdate.setCategoryName("name");
		when(categoryRepository.save(categoryToUpdate)).thenReturn(categoryToUpdate);
		Category categoryUpdated = categoryService.updateCategory(categoryToUpdate);
		assertEquals("code".toUpperCase(), categoryUpdated.getCategoryCode());
		assertEquals("name".toUpperCase(), categoryUpdated.getCategoryName());
		verify(categoryRepository, times(1)).save(categoryToUpdate);
	}

	@Test
	public void testUpdateCategoryThrowAddingCategoryException() {
		Category categoryToUpdate = new Category();
		categoryToUpdate.setCategoryId(new Long(1));
		categoryToUpdate.setCategoryDescription("description");
		categoryToUpdate.setCategoryState(true);
		categoryToUpdate.setCategoryCode("code");
		categoryToUpdate.setCategoryName("name");
		when(categoryRepository.save(categoryToUpdate)).thenThrow(DataIntegrityViolationException.class);
		try {
			Category categoryUpdated = categoryService.updateCategory(categoryToUpdate);
		} catch (UpdatingCategoryException e) {
			assertEquals("Hubo un problema actualizando la categoría", e.getMessage());
			verify(categoryRepository, times(1)).save(categoryToUpdate);
		}
	}

	@Test
	public void testSearchCategoryByIdAndFindACategory() {
		Category category = new Category();
		Long categoryId = new Long(1);
		category.setCategoryId(new Long(1));
		category.setCategoryDescription("description");
		category.setCategoryState(true);
		category.setCategoryCode("code");
		category.setCategoryName("name");
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
		Category categoryReturn = categoryService.searchCategoryById(categoryId);
		assertEquals(category.getCategoryName(), categoryReturn.getCategoryName());
		assertEquals(category.getCategoryCode(), categoryReturn.getCategoryCode());
		assertEquals(category.getCategoryDescription(), categoryReturn.getCategoryDescription());
		assertEquals(category.getCategoryState(), categoryReturn.getCategoryState());
		assertTrue(category.getCategoryId().equals(categoryReturn.getCategoryId()));
		verify(categoryRepository, times(1)).findById(categoryId);
	}

	@Test
	public void testSearchCategoryByIdAndNotFindACategory() {
		Category category = new Category();
		Long categoryId = new Long(1);
		category.setCategoryId(new Long(1));
		category.setCategoryDescription("description");
		category.setCategoryState(true);
		category.setCategoryCode("code");
		category.setCategoryName("name");
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		try {
			Category categoryReturn = categoryService.searchCategoryById(categoryId);
		} catch (NotExistingCategoryException e) {
			assertEquals("No se encontro una categoría con esta clave", e.getMessage());
			verify(categoryRepository, times(1)).findById(categoryId);
		}
	}

	@Test
	public void testGetAllActivesCategoriesAndFindActiveCategories() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		Category anotherCategory = new Category();
		anotherCategory.setCategoryId(new Long(2));
		anotherCategory.setCategoryCode("anothercode");
		anotherCategory.setCategoryName("anothername");
		anotherCategory.setCategoryDescription("another descrition");
		anotherCategory.setCategoryState(true);
		List<Category> categories = new ArrayList<>();
		categories.add(aCategory);
		categories.add(anotherCategory);
		when(categoryRepository.findActiveBrands()).thenReturn(categories);
		List<Category> categoriesList = categoryService.getActiveBrands();
		categoriesList.stream().forEach(category -> assertTrue(category.getCategoryState()));
	}

	@Test
	public void testUpperCaseCategoryNameAndCode() {
		Category aCategory = new Category();
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		categoryService.categoryCodaAndNameToUpperCase(aCategory);
		assertEquals("acode".toUpperCase(), aCategory.getCategoryCode());
		assertEquals("aname".toUpperCase(), aCategory.getCategoryName());
	}

	@Test
	public void testCheckIfCategoryListIsEmptyAndIsEmpty() {
		Category aCategory = new Category();
		aCategory.setCategoryId(new Long(1));
		aCategory.setCategoryCode("acode");
		aCategory.setCategoryName("aname");
		aCategory.setCategoryDescription("a descrition");
		aCategory.setCategoryState(true);
		List<Category> categories = new ArrayList<>();
		categories.add(aCategory);
		categoryService.checkIfCategoryListIsEmpty(categories);
		assertFalse(categories.isEmpty());
	}

	@Test
	public void testCheckIfCategoryListIsEmptyAndIsNotEmpty() {
		List<Category> categories = new ArrayList<>();
		try {
			categoryService.checkIfCategoryListIsEmpty(categories);
		} catch (EmptyCategoryListException e) {
			assertEquals("No hay categorías cargadas", e.getMessage());
		}
	}
}
