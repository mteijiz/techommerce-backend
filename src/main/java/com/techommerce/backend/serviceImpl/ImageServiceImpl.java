package com.techommerce.backend.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.repository.ImageRepository;
import com.techommerce.backend.response.ImageResponse;
import com.techommerce.backend.response.ProductResponse;
import com.techommerce.backend.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ImageRepository imageRepository;
	
	
	public static String imageDirectory = System.getProperty("user.dir") + "\\Image product";
	
	
	private static String missingImageDirectory = System.getProperty("user.dir")
			+ "\\Missing product image\\missing_product.png";
	
	@Override
	public void uploadImages(MultipartFile[] images, Product product) {
		// TODO Auto-generated method stub
		for(MultipartFile image : images) {
			saveImageInDatabaseAndFolder(product, image);
		}
	}

	public void saveImageInDatabaseAndFolder(Product product, MultipartFile image) {
		saveImageIntoFolder(image);
		String filePath = Paths.get(imageDirectory, image.getOriginalFilename()).toString();
		File imageFile = new File(filePath);
		Image productImageToUpload = new Image(image.getOriginalFilename(), image.getContentType(), imageFile.getPath(), product);
		imageRepository.save(productImageToUpload);
	}

	private void saveImageIntoFolder(MultipartFile image) {
		// TODO Auto-generated method stub
		Path filePath = Paths.get(imageDirectory, image.getOriginalFilename());
		try {
			OutputStream os = Files.newOutputStream(filePath);
			os.write(image.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Image> getImagesOfProduct(Product product) {
		// TODO Auto-generated method stub
		List<Image> images = imageRepository.findByProduct(product);
		return images;
	}

	@Override
	public List<ImageResponse> convertImageList(List<Image> imagesOfProduct) {
		// TODO Auto-generated method stub
		List<ImageResponse> imageResponses = new ArrayList<>();
		for(Image image : imagesOfProduct) {
			ImageResponse ir = new ImageResponse(image);
			imageResponses.add(ir);
		}
		return imageResponses;
	}

	@Override
	public ImageResponse getMissingImage() {
		// TODO Auto-generated method stub
		Path filePath = Paths.get(missingImageDirectory);
		String name = filePath.getFileName().toString();
		String contentType = "image/png";
		byte[] missingProductBytes = null;
		ImageResponse missingProductImage = null;
		try {
			missingProductBytes = Files.readAllBytes(filePath);
			missingProductImage = new ImageResponse(name, contentType, missingProductBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return missingProductImage;
	}

	@Override
	public Image searchImageById(Long imageId) {
		// TODO Auto-generated method stub
		Image image = imageRepository.findById(imageId).get();
		return image;
	}

	@Override
	public void deleteImage(Image image) {
		// TODO Auto-generated method stub
		deleteImageFromDatabase(image);
		deleteImageFromFolder(image);
	}

	public void deleteImageFromFolder(Image image) {
		// TODO Auto-generated method stub
		File imageFile = new File(imageDirectory + image.getName());
		imageFile.delete();
	}

	public void deleteImageFromDatabase(Image image) {
		// TODO Auto-generated method stub
		imageRepository.delete(image);
	}

	@Override
	public void convertImageListToResponse(ProductResponse auxProductResponse, Product product) {
		// TODO Auto-generated method stub
		if(product.getProductImages().isEmpty()) {
			ImageResponse missingImage = getMissingImage();
			auxProductResponse.getProductImage().add(missingImage);
		}
		else {
			auxProductResponse.setProductImage(convertImageList(product.getProductImages()));
		}
	}

}
