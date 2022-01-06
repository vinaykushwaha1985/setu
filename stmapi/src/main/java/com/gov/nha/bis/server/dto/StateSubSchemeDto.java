package com.gov.nha.bis.server.dto;

public class StateSubSchemeDto {
	
	private String  subschemename ;
	private String  subschemeid ;
	
	
	public StateSubSchemeDto(String subschemename, String subschemeid) {
		super();
		this.subschemename = subschemename;
		this.subschemeid = subschemeid;
	}
	
	public String getSubschemename() {
		return subschemename;
	}
	public void setSubschemename(String subschemename) {
		this.subschemename = subschemename;
	}
	public String getSubschemeid() {
		return subschemeid;
	}
	public void setSubschemeid(String subschemeid) {
		this.subschemeid = subschemeid;
	}
	
	
}
