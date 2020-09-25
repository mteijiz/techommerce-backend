package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.exception.EmptyProductListException;
import com.techommerce.backend.exception.ExistingProductException;
import com.techommerce.backend.exception.ProductQuantityIsLowerThanZeroException;
import com.techommerce.backend.repository.ProductRepository;
import com.techommerce.backend.response.ImageResponse;
import com.techommerce.backend.response.ProductResponse;
import com.techommerce.backend.service.ImageService;
import com.techommerce.backend.service.ProductService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ImageService imageService;

	@Override
	public Product addProduct(Product productToAdd) {
		productCodeAndNameUppercase(productToAdd);
		try {
			Product productAdded = productRepository.save(productToAdd);
			return productAdded;
		} catch (DataIntegrityViolationException e) {
			throw new ExistingProductException("Hubo un problema creando el producto", e);
		}
	}

	@Override
	public void productCodeAndNameUppercase(Product product) {
		product.setProductCode(product.getProductCode().toUpperCase());
		product.setProductName(product.getProductName().toUpperCase());
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productList = productRepository.findAll(Sort.by("productName"));
		return productList;
	}

	@Override
	public List<ProductResponse> buildProductResponseList(List<Product> products) {
		checkIfProductListIsEmpty(products);
		List<ProductResponse> productResponseList = products.stream().map(product -> buildProductResponse(product))
				.collect(Collectors.toList());
		return productResponseList;
	}

	@Override
	public void checkIfProductListIsEmpty(List<Product> productsList) {
		if (productsList.isEmpty())
			throw new EmptyProductListException("No hay productos cargados");
	}

	@Override
	public Product updateProduct(Product productToUpdate) {
		productCodeAndNameUppercase(productToUpdate);
		Product productUpdated = productRepository.save(productToUpdate);
		return productUpdated;
	}

	@Override
	public void checkIfProductQuantityIsLowerThanZero(Product product) {
		if (product.getProductQuantity() < 0)
			throw new ProductQuantityIsLowerThanZeroException("La cantidad de un producto no puede ser menor que cero");
	}

	@Override
	public void addOrSubstractQuantityFromProduct(Product product, Integer quantityToAddOrSubstract) {
		product.setProductQuantity(product.getProductQuantity() + quantityToAddOrSubstract);
	}

	@Override
	public Product searchProductById(Long productId) {
		Product product = productRepository.findById(productId).get();
		return product;
	}

	@Override
	public List<Product> getActiveProducts() {
		List<Product> products = productRepository.findAllByStatus(Sort.by("productName")).stream()
				.filter(product -> checkIfProductIsActiveOrInactive(product))
				.collect(Collectors.toList());
		return products;
	}

	@Override
	public boolean checkIfProductIsActiveOrInactive(Product product) {
		return product.getProductState() && product.getProductBrand().getBrandState()
				&& product.getProductCategory().getCategoryState()
				&& product.getProductSubcategory().getSubcategoryState();
	}

	@Override
	public ProductResponse buildProductResponse(Product product) {
		List<ImageResponse> imagesResponse = new ArrayList<>();
		if(product.getProductImages().isEmpty()) {
			ImageResponse imageResponse = imageService.getMissingImage();
			imagesResponse.add(imageResponse);
		}
		else {
			for(int i = 0; i < product.getProductImages().size(); i++) {
				ImageResponse aux = new ImageResponse(product.getProductImages().get(i));
				if(aux.getIsMainimage()) {
					imagesResponse.add(0, aux);
				}else {
					imagesResponse.add(aux);
				}
			}
		}
		ProductResponse productResponse = new ProductResponse(product, imagesResponse);
		return productResponse;
	}

	@Override
	@Transactional
	public void substractProductQuantity(List<CartDetails> details) {
		details.stream().forEach(detail -> removeQuantityOfProductsFromDetails(detail));
	}

	@Override
	public void removeQuantityOfProductsFromDetails(CartDetails detail) {
		Product product = detail.getProduct();
		checkIfQuantityProductHasQuantityLowerThanZero(product, detail.getQuantity());
		product.setProductQuantity(product.getProductQuantity() - detail.getQuantity());
		Product productUpdate = productRepository.save(product);
		detail.setProduct(productUpdate);
	}

	@Override
	public void checkIfQuantityProductHasQuantityLowerThanZero(Product product, Integer quantityToSubstract) {
		if(product.getProductQuantity() - quantityToSubstract < 0)
			throw new ProductQuantityIsLowerThanZeroException("No hay suficiente stock del producto " + product.getProductName());
	}

	@Override
	public List<Product> getProductsByFilter(List<Brand> brands, List<Category> categories,
			List<Subcategory> subcategories, Float minPrice, Float maxPrice) {
		System.out.println(minPrice);
		System.out.println(maxPrice);
		if(brands.isEmpty())
			brands = null;
		if(categories.isEmpty())
			categories = null;
		if(subcategories.isEmpty())
			subcategories = null;
		List<Product> products = productRepository.findProductWithFilter(brands, categories, subcategories, minPrice, maxPrice);
		return products;
	}
}
