package com.ezXpense.frontend.user.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


public class OrganizationDto {

	
	
	  private Integer organizationId;
	  private String orgName;
	  private String orgShortName;
	  private String onSiteFlag;
	  private Date startDate;
	  private Date endDate;
	  private String dbIp;
	  private Integer dbPort;
	  private String baseURL;
	  private String dbName;
	  private String currency;
	  private  String logoFile;
	  private  MultipartFile logoImage;
	
	
	
	public MultipartFile getLogoImage() {
		return logoImage;
	}
	public void setLogoImage(MultipartFile logoImage) {
		this.logoImage = logoImage;
	}
	public String getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgShortName() {
		return orgShortName;
	}
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	public String getOnSiteFlag() {
		return onSiteFlag;
	}
	public void setOnSiteFlag(String onSiteFlag) {
		this.onSiteFlag = onSiteFlag;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getDbIp() {
		return dbIp;
	}
	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}
	public Integer getDbPort() {
		return dbPort;
	}
	public void setDbPort(Integer dbPort) {
		this.dbPort = dbPort;
	}
	public String getBaseURL() {
		return baseURL;
	}
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
