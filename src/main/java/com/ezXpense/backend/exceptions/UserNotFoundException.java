package com.ezXpense.backend.exceptions;
/***
 * Created By Bhagya On july 14th,2016
 *	This Exception Handles the condition when the user not found
 */
public class UserNotFoundException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	@Override
	public String toString(){
		return "User Not Found";
	}
}