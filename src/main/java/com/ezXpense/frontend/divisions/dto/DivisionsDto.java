package com.ezXpense.frontend.divisions.dto;

/**
 * DTO for Divisions Table
 * Created by Surabhi on 16-08-2016
 */

public class DivisionsDto {
	
	private Long organisationId;
	private Integer divisionId;
	private String divisionName;
	private Integer loggedUserId;
	
	
	public Long getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}
	public Integer getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public Integer getLoggedUserId() {
		return loggedUserId;
	}
	public void setLoggedUserId(Integer loggedUserId) {
		this.loggedUserId = loggedUserId;
	}
	public String toString(){
		return "organisationId="+organisationId+",divisionId="+divisionId+",divisionName="+divisionName+",loggedUserId="+loggedUserId;
	}
}
