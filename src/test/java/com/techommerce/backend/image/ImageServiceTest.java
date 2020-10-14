package com.techommerce.backend.image;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.codec.multipart.SynchronossPartHttpMessageReader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.exception.TooManyImagesForAProductException;
import com.techommerce.backend.repository.ImageRepository;
import com.techommerce.backend.response.ImageResponse;
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
	public void testCreateFolderOfProductSuccessfully() {
		Product product = new Product();
		product.setProductCode("TESTCODE");
		imageService.createFolderOfProduct(product);
		File productFolder = new File(ImageServiceImpl.imageDirectory + "\\" + product.getProductCode());
		assertTrue(productFolder.exists());
		productFolder.delete();//Puede ir en el after
	}
	
	@Test
	public void testCreateImageIntoFolderSuccessfully() {
		MockMultipartFile aMockImage = new MockMultipartFile("a test image", "a product image", "image/png",
				"image-test".getBytes());
		Product product = new Product();
		product.setProductCode("TESTCODE");
		File productImage = new File(ImageServiceImpl.imageDirectory + "\\" + product.getProductCode() + "\\" + aMockImage.getOriginalFilename());
		File productFolder = new File(ImageServiceImpl.imageDirectory + "\\" + product.getProductCode());
		imageService.createFolderOfProduct(product);
		imageService.saveImageIntoFolder(aMockImage, product);
		assertTrue(productFolder.exists());
		assertTrue(productImage.exists());
		productImage.delete();
		productFolder.delete();
	}
	
	@Test
	public void testCreateImageInDatabaseSuccessfully() {
		MockMultipartFile aMockImage = new MockMultipartFile("a test image", "a product image", "image/png",
				"image-test".getBytes());
		Product product = new Product();
		product.setProductCode("TESTCODE");
		Boolean isMainImage = new Boolean(true);
		String filePath = Paths.get(ImageServiceImpl.imageDirectory, product.getProductCode(), aMockImage.getOriginalFilename()).toString();
		Image image = new Image(aMockImage.getOriginalFilename(), aMockImage.getContentType(), filePath, product, isMainImage);
		when(imageRepository.save(image)).thenReturn(image);
		Image imageAdded = imageService.saveImageInDatabase(product, aMockImage, isMainImage);
		assertEquals(imageAdded.getImagePath(), System.getProperty("user.dir") + "\\Image product\\TESTCODE\\a product image");
		assertTrue(imageAdded.getIsMainImage());
		assertEquals(imageAdded.getType(), "image/png");
		assertEquals(imageAdded.getProduct(), product);
		assertEquals(imageAdded.getName(), "a product image");
	}
	
	@Test
	public void testUploadImageSuccessfully() {
		MockMultipartFile aMockImage = new MockMultipartFile("a test image", "a product image", "image/png",
				"image-test".getBytes());
		Product product = new Product();
		product.setProductCode("TESTCODE");
		Boolean isMainImage = new Boolean(true);
		String filePath = Paths.get(ImageServiceImpl.imageDirectory, product.getProductCode(), aMockImage.getOriginalFilename()).toString();
		Image image = new Image(aMockImage.getOriginalFilename(), aMockImage.getContentType(), filePath, product, isMainImage);
		File productImage = new File(ImageServiceImpl.imageDirectory + "\\" + product.getProductCode() + "\\" + aMockImage.getOriginalFilename());
		File productFolder = new File(ImageServiceImpl.imageDirectory + "\\" + product.getProductCode());
		imageService.createFolderOfProduct(product);
		imageService.saveImageIntoFolder(aMockImage, product);
		List<Image> images = new ArrayList<>();
		images.add(image);
		List<ImageResponse> imagesResponse = imageService.convertImageList(images);
		assertFalse(imagesResponse.isEmpty());
		assertTrue(imagesResponse.size() > 0);
		assertTrue(imagesResponse.get(0).getImageBytes().length>0);
		assertTrue(imagesResponse.get(0).getIsMainimage());
		assertEquals(imagesResponse.get(0).getImageType(), "image/png");
		assertEquals(imagesResponse.get(0).getImageName(), "a product image");
		productImage.delete();
		productFolder.delete();
	}
	
	@Test
	public void testGetMissingImageSuccessfully() {
		ImageResponse image = imageService.getMissingImage();
		assertTrue(image.getImageBytes().length>0);
		assertEquals(image.getImageType(), "image/png");
		assertEquals(image.getImageName(), "missing_product.png");
	}
	
	@Test
	public void testSearchImageById() {
		Long id = new Long(1);
		Image anImage = new Image();
		anImage.setId(id);
		Optional<Image> optionalImage = Optional.of(anImage);
		when(imageRepository.findById(id)).thenReturn(optionalImage);
		Image image = imageService.searchImageById(id);
		assertTrue(image.getId().equals(id));
	}
}
