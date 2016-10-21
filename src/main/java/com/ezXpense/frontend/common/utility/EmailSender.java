package com.ezXpense.frontend.common.utility;

import com.ezXpense.frontend.common.exceptions.MailNotSentException;
import com.ezXpense.frontend.user.dto.ChangeSiteAdminPasswordDto;
import com.ezXpense.frontend.user.dto.EzXpenseUserDto;



public interface EmailSender{
	
	public void sendRegistrationMail(String orgName,String firstName,String lastName,String username,String password,String emailId)throws MailNotSentException;

	public void sendPasswordresetMail(ChangeSiteAdminPasswordDto changeSiteAdminPasswordDto);
}