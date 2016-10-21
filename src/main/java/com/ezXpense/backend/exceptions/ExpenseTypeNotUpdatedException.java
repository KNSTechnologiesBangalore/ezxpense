package com.ezXpense.backend.exceptions;

/**
 * Created by Surabhi on 30-08-2016
 * This class is used to throw exception when no Expense Type inserted or updated
 */
public class ExpenseTypeNotUpdatedException extends Exception {
	private static final long serialVersionUID = 1L;
	@Override
	public String toString(){
		return "No Expense Type Inserted or Updated";
	}

}
