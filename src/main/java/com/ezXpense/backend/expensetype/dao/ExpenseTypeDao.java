package com.ezXpense.backend.expensetype.dao;

/**
 * Created by Surabhi on 29-08-2016
 * This Interface is to access the data from db required for expense type
 */

import java.util.ArrayList;
/**
 * Created by Surabhi on 29-08-2016
 * This Interface is to access the data from db required for Expense Type
 */
import com.ezXpense.frontend.expensetype.dto.ExpenseTypeDto;

public interface ExpenseTypeDao {
	
	public Long executeUpdateExpenseTypeInDB(Integer orgId,String expenseType,String expenseFlag,String active) throws Exception;
	public ExpenseTypeDto getExpenseTypeFromDB(String dbSchema, String expenseType) throws Exception;
	public ArrayList<ExpenseTypeDto> getAllExpenseTypesFromDB(String dbSchema) throws Exception;
	public void deleteExpenseTypeFromDB(String dbSchema,String expenseType) throws Exception;
}
