package com.techommerce.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.entity.CartDetails;
import com.techommerce.backend.entity.Category;
import com.techommerce.backend.entity.Image;
import com.techommerce.backend.entity.Product;
import com.techommerce.backend.entity.Subcategory;
import com.techommerce.backend.exception.EmptyProductListException;
import com.techommerce.backend.exception.ExistingBrandException;
import com.techommerce.backend.exception.ExistingProductException;
import com.techommerce.backend.exception.ProductQuantityIsLowerThanZeroException;
import com.techommerce.backend.repository.ProductRepository;
import com.techommerce.backend.request.AddVoteToProductRequest;
import com.techommerce.backend.response.ImageResponse;
import com.techommerce.backend.response.ProductResponse;
import com.techommerce.backend.service.ImageService;
import com.techommerce.backend.service.ProductService;

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
//			checkIfProductCodeExists(e, productToAdd);
//			checkIfProductNameExists(e, productToAdd);
			throw new ExistingProductException("Hubo un problema creando el producto", e);
		}
	}

	public void checkIfProductNameExists(DataIntegrityViolationException e, Product product) {
		if (e.getCause().getCause().getMessage()
				.contains("(product_name)=(" + product.getProductName() + ") already exists"))
			throw new ExistingProductException("The product name " + product.getProductName() + " already exists", e);
	}

	public void checkIfProductCodeExists(DataIntegrityViolationException e, Product product) {
		if (e.getCause().getCause().getMessage()
				.contains("(product_code)=(" + product.getProductCode() + ") already exists"))
			throw new ExistingProductException("The product code " + product.getProductCode() + " already exists", e);
	}

	@Override
	public void productCodeAndNameUppercase(Product product) {
		product.setProductCode(product.getProductCode().toUpperCase());
		product.setProductName(product.getProductName().toUpperCase());
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		return productList;
	}

	@Override
	public List<ProductResponse> buildProductResponseList(List<Product> products) {
		checkIfProductListIsEmpty(products);
		List<ProductResponse> productResponseList = products.stream().map(product -> buildProductResponse(product))
				.collect(Collectors.toList());
		return productResponseList;
	}

	public void checkIfProductListIsEmpty(List<Product> productsList) {
		if (productsList.isEmpty())
			throw new EmptyProductListException("No hay productos cargados");
	}

//	@Override
//	public Product updateProductState(Product productToUpdate) {
//		if (productToUpdate.getProductState())
//			productToUpdate.setProductState(false);
//		else
//			productToUpdate.setProductState(true);
//		Product productStateUpdated = productRepository.save(productToUpdate);
//		return productStateUpdated;
//	}

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
	public void addOrSubstractQuantityFromProduct(Product product) {
		Product actualProduct = productRepository.findById(product.getProductId()).get();
		product.setProductQuantity(product.getProductQuantity() + actualProduct.getProductQuantity());
	}

//	@Override
//	public void changingActiveStateOfProductsBelongToCategory(Category category) {
//		List<Product> productsBelongToCategoryList = productRepository.findByProductCategory(category);
//		for (Product product : productsBelongToCategoryList) {
//			if (product.getProductState())
//				product.setProductState(false);
//			productRepository.save(product);
//		}
//	}

//	@Override
//	public void changingInactiveStateOfProductsBelongToCategory(Category category) {
//		List<Product> productsBelongToCategoryList = productRepository.findByProductCategory(category);
//		for (Product product : productsBelongToCategoryList) {
//			if (!product.getProductState())
//				product.setProductState(true);
//			productRepository.save(product);
//		}
//	}

//	@Override
//	public void changingInactiveStateOfProductsBelongToSubcategory(Subcategory subcategory) {
//		List<Product> productsBelongToCategoryList = productRepository.findByProductSubcategory(subcategory);
//		for (Product product : productsBelongToCategoryList) {
//			if (product.getProductState())
//				product.setProductState(false);
//			productRepository.save(product);
//		}
//	}

//	@Override
//	public void changingActiveStateOfProductsBelongToSubcategory(Subcategory subcategory) {
//		List<Product> productsBelongToCategoryList = productRepository.findByProductSubcategory(subcategory);
//		for (Product product : productsBelongToCategoryList) {
//			if (!product.getProductState())
//				product.setProductState(true);
//			productRepository.save(product);
//		}
//	}

	@Override
	public Product searchProductById(Long productId) {
		Product product = productRepository.findById(productId).get();
		return product;
	}

//	@Override
//	public void addVoteToProduct(Review review) {
//		// TODO Auto-generated method stub
//		Float actualRate = new Float(review.getProduct().getProductRate());
//		Float newRate = actualRate + review.getVote();
//		review.getProduct().setProductRate(newRate);
//		review.getProduct().setProductQuantityOfVotes(review.getProduct().getProductQuantityOfVotes() + 1);
//		productRepository.save(review.getProduct());
//	}

//	@Override
//	public void changingActiveStateOfProductBelongToBrand(Brand brand) {
//		List<Product> productsBelongToBrandList = productRepository.findByProductBrand(brand);
//		for (Product product : productsBelongToBrandList) {
//			if (!product.getProductState())
//				product.setProductState(false);
//			productRepository.save(product);
//		}
//	}

//	@Override
//	public void changingInactiveStateOfProductBelongToBrand(Brand brand) {
//		List<Product> productsBelongToBrandList = productRepository.findByProductBrand(brand);
//		for (Product product : productsBelongToBrandList) {
//			if (!product.getProductState())
//				product.setProductState(true);
//			productRepository.save(product);
//		}
//	}

	@Override
	public List<Product> getActiveProducts() {
		List<Product> products = productRepository.findAllByStatus().stream()
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

//	@Override
//	public Product updateProductRate(@Valid AddVoteToProductRequest voteRequest) {
//		Product productToUpdateRate = voteRequest.getProduct();
//		productToUpdateRate.setProductQuantityOfVotes(productToUpdateRate.getProductQuantityOfVotes() + 1);
//		productToUpdateRate.setProductTotalPoints(productToUpdateRate.getProductTotalPoints() + voteRequest.getVote());
//		productToUpdateRate.setProductRate(
//				productToUpdateRate.getProductTotalPoints() / productToUpdateRate.getProductQuantityOfVotes());
//		Product productUpdated = productRepository.save(productToUpdateRate);
//		return productUpdated;
//	}

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

	private void removeQuantityOfProductsFromDetails(CartDetails detail) {
		Product product = detail.getProduct();
		checkIfQuantityProductHasQuantityLowerThanZero(product, detail.getQuantity());
		product.setProductQuantity(product.getProductQuantity() - detail.getQuantity());
		Product productUpdate = productRepository.save(product);
		detail.setProduct(productUpdate);
	}

	private void checkIfQuantityProductHasQuantityLowerThanZero(Product product, Integer quantityToSubstract) {
		if(product.getProductQuantity() - quantityToSubstract < 0)
			throw new ProductQuantityIsLowerThanZeroException("No hay suficiente stock del producto " + product.getProductName());
	}

	@Override
	public List<Product> getProductsByFilter(List<Brand> brands, List<Category> categories,
			List<Subcategory> subcategories) {
		List<Product> products = productRepository.findProductInBrand(brands, categories, subcategories).stream()
				.filter(product -> checkIfProductIsActiveOrInactive(product))
				.collect(Collectors.toList());
		return products;
	}
}
