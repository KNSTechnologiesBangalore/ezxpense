package com.ezXpense.backend.organization.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ezXpense.backend.exceptions.DatabaseDoesNotExistException;
import com.ezXpense.backend.exceptions.OrganizationsNotFoundException;
import com.ezXpense.backend.user.dao.UserDaoImpl;
import com.ezXpense.common.db.DBUtil;
import com.ezXpense.frontend.user.dto.OrganizationDto;

import java.util.ArrayList;
import java.util.Date;


@Repository("organizationDao")
public class OrganizationDaoImpl implements OrganizationDao{
	
	private static Logger log=Logger.getLogger(UserDaoImpl.class);
	
	/**
	 * Added by Firdous on 09th August 2016
	 * Method to add new organization
	 * 
	 */
	@Override
	public Integer saveorUpdateOrganization(OrganizationDto organization) throws Exception {
		log.info("inside OrganizationDaoImpl--->saveorUpdateOrganization()");
		Connection con = DBUtil.getConnection();
		Date startDate=organization.getStartDate();
		Date endDate=organization.getEndDate();
		java.sql.Date startDate1=new java.sql.Date(startDate.getTime());
		java.sql.Date endDate1=new java.sql.Date(endDate.getTime());
		CallableStatement insert_organization_stmt= con.prepareCall("{?= call ins_organization(?,?,?,?,?,?,?,?,?,?,?,?,?) }");
		insert_organization_stmt.registerOutParameter(1, java.sql.Types.INTEGER);
		insert_organization_stmt.setString(2,organization.getOrgName());
		insert_organization_stmt.setString(3,organization.getOrgShortName());
		insert_organization_stmt.setString(4,organization.getOnSiteFlag());
		insert_organization_stmt.setString(5,organization.getBaseURL());
		insert_organization_stmt.setString(6,organization.getDbIp());
		insert_organization_stmt.setInt(7,organization.getDbPort());
		insert_organization_stmt.setString(8,organization.getDbName());
		insert_organization_stmt.setString(9,organization.getLogoFile());
		insert_organization_stmt.setDate(10,startDate1);
		insert_organization_stmt.setDate(11,endDate1);
		insert_organization_stmt.setString(12,"");
		insert_organization_stmt.setString(13,organization.getCurrency());
		insert_organization_stmt.setInt(14,1);
		System.out.println(" PSQL QUERY FOR ARGANIZATION INSERT"+insert_organization_stmt.toString());
	    insert_organization_stmt.execute();
	    Integer returnValue=insert_organization_stmt.getInt(1);
		System.out.println("return value="+returnValue);
	    insert_organization_stmt.close();
		con.close();
		Connection con1 = DBUtil.getDBConnection(organization.getDbName());
		CallableStatement insert_organization_stmt1= con1.prepareCall("{call ins_organization(?,?,?,?,?,?,?,?,?,?,?,?,?) }");
		
		insert_organization_stmt1.setString(1,organization.getOrgName());
		insert_organization_stmt1.setString(2,organization.getOrgShortName());
		insert_organization_stmt1.setString(3,organization.getOnSiteFlag());
		insert_organization_stmt1.setString(4,organization.getBaseURL());
		insert_organization_stmt1.setString(5,organization.getDbIp());
		insert_organization_stmt1.setInt(6,organization.getDbPort());
		insert_organization_stmt1.setString(7,organization.getDbName());
		insert_organization_stmt1.setString(8,organization.getLogoFile());
		/*insert_organization_stmt.setDate(9,organization.getStartDate());
		insert_organization_stmt.setDate(10,organization.getEndDate());*/
		insert_organization_stmt1.setDate(9,startDate1);
		insert_organization_stmt1.setDate(10,endDate1);
		
		insert_organization_stmt1.setString(11,"");
		insert_organization_stmt1.setString(12,organization.getCurrency());
		/*insert_organization_stmt.setInt(13,(BigInteger)1);*/
		insert_organization_stmt1.setInt(13,1);
		System.out.println(" PSQL QUERY FOR ORGANIZATION INSERT"+insert_organization_stmt.toString());
	    insert_organization_stmt1.execute();
	   
	    insert_organization_stmt1.close();
		con1.close();
		return returnValue;
	}
	
