package com.ezXpense.frontend.common.utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.apache.log4j.Logger;

import com.ezXpense.frontend.common.exceptions.MailNotSentException;
import com.ezXpense.frontend.user.dto.ChangeSiteAdminPasswordDto;
import com.ezXpense.frontend.user.dto.EzXpenseUserDto;




@Service("emailSender")
public class EmailSenderImpl implements EmailSender{
	private static Logger log=Logger.getLogger(EmailSenderImpl.class);
	
	/**
	 * handles sending mail. Autowired using the bean defined in name mailSender
	 */
	@Autowired
	private JavaMailSender mailSender;

	
	/**
	 * Template engine. Autowired using the bean defined in the name velocityEngine
	 */
	@Autowired
	private VelocityEngine velocityEngine;

	/**
	 * 
	 * holds the server url (used to build the forgot password link in mail body)
	 * 
	 */
	private String serverHost;
	private String imagesPath;
	
	public String getServerHost() {
		return serverHost;
	}


	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	
	public String getImagesPath() {
		return imagesPath;
	}


	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}


	@Override
	public void sendRegistrationMail(final String orgName,final String firstName,final String lastName,final String username,final String password,final String emailId) throws MailNotSentException {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				message.setTo(emailId);
				message.setSubject("Account Created");
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("email",emailId);
				if(firstName!=null && firstName.trim().length()>0 && lastName!=null && lastName.trim().length()>0){
					map.put("name",firstName+" "+lastName);
				}
				else{
					map.put("name",username);
				}
				map.put("username",username);
				map.put("password",password);
				map.put("orgName",orgName);
				map.put("serverURL", serverHost);
				//System.out.println(map.toString());
				String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/ezXpense/Templates/registration.vm","UTF-8", map);
				message.setText(content,true);
				FileSystemResource res = new FileSystemResource(new File(
	        		       imagesPath+ "/logo.png"));
	        		message.addInline("logo", res);
	        		
	        	FileSystemResource res2=new FileSystemResource(new File(imagesPath+"/fb.png"));
	        	message.addInline("fb", res2);
	        	
	        	FileSystemResource res3=new FileSystemResource(new File(imagesPath+"/twitter.png"));
	        	message.addInline("twitter", res3);
	        	
			}
		};
		this.mailSender.send(preparator);
		
	}


	@Override
	public void sendPasswordresetMail(final ChangeSiteAdminPasswordDto changeSiteAdminPasswordDto) {
MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				message.setTo(changeSiteAdminPasswordDto.getEmailId());
				message.setSubject("PASSWORD IS SUCCESSFULLY RESET");
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("email",changeSiteAdminPasswordDto.getEmailId());
				map.put("name",changeSiteAdminPasswordDto.getUsername());
				map.put("password",changeSiteAdminPasswordDto.getNewPassword());
				map.put("orgName",changeSiteAdminPasswordDto.getOrgName());
				map.put("serverURL", serverHost);
				//System.out.println(map.toString());
				String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/ezXpense/Templates/passwordresetmail.vm","UTF-8", map);
				message.setText(content,true);
				FileSystemResource res = new FileSystemResource(new File(
	        		       imagesPath+ "/logo.png"));
	        		message.addInline("logo", res);
	        		
	        	FileSystemResource res2=new FileSystemResource(new File(imagesPath+"/fb.png"));
	        	message.addInline("fb", res2);
	        	
	        	FileSystemResource res3=new FileSystemResource(new File(imagesPath+"/twitter.png"));
	        	message.addInline("twitter", res3);
	        	
			}
		};
		this.mailSender.send(preparator);
		
	}
	
}