package com.ezXpense.backend.exceptions;

public class UserNameAlreadyExistException extends Exception{
private static final long serialVersionUID = 1L;
	
	@Override
	public String toString(){
		return "User Name already exist";
	}
}
