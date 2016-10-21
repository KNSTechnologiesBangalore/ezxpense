package com.ezXpense.common.security.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.ezXpense.frontend.user.dto.EzXpenseUserDto;
import com.ezXpense.frontend.user.service.UserService;

@Service(value="userDetailsService")
public class UserDetailsServiceImpl  extends  RequestContextHolder implements UserDetailsService{

	private static Logger log=Logger.getLogger(UserDetailsServiceImpl.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Autowired
	private org.springframework.security.crypto.password.PasswordEncoder bCryptEncoder;
	
	@Autowired
	private HttpSession session;
	
	
	Boolean accountNonLocked=null;
	Boolean accountNonExpired=null;
	Boolean enabled =null;
	Integer maxLogin=null;
	 
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
	    log.info("inside loadUserByUsername()");
		/*HttpServletRequest request
		request.getAttribute("organization");*/
	   /* String orgName=(String)request.getAttribute("organization");
	    String userName=(String)request.getAttribute("username");
	    System.out.println("username="+userName);
	    System.out.println("organization name="+orgName);*/
		EzXpenseUserDto userDto=null;
		try {
			
			userDto=this.userService.getezXpenseUserDetailsByUsername(username);
			/*Integer maxLogin=userDto.getMax_login_attempts();*/
			String username1=userDto.getUsername();
			String password=userDto.getPassword();
			System.out.println("user name="+username1);
			System.out.println("password="+password);
			System.out.println("userROLE="+userDto.getUserRole());
			/* checking for user active condition*/
			if(userDto.getAccountActive()==1){
				accountNonExpired =true;
			}
			else{
				accountNonExpired =false;
			}
			/*checking if the user account is locked or not*/
			if(userDto.getAccountLocked().equals("Y")){
				accountNonLocked =false;
			}
			else{
				accountNonLocked =true;
			}
			
			Boolean enabled=true;
			Boolean credentialsNonExpired =true;
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(userDto.getUserRole().toString()));
			User user=new User(username1, password.trim(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			return (UserDetails)user;
		 
		} 
		
		catch (UsernameNotFoundException e) {
			System.out.println("inside user name not found exception");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}