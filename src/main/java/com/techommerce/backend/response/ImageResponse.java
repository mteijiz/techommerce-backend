package com.techommerce.backend.response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.techommerce.backend.entity.Image;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageResponse {

	private Long imageId;
	private String imageName;
	private String imageType;
	private byte[] imageBytes;
	private Boolean isMainimage;
	
	public ImageResponse(Image image) {
		this.imageId = image.getId();
		this.imageName = image.getName();
		this.imageType = image.getType();
		this.imageBytes = getBytesFromImage(image);
		this.isMainimage = image.getIsMainImage();
	}

	private byte[] getBytesFromImage(Image image) {
		File imageFile = new File(image.getImagePath());
		byte[] imageBytes = null;
		try {
			imageBytes = Files.readAllBytes(imageFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageBytes;
	}
	
	public ImageResponse(String name, String contentType, byte[] bytes) {
		this.imageName = name;
		this.imageType = contentType;
		this.imageBytes = bytes;
	}
	
}
