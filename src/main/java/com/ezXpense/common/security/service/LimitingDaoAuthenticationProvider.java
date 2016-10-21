package com.ezXpense.common.security.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ezXpense.backend.user.dao.UserDao;
import com.ezXpense.frontend.user.service.UserService;

public class LimitingDaoAuthenticationProvider extends DaoAuthenticationProvider{

	
	
	
	  @Autowired
	  private UserService userService;
	  @Resource
	  private UserDao userDao;
	    
	   /**
	    * Method to authenticate the user
	    */
	    @Override
	    public Authentication authenticate(Authentication authentication)
	        throws AuthenticationException,LockedException,UsernameNotFoundException,AccountExpiredException {
	     
	    	Authentication authencation1=null;
	    	try {
	    		authencation1=super.authenticate(authentication);
	    		try{
	    			
	    			userDao.resetFailAttempts(authencation1.getName());//if user enter the right credentials and he is a valid user
	    		}
	    		catch(Exception e){
	    		
	    		}
	        return authencation1;
	       
	    	}
	    	catch(UsernameNotFoundException e){
	    		System.out.println("user name not found exception");
	    		throw e;
	    	}
	    	catch(AccountExpiredException e){
	    		throw e;
	    	}
	    	catch(LockedException e){
		    	  throw e; 
		    }
	    	catch (BadCredentialsException e) {
	    	
	    		try{
	    			userDao.updateFailAttempts(authentication.getName());//if user enters the bad credentials
	    		}
	    		catch(Exception e1){
	    			System.out.println("update fail catch");
	    		}
	    		throw e;
	    	}
	    	
	   }
}
