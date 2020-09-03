package com.techommerce.backend.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

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
import com.techommerce.backend.request.NewMainImageRequest;
import com.techommerce.backend.service.ImageService;
import com.techommerce.backend.service.ProductService;

@Controller
@RequestMapping("images")
@CrossOrigin(origins = "${spring.frontend.url}")
public class ImageController {

	@Autowired
	public ImageService imageService;

	@Autowired
	private ProductService productService;

	@PostMapping("/upload/{productId}")
	@RolesAllowed("admin")
	public ResponseEntity<?> uploadProductImage(@RequestParam("images") MultipartFile[] images,
			@PathVariable Long productId) {
		Product productToAddimage = productService.searchProductById(productId);
		imageService.checkIfMoreThanFourImagesForTheProductAreUploaded(images, productToAddimage);
		imageService.uploadImages(images, productToAddimage);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/uploadMain/{productId}")
	@RolesAllowed("admin")
	public ResponseEntity<?> uploadMainProductImage(@RequestParam("image") MultipartFile[] image, @PathVariable Long productId){
		Product productToAddimage = productService.searchProductById(productId);
		imageService.checkIfMoreThanFourImagesForTheProductAreUploaded(image, productToAddimage);
		imageService.checkIfThereAreNoMainImage(productToAddimage);
		imageService.uploadMainImages(image, productToAddimage);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/get/{productId}")
	public ResponseEntity<?> getImageOfProduct(@PathVariable Long productId){
		Product product = productService.searchProductById(productId);
		List<Image> imagesOfProduct = imageService.getImagesOfProduct(product);
		List<ImageResponse> imageResponseList = imageService.convertImageList(imagesOfProduct);
		imageService.checkIfImageListIsEmptyAndGetMissingImagen(imageResponseList);
		return new ResponseEntity<List<ImageResponse>>(imageResponseList, HttpStatus.OK);
	}
	
	@GetMapping("/getMain/{productId}")
	public ResponseEntity<?> getMainImageOfProduct(@PathVariable Long productId){
		Product product = productService.searchProductById(productId);
		List<Image> imagesOfProduct = imageService.getMainImagesOfProduct(product);
		List<ImageResponse> imageResponseList = imageService.convertImageList(imagesOfProduct);
		imageService.checkIfImageListIsEmptyAndGetMissingImagen(imageResponseList);
		return new ResponseEntity<List<ImageResponse>>(imageResponseList, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{imageId}")
	@RolesAllowed("admin")
	public ResponseEntity<?> updateImageState(@PathVariable Long imageId ){
		Image imageToUpdate = imageService.searchImageById(imageId);
		imageService.deleteImageFromDatabase(imageToUpdate);
		imageService.deleteImageFromFolder(imageToUpdate);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/updateMainImage")
	public ResponseEntity<?> changeMainImage(@RequestBody NewMainImageRequest request){
		//estos dos metodos reciben algo, guardarlos en una variable
		imageService.searchImageById(request.getImageId());
		imageService.searchImagesOfAProduct(request.getProduct());
		//agregar logica que busque la imagen main actual cambiarla a false y la otra cambiarla a true
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
