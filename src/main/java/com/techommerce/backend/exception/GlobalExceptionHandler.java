package com.techommerce.backend.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ExistingBrandException.class)
	public ResponseEntity<?> ExistingBrandException(ExistingBrandException e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidRequestBrandException.class)
	public ResponseEntity<?> InvalidRequestBrandException(InvalidRequestBrandException e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyBrandListException.class)
	public ResponseEntity<?> EmptyBrandListException(EmptyBrandListException e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotExistingBrandException.class)
	public ResponseEntity<?> NotExistingBrandException(NotExistingBrandException e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyCategoryListException.class)
	public ResponseEntity<?> EmptyCategoryListException(EmptyCategoryListException e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> MethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request){
		String allErrorMessages = "";
		for(int i = 0; i < e.getBindingResult().getAllErrors().size(); i++) {
			allErrorMessages = allErrorMessages + e.getBindingResult().getAllErrors().get(i).getDefaultMessage() + "\n";
		}
		ErrorDetails errorDetails = new ErrorDetails(new Date(), allErrorMessages, request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistingCategoryCodeException.class)
	public ResponseEntity<?> ExistingCategoryCodeException(ExistingCategoryCodeException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistingCategoryNameException.class)
	public ResponseEntity<?> ExistingCategoryNameException(ExistingCategoryNameException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SubcategoryStateCannotBeChanged.class)
	public ResponseEntity<?> SubcategoryStateCannotBeChanged(SubcategoryStateCannotBeChanged e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistingSubcategoryCodeException.class)
	public ResponseEntity<?> ExistingSubcategoryCodeException(ExistingSubcategoryCodeException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistingSubcategoryNameException.class)
	public ResponseEntity<?> ExistingSubcategoryNameException(ExistingSubcategoryNameException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AddingSubcategoryException.class)
	public ResponseEntity<?> AddingSubcategoryException(AddingSubcategoryException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptySubcategoryListException.class)
	public ResponseEntity<?> EmptySubcategoryListException(EmptySubcategoryListException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TooManyImagesForAProductException.class)
	public ResponseEntity<?> TooManyImagesForAProductException(TooManyImagesForAProductException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistsMainImageOfAProductException.class)
	public ResponseEntity<?> ExistsMainImageOfAProductException(ExistsMainImageOfAProductException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistingProductException.class)
	public ResponseEntity<?> ExistingProductException(ExistingProductException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyProductListException.class)
	public ResponseEntity<?> EmptyProductListException(EmptyProductListException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyOrderListException.class)
	public ResponseEntity<?> EmptyOrderListException(EmptyOrderListException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductQuantityIsLowerThanZeroException.class)
	public ResponseEntity<?> ProductQuantityIsLowerThanZeroException(ProductQuantityIsLowerThanZeroException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyCartException.class)
	public ResponseEntity<?> EmptyCartException(EmptyCartException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UpdatingCategoryException.class)
	public ResponseEntity<?> UpdatingCategoryException(UpdatingCategoryException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UpdatingSubcategoryException.class)
	public ResponseEntity<?> UpdatingSubcategoryException(UpdatingSubcategoryException e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ErrorSavingImageIntoAFolder.class)
	public ResponseEntity<?> ErrorSavingImageIntoAFolder(ErrorSavingImageIntoAFolder e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
