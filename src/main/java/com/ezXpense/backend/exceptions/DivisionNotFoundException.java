package com.ezXpense.backend.exceptions;

/**
 * Created by Surabhi on 19-08-2016
 * This class is used to throw exception when the required division is not found
 */
public class DivisionNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString(){
		return "Division Not Found";
	}
}
