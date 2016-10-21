/**
 *  Created by    : Bhagya

 * Created Date	  : November 11th, 2015
 * file Name	  : CustomAuthenticationFailureHandler.java
 * Purpose		  : Handling Authentication Failure mechanism and redirecting to User according to the Login Failure Pages..
 * Type			  : Support Utility/Service
 */


package com.ezXpense.common.security.service;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.ezXpense.backend.user.dao.UserDao;
import com.ezXpense.backend.user.dao.UserDaoImpl;


/*
 * Custom Authentication Failure Adapter,
 * Handles Authentication Failure Conditions With Detailed Messages for Each Failure....
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private static Logger log=Logger.getLogger(UserDaoImpl.class);
	/* pre defined */
	@Resource
	UserDao userDao;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		super.onAuthenticationFailure(request, response, exception);
		String message;
		System.out.println("EXCEPTION "+exception.getMessage()+" "+exception.getClass());
		if(exception.getClass().isAssignableFrom(CredentialsExpiredException.class)){
			log.error("credentials expired exception");
			message="Your Account is Expired, Please Contact the Admin for further Details";
		}		
		else if(exception.getClass().isAssignableFrom(LockedException.class)){
			log.error("Account locked excption");
			message="Your Account is Locked, Please Contact the Admin for further Details";
		}
		else if(exception.getClass().isAssignableFrom(AccountExpiredException.class)){
			log.error("account expired exception");
			message="Your Account is InActive, Please Contact the Admin for further Details";
		}

		else if(exception.getClass().isAssignableFrom(DisabledException.class)){
			log.error("account disabled exception");
			message="Your Account is not yet Activated, Please click on the Activation link on your Mail";			
		}
		else if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)){
			log.error("username not found exception");
			message="Username does not exist";
			
		}
		else if(exception.getClass().isAssignableFrom(BadCredentialsException.class)){
			log.error("invalid username or password exception");
			message="invalid username or password";
		}
		
		else if(exception.getClass().isAssignableFrom(Exception.class)){
			message="Error occured";
			
		}
		else{
			message="invalid login credentials";
		}
        request.getSession().setAttribute("error", message );      
		
	}
	
	
}
