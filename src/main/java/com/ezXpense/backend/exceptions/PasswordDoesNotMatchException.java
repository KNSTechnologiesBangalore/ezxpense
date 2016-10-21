package com.ezXpense.backend.exceptions;
/***
 * Created By Bhagya On july 14th,2016
 *	This Exception Handles the condition when the does not match with the db password
 */
public class PasswordDoesNotMatchException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	@Override
	public String toString(){
		return "Password Does Not Match With Current User Password";
	}
}