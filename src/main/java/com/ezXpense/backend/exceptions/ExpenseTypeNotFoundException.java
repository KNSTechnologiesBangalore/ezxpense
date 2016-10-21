package com.ezXpense.backend.exceptions;

/**
 * Created by Surabhi on 30-08-2016
 * This class is used to throw exception when the required Expense Type is not found 
 */
public class ExpenseTypeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString(){
		return "Expense Type Not Found";
	}
}
