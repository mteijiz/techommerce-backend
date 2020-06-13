package com.techommerce.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.techommerce.backend.response.ImageResponse;
import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.service.ImageService;
import com.techommerce.backend.service.ProductService;

@Controller
@RequestMapping("images")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ProductService productService;

	@PostMapping("/upload/{productId}")
	public ResponseEntity<?> uploadProductImage(@RequestParam("images") MultipartFile[] images,
			@PathVariable Long productId) {
		Product productToAddimage = productService.searchProductById(productId);
		imageService.uploadImages(images, productToAddimage);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/get/{productId}")
	public ResponseEntity<?> getImageOfProduct(@PathVariable Long productId){
		Product product = productService.searchProductById(productId);
		List<Image> imagesOfProduct = imageService.getImagesOfProduct(product);
		List<ImageResponse> imageResponseList = imageService.convertImageList(imagesOfProduct);
		if(imageResponseList.isEmpty()) {
			ImageResponse missingImage = imageService.getMissingImage();
			imageResponseList.add(missingImage);
		}
		return new ResponseEntity<List<ImageResponse>>(imageResponseList, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{imageId}")
	public ResponseEntity<?> updateImageState(@PathVariable Long imageId ){
		Image imageToUpdate = imageService.searchImageById(imageId);
		imageService.deleteImage(imageToUpdate);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
