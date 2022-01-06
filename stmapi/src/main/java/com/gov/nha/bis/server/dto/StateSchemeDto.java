package com.gov.nha.bis.server.dto;

public class StateSchemeDto {
	
	private String  schemename ;
	private Integer  schemeid ;
	//private Integer  subschemeflag ;
	
	//,Integer  subschemeflag
	public StateSchemeDto(Integer schemeid,String schemename) {
		this.schemeid=schemeid;
		this.schemename=schemename;
		//this.subschemeflag=subschemeflag;
	}


	/*
	 * public Integer getSubschemeflag() { return subschemeflag; }
	 * 
	 * 
	 * public void setSubschemeflag(Integer subschemeflag) { this.subschemeflag =
	 * subschemeflag; }
	 */

	public String getSchemename() {
		return schemename;
	}


	public void setSchemename(String schemename) {
		this.schemename = schemename;
	}


	public Integer getSchemeid() {
		return schemeid;
	}


	public void setSchemeid(Integer schemeid) {
		this.schemeid = schemeid;
	}
	
	
	
	

}
