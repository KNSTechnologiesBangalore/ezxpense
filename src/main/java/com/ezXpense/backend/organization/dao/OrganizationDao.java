package com.ezXpense.backend.organization.dao;


import java.util.ArrayList;

import com.ezXpense.frontend.user.dto.OrganizationDto;

public interface OrganizationDao {

	public Integer saveorUpdateOrganization(OrganizationDto organization) throws Exception;

	public OrganizationDto getOrganization() throws Exception;

	public ArrayList<OrganizationDto> getAllOrganization() throws Exception;

	public OrganizationDto getOrganizationDetail(Integer orgId) throws Exception;

	public Long getOrganizationIdByUserName(String username) throws Exception;

}
