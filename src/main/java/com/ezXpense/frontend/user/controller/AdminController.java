package com.ezXpense.frontend.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezXpense.backend.exceptions.UserNameAlreadyExistException;
import com.ezXpense.frontend.user.dto.ChangePasswordDto;
import com.ezXpense.frontend.user.dto.ChangeSiteAdminPasswordDto;
import com.ezXpense.frontend.user.dto.EzXpenseUserDto;
import com.ezXpense.frontend.user.dto.OrganizationDto;
import com.ezXpense.frontend.user.service.UserService;

@RequestMapping("/admin")
@Controller("AdminController")
public class AdminController {
	
	private static Logger log=Logger.getLogger(AdminController.class);
	
	
	@Resource(name="userService")
	private UserService userService;
	
	
	/**
	 * Added by Firdous on 20 August 2016
	 * Method to add the add the new organization
	 * @param organizationDto
	 * @param map
	 * @param result
	 * @param redAttribs
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addorganization.do",method=RequestMethod.POST)
	  public String addOrganization(@ModelAttribute("organizationDto")OrganizationDto organizationDto,Map<String, Object> map,BindingResult result,RedirectAttributes redAttribs) throws Exception{
		
			log.info("inside AdminController-------> addOrganization()");
	       	try {
			 	Integer savedResult=this.userService.saveOrUpdateOrganization(organizationDto);
			    if(savedResult>0){
					redAttribs.addFlashAttribute("status","Organization added Successfully");		
					return "redirect:/adminhome.do?orgId="+savedResult;
			    }
			    else {
					throw new Exception();
			    }
				
			}
			catch(Exception e){
				e.printStackTrace();
				return "error";
			}
		
		}
	
	
	
	/**
	 * Added by Firdous on 19 August 2016
	 * @param request
	 * @param response
	 * @param organizationDto
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addorganization.do",method=RequestMethod.GET)
	public String addOrganization(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("organizationDto")OrganizationDto organizationDto,Map<String,Object> map) {
		log.info("inside AdminController------>addOrganization()");
		HttpSession session=request.getSession(true);
		try{
			System.out.println("inside add new organization");
			String username=(String)session.getAttribute("username");
			Integer userId=(Integer)session.getAttribute("userId");
			String userRole=(String)session.getAttribute("userRole");
			map.put("userId",userId);
			map.put("username",username);
			map.put("userRole",userRole);
			return "success";
			
		}
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * Added by Firdous on 19 August 2016
	 * @param request
	 * @param response
	 * @param organizationDto
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/homepage.do",method=RequestMethod.GET)
	public String adminHomePage(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("organizationDto")OrganizationDto organizationDto,Map<String, Object> map){
		log.info("inside AdminController----->adminHomePage()");
		HttpSession session=request.getSession(true);
		try{
			String username=(String)session.getAttribute("username");
			Integer userId=(Integer)session.getAttribute("userId");
			String userRole=(String)session.getAttribute("userRole");
			ArrayList<OrganizationDto> organizations=this.userService.getAllOrganizations();
			map.put("organizations",organizations);
			map.put("userId",userId);
			map.put("username",username);
			map.put("userRole",userRole);
			return "admin/adminHome";
		}
		catch(Exception e){
			System.out.println("error");
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * Added by Firdous on 19 August 2016
	 * @param request
	 * @param response
	 * @param organizationDto
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getorganization.do",method=RequestMethod.GET)
    public String getOrganizationDetail(Map<String, Object> map,@RequestParam(value="orgId",required=false) String orgId){
		log.info("inside getOrganizationDetail()");
		return "success";
	}
	
	
	/**
	 * Added by Firdous on 24 August,Method to add the site admin in an organization
	 * @param organizationId
	 * @param orgName
	 * @param dbName
	 * @param employeeNumber
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param middleInitial
	 * @param startDate
	 * @param endDate
	 * @param emailId
	 * @param password
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value="/addsiteadmin.do",method=RequestMethod.POST)
	public String addSiteAdmin(@RequestParam("organizationId")Integer organizationId,@RequestParam("orgName") String orgName,@RequestParam("dbName") String dbName,@RequestParam("employeeNumber") String employeeNumber,
			@RequestParam("username") String username,@RequestParam("firstName")String firstName,@RequestParam("lastName") String lastName,@RequestParam("middleInitial")String middleInitial,@RequestParam("startDate")Date startDate,@RequestParam("endDate") Date endDate,@RequestParam("emailId") String emailId,@RequestParam("password") String password){
		log.info("inside AdminController------->addSiteAdmin()");
		try{
			Long result=this.userService.addSiteAdmin(organizationId,orgName,dbName,employeeNumber,username,firstName,lastName,middleInitial,startDate,endDate,emailId,password);
			if(result>0){
				return "success";
			}
			else{
				throw new Exception();
			}
		}
		catch(UserNameAlreadyExistException e){
			log.error("Username Already Exists "+e.toString());
			return "username already exists";
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("Error While Adding Site Admin");
			return "error";
		}
	}
	
	
	/**
	 * Added by Firdous on 25th August 2016,method to edit the site admin profile
	 * @param ezXpenseUserDto
	 * @param map
	 * @param redAttribs
	 * @return
	 * 
	 */
	@RequestMapping(value="/editprofile.do",method=RequestMethod.POST)
	public String editSiteAdminDetails(@ModelAttribute("ezXpenseUserDto") EzXpenseUserDto ezXpenseUserDto,Map<String,Object>map,RedirectAttributes redAttribs){
		log.info("inside AdminController-------> editSiteAdminDetails()");
		try{
			Long result=this.userService.editSiteAdminProfile(ezXpenseUserDto);
			if(result>0){
				redAttribs.addFlashAttribute("status","Profile successfully updated");	
				return "redirect:/adminhome.do?orgId="+ezXpenseUserDto.getOrgId();
			}
			else{
				throw new Exception();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("Error While Adding Site Admin");
			return "error";
		}
	}
	/**
	 * Added by Firdous
	 * method to change the password of site admin by admin
	 */
	@RequestMapping(value="/changepasswordofsiteadmin.do",method=RequestMethod.POST)
	public String updatepasswordOfSiteAdmin(@ModelAttribute("changeSiteAdminPasswordDto")ChangeSiteAdminPasswordDto changeSiteAdminPasswordDto,Map<String, Object> map,RedirectAttributes redAttribs){
			try{
		       Long userId=this.userService.changeSiteAdminPassword(changeSiteAdminPasswordDto);
		       if(userId>0){
		    	       redAttribs.addFlashAttribute("status","Password successfully changed");	
		               return "redirect:/adminhome.do?orgId="+changeSiteAdminPasswordDto.getOrgId();
		       }
		       else{
		    	   throw new Exception();
		       }
			}
			catch(Exception e){
				e.printStackTrace();
				return "error";
			}
		
	}
}
