package com.ezXpense.backend.exceptions;

/**
 * Created by Surabhi on 19-08-2016
 * This class is used to throw exception when no division inserted or updated
 */
public class DivisionNotUpdatedException extends Exception{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString(){
		return "No Division Inserted or Updated";
	}
}
