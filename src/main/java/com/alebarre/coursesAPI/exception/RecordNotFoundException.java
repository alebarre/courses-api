package com.alebarre.coursesAPI.exception;

public class RecordNotFoundException extends RuntimeException {
	
	private static final Long serialVersionUID = 1L;
	
	public RecordNotFoundException(Long id) {
		super("Registro não encontrado com o id: " + id + " 🚫");
	}
	
}
