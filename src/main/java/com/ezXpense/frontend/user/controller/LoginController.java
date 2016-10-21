package com.ezXpense.frontend.user.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




import com.ezXpense.backend.exceptions.DatabaseDoesNotExistException;
import com.ezXpense.backend.exceptions.NoSiteAdminFoundForOrganization;
import com.ezXpense.backend.exceptions.OrganizationsNotFoundException;
import com.ezXpense.backend.exceptions.PasswordDoesNotMatchException;
import com.ezXpense.backend.exceptions.UserNotFoundException;
import com.ezXpense.frontend.user.dto.ChangePasswordDto;
import com.ezXpense.frontend.user.dto.EzXpenseUserDto;
import com.ezXpense.frontend.user.dto.OrganizationDto;
import com.ezXpense.frontend.user.service.UserService;

import twitter4j.org.json.JSONObject;


/**
 * Created By Bhagya On July 14th, 2016
 * 
 * WEB Controller class for performing the Login Functionalities
 * @param <ChangePasswordDto>
 * @param <UserService>
 */
@Controller("webLoginController")
public class LoginController{
	
	private static Logger log=Logger.getLogger(LoginController.class);
	
	
	@Resource(name="userService")
	private UserService userService;
	
	
	/**
	 * Created by bhagya on july 14th,2016
	 * Initiate the login Page
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String login(){
		log.info("inside LoginController------> login()");
		
		try{
			 return "login";
		}
		
		catch(Exception e){
			e.printStackTrace();
			log.error("error while login");
			return "error";
		}
	}
	/**
	 * Created by Firdous on 1st August 2016
	 * @param request
	 * @param map
	 * @return
	 * 
	 * Method for handling the process after login success
	 * modified by Manjunath for sitesdmin after login
	 */
	
