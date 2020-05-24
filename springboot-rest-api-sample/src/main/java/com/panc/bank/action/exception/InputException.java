package com.panc.bank.action.exception;

public class InputException extends Exception {

	private String message;
	
	public InputException(String string) {
		message = string;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
