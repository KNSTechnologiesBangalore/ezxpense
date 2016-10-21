package com.ezXpense.backend.exceptions;

/**
 * Created by Surabhi on 26-08-2016
 * This class is used to throw exception when DBSchema name not found in DB using organizationId
 */
public class DBSchemaNameNotFound extends Exception{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString(){
		return "DBSchema Name Not Found";
	}
}
