package com.ezXpense.frontend.user.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezXpense.backend.exceptions.DBSchemaNameNotFound;
import com.ezXpense.backend.exceptions.PasswordDoesNotMatchException;
import com.ezXpense.backend.organization.dao.OrganizationDao;
import com.ezXpense.backend.user.dao.UserDao;
import com.ezXpense.frontend.common.utility.EmailSender;
import com.ezXpense.frontend.user.dto.ChangePasswordDto;
import com.ezXpense.frontend.user.dto.ChangeSiteAdminPasswordDto;
import com.ezXpense.frontend.user.dto.EzXpenseUserDto;
import com.ezXpense.frontend.user.dto.OrganizationDto;



@Service("userService")
public class UserServiceImpl implements UserService{
	
	
	private static Logger log=Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private org.springframework.security.crypto.password.PasswordEncoder bCryptEncoder; 
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Resource(name = "emailSender")
	private EmailSender emailSender;
	
	@Resource(name = "organizationDao")
	private OrganizationDao organizationDao;
	
	

	private String logoImagePath;


	public String getLogoImagePath() {
		return logoImagePath;
	}


	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
	}


	/**
	 * Created by Firdous on 1st August 2016
	 * Method to get the user details by accepting username
	 * 
	 */
	public EzXpenseUserDto getezXpenseUserDetailsByUsername(String username) throws Exception{
		log.info("inside userService======> getezXpenseUserDetailsByUsername()");
		EzXpenseUserDto ezXpenseUser;
		try {
			ezXpenseUser = this.userDao.getezXpenseUserForAuthentication(username);
			return ezXpenseUser;
		} catch (UsernameNotFoundException e) {
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
    
	
	/** 
	 * Created by firdous on 1st August 2016
	 * method to get the user by accepting username
	 * 
	 */
	@Override
	public EzXpenseUserDto getezXpenseUserByUsername(String username) throws Exception {
		log.info("inside userService======> getezXpenseUserByUsername()");
		EzXpenseUserDto ezXpenseUser=this.userDao.getezXpenseUserForAuthentication(username);
		return ezXpenseUser;
	}


	/**
	 * Added by Firdous on 09th august 2016
	 * Method to save or update the organization
	 */
	public Integer saveOrUpdateOrganization(OrganizationDto organizationDto) throws Exception {
		log.info("inside userService======>saveOrUpdateOrganization()");
		if(organizationDto.getLogoImage()!=null){
			MultipartFile file=organizationDto.getLogoImage();
			String fileType=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			File destinationFile=new File(logoImagePath+"/"+new Date().getTime()+"."+fileType);
			if(!destinationFile.exists()){
				destinationFile.mkdirs();
			}
			file.transferTo(destinationFile);
			organizationDto.setLogoFile(destinationFile.getName());
		}
		Integer result=this.organizationDao.saveorUpdateOrganization(organizationDto);
		return result;
	}

	/**
	 * Added by Firdous on 9th august 2016
	 * Method to get an organization detail
	 */
	@Override
	public OrganizationDto getOrganization() throws Exception {
		log.info("inside userService======>getOrganization()");
		OrganizationDto organization=this.organizationDao.getOrganization();
		return organization;
	}

	/**
	 * Added by Firdous on 19th August 2016
	 * Method to get all the organizations present
	 */
	@Override
	public ArrayList<OrganizationDto> getAllOrganizations() throws Exception {
		log.info("inside userService======>getAllOrganizations()");
		ArrayList<OrganizationDto> organizations=this.organizationDao.getAllOrganization();
		return organizations;
	}

	/**
	 * Added by Firdous on 19th August 2016
	 * Method to get organization detail based on organizationId
	 */
	@Override
	public OrganizationDto getOrganizationDetail(Integer orgId) throws Exception {
		log.info("inside userService======>getOrganizationDetail()");
		OrganizationDto organization=this.organizationDao.getOrganizationDetail(orgId);
		return organization;
	}
	
	/**
	 * Added by Firdous on 19th August 2016
	 * Method to get all the site admin of an organization
	 * 
	 */
	@Override
	public ArrayList<EzXpenseUserDto> getAllSiteAdminOfOrganization(String orgName,String dbName) throws Exception {
		log.info("inside userService======>getAllSiteAdminOfOrganization()");
		ArrayList<EzXpenseUserDto> ezXpenseUserDtos=this.userDao.getAllSiteAdminOfOranization(orgName,dbName);
		return ezXpenseUserDtos;
	}

	/**
	 * Added by Firdous on 19th August 2016
	 * Method to add the site admin in an organization
	 * 
	 */
	@Override
	public Long addSiteAdmin(Integer organizationId, String orgName,String dbName,String employeeNumber, String username,
		String firstName, String lastName, String middleInitial, Date startDate, Date endDate, String emailId,
		String password) throws Exception {
		    log.info("inside userService=========>addSiteAdmin()");
			String encryptedPassword=bCryptEncoder.encode(password);
			Long result=this.userDao.addSiteAdmin(organizationId,orgName,dbName,employeeNumber,username,firstName,lastName,middleInitial,startDate,endDate,emailId,encryptedPassword);
			this.emailSender.sendRegistrationMail(orgName,firstName,lastName,username,password,emailId);
			return result;
	}

/**
 * Added by manjunath on 23-08-2016
 * @param userId
 * @return
 * @throws Exception
 * Method to get the user details by accepting userid
	 * 
 */
private EzXpenseUserDto getezXpenseUserByUserId(Integer userId) throws Exception {
	log.info("inside userService======> getezXpenseUserDetailsByUserId()");
	EzXpenseUserDto ezXpenseUser;
	try {
		ezXpenseUser = this.userDao.getUserInfoByUserId(userId);
		return ezXpenseUser;
	} catch (UsernameNotFoundException e) {
		throw e;
	}
	catch(Exception e){
		e.printStackTrace();
		throw e;
	}
}
/**
 * Added by Manjunath on23-08-2016
 * @return
 * @throws Exception
 * Method for updating siteadmin login password 
 * 
 */
public Integer updateUserPassword(Integer userId,ChangePasswordDto changePasswordDto)throws Exception{
	log.info("inside uuserService======>updateUserPassword()");
	EzXpenseUserDto ezXpenseUserDto=this.getezXpenseUserByUserId(userId);
	Integer updatedResult=0;
	if(changePasswordDto.getCurrentPassword()!= null && changePasswordDto.getCurrentPassword().length()>0 ) {
		String encodedCurrentPassword=changePasswordDto.getCurrentPassword().trim();
			if(bCryptEncoder.matches(encodedCurrentPassword, ezXpenseUserDto.getPassword())){
				updatedResult=this.userDao.updatePassword(userId, bCryptEncoder.encode(changePasswordDto.getConfirmPassword()));
			}
			else{
				throw new PasswordDoesNotMatchException();
			}
	}
	return updatedResult;
}

/**
 * Created by Surabhi on 26-08-2016
 * This function is to get DBSchema name based on Organisation_ID
 */
@Override
public String getDBSchema(Long orgId) throws  DBSchemaNameNotFound{
	log.info("inside userService======> getDBSchema()");
	String DBSchema = null;
	try {
		DBSchema = this.userDao.getDBSchemaByDB(orgId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return DBSchema;
}


/**
 * Created by Firdous,Method to edit or update the site admin profile
 * 
 */

@Override
public Long editSiteAdminProfile(EzXpenseUserDto ezXpenseUserDto) throws Exception {
	log.info("inside  userService======>editSiteAdminProfile" );
    Long userId=this.userDao.updateSiteAdminProfile(ezXpenseUserDto);
	return userId;
}

/**
 * Added by Firdous,method to change the site admin password and 
 * send the updated password mail to user(site admin)
 * 
 */
@Override
public Long changeSiteAdminPassword(ChangeSiteAdminPasswordDto changeSiteAdminPasswordDto) throws Exception {
	log.info("inside userService======>changeSiteAdminPassword()");
	String password=changeSiteAdminPasswordDto.getNewPassword();
	String encryptedPassword=bCryptEncoder.encode(changeSiteAdminPasswordDto.getNewPassword());
	changeSiteAdminPasswordDto.setNewPassword(encryptedPassword);
	Long userId=this.userDao.changePasswordOfSiteAdmin(changeSiteAdminPasswordDto);
	changeSiteAdminPasswordDto.setNewPassword(password);
	this.emailSender.sendPasswordresetMail(changeSiteAdminPasswordDto);
	return userId;
	
}


@Override
public Long getOrganizationIdbyUserName(String username) throws Exception {
	Long orgId=this.organizationDao.getOrganizationIdByUserName(username);
	return orgId;
}
}