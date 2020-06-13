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

	void deleteImage(Image imageToUpdate);

	void convertImageListToResponse(ProductResponse auxProductResponse, Product product);


}
