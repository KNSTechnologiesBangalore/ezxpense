package com.ezXpense.common.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class ExUsernamePasswordAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{
	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
	        System.out.println("inside spring security filter");
	    	
	    	final String organization = request.getParameter("organization");
	        request.getSession().setAttribute("organization",organization);

	        return super.attemptAuthentication(request, response); 
	    } 
}
