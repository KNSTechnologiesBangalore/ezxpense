package com.ezXpense.backend.expensetype.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ezXpense.backend.divisions.dao.DivisionsDaoImpl;
import com.ezXpense.backend.exceptions.ExpenseTypeNotFoundException;
import com.ezXpense.common.db.DBUtil;
import com.ezXpense.frontend.expensetype.dto.ExpenseTypeDto;

@Repository("expenseTypeDao")
public class ExpenseTypeDaoImpl implements ExpenseTypeDao{
	
	private static Logger log=Logger.getLogger(DivisionsDaoImpl.class);
	
	/**
	 * Created by Surabhi on 30-08-2016
	 * modified By Manjunath 0n 22-09-2016
	 */
	@Override
	public Long executeUpdateExpenseTypeInDB(Integer orgId,String expenseType,String expenseFlag,String active) throws Exception {
		log.info("inside ExpenseTypeDaoImpl--->executeUpdateExpenseTypeInDB()");
		String dbName = "knstechexpense";
		Integer bigint = 1;
		String orgName = "knstech";
		String orgSName = "kns";
		Connection con= DBUtil.getDBConnection(dbName);
		System.out.println("inside dado part"+dbName + orgId);
		CallableStatement ins_exptyp= con.prepareCall("{call ins_expense_type(?,?,?,?,?,?,?)}");
		ins_exptyp.setInt(1, orgId);
		ins_exptyp.setString(2, orgName);
		ins_exptyp.setString(3, orgSName);
		ins_exptyp.setString(4,expenseType);
		ins_exptyp.setString(5, expenseFlag);
		ins_exptyp.setString(6, active);
		ins_exptyp.setInt(7, bigint);
		System.out.println(" PSQL QUERY FOR ORGANIZATION INSERT"+ins_exptyp.toString());
		ins_exptyp.executeUpdate();
		ins_exptyp.close();
		return 1l;
		
	}

	/**
	 * Created by Surabhi on 30-08-2016
	 * This function is to retrieve the expense types from DB with all its value using expenseType 
	 */
	@Override
	public ExpenseTypeDto getExpenseTypeFromDB(String dbSchema, String expenseType) throws Exception {
		log.info("inside ExpenseTypeDaoImpl--->getExpenseTypeFromDB()");
		ExpenseTypeDto expenseTypeDto=new ExpenseTypeDto();
		Connection con = DBUtil.getDBConnection(dbSchema);
		PreparedStatement pstmt=con.prepareStatement("select * from conf_expense_type where expense_type=?");
		pstmt.setString(1,expenseType);
		ResultSet rs=pstmt.executeQuery();
		if(null!=rs){
			while(rs.next()){                   
				expenseTypeDto.setOrganisationId(rs.getInt(1));
				expenseTypeDto.setExpenseType(rs.getString(2));
				expenseTypeDto.setMileage_flag(rs.getString(3));
				expenseTypeDto.setActive_flag(rs.getString(4));
			}
			return expenseTypeDto;
		}
		else{
			throw new ExpenseTypeNotFoundException();
		}
	}
	
	/**
	 * Created by Surabhi on 30-08-2016
	 * This function is to retrieve all the expense types from DB 
	 */
	@Override
	public ArrayList<ExpenseTypeDto> getAllExpenseTypesFromDB(String dbSchema) throws Exception {
		log.info("inside ExpenseTypeDaoImpl--->getAllExpenseTypeFromDB()");
		ArrayList<ExpenseTypeDto> expenseTypeDtos=new ArrayList<ExpenseTypeDto>();
		Connection con = DBUtil.getDBConnection(dbSchema);
		PreparedStatement pstmt=con.prepareStatement("select * from conf_expense_type");
		ResultSet rs=pstmt.executeQuery();
		if(null!=rs){
			while(rs.next()){    
				ExpenseTypeDto expenseTypeDto=new ExpenseTypeDto();
				expenseTypeDto.setOrganisationId(rs.getInt(1));
				expenseTypeDto.setExpenseType(rs.getString(2));
				expenseTypeDto.setMileage_flag(rs.getString(3));
				expenseTypeDto.setActive_flag(rs.getString(4));
				expenseTypeDtos.add(expenseTypeDto);
			}
			return expenseTypeDtos;
		}
		else{
			throw new ExpenseTypeNotFoundException();
		}
	}

	/**
	 * Created by Surabhi on 24-08-2016
	 * This function is to delete the expense type based on its expenseType value from DB 
	 */
	@Override
	public void deleteExpenseTypeFromDB(String dbSchema,String expenseType) throws Exception {
		log.info("inside ExpenseTypeDaoImpl--->deleteExpenseTypeFromDB()");
		Connection con = DBUtil.getDBConnection(dbSchema);
		PreparedStatement pstmt=con.prepareStatement("delete from conf_expense_type where expense_type=?");
		pstmt.setString(1,expenseType);
		int rs=pstmt.executeUpdate();
		System.out.println(rs);
		if(rs<0){
			throw new ExpenseTypeNotFoundException();
		}
	}

}
