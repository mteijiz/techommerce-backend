package com.techommerce.backend.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.exception.ErrorSavingImageIntoAFolder;
import com.techommerce.backend.exception.ExistsMainImageOfAProductException;
import com.techommerce.backend.exception.TooManyImagesForAProductException;
import com.techommerce.backend.repository.ImageRepository;
import com.techommerce.backend.response.ImageResponse;
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
		for(MultipartFile image : images) {
			createFolderOfProduct(product);
			saveImageIntoFolder(image, product);
			saveImageInDatabase(product, image, false);
		}
	}

	@Override
	public Image saveImageInDatabase(Product product, MultipartFile image, Boolean isMainImage) {
		String filePath = Paths.get(imageDirectory, product.getProductCode(), image.getOriginalFilename()).toString();
		File imageFile = new File(filePath);
		Image productImageToUpload = new Image(image.getOriginalFilename(), image.getContentType(), imageFile.getPath(), product, isMainImage);
		Image imageAdded = imageRepository.save(productImageToUpload);
		return imageAdded;
	}

	@Override
	public void saveImageIntoFolder(MultipartFile image, Product product) {
		String folderOfProduct = imageDirectory + "\\" + product.getProductCode();
		Path filePath = Paths.get(folderOfProduct, image.getOriginalFilename());
		try {
			OutputStream os = Files.newOutputStream(filePath);
			byte[] imageBytes = image.getBytes();
			os.write(imageBytes);
		} catch (IOException e) {
			throw new ErrorSavingImageIntoAFolder("Hubo un problema guardando la imagen dentro en la carpeta");
		}
	}

	@Override
	public void createFolderOfProduct(Product product) {
		String folderOfProduct = imageDirectory + "\\" + product.getProductCode();
		File folderOfProductFile = new File(folderOfProduct);
		if(!folderOfProductFile.exists()) {
			folderOfProductFile.mkdirs();
		}
	}

	@Override
	public List<ImageResponse> convertImageList(List<Image> imagesOfProduct) {
		List<ImageResponse> imageResponses = imagesOfProduct.stream().map(image -> new ImageResponse(image)).collect(Collectors.toList());
		return imageResponses;
	}

	@Override
	public ImageResponse getMissingImage() {
		Path filePath = Paths.get(missingImageDirectory);
		String name = filePath.getFileName().toString();
		String contentType = "image/png";
		byte[] missingProductBytes = null;
		ImageResponse missingProductImage = null;
		try {
			missingProductBytes = Files.readAllBytes(filePath);
			missingProductImage = new ImageResponse(name, contentType, missingProductBytes);
		} catch (IOException e) {
			throw new ErrorSavingImageIntoAFolder("Hubo un problema recuperando la imagen dentro en la carpeta " + e.getMessage());
		}
		return missingProductImage;
	}

	@Override
	public Image searchImageById(Long imageId) {
		Image image = imageRepository.findById(imageId).get();
		return image;
	}

	@Override
	public void deleteImageFromFolder(Image image) {
		File imageFile = new File(image.getImagePath());
		imageFile.delete();
	}

	@Override
	public void deleteImageFromDatabase(Image image) {
		imageRepository.delete(image);
	}

	@Override
	public void uploadMainImages(MultipartFile[] images, Product product) {
		for(MultipartFile image : images) {
			createFolderOfProduct(product);
			saveImageIntoFolder(image, product);
			saveImageInDatabase(product, image, true);
		}
	}
	
	@Override
	public void checkIfThereAreNoMainImage(Product product) {
		List<Image> imageList = imageRepository.findByProduct(product);
		for(Image image : imageList) {
			if(image.getIsMainImage())
				throw new ExistsMainImageOfAProductException("El producto ya tiene una imagen principal");
		}
	}

	@Override
	public void checkIfMoreThanFourImagesForTheProductAreUploaded(MultipartFile[] images, Product product) {
		List<Image> imageList = imageRepository.findByProduct(product);
		if(images.length + imageList.size() > 4 )
			throw new TooManyImagesForAProductException("Un producto puede tener solo 4 imagenes y tiene " + imageList.size());
	}

	@Override
	public List<Image> searchImagesOfAProduct(Product product) {
		List<Image> images = imageRepository.findByProduct(product);
		return images;
	}

	@Override
	public void setNewMainImage(Image newMainImage, List<Image> images) {
		images.stream().forEach(image -> {
			if(image.getId().equals(newMainImage.getId())) {
				image.setIsMainImage(true);
			}
			imageRepository.save(image);
		});
	}

	@Override
	public void checkIfThereIsMainImageChangesItToSecondaryImage(List<Image> images) {
		images.stream().forEach(image -> {
			if(image.getIsMainImage())
				image.setIsMainImage(false);
		});
	}

}
