package com.techommerce.backend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.response.ImageResponse;

public interface ImageService {

	void uploadImages(MultipartFile[] images, Product productToAddimage);

	List<ImageResponse> convertImageList(List<Image> imagesOfProduct);

	ImageResponse getMissingImage();

	Image searchImageById(Long imageId);

	void uploadMainImages(MultipartFile[] image, Product productToAddimage);

	List<Image> searchImagesOfAProduct(Product product);
	
	void createFolderOfProduct(Product product);
	
	void saveImageIntoFolder(MultipartFile image, Product product);

	Image saveImageInDatabase(Product product, MultipartFile image, Boolean isMainImage);
	
	void checkIfMoreThanFourImagesForTheProductAreUploaded(MultipartFile[] images, Product product);
	
	void checkIfThereAreNoMainImage(Product product);
	
	void deleteImageFromFolder(Image image);
	
	void deleteImageFromDatabase(Image image);

	void setNewMainImage(Image image, List<Image> images);

	void checkIfThereIsMainImageChangesItToSecondaryImage(List<Image> images);
	
}
