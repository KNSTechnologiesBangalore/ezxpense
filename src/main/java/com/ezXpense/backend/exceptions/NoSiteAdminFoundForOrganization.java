package com.ezXpense.backend.exceptions;

public class NoSiteAdminFoundForOrganization extends Exception {
	private static final long serialVersionUID = 1L;
	@Override
	public String toString(){
		return "Site admin does not exist for this organization";
	}
}