	@RequestMapping(value="/loginsuccess.do",method=RequestMethod.GET)
	public String redirectToLoginSuccess(HttpServletRequest request,Map<String, Object> map){
		log.info("inside LoginController----> redirectToLoginSuccess()");
		HttpSession session=request.getSession(true);
		try{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			System.out.println("inside login success");
			String user=auth.getName();
			
			EzXpenseUserDto userDto= this.userService.getezXpenseUserByUsername(user);
			map.put("username",userDto.getUsername());
			map.put("userId",userDto.getUserId());
			map.put("userRole",userDto.getUserRole());
			/*String dbSchema=this.userService.getDBSchema(userDto.getOrgId());*/
			Long organizationId=this.userService.getOrganizationIdbyUserName(userDto.getUsername());
			String dbSchema=this.userService.getDBSchema(organizationId);
			System.out.println("user role="+userDto.getUserRole());
			session.setAttribute("username",userDto.getUsername());
			session.setAttribute("userId",userDto.getUserId());
			session.setAttribute("userRole",userDto.getUserRole());
			session.setAttribute("orgId",organizationId);
			session.setAttribute("dbSchema",dbSchema);
			if(userDto.getUserRole().equals("Admin")){
				System.out.println("REDIRECTING TO HOME PAGE");
			    return "redirect:/adminhome.do";
			}
			else if(userDto.getUserRole().equalsIgnoreCase("Site Admin")){
				System.out.println("REDIRECTING TO SITEADMIN HOME PAGE");
			    return "redirect:/siteAdminHome.do";
			}
			else{
				return "homePage";
			}
		}
		catch(UserNotFoundException e){
			e.printStackTrace();
			log.error("user not found"+e.toString());
			String error="User Not Found";
			map.put("message",error);
			return "error";
		}
		catch(Exception e){
			e.printStackTrace();
			String error="Problem While Login, Please Try Again Later";
			log.error("error in login:"+e.toString());
			map.put("message",error);
			return "error";
		}
	}
	
	
	/** 
	 * Added by Firdous on 25th august 2016
	 * @param request
	 * @param response
	 * @param organizationDto
	 * @param map
	 * @param orgId
	 * @return
	 *//*
//	@SuppressWarnings("unchecked")
	@RequestMapping(value="/adminhome.do",method=RequestMethod.GET)
	public String adminHome(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("organizationDto")OrganizationDto organizationDto,Map<String, Object> map,@RequestParam(value="orgId",required=false)Integer orgId){
		log.info("inside LoginController---->adminHome()");
		HttpSession session=request.getSession(true);
		try{
			Integer organiaztionId=orgId;
			System.out.println("inside admin home");
			String username=(String)session.getAttribute("username");
			Integer userId=(Integer)session.getAttribute("userId");
			String userRole=(String)session.getAttribute("userRole");
			OrganizationDto organization=null;
			ArrayList<OrganizationDto> organizations=this.userService.getAllOrganizations();
			if(null!=orgId){
				 organization=this.userService.getOrganizationDetail(orgId);
			}
			else{
				organization=this.userService.getOrganization();
			}
			map.put("organizations",organizations);
			map.put("organizationDto",organization);
			map.put("userId",userId);
			map.put("username",username);
			map.put("userRole",userRole);
			System.out.println("organization id="+organiaztionId);
				return "admin/adminHome";
		}
		catch(Exception e){
			System.out.println("error");
			e.printStackTrace();
			log.error("error while displaying home page");
			return "error";
		}
	}	*/
	/** 
	 * Added by Firdous on 25th august 2016
	 * @param request
	 * @param response
	 * @param organizationDto
	 * @param map
	 * @param orgId
	 * @return
	 */
//	@SuppressWarnings("unchecked")
	@RequestMapping(value="/adminhome.do",method=RequestMethod.GET)
	public String adminHome(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map,@RequestParam(value="orgId",required=false)Integer orgId){
		log.info("inside LoginController---->adminHome()");
		HttpSession session=request.getSession(true);
		try{
			Integer organiaztionId=orgId;
			String username=(String)session.getAttribute("username");
			Integer userId=(Integer)session.getAttribute("userId");
			String userRole=(String)session.getAttribute("userRole");
			
			OrganizationDto organization=null;
			ArrayList<EzXpenseUserDto> userDtos=null;
			ArrayList<OrganizationDto> organizations=this.userService.getAllOrganizations();
			if(null!=orgId){
				try{
				   organization=this.userService.getOrganizationDetail(orgId);
				   userDtos=this.userService.getAllSiteAdminOfOrganization(organization.getOrgName(),organization.getDbName());
				   map.put("siteAdmins",userDtos);
				 }
				
				catch(DatabaseDoesNotExistException e){
					
					organization=this.userService.getOrganization();
					map.put("siteAdmins",null);
					map.put("status",e.getMessage());
					
				}
				 catch(NoSiteAdminFoundForOrganization e){
					organization=this.userService.getOrganization();
					map.put("siteAdmins",null);
					map.put("status",e.getMessage());
				 }
			}
			else{
				organization=this.userService.getOrganization();
				map.put("siteAdmins",null);
			}
			map.put("organizations",organizations);
			map.put("organizationDto",organization);
			map.put("userId",userId);
			map.put("username",username);
			map.put("userRole",userRole);
			return "admin/adminHome";
		}
		
		catch(Exception e){
			e.printStackTrace();
			log.error("error while displaying home page");
			return "error";
		}
	}	
	/**
	 * Added by Manjunath p on 31-08-2016
	 * method for displaying siteadmin homepage
	 * @return
	 */
	@RequestMapping(value="/siteAdminHome.do",method=RequestMethod.GET)
	public String siteAdminHome(){
		log.info("inside passwordChange------>siteAdminHome()");
			 return "admin/siteAdminHome";
	}
	
	
	/**
	 * Added by Manjunath 25-08-2016
	 * @return
	 * get method for changepassword for site admin
	 */
	@RequestMapping(value="/changepassword.do",method=RequestMethod.GET)
	public String passwordChange(){
		log.info("inside passwordChange------> passwordChange()");
			 return "/common/changepassword";
	}
	/**
	 * added by manjunath 26-08-2016
	 * @param request
	 * @param response
	 * @param changePasswordDto
	 * @param map
	 * @return
	 * method for handling password change function
	 */
	@RequestMapping(value = "/changepassword.do", method = RequestMethod.POST)
	public String submitChangePassword(HttpServletRequest request,@ModelAttribute("ChangePassword")ChangePasswordDto changePasswordDto,Map<String, Object> map){
		log.info("inside LoginController------>submitChangePassword()");
		HttpSession session = request.getSession(true);
		Integer userId = (Integer) session.getAttribute("userId");	
		try {
			Integer updatedResult=this.userService.updateUserPassword(userId, (com.ezXpense.frontend.user.dto.ChangePasswordDto) changePasswordDto);
			if(updatedResult>0){
				map.put("message", "Password Changed Successfully");
				
			}
			else{
				map.put("message", "Password Not Updated Successfully");
			}
			return "common/changepassword";
		} catch (PasswordDoesNotMatchException e) {
			e.printStackTrace();
			String error="Password Does Not Match With Current User Password";
			map.put("errormessage",error);
			return "/common/changepassword";
		}catch (Exception e) {
			e.printStackTrace();
			return "/common/changepassword";
		}
	
	}
	@ResponseBody
	@RequestMapping(value="/getallorganization.do",method=RequestMethod.GET)
	public String getAllOrganization(HttpServletRequest request,HttpServletResponse response){
		log.info("inside loginController------->getAllOrganization()");
		try{
			System.out.println("inside controller for getting organization list");
			JSONObject organizations=new JSONObject();
			ArrayList<OrganizationDto> organizationsList=this.userService.getAllOrganizations();
			for(OrganizationDto organizationDto: organizationsList){
				JSONObject orgainizationJSON=new JSONObject();
				orgainizationJSON.accumulate("organizationId",organizationDto.getOrganizationId());
				orgainizationJSON.accumulate("orgName",organizationDto.getOrgName());
				orgainizationJSON.accumulate("dbName",organizationDto.getDbName());
				organizations.append("organization",orgainizationJSON);
			}
			System.out.println("length of organization="+organizations.length());
			return organizations.toString();
		}
		catch(OrganizationsNotFoundException e){
			e.printStackTrace();
			log.error("Organization not found");
			return "error";
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("failed to get the organization list");
			return "error";
		}
	}
	
