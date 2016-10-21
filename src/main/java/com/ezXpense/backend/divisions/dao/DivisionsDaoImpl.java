package com.ezXpense.backend.divisions.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ezXpense.backend.exceptions.DivisionNotFoundException;
import com.ezXpense.backend.exceptions.DivisionNotUpdatedException;
import com.ezXpense.common.db.DBUtil;
import com.ezXpense.frontend.divisions.dto.DivisionsDto;

@Repository("divisionsDao")
public class DivisionsDaoImpl implements DivisionsDao {
	
	private static Logger log=Logger.getLogger(DivisionsDaoImpl.class);
	
	/**
	 * Created by Surabhi on 17-08-2016
	 * This function is to save a new division to DB or update the existing division in DB
	 */

	@Override
	public void executeUpdateDivisionInDB(String dbSchema,DivisionsDto division) throws Exception {
		log.info("inside DivisionsDaoImpl--->executeUpdateDivisionInDB()");
		Connection con= DBUtil.getDBConnection(dbSchema);
		System.out.println("inside DivisionsDaoImpl--->executeUpdateDivisionInDB()");
		System.out.println(division.getDivisionId()+","+division.getDivisionName()+","+division.getOrganisationId()+","+division.getLoggedUserId());
		CallableStatement ins_division= con.prepareCall("{ call ins_division(?,?,?,?) }");
		ins_division.setLong(1, division.getOrganisationId());
		if(null!= division.getDivisionId()){
			ins_division.setInt(2, division.getDivisionId());
		}else{
			ins_division.setNull(2, java.sql.Types.INTEGER);
		}
		ins_division.setString(3, division.getDivisionName());
		ins_division.setInt(4, division.getLoggedUserId());
		System.out.println(ins_division.toString());
		int rs=ins_division.executeUpdate();
		//System.out.println(rs);
		if(rs<0){
			throw new DivisionNotUpdatedException();
		}
	}
	
	/**
	 * Created by Surabhi on 22-08-2016
	 * This function is to retrieve the division from DB with all its value using Division_ID
	 */
	
	@Override
	public DivisionsDto getDivisionsByIDFromDB(String dbSchema,Integer divId) throws Exception {
		log.info("inside DivisionsDaoImpl--->getDivisionsByIDFromDB()");
		System.out.println("inside DivisionsDaoImpl--->getDivisionsByIDFromDB()");
		DivisionsDto divisionsDto=new DivisionsDto();
		Connection con = DBUtil.getDBConnection(dbSchema);
		PreparedStatement pstmt=con.prepareStatement("select * from hr_division where division_id=?");
		pstmt.setInt(1,divId);
		ResultSet rs=pstmt.executeQuery();
		if(null!=rs){
			while(rs.next()){                   
				divisionsDto.setOrganisationId(rs.getLong(1));
				divisionsDto.setDivisionId(rs.getInt(2));
				divisionsDto.setDivisionName(rs.getString(3));
			}
			return divisionsDto;
		}
		else{
			throw new DivisionNotFoundException();
		}
	}
	
	/**
	 * Created by Surabhi on 22-08-2016
	 * This function is to retrieve all the division from DB 
	 */
	@Override
	public ArrayList<DivisionsDto> getAllDivisionsFromDB(String dbSchema) throws Exception {
		log.info("inside DivisionsDaoImpl--->getAllDivisionsFromDB()");
		System.out.println("inside DivisionsDaoImpl--->getAllDivisionsFromDB()");
		ArrayList<DivisionsDto> divisionsDtos=new ArrayList<DivisionsDto>();
		Connection con = DBUtil.getDBConnection(dbSchema);
		PreparedStatement pstmt=con.prepareStatement("select * from hr_division");
		ResultSet rs=pstmt.executeQuery();
		if(null!=rs){
			while(rs.next()){
				DivisionsDto divisionsDto=new DivisionsDto();
				divisionsDto.setOrganisationId(rs.getLong(1));
				divisionsDto.setDivisionId(rs.getInt(2));
				divisionsDto.setDivisionName(rs.getString(3));
				divisionsDtos.add(divisionsDto);
			}
			return divisionsDtos;
		}
		else{
			throw new DivisionNotFoundException();
		}
	}
	
	/**
	 * Created by Surabhi on 24-08-2016
	 * This function is to delete the division based on its Division_ID from DB 
	 */
	@Override
	public void deleteDivisionByIDFromDB(String dbSchema,Integer divId,Long orgId) throws Exception {
		log.info("inside DivisionsDaoImpl--->deleteDivisionByIDFromDB()");
		System.out.println("inside DivisionsDaoImpl--->deleteDivisionByIDFromDB()");
		Connection con = DBUtil.getDBConnection(dbSchema);
		PreparedStatement pstmt=con.prepareStatement("delete from hr_division where division_id=? and organization_id=?");
		pstmt.setInt(1,divId);
		pstmt.setLong(2,orgId);
		int rs=pstmt.executeUpdate();
		if(rs<0){
			throw new DivisionNotFoundException();
		}
	}

	
}
