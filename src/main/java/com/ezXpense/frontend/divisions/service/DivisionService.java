package com.ezXpense.frontend.divisions.service;

/**
 * Created by Surabhi on 22-08-2016
 * This interface provides services for controller in saving, updating or fetching Divisions from DAO
 */

import java.util.ArrayList;
import com.ezXpense.backend.exceptions.DivisionNotFoundException;
import com.ezXpense.backend.exceptions.DivisionNotUpdatedException;
import com.ezXpense.frontend.divisions.dto.DivisionsDto;

public interface DivisionService {
	
	public void saveOrUpdateDivision(String dbSchema,DivisionsDto division) throws Exception;
	public DivisionsDto getDivisionbyID(String dbSchema,Integer divId) throws DivisionNotFoundException ;
	public ArrayList<DivisionsDto> getAllDivisions(String dbSchema) throws DivisionNotFoundException;
	public void deleteDivisionByID(String dbSchema,Integer divId,Long orgId) throws DivisionNotFoundException;
	
}
