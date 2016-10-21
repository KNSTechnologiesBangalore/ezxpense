package com.ezXpense.frontend.divisions.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezXpense.frontend.divisions.dto.DivisionsDto;
import com.ezXpense.frontend.divisions.service.DivisionService;
import com.ezXpense.frontend.user.service.UserService;

/**
 * Created by Surabhi on 23-08-2016
 * Division Controller 
 */
@RequestMapping("/siteadmin")
@Controller("DivisionController")
public class DivisionController {
	private static Logger log=Logger.getLogger(DivisionController.class);
	
	@Resource(name="divisionService")
	private DivisionService divisionService;
	
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * Created by Surabhi
	 * Method to read all the divisions from database
	 */
	@RequestMapping(value="/division.do",method = RequestMethod.GET)
	public String showAllDivisions(HttpServletRequest request,Model model,Map<String, Object> map){
		log.info("inside DivisionController---> showAllDivisions()");
		HttpSession session=request.getSession();
		System.out.println("inside showAllDivisions()");
		Long orgId=(Long)session.getAttribute("orgId");
       	try {	  		
       		String dbSchema=null;
       		ArrayList<DivisionsDto> divisionsDtos=this.divisionService.getAllDivisions(dbSchema);
   			model.addAttribute("divisionsDtos",divisionsDtos);
   			return "division/division";
       	}
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}	
	}	
	
	/**
	 * Created by Surabhi
	 * Method to add new division into database
	 */
	@RequestMapping(value="/divadd.do",method = RequestMethod.POST)
	public String addDivision(@ModelAttribute("division") DivisionsDto division,HttpServletRequest request,Model model,Map<String, Object> map,BindingResult result,RedirectAttributes redAttribs){
		log.info("inside DivisionController---> addDivision()");
		HttpSession session=request.getSession();
		System.out.println("inside addDivision() POST");
		Long organizationId=(Long)session.getAttribute("orgId");
		
		division.setOrganisationId(organizationId);
     	Integer loggedUserId=(Integer)session.getAttribute("userId");
		//Integer loggedUserId=1;
		division.setLoggedUserId(loggedUserId);
       	try {	
       		String dbSchema=(String)session.getAttribute("dbSchema");
       		System.out.println("division name:"+division.getDivisionName());
       		this.divisionService.saveOrUpdateDivision(dbSchema, division);
       		redAttribs.addFlashAttribute("data","success");		
			return "redirect:/siteadmin/division.do";
       	}
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}	
	}	
	
	/**
	 * Created by Surabhi
	 * Method to edit division and save it into database
	 */
	@RequestMapping(value="/editprofile.do",method=RequestMethod.GET)
	public String editDivisionName(@ModelAttribute("divisionsDto") DivisionsDto division,HttpServletRequest request,Model model,
			RedirectAttributes redAttribs){
		log.info("inside DivisionController---> editprofile()");
		HttpSession session=request.getSession();
		System.out.println("inside editprofile() POST");
		System.out.println("division name:"+division.getDivisionName()+" division Id:"+division.getDivisionId());
		
	   	try {
	   		String dbSchema=(String)session.getAttribute("dbSchema");
	   		DivisionsDto newdivision=getDivisionInfo(dbSchema, division.getDivisionId());
	   		newdivision.setDivisionName(division.getDivisionName());
	   		newdivision.setLoggedUserId(1);
	   		System.out.println("after="+newdivision.toString());
	   		this.divisionService.saveOrUpdateDivision(dbSchema,newdivision);
	   		redAttribs.addFlashAttribute("data","success");		
			return "redirect:/siteadmin/division.do";
	   	}
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}	
	}	
	
	/**
	 * Created by Surabhi
	 * Method to get the a division by divisionId
	 */
	public DivisionsDto getDivisionInfo(String dbSchema,Integer divId){
		log.info("inside DivisionController---> getDivisionInfo()");
		System.out.println("inside getDivisionInfo() ");
		try {		
			   		DivisionsDto division=this.divisionService.getDivisionbyID(dbSchema,divId);
			   		System.out.println("before="+division.toString());
					return division;
					}
				catch(Exception e){
					e.printStackTrace();
					return null;
				}	
	
		
	}
	
	/**
	 * Created by Surabhi
	 * Method to delete division from database
	 */
	@RequestMapping(value="/divdelete.do",method = RequestMethod.POST)
	public String deleteDivision(@ModelAttribute("division") DivisionsDto division,HttpServletRequest request,Model model,Map<String, Object> map,BindingResult result,RedirectAttributes redAttribs){
		log.info("inside DivisionController---> deleteDivision()");
		HttpSession session=request.getSession();
		Integer divisionId=division.getDivisionId();
		System.out.println("inside deleteDivision() "+"\n divisionId="+divisionId);
    	Long organizationId=(Long)session.getAttribute("orgId");
//		
       	try {	
       		String dbSchema=(String)session.getAttribute("dbSchema");
       		this.divisionService.deleteDivisionByID(dbSchema, divisionId, organizationId );
       		redAttribs.addFlashAttribute("data","success");		
			return "redirect:/siteadmin/division.do?divisionId="+divisionId;
       	}
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}	
	}	
	
}



    