package com.techommerce.backend.image;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.exception.TooManyImagesForAProductException;
import com.techommerce.backend.repository.ImageRepository;
import com.techommerce.backend.serviceImpl.ImageServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
class ImageServiceTest {

	@Mock
	private ImageRepository imageRepository;

	@InjectMocks
	private ImageServiceImpl imageService;

	@Test
	public void testcheckIfMoreThanFourImagesForTheProductAreUploadedAndIsLowerThan4Images() {
		MockMultipartFile image = new MockMultipartFile("a test image", "a product image", "image/png",
				"image-test".getBytes());
		MockMultipartFile[] imagesFile = new MockMultipartFile[1];
		imagesFile[0] = image;
		Product product = new Product();
		List<Image> images = new ArrayList<>();
		Image anImage = new Image();
		Image anotherImage = new Image();
		images.add(anImage);
		images.add(anotherImage);
		when(imageRepository.findByProduct(product)).thenReturn(images);
		imageService.checkIfMoreThanFourImagesForTheProductAreUploaded(imagesFile, product);
		assertNotNull(imagesFile);
		assertNotNull(images);
		assertTrue(images.size() + imagesFile.length < 5);
	}

	@Test
	public void testcheckIfMoreThanFourImagesForTheProductAreUploadedAndThrowTooManyImagesForAProductException() {
		MockMultipartFile aMockImage = new MockMultipartFile("a test image", "a product image", "image/png",
				"image-test".getBytes());
		MockMultipartFile anotherMockImage = new MockMultipartFile("a test image", "a product image", "image/png",
				"image-test".getBytes());
		MockMultipartFile[] imagesFile = new MockMultipartFile[2];
		imagesFile[0] = aMockImage;
		imagesFile[1] = anotherMockImage;
		Product product = new Product();
		List<Image> images = new ArrayList<>();
		Image anImage = new Image();
		Image anotherImage = new Image();
		Image otherImage = new Image();
		images.add(anImage);
		images.add(anotherImage);
		when(imageRepository.findByProduct(product)).thenReturn(images);
		try {
			imageService.checkIfMoreThanFourImagesForTheProductAreUploaded(imagesFile, product);
		} catch (TooManyImagesForAProductException e) {
			assertEquals("Un producto puede tener solo 4 imagenes y tiene " + images.size(), e.getMessage());
		}
	}
	
	@Test
	public void testSaveImageIntoFolderSuccessfully() {
		MockMultipartFile aMockImage = new MockMultipartFile("a test image", "a product image", "image/png",
				"image-test".getBytes());
		Product aProduct = new Product();
		aProduct.setProductId(new Long(1));
	}
	
	@Test
	public void testCreateFolderOfProduct() {
		Product product = new Product();
		product.setProductCode("TESTCODE");
		File folderFile = new File(ImageServiceImpl.imageDirectory + "\\TESTCODE");
		imageService.createFolderOfProduct(folderFile);
	}
}
