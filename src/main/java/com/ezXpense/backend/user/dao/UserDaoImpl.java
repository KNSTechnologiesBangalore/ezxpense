package com.ezXpense.backend.user.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ezXpense.backend.exceptions.NoSiteAdminFoundForOrganization;
import com.ezXpense.backend.exceptions.UserNameAlreadyExistException;
import com.ezXpense.backend.exceptions.DBSchemaNameNotFound;
import com.ezXpense.backend.exceptions.UserNotFoundException;
import com.ezXpense.common.db.DBUtil;
import com.ezXpense.frontend.user.dto.ChangeSiteAdminPasswordDto;
import com.ezXpense.frontend.user.dto.EzXpenseUserDto;
import com.ezXpense.frontend.user.dto.OrganizationDto;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());
	private static Logger log=Logger.getLogger(UserDaoImpl.class);
	
	/**
	 * created by Firdous on 2nd August 2016
	 * @return
	 * @throws Exception
	 * Method to get all the information related to user from database
	 * 
	 */
	
	public EzXpenseUserDto getezXpenseUserForAuthentication(String username) throws  Exception{
		log.info("Inside UserDaoImpl -> getezXpenseUserForAuthentication()");
		
		String password=null;
		Integer account_active=null;
		Integer userId=null;
		String userRole=null;
	    Integer no_of_invalid_login_attempts=null;
	    String accountLocked=null;
	    Long orgId=null;
	    String dbName=null;
		Connection con = DBUtil.getDBConnection(dbName);
		CallableStatement login_user_info_stmt= con.prepareCall("{ call user_login( ?,?,?) }");

		 login_user_info_stmt.setString(1,"");
		 login_user_info_stmt.setString(2,"");
		 login_user_info_stmt.setString(3,username);
		 
		 ResultSet rs3=login_user_info_stmt.executeQuery();
		 if(rs3.next()){
			    password=rs3.getString(1);
				account_active=rs3.getInt(2);
				accountLocked=rs3.getString(3);
				no_of_invalid_login_attempts=rs3.getInt(4);
				userId=rs3.getInt(5);
				userRole=rs3.getString(6);
				orgId=rs3.getLong(7);
				EzXpenseUserDto ezXpenseUser=new EzXpenseUserDto();
			
				ezXpenseUser.setUserId(userId);
				ezXpenseUser.setUsername(username);
				ezXpenseUser.setUserRole(userRole);
				ezXpenseUser.setAccountActive(account_active);
				ezXpenseUser.setPassword(password);
				ezXpenseUser.setNoOfInvalidLogin(no_of_invalid_login_attempts);
				ezXpenseUser.setAccountLocked(accountLocked);
			    ezXpenseUser.setOrgId(orgId);
				login_user_info_stmt.close();
				con.close();
				return ezXpenseUser;
		 }
		 else{
			 	throw new UsernameNotFoundException("");
		 }
		
	}

	/**
	 * created by Firdous on 2nd August 2016
	 * method to update the failed attempts of a user when user login with invalid password
	 */
	@Override
	public void updateFailAttempts(String username) throws Exception {
		log.info("Inside UserDaoImpl ->updateFailAttempts()");
		String dbName=null;
		Connection con = DBUtil.getDBConnection(dbName);
	
		CallableStatement login_user_info_stmt= con.prepareCall("{ call set_user_login_validity(?,?,?,?)}");
		login_user_info_stmt.setString(1,"");
		login_user_info_stmt.setString(2,"");
		login_user_info_stmt.setString(3,username);
		login_user_info_stmt.setBoolean(4,false);
		System.out.println("query string is="+ login_user_info_stmt.toString());
	    ResultSet rs3=login_user_info_stmt.executeQuery();
	}
     
	/**
	 * created by Firdous on 2nd August 2016
	 * method to reset the failed attempts of a user when user login with valid credentials
	 * 
	 */
	@Override
	public void resetFailAttempts(String username) throws Exception {
		log.info("Inside UserDaoImpl ->resetFailAttempts()");
		System.out.println("inside reset fail attempts");
		String dbName=null;
		Connection con = DBUtil.getDBConnection(dbName);
		
	
		CallableStatement login_user_info_stmt= con.prepareCall("{ call set_user_login_validity( ?,?,?,?) }");
		login_user_info_stmt.setString(1,"");
		login_user_info_stmt.setString(2,"");
		login_user_info_stmt.setString(3,username);
		login_user_info_stmt.setBoolean(4,true);
		ResultSet rs3=login_user_info_stmt.executeQuery();
	}

	
	
	@Override
	public Integer getUserAttempts(String username) {
		
		return null;
	}

	@Override
	public void setUserLocked(String username) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserUnLocked(String username) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * created by Firdous on 29th August
	 * method to get the site admin login details
	 * 
	 */
	public EzXpenseUserDto getSiteAdiminLoginDetails(String username)throws Exception{
		log.info("inside getSiteAdiminLoginDetails()");
		String password=null;
		Integer account_active=null;
		Integer userId=null;
		String userRole=null;
	    Integer no_of_invalid_login_attempts=null;
	    String accountLocked=null;
		Connection con = DBUtil.getConnection();
		CallableStatement login_site_admin_info= con.prepareCall(" select organization_id,user_name,passwd from conf_users");
		login_site_admin_info.setInt(1,1);
		login_site_admin_info.setString(2,"");
		login_site_admin_info.setString(3,username);
		ResultSet rs3=login_site_admin_info.executeQuery();
		System.out.println(rs3);
		 if(rs3.next()){
			    password=rs3.getString(1);
				account_active=rs3.getInt(2);
				accountLocked=rs3.getString(3);
				no_of_invalid_login_attempts=rs3.getInt(4);
				userId=rs3.getInt(5);
				userRole=rs3.getString(6);
				EzXpenseUserDto ezXpenseUser=new EzXpenseUserDto();
			
				ezXpenseUser.setUserId(userId);
				ezXpenseUser.setUsername(username);
				ezXpenseUser.setUserRole(userRole);
				ezXpenseUser.setAccountActive(account_active);
				ezXpenseUser.setPassword(password);
				ezXpenseUser.setNoOfInvalidLogin(no_of_invalid_login_attempts);
				ezXpenseUser.setAccountLocked(accountLocked);
				con.close();
				return ezXpenseUser;
		 }
		 else{
			 	throw new UsernameNotFoundException("");
		 }
		
	}
	/**
	 * Added by Manjunath p on 28-08-2016
	 * @param userid
	 * @return
	 * @throws Exception
	 * method to get data by user id
	 */
	@Override
	public ArrayList<EzXpenseUserDto> getAllSiteAdminOfOranization(String orgName,String dbName) throws Exception {
		log.info("inside UserDaoImpl---->getAllSiteAdminOfOranization()");
		
		ArrayList<EzXpenseUserDto> ezXpenseUserDtos=new ArrayList<EzXpenseUserDto>();
		Connection con = DBUtil.getDBConnection(dbName);
		CallableStatement get_organizationId_stmt=con.prepareCall("{ ?=call get_organization_id(?,?) }");
		get_organizationId_stmt.registerOutParameter(1, java.sql.Types.BIGINT);
		get_organizationId_stmt.setString(2,orgName);
		get_organizationId_stmt.setString(3,null);
		System.out.println(" PSQL QUERY FOR GETTING ORGANIZATION ID"+get_organizationId_stmt.toString());
		get_organizationId_stmt.execute();
		Long orgId=get_organizationId_stmt.getLong(1);
		get_organizationId_stmt.close();
		PreparedStatement pstmt=con.prepareStatement("select C.user_id,E.employee_id, E.employee_number,C.user_name,C.passwd,E.last_name,E.first_name,E.middle_initial,E.start_date,E.end_date,E.email_id from conf_users C,hr_employees E where C.employee_id=E.employee_id AND C.organization_id=E.organization_id AND C.organization_id="+orgId);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			EzXpenseUserDto userDto=new EzXpenseUserDto();
			userDto.setUserId(rs.getInt(1));
			userDto.setEmployeeId(rs.getInt(2));
			userDto.setEmployeeNumber(rs.getString(3));
			userDto.setUsername(rs.getString(4));
			userDto.setPassword(rs.getString(5));
			userDto.setLastName(rs.getString(6));
			userDto.setFirstName(rs.getString(7));
			userDto.setMiddleInitial(rs.getString(8));
			userDto.setStartDate(rs.getDate(9));
			userDto.setEndDate(rs.getDate(10));
			userDto.setEmailId(rs.getString(11));
			ezXpenseUserDtos.add(userDto);
		}
		pstmt.close();
		con.close();
		if(ezXpenseUserDtos.size()>0){
			return ezXpenseUserDtos;
		}
		else{
			throw new NoSiteAdminFoundForOrganization();
		}
		
		
	}
	
	/**
	 * 
	 * Added by Firdous on 29th August 2016
	 * Method to add the site admin inside an organization
	 * 
	 */
	@Override
	public Long addSiteAdmin(Integer organizationId, String orgName,String dbName,String employeeNumber, String username,
			String firstName, String lastName, String middleInitial, Date startDate, Date endDate, String emailId,
			String password) throws Exception {
		log.info("inside userDaoImpl============>addSiteAdmin()");
		System.out.println("inside user dao for adding site admin");
		Connection con = DBUtil.getDBConnection(dbName);
		PreparedStatement pstmt=con.prepareStatement("select * from conf_users where user_name=?");
		pstmt.setString(1,username);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			throw new UserNameAlreadyExistException();
		}
		else{
			java.sql.Date startDate1=new java.sql.Date(startDate.getTime());
			java.sql.Date endDate1=new java.sql.Date(endDate.getTime());
			CallableStatement insert_siteAdmin_stmt= con.prepareCall("{?=call ins_users(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
		
			insert_siteAdmin_stmt.registerOutParameter(1, java.sql.Types.BIGINT);
			insert_siteAdmin_stmt.setString(2,orgName);
			insert_siteAdmin_stmt.setString(3,null);
			insert_siteAdmin_stmt.setString(4,username);
			insert_siteAdmin_stmt.setString(5,password);
			insert_siteAdmin_stmt.setNull(6,java.sql.Types.INTEGER);
			insert_siteAdmin_stmt.setString(7,employeeNumber);
			insert_siteAdmin_stmt.setString(8,"Administration");
			insert_siteAdmin_stmt.setString(9,lastName);
			insert_siteAdmin_stmt.setString(10,firstName);
			insert_siteAdmin_stmt.setString(11,middleInitial);
			insert_siteAdmin_stmt.setString(12,emailId);
			insert_siteAdmin_stmt.setDate(13,startDate1);
			insert_siteAdmin_stmt.setDate(14,endDate1);
			insert_siteAdmin_stmt.setInt(15,1);
		
		System.out.println(" PSQL QUERY FOR ORGANIZATION INSERT"+insert_siteAdmin_stmt.toString());
		
		insert_siteAdmin_stmt.execute();
		Long returnValue=insert_siteAdmin_stmt.getLong(1);
		insert_siteAdmin_stmt.close();
		System.out.println("site admin added");
		
		//Adding site admin rule for this user
		CallableStatement insert_siteAdmin_role= con.prepareCall("{ call ins_user_roles(?,?,?,?,?,?,?,?,?) }");
		insert_siteAdmin_role.setNull(1,java.sql.Types.INTEGER);
		insert_siteAdmin_role.setString(2,orgName);
		insert_siteAdmin_role.setString(3,"");
		insert_siteAdmin_role.setString(4,username);
		insert_siteAdmin_role.setLong(5,returnValue);
		insert_siteAdmin_role.setString(6,"Site Admin");
		insert_siteAdmin_role.setDate(7, startDate1);
		insert_siteAdmin_role.setDate(8, endDate1);
		insert_siteAdmin_role.setInt(9,1);
		Integer result=insert_siteAdmin_role.executeUpdate();
		insert_siteAdmin_role.close();
		System.out.println("site admin role added");
		con.close();
		return returnValue;
		}
	}

	/**
	 * 
	 * Added by Firdous on 29th August 2016
	 * Method to get the user info from user id
	 * 
	 */
	public EzXpenseUserDto getUserInfoByUserId(Integer userId) throws Exception {
		log.info("inside userDaoImpl============>getUserInfoByUserId()");
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from conf_users where user_id = ?");
			ps.setInt(1, userId);
			ResultSet res = ps.executeQuery();
			EzXpenseUserDto ezXpenseUserDto=new EzXpenseUserDto();
			if(res!=null && res.next()){
				ezXpenseUserDto.setOrganizationId(res.getInt(1));
				ezXpenseUserDto.setUsername(res.getString(3));
				ezXpenseUserDto.setPassword(res.getString(4));
				res.close();
				ps.close();
				conn.close();
			return ezXpenseUserDto;
			}
			else {
				res.close();
				ps.close();
				conn.close();
				throw new UserNotFoundException();
			}
			
	}
	
	/**
	 * Added by Manjunath p on 28-08-2016
	 * @param userId
	 * @param confirmpassword
	 * @return
	 * @throws Exception
	 * method to update password for siteadmin
	 */
	
	public Integer updatePassword(Integer userId, String confirmPassword) throws Exception {
		log.info("inside userDaoImpl==========> updatePassword()");
		Connection conn =  DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(" update conf_users set passwd = ? where user_id = ? ");
			ps.setString(1,confirmPassword);
			ps.setInt(2,userId);
			Integer result=	ps.executeUpdate();
			ps.close();
			conn.close();
			return result;
		
	}
	
	/**
	 * Created by Surabhi on 26-08-2016
	 * This method is used to get DBSchema Name from Database using organization_id
	 */
	@Override
	public String getDBSchemaByDB(Long orgID) throws Exception {
		log.info("inside DivisionsDaoImpl--->getDBSchemaByDB()");
		String dbSchema=null;
		Connection con= DBUtil.getDBConnection(dbSchema);
		PreparedStatement pstmt=con.prepareStatement("select db_name from conf_organization where organization_id=?");
		pstmt.setLong(1,orgID);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
		 dbSchema=rs.getString(1);
		}
		if(null!=dbSchema){
			return dbSchema;
		}else{
			throw new DBSchemaNameNotFound();
		}
	}
	
	
	/**
	 * Added by Firdous on 29th August 2016
	 * Method to update the site admin profile 
	 */
	@Override
	public Long updateSiteAdminProfile(EzXpenseUserDto ezXpenseUserDto) throws Exception {
		log.info("inside userDaoImpl===========>updateSiteAdminProfile()");
		Connection con=DBUtil.getDBConnection(ezXpenseUserDto.getDbName());
		java.sql.Date startDate1=new java.sql.Date(ezXpenseUserDto.getStartDate().getTime());
		java.sql.Date endDate1=new java.sql.Date(ezXpenseUserDto.getEndDate().getTime());
		CallableStatement insert_siteAdmin_stmt= con.prepareCall("{?=call ins_employee(?,?,?,?,?,?,?,?,?,?,?,?) }");
		
		insert_siteAdmin_stmt.registerOutParameter(1, java.sql.Types.BIGINT);
		insert_siteAdmin_stmt.setString(2,ezXpenseUserDto.getOrgName());
		insert_siteAdmin_stmt.setString(3,null);
		insert_siteAdmin_stmt.setString(4,ezXpenseUserDto.getEmployeeNumber());
		insert_siteAdmin_stmt.setString(5,"Administration");
		
		insert_siteAdmin_stmt.setString(6, ezXpenseUserDto.getLastName());
		insert_siteAdmin_stmt.setString(7, ezXpenseUserDto.getFirstName());
		insert_siteAdmin_stmt.setString(8, ezXpenseUserDto.getMiddleInitial());
		insert_siteAdmin_stmt.setDate(9,null);
		insert_siteAdmin_stmt.setDate(10, startDate1);
		insert_siteAdmin_stmt.setDate(11,endDate1);
		insert_siteAdmin_stmt.setString(12,ezXpenseUserDto.getEmailId());
		insert_siteAdmin_stmt.setInt(13,1);
		
		System.out.println(" PSQL QUERY FOR SITE ADMIN INSERT"+insert_siteAdmin_stmt.toString());
		
		insert_siteAdmin_stmt.execute();
		Long returnValue=insert_siteAdmin_stmt.getLong(1);
		System.out.println("return value="+returnValue);
	  
		insert_siteAdmin_stmt.close();
		con.close();
		
		return returnValue;
	}
    
	
	
	/**
	 * Added by Firdous on 1st september 2016
	 * Method to change/update the password of site admin
	 * 
	 */
	@Override
	public Long changePasswordOfSiteAdmin(ChangeSiteAdminPasswordDto changeSiteAdminPasswordDto) throws Exception {
		log.info("inside userDaoImpl==============>changePasswordOfSiteAdmin()");
		Connection con=DBUtil.getDBConnection(changeSiteAdminPasswordDto.getDbName());
		CallableStatement insert_siteAdmin_stmt= con.prepareCall("{?=call ins_users(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
		insert_siteAdmin_stmt.registerOutParameter(1, java.sql.Types.BIGINT);
		insert_siteAdmin_stmt.setString(2,changeSiteAdminPasswordDto.getOrgName());
		insert_siteAdmin_stmt.setString(3,null);
		insert_siteAdmin_stmt.setString(4,changeSiteAdminPasswordDto.getUsername());
		insert_siteAdmin_stmt.setString(5, changeSiteAdminPasswordDto.getNewPassword());
		insert_siteAdmin_stmt.setNull(6,java.sql.Types.INTEGER);
		insert_siteAdmin_stmt.setString(7,null);
		insert_siteAdmin_stmt.setString(8,null);
		insert_siteAdmin_stmt.setString(9,null);
		insert_siteAdmin_stmt.setString(10,null);
		insert_siteAdmin_stmt.setString(11,null);
		insert_siteAdmin_stmt.setString(12,null);
		insert_siteAdmin_stmt.setDate(13,null);
		insert_siteAdmin_stmt.setDate(14,null);
		insert_siteAdmin_stmt.setInt(15,1);
		
		System.out.println(" PSQL QUERY FOR ORGANIZATION INSERT"+insert_siteAdmin_stmt.toString());
		
		insert_siteAdmin_stmt.execute();
		Long returnValue=insert_siteAdmin_stmt.getLong(1);
		System.out.println("return value="+returnValue);
	  
		insert_siteAdmin_stmt.close();
		con.close();
		
		return returnValue;
		
	}

	
}