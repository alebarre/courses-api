package com.alebarre.coursesAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.alebarre.coursesAPI.exception.RecordNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(RecordNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(erro -> erro.getField() + " : " + erro.getDefaultMessage())
				.reduce("", (acumulador, erro) -> acumulador + erro + "\n");
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleConstraintViolationException(ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream()
				.map(erro -> erro.getPropertyPath() + " : " + erro.getMessage())
				.reduce("", (acumulador, erro) -> acumulador + erro + "\n");
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		if (ex != null && ex.getRequiredType() != null) {
			String type = ex.getRequiredType().getName();
			String[] parts = type.split("\\.");
			String typeName = parts[parts.length - 1];
			return "o campo '" + ex.getName() + "'  Dever ser do tipo " + typeName + " ❗";
		}
		return "Tipo inválido";
	}
}
