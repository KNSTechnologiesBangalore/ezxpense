package com.ezXpense.frontend.expensetype.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezXpense.frontend.expensetype.dto.ExpenseTypeDto;
import com.ezXpense.frontend.expensetype.service.ExpenseTypeService;
import com.ezXpense.frontend.user.service.UserService;

/**
 *  Created by Surabhi on 31-08-2016
 *
 */
@RequestMapping("/siteadmin")
@Controller("ExpenseTypeController")
public class ExpenseTypeController {
	private static Logger log=Logger.getLogger(ExpenseTypeController.class);
	
	@Resource(name="expenseTypeService")
	private ExpenseTypeService expenseTypeService;
	
	@Resource(name="userService")
	private UserService userService;
	/**
	 * created by Manjunath p 21-09-2016
	 * @param model
	 * @param dbName
	 * @param expenseType
	 * @return
	 * method : getting all expense types data stored in database 
	 */
	@RequestMapping(value="/expensetype.do",method = RequestMethod.GET)
	public String ExpenseType(ModelMap model,String dbName,@ModelAttribute("expenseType")ExpenseTypeDto expenseType){
		log.info("inside ExpenseTypeController------->addExpenseType()");
		dbName="knstechexpense";
		try {
			System.out.println("inside type");
			ArrayList<ExpenseTypeDto> expenseTypeDtos = this.expenseTypeService.getAllExpenseTypes(dbName);
			Collections.sort(expenseTypeDtos, new Comparator<ExpenseTypeDto>() {
				public int compare(ExpenseTypeDto o1, ExpenseTypeDto o2) {
				String a = o2.getExpenseType() ;
				String b = o1.getExpenseType() ;
				return b.compareTo(a);
				}
			});
				model.addAttribute("expenseTypeDto", expenseTypeDtos);
			return "/expensetype/expenseType";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		} 
	}
	/**
	 * created by Manjunath p 22-09-2016
	 * @param model
	 * @param expenseType
	 * @param expenseFlag
	 * @param active
	 * @return
	 * method : for adding new expense type to the database
	 */
	@ResponseBody
	@RequestMapping(value="/addexpensetype.do",method = RequestMethod.POST)
	public String addExpenseType(ModelMap model,@RequestParam("expenseType")String expenseType,@RequestParam("expenseFlag") String expenseFlag,@RequestParam("active") String active){
		log.info("inside ExpenseTypeController------->addExpenseType()");
		Integer orgId=2;
		System.out.println("inside controller");
		String dbName = "knstechexpense";
		try {
			ArrayList<ExpenseTypeDto> expenseTypeDtos = this.expenseTypeService.getAllExpenseTypes(dbName);
			Collections.sort(expenseTypeDtos, new Comparator<ExpenseTypeDto>() {
				public int compare(ExpenseTypeDto o1, ExpenseTypeDto o2) {
				String a = o2.getExpenseType() ;
				String b = o1.getExpenseType() ;
				return b.compareTo(a);
				}
			});
			for (ExpenseTypeDto expenseTypeDto : expenseTypeDtos) {
				if(expenseType.equalsIgnoreCase(expenseTypeDto.getExpenseType())){
					String failure = "this expense type is already exist"; 
					return failure;
				}
			}
			Long result = this.expenseTypeService.saveExpenseType(dbName,orgId,expenseType,expenseFlag,active);
			if(result>0){
				model.addAttribute("expenseTypeDto", expenseTypeDtos);
				return "success";
			}
			else{
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	/**
	 * created by Manjunath on 22-09-2016
	 * @param dbName
	 * @param model
	 * @return
	 * method:deleting the existing expense type data
	 */
	@RequestMapping(value="/deleteexpensetype.do",method = RequestMethod.GET)
	public String deleteExpenseType(String dbName,ModelMap model,@RequestParam("expenseType")String expenseType){
		log.info("inside ExpenseTypeController------->addExpenseType()");
		dbName="knstechexpense";
		try {
			System.out.println("inside type");
			this.expenseTypeService.deleteExpenseType(dbName, expenseType);
			ArrayList<ExpenseTypeDto> expenseTypeDtos = this.expenseTypeService.getAllExpenseTypes(dbName);
			Collections.sort(expenseTypeDtos, new Comparator<ExpenseTypeDto>() {
				public int compare(ExpenseTypeDto o1, ExpenseTypeDto o2) {
				String a = o2.getExpenseType() ;
				String b = o1.getExpenseType() ;
				return b.compareTo(a);
				}
			});
			model.addAttribute("expenseTypeDto", expenseTypeDtos);
			return "/expensetype/expenseType";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		} 
	}
	/**
	 * created by Manjunath p 23-09-2016
	 * @param expenseType
	 * @param expenseFlag
	 * @param active
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateexpensetype.do",method = RequestMethod.POST)
	public String updateExpenseType(@RequestParam("expenseType")String expenseType,@RequestParam("expenseFlag") String expenseFlag,@RequestParam("active") String active){
		log.info("inside ExpenseTypeController------->addExpenseType()");
		Integer orgId=2;
		System.out.println("inside controller");
		String dbName = "knstechexpense";
		try {
			ArrayList<ExpenseTypeDto> expenseTypeDtos = this.expenseTypeService.getAllExpenseTypes(dbName);
			Collections.sort(expenseTypeDtos, new Comparator<ExpenseTypeDto>() {
				public int compare(ExpenseTypeDto o1, ExpenseTypeDto o2) {
				String a = o2.getExpenseType() ;
				String b = o1.getExpenseType() ;
				return b.compareTo(a);
				}
			});
			for (ExpenseTypeDto expenseTypeDto : expenseTypeDtos) {
				if(expenseType.equalsIgnoreCase(expenseTypeDto.getExpenseType())){
					String failure = "this expense type is already exist"; 
					return failure;
				}
			}
			Long result = this.expenseTypeService.saveExpenseType(dbName,orgId,expenseType,expenseFlag,active);
			if(result>0){
				return "success";
			}
			else{
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
