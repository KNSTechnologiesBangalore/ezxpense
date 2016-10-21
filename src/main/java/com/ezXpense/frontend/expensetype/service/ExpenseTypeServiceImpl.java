package com.ezXpense.frontend.expensetype.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.ezXpense.backend.exceptions.ExpenseTypeNotFoundException;
import com.ezXpense.backend.expensetype.dao.ExpenseTypeDao;
import com.ezXpense.frontend.expensetype.dto.ExpenseTypeDto;

@Service("expenseTypeService")
public class ExpenseTypeServiceImpl implements ExpenseTypeService {
	
	private static Logger log=Logger.getLogger(ExpenseTypeServiceImpl.class);
	
	
	
	@Resource(name ="expenseTypeDao")
	private ExpenseTypeDao expenseTypeDao;
	
	/**
	 * Created by Surabhi on 30-08-2016
	 * This function is to save a new expense type 
	 */
	@Override
	public Long saveExpenseType(String dbName,Integer orgId,String expenseType,String expenseFlag,String active) {
		log.info("inside ExpenseTypeServiceImpl---> saveExpenseType()");
		System.out.println("inside service part");
		System.out.println(dbName+orgId+expenseType+expenseFlag+active);
			Long result = 0l;
			try {
				result = this.expenseTypeDao.executeUpdateExpenseTypeInDB(orgId,expenseType,expenseFlag,active);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;	
	}
	
	/**
	 * Created by Surabhi on 30-08-2016
	 * This function is to retrieve the expense type using expense_type
	 */
	@Override
	public ExpenseTypeDto getExpenseType(String dbName, String expenseType) throws ExpenseTypeNotFoundException {
		log.info("inside ExpenseTypeServiceImpl---> getExpenseType()");
		ExpenseTypeDto expenseTypeDto = null;
		try {
			expenseTypeDto=expenseTypeDao.getExpenseTypeFromDB(dbName, expenseType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expenseTypeDto;
	}
	
	/**
	 * Created by Surabhi on 30-08-2016
	 * This function is to retrieve all the expense type
	 */
	@Override
	public ArrayList<ExpenseTypeDto> getAllExpenseTypes(String dbSchema) throws ExpenseTypeNotFoundException {
		log.info("inside ExpenseTypeServiceImpl---> getAllExpenseTypes()");
		ArrayList<ExpenseTypeDto> expenseTypeDtos = null;
		try {
			expenseTypeDtos=expenseTypeDao.getAllExpenseTypesFromDB(dbSchema);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expenseTypeDtos;
	}
	
	/**
	 * Created by Surabhi on 30-08-2016
	 * This function is to delete the expense type using expense_type
	 */
	@Override
	public void deleteExpenseType(String dbSchema, String expenseType) throws ExpenseTypeNotFoundException {
		log.info("inside ExpenseTypeServiceImpl---> deleteExpenseType()");
		try {
			expenseTypeDao.deleteExpenseTypeFromDB(dbSchema, expenseType);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
}
