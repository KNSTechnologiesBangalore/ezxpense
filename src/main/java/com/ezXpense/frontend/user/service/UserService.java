package com.ezXpense.frontend.user.service;

import java.util.ArrayList;
import java.util.Date;

import com.ezXpense.backend.exceptions.DBSchemaNameNotFound;
import com.ezXpense.frontend.user.dto.ChangePasswordDto;
import com.ezXpense.frontend.user.dto.ChangeSiteAdminPasswordDto;
import com.ezXpense.frontend.user.dto.EzXpenseUserDto;
import com.ezXpense.frontend.user.dto.OrganizationDto;

public interface UserService{
	public EzXpenseUserDto getezXpenseUserDetailsByUsername(String username) throws Exception;

	public EzXpenseUserDto getezXpenseUserByUsername(String username) throws Exception;

	public Integer saveOrUpdateOrganization(OrganizationDto organizationDto) throws Exception;

	public OrganizationDto getOrganization() throws Exception;

	public ArrayList<OrganizationDto> getAllOrganizations() throws Exception;

	public OrganizationDto getOrganizationDetail(Integer orgId) throws Exception;

	public ArrayList<EzXpenseUserDto> getAllSiteAdminOfOrganization(String orgName,String dbName) throws Exception;

	public Long addSiteAdmin(Integer organizationId, String orgName,String dbName,String employeeNumber, String username,
			String firstName, String lastName, String middleInitial, Date startDate, Date endDate, String emailId,
			String password) throws Exception;

	public Integer updateUserPassword(Integer userid,ChangePasswordDto changePasswordDto)throws Exception;

	public String getDBSchema(Long orgId) throws DBSchemaNameNotFound;

	public Long editSiteAdminProfile(EzXpenseUserDto ezXpenseUserDto) throws Exception;

	public Long changeSiteAdminPassword(ChangeSiteAdminPasswordDto changeSiteAdminPasswordDto) throws Exception;

	public Long getOrganizationIdbyUserName(String username) throws Exception;
	
}