package com.ezXpense.frontend.common.exceptions;

public class MailNotSentException extends Exception{
private static final long serialVersionUID = 1L;
	
	@Override
	public String toString(){
		return "Mail Not Sent Exception";
	}
}
