package com.ezXpense.backend.user.dao;

import java.util.ArrayList;
import java.util.Date;

import com.ezXpense.frontend.user.dto.ChangeSiteAdminPasswordDto;
import com.ezXpense.frontend.user.dto.EzXpenseUserDto;


public interface UserDao{
	public EzXpenseUserDto getezXpenseUserForAuthentication(String username) throws Exception;
	public void updateFailAttempts(String username) throws Exception;
	public void resetFailAttempts(String username) throws Exception;
	public Integer  getUserAttempts(String username) throws Exception;
	public void setUserLocked(String username) throws Exception;
	public void setUserUnLocked(String username) throws Exception;
	public ArrayList<EzXpenseUserDto> getAllSiteAdminOfOranization(String orgName,String dbName) throws Exception;
	public Long addSiteAdmin(Integer organizationId, String orgName,String dbName,String employeeNumber, String username,
			String firstName, String lastName, String middleInitial, Date startDate, Date endDate, String emailId,
			String password) throws Exception;
	public Integer updatePassword(Integer userId, String confirmPassword) throws Exception;
	public EzXpenseUserDto getUserInfoByUserId(Integer userId) throws Exception;
	public String getDBSchemaByDB(Long orgID) throws Exception;
	public Long updateSiteAdminProfile(EzXpenseUserDto ezXpenseUserDto) throws Exception;
	public Long changePasswordOfSiteAdmin(ChangeSiteAdminPasswordDto changeSiteAdminPasswordDto) throws Exception;
}