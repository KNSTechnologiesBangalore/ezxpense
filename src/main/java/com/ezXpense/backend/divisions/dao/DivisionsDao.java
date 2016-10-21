package com.ezXpense.backend.divisions.dao;

/**
 * Created by Surabhi on 17-08-2016
 * This Interface is to access the data from db required for Divisions
 */

import java.util.ArrayList;

import com.ezXpense.frontend.divisions.dto.DivisionsDto;

public interface DivisionsDao {
	
	
	public void executeUpdateDivisionInDB(String dbSchema,DivisionsDto division) throws Exception;
	public DivisionsDto getDivisionsByIDFromDB(String dbSchema,Integer divId) throws Exception;
	public ArrayList<DivisionsDto> getAllDivisionsFromDB(String dbSchema) throws Exception;
	public void deleteDivisionByIDFromDB(String dbSchema,Integer divId,Long orgId) throws Exception;
	
}
	