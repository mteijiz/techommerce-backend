package com.techommerce.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_images", schema = "ecommerce")
@Data
@NoArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private Long id;

	@Column(name = "image_name", nullable = false)
	@NotNull
	@Size(min = 1)
	private String name;

	@Column(name = "image_type", nullable = false)
	@NotNull
	@Size(min = 1)
	private String type;

	@Column(name = "image_path", nullable = false)
	private String imagePath;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@NotNull
	private Boolean isMainImage;

	public Image(String originalFilename, String contentType, String path, Product product, Boolean isMainImage) {
		this.name = originalFilename;
		this.type = contentType;
		this.imagePath = path;
		this.product = product;
		this.isMainImage = isMainImage;
	}
}
