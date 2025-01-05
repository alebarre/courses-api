package com.alebarre.coursesAPI.enums;

public enum Status {
	
	ACTIVE("Ativo"), INATIVE("Inativo");
	
	private String value;
	
	private Status(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	//toString
	public String toString() {
		return value;
	}

}
