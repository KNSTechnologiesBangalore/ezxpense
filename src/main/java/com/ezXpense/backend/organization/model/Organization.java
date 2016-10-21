package com.ezXpense.backend.organization.model;

import java.io.Serializable;
import java.sql.Date;


public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	  Integer organizationId;
	  String orgName;
	  String orgShortName;
	  String onSiteFlag;
	  Date startDate;
	  Date endDate;
	  String dbIp;
	  Integer dbPort;
	  String baseURL;
	  String dbName;
	  String currency;
	  String logoFile;
	  
	
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
