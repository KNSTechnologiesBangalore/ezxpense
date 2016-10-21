package com.ezXpense.frontend.expensetype.dto;

/**
 * DTO for Expense Type Table
 * Created by Surabhi on 29-08-2016
 */
public class ExpenseTypeDto {
	
	private Integer organisationId;
	private String expenseType;
	private String active_flag;
	private String mileage_flag;
	
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public String getActive_flag() {
		return active_flag;
	}
	public void setActive_flag(String active_flag) {
		this.active_flag = active_flag;
	}
	public String getMileage_flag() {
		return mileage_flag;
	}
	public void setMileage_flag(String mileage_flag) {
		this.mileage_flag = mileage_flag;
	}
	
	
}
