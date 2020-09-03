package com.techommerce.backend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.response.ImageResponse;
import com.techommerce.backend.response.ProductResponse;

public interface ImageService {

	void uploadImages(MultipartFile[] images, Product productToAddimage);

	List<Image> getImagesOfProduct(Product product);

	List<ImageResponse> convertImageList(List<Image> imagesOfProduct);

	ImageResponse getMissingImage();

	Image searchImageById(Long imageId);

	void convertImageListToResponse(ProductResponse auxProductResponse, Product product);

	void uploadMainImages(MultipartFile[] image, Product productToAddimage);

	void checkIfImageListIsEmptyAndGetMissingImagen(List<ImageResponse> imageResponseList);

	List<Image> getMainImagesOfProduct(Product product);

	List<Image> searchImagesOfAProduct(Product product);
	
	void createFolderOfProduct(Product product);
	
	void saveImageIntoFolder(MultipartFile image, Product product);

	void saveImageInDatabaseAndFolder(Product product, MultipartFile image, Boolean isMainImage);
	
	void checkIfMoreThanFourImagesForTheProductAreUploaded(MultipartFile[] images, Product product);
	
	void checkIfThereAreNoMainImage(Product product);
	
	void deleteImageFromFolder(Image image);
	
	void deleteImageFromDatabase(Image image);
	
}
