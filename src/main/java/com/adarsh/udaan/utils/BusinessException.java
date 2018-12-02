package com.adarsh.udaan.utils;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public BusinessException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
