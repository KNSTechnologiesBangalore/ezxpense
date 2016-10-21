package com.ezXpense.frontend.expensetype.service;

import java.util.ArrayList;
import com.ezXpense.backend.exceptions.ExpenseTypeNotFoundException;
import com.ezXpense.backend.exceptions.ExpenseTypeNotUpdatedException;
import com.ezXpense.frontend.expensetype.dto.ExpenseTypeDto;

/**
 * Created by Surabhi on 30-08-2016
 * This interface provides services for controller in saving, updating or fetching expense types from DAO
 */
public interface ExpenseTypeService {
	public Long saveExpenseType(String dbName,Integer orgId,String expenseType,String expenseFlag,String active) throws ExpenseTypeNotUpdatedException;
	public ExpenseTypeDto getExpenseType(String dbName, String expenseType) throws ExpenseTypeNotFoundException;
	public ArrayList<ExpenseTypeDto> getAllExpenseTypes(String dbSchema) throws ExpenseTypeNotFoundException;
	public void deleteExpenseType(String dbSchema,String expenseType) throws ExpenseTypeNotFoundException;
}