	/**
	 * Added by Firdous
	 * Method to set the dbName in property file..to get the connection with particular db
	 * @param dbName
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getorganizationdetail.do",method=RequestMethod.GET)
	public String getOrganizationDetail(@RequestParam("dbName")String dbName,HttpServletRequest request,HttpServletResponse response){
		log.info("inside loginController------->getOrganizationDetail()");
		try{
			System.out.println("dbname="+dbName);
			File configFile = new File("ezXpense.properties");
			
			try {
			Properties props = new Properties();
			props.setProperty("dbName",dbName);
			FileWriter writer = new FileWriter(configFile);
			props.store(writer,"");
			writer.close();
			} catch (FileNotFoundException ex) {
			// file does not exist
			} catch (IOException ex) {
			// I/O error
			}
			return "success";
		}
		
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(value="/logoutsuccess.do", method=RequestMethod.GET)
	public String logoutHandler(Map<String, Object>map,HttpServletRequest request,HttpServletResponse response){
		log.info("inside logoutHandler()");
		try{
			
			HttpSession session=request.getSession(false);
			if(null!=session)
				session.invalidate();
				response.reset();
			    response.setHeader("Cache-Control", "no-cache");
			    response.setHeader("Pragma", "no-cache");
			    response.setHeader("Cache-Control", "no-store");
			    response.setHeader("Cache-Control", "must-revalidate");
			    response.setDateHeader("Expires", 0); 
			    String message="Successfully Logged Out";
			    map.put("message", message);			  
			    return "logout";
		}
		catch(Exception e){
			log.info("User Logged Out"+ e.toString());
			e.printStackTrace();
			String message="Error While Logging out the User";
			map.put("message", message);
			return "error";
		}
	}
	
	
	
}