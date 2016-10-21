package com.ezXpense.frontend.divisions.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ezXpense.backend.divisions.dao.DivisionsDao;
import com.ezXpense.backend.exceptions.DivisionNotFoundException;
import com.ezXpense.backend.exceptions.DivisionNotUpdatedException;
import com.ezXpense.backend.user.dao.UserDao;
import com.ezXpense.frontend.divisions.dto.DivisionsDto;

@Service("divisionService")
public class DivisionServiceImpl implements DivisionService {

private static Logger log=Logger.getLogger(DivisionServiceImpl.class);
	
	@Resource(name = "divisionsDao")
	private DivisionsDao divisionsDao;
	
	@Resource(name = "userDao")
	private UserDao userDao;

	/**
	 * Created by Surabhi on 22-08-2016
	 * This function is to save a new division or update the existing division
	 */
	public void saveOrUpdateDivision(String dbSchema,DivisionsDto division) throws DivisionNotUpdatedException{
		log.info("inside divisionService---> saveOrUpdateDivision()");
		System.out.println("inside divisionService---> saveOrUpdateDivision()");
		try {
			divisionsDao.executeUpdateDivisionInDB(dbSchema, division);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Created by Surabhi on 22-08-2016
	 * This function is to retrieve the division with all its value using Division_ID
	 */
	public DivisionsDto getDivisionbyID(String dbSchema,Integer divId) throws DivisionNotFoundException {
		log.info("inside divisionService---> getDivisionsByID()");
		System.out.println("inside divisionService---> getDivisionsByID()");
		DivisionsDto division = null;
		try {
			division = divisionsDao.getDivisionsByIDFromDB(dbSchema, divId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return division;
	}
	
	/**
	 * Created by Surabhi on 22-08-2016
	 * This function is to retrieve all the division with all its value 
	 */
	public ArrayList<DivisionsDto> getAllDivisions(String dbSchema) throws DivisionNotFoundException {
		log.info("inside divisionService---> getAllDivisions()");
		System.out.println("inside divisionService---> getAllDivisions()");
		ArrayList<DivisionsDto> division = null;
		try {
			division = divisionsDao.getAllDivisionsFromDB(dbSchema);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return division;
	}
	
	/**
	 * Created by Surabhi on 24-08-2016
	 * This function is to delete the division based on division_ID
	 */
	@Override
	public void deleteDivisionByID(String dbSchema, Integer divId,Long orgId) throws DivisionNotFoundException {
		log.info("inside divisionService---> deleteDivisionByID()");
		System.out.println("inside divisionService---> deleteDivisionByID()");
		try {
			divisionsDao.deleteDivisionByIDFromDB(dbSchema, divId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	
}