	/**
	 * Added by Firdous on 09th August 2016
	 * Method to get an organization from db
	 */
	@Override
	public OrganizationDto getOrganization() throws Exception {
		log.info("inside OrganizationDaoImpl---->getOrganization()");
		OrganizationDto organization=new OrganizationDto();
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt=con.prepareStatement("select * from conf_organization where organization_id=?");
		pstmt.setInt(1,1);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			Integer orgId=rs.getInt(1);
			String org_name=rs.getString(2);
			String orgShortName=rs.getString(3);
			String logoFile=rs.getString(4);
			Date startDate=rs.getDate(5);
			Date endDate=rs.getDate(6);
			String onSiteFlag=rs.getString(12);
			String baseURL=rs.getString(13);
			String dbIp=rs.getString(14);
			Integer dbPort=rs.getInt(15);
			String dbName=rs.getString(16);
			String currencyCode=rs.getString(17);
			organization.setOrganizationId(orgId);
			organization.setOrgName(org_name);
			organization.setOrgShortName(orgShortName);
			organization.setLogoFile(logoFile);
			organization.setStartDate(startDate);
			organization.setEndDate(endDate);
			organization.setOnSiteFlag(onSiteFlag);
			organization.setBaseURL(baseURL);
			organization.setDbIp(dbIp);
			organization.setDbPort(dbPort);
			organization.setDbName(dbName);
			organization.setCurrency(currencyCode);
		
			pstmt.close();
			con.close();
			return organization;
		}
		else{
			pstmt.close();
			con.close();
			throw new OrganizationsNotFoundException();
		}
	}
	private static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
    
	
	/**
	 * Added by Firdous on 19th august 2016
	 * Method to get all the organizations present in db
	 */
	@Override
	public ArrayList<OrganizationDto> getAllOrganization() throws Exception {
		log.info("inside getAllOrganization()()");
		ArrayList<OrganizationDto> organizations=new ArrayList<OrganizationDto>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt=con.prepareStatement("select * from conf_organization");
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			OrganizationDto organization=new OrganizationDto();
			Integer orgId=rs.getInt(1);
			String org_name=rs.getString(2);
			String orgShortName=rs.getString(3);
			String logoFile=rs.getString(4);
			Date startDate=rs.getDate(5);
			Date endDate=rs.getDate(6);
			String onSiteFlag=rs.getString(12);
			String baseURL=rs.getString(13);
			String dbIp=rs.getString(14);
			Integer dbPort=rs.getInt(15);
			String dbName=rs.getString(16);
			String currencyCode=rs.getString(17);
			organization.setOrganizationId(orgId);
			organization.setOrgName(org_name);
			organization.setOrgShortName(orgShortName);
			organization.setLogoFile(logoFile);
			organization.setStartDate(startDate);
			organization.setEndDate(endDate);
			organization.setOnSiteFlag(onSiteFlag);
			organization.setBaseURL(baseURL);
			organization.setDbIp(dbIp);
			organization.setDbPort(dbPort);
			organization.setDbName(dbName);
			organization.setCurrency(currencyCode);
			organizations.add(organization);
		}
		pstmt.close();
		con.close();
		if(organizations.size()>0){
		    return organizations;
		}
		else{
			throw new OrganizationsNotFoundException();
		}
	}
    
	
	/**
	 * Added by Firdous on 19th august 2016
	 * Method to get all the organization detail based on organization id
	 */
	@Override
	public OrganizationDto getOrganizationDetail(Integer organizationId) throws Exception {
		log.info("inside OrganizationDaoImpl---->getOrganizationDetail()");
		try{
		OrganizationDto organization=new OrganizationDto();
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt=con.prepareStatement("select * from conf_organization where organization_id=?");
		pstmt.setInt(1,organizationId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			Integer orgId=rs.getInt(1);
			String org_name=rs.getString(2);
			String orgShortName=rs.getString(3);
			String logoFile=rs.getString(4);
			Date startDate=rs.getDate(5);
			Date endDate=rs.getDate(6);
			String onSiteFlag=rs.getString(12);
			String baseURL=rs.getString(13);
			String dbIp=rs.getString(14);
			Integer dbPort=rs.getInt(15);
			String dbName=rs.getString(16);
			String currencyCode=rs.getString(17);
			organization.setOrganizationId(orgId);
			organization.setOrgName(org_name);
			organization.setOrgShortName(orgShortName);
			organization.setLogoFile(logoFile);
			organization.setStartDate(startDate);
			organization.setEndDate(endDate);
			organization.setOnSiteFlag(onSiteFlag);
			organization.setBaseURL(baseURL);
			organization.setDbIp(dbIp);
			organization.setDbPort(dbPort);
			organization.setDbName(dbName);
			organization.setCurrency(currencyCode);
		
		pstmt.close();
		con.close();
		return organization;
	    }
		else{
			pstmt.close();
			con.close();
			throw new OrganizationsNotFoundException();
		}
		}
		catch(PSQLException e){
			throw new DatabaseDoesNotExistException();
		}
	}

	@Override
	public Long getOrganizationIdByUserName(String username) throws Exception {
		Long orgId=null;
		String dbName=null;
		Connection con = DBUtil.getDBConnection(dbName);
		PreparedStatement pstmt=con.prepareStatement("select organization_id from conf_users where user_name=?");
		pstmt.setString(1,username);
		System.out.println("query="+pstmt.toString());
		ResultSet rs=pstmt.executeQuery();
		
		if(rs!=null && rs.next()){
			orgId=rs.getLong(1);
		}
		rs.close();
		pstmt.close();
		con.close();
		return orgId;
	}
     
}
