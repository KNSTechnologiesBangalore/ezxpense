package com.ezXpense.backend.exceptions;

public class DatabaseDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	@Override
	public String toString(){
		return "DATA Base Does Not Exist";
	}
}
