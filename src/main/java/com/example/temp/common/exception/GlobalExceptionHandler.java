package com.example.temp.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomException e) {
		log.error("CustomException: {}", e.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getDetail());
		return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> handleException(MissingServletRequestParameterException e) {
		log.error("MissingServletRequestParameterException: {}", e.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("Missing Request Parameter", e.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException e) {
		log.error("MethodArgumentTypeMismatchException: {}", e.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("Argument Type Mismatch", e.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException e) {
		log.error("HttpMessageNotReadableException: {}", e.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("HTTP Message Not Readable", e.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleException(HttpRequestMethodNotSupportedException e) {
		log.error("HttpRequestMethodNotSupportedException: {}", e.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("HTTP Method Not Supported", e.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e) {
		log.error("MethodArgumentNotValidException : {}", e.getMessage());
		String errorMessage = createValidationErrorMessage(e.getBindingResult());
		ErrorResponse errorResponse = new ErrorResponse("Invalid Argument", errorMessage);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		log.error(errors.toString());
		ErrorResponse errorResponse = new ErrorResponse("Unexpected Error");
		return ResponseEntity.badRequest().body(errorResponse);
	}

	private String createValidationErrorMessage(BindingResult bindingResult) {
		return bindingResult.getFieldErrors().stream()
			.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
			.collect(Collectors.joining(", "));
	}
}
