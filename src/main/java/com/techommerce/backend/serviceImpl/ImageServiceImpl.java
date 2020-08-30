package com.techommerce.backend.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.exception.ErrorSavingImageIntoAFolder;
import com.techommerce.backend.exception.ExistsMainImageOfAProductException;
import com.techommerce.backend.exception.TooManyImagesForAProductException;
import com.techommerce.backend.repository.ImageRepository;
import com.techommerce.backend.response.ImageResponse;
import com.techommerce.backend.response.ProductResponse;
import com.techommerce.backend.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ImageRepository imageRepository;
	
	public static String imageDirectory = System.getProperty("user.dir") + "\\Image product";;
	
	private static String missingImageDirectory = System.getProperty("user.dir")
			+ "\\Missing product image\\missing_product.png";
	
	@Override
	public void uploadImages(MultipartFile[] images, Product product) {
		checkIfMoreThanFourImagesForTheProductAreUploaded(images, product);
		for(MultipartFile image : images) {
			saveImageIntoFolder(image, product);
			saveImageInDatabaseAndFolder(product, image, false);
		}
	}

	public void saveImageInDatabaseAndFolder(Product product, MultipartFile image, Boolean isMainImage) {
		//saveImageIntoFolder(image, product);
		String filePath = Paths.get(imageDirectory, product.getProductCode(), image.getOriginalFilename()).toString();
		File imageFile = new File(filePath);
		Image productImageToUpload = new Image(image.getOriginalFilename(), image.getContentType(), imageFile.getPath(), product, isMainImage);
		imageRepository.save(productImageToUpload);
	}

	public void saveImageIntoFolder(MultipartFile image, Product product) {
		String folderOfProduct = imageDirectory + "\\" + product.getProductId();
		File folderOfProductFile = new File(folderOfProduct);
		createFolderOfProduct(folderOfProductFile);
		Path filePath = Paths.get(folderOfProduct, image.getOriginalFilename());
		try {
			OutputStream os = Files.newOutputStream(filePath);
			byte[] imageBytes = image.getBytes();
			os.write(imageBytes);
		} catch (IOException e) {
			throw new ErrorSavingImageIntoAFolder("Hubo un problema guardando la imagen dentro en la carpeta");
		}
	}

//	private byte[] resizeImage(MultipartFile image) throws IOException {
//		BufferedImage originalImage = ImageIO.read(image.getInputStream());
//		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
//        Thumbnails.of(originalImage)
//            .size(500, 500)
//            .outputFormat(image.getContentType())
//            .outputQuality(0.99)
//            .toOutputStream(byteOutputStream);
//        byte[] data = byteOutputStream.toByteArray();
//		return data;
//	}

	public void createFolderOfProduct(File folderOfProductFile) {
		if(!folderOfProductFile.exists()) {
			folderOfProductFile.mkdirs();
		}
	}

	@Override
	public List<Image> getImagesOfProduct(Product product) {
		List<Image> images = imageRepository.findByProductAndIsNotMain(product.getProductId());
		return images;
	}

	@Override
	public List<ImageResponse> convertImageList(List<Image> imagesOfProduct) {
		List<ImageResponse> imageResponses = new ArrayList<>();
		for(Image image : imagesOfProduct) {
			ImageResponse ir = new ImageResponse(image);
			imageResponses.add(ir);
		}
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
			e.printStackTrace();
		}
		return missingProductImage;
	}

	@Override
	public Image searchImageById(Long imageId) {
		Image image = imageRepository.findById(imageId).get();
		return image;
	}

	@Override
	public void deleteImage(Image image) {
		deleteImageFromDatabase(image);
		deleteImageFromFolder(image);
	}

	public void deleteImageFromFolder(Image image) {
		File imageFile = new File(image.getImagePath());
		if(!imageFile.delete())
			System.out.println("deleteImageFromFolder - Hubo un problema borrando la imagen");
	}

	public void deleteImageFromDatabase(Image image) {
		imageRepository.delete(image);
	}

	@Override
	public void convertImageListToResponse(ProductResponse auxProductResponse, Product product) {
		if(product.getProductImages().isEmpty()) {
			ImageResponse missingImage = getMissingImage();
			auxProductResponse.getProductImage().add(missingImage);
		}
		else {
			auxProductResponse.setProductImage(convertImageList(product.getProductImages()));
		}
	}

	@Override
	public void uploadMainImages(MultipartFile[] images, Product product) {
		checkIfMoreThanFourImagesForTheProductAreUploaded(images, product);
		checkIfThereAreNoMainImage(product);
		for(MultipartFile image : images) {
			saveImageInDatabaseAndFolder(product, image, true);
		}
	}
	
	private void checkIfThereAreNoMainImage(Product product) {
		List<Image> imageList = imageRepository.findByProduct(product);
		for(Image image : imageList) {
			if(image.getIsMainImage())
				throw new ExistsMainImageOfAProductException("El producto ya tiene una imagen principal");
		}
	}

	public void checkIfMoreThanFourImagesForTheProductAreUploaded(MultipartFile[] images, Product product) {
		List<Image> imageList = imageRepository.findByProduct(product);
		if(images.length + imageList.size() > 4 )
			throw new TooManyImagesForAProductException("Un producto puede tener solo 4 imagenes y tiene " + imageList.size());
	}

	public void saveMainImageInDatabaseAndFolder(Product product, MultipartFile image) {
		saveImageIntoFolder(image, product);
		String filePath = Paths.get(imageDirectory, image.getOriginalFilename()).toString();
		File imageFile = new File(filePath);
		Image productImageToUpload = new Image(image.getOriginalFilename(), image.getContentType(), imageFile.getPath(), product, true);
		imageRepository.save(productImageToUpload);
	}
	
	@Override
	public void checkIfImageListIsEmptyAndGetMissingImagen(List<ImageResponse> imageResponseList) {
		if(imageResponseList.isEmpty()) {
			ImageResponse missingImage = getMissingImage();
			imageResponseList.add(missingImage);
		}
	}

	@Override
	public List<Image> getMainImagesOfProduct(Product product) {
		List<Image> images = imageRepository.findByProductAndIsMain(product.getProductId());
		return images;
	}

	@Override
	public List<Image> searchImagesOfAProduct(Product product) {
		List<Image> images = imageRepository.findByProduct(product);
		return images;
	}

}