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
	
	public ImageResponse(Image image) {
		// TODO Auto-generated constructor stub
		this.imageId = image.getId();
		this.imageName = image.getName();
		this.imageType = image.getType();
		this.imageBytes = getBytesFromImage(image);
	}

	private byte[] getBytesFromImage(Image image) {
		// TODO Auto-generated method stub
		File imageFile = new File(image.getImagePath());
		byte[] imageBytes = null;
		try {
			imageBytes = Files.readAllBytes(imageFile.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
