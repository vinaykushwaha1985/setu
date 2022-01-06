package com.gov.nha.bis.server.model;

public class StateScheme {
	private String  schemename ;
	private String  schemeid ;
	private Integer  subschemeflag ;
	
	
	public StateScheme(String schemeid,String schemename,Integer  subschemeflag) {
		this.schemeid=schemeid;
		this.schemename=schemename;
		this.subschemeflag=subschemeflag;
	}


	public Integer getSubschemeflag() {
		return subschemeflag;
	}


	public void setSubschemeflag(Integer subschemeflag) {
		this.subschemeflag = subschemeflag;
	}


	public String getSchemename() {
		return schemename;
	}


	public void setSchemename(String schemename) {
		this.schemename = schemename;
	}


	public String getSchemeid() {
		return schemeid;
	}


	public void setSchemeid(String schemeid) {
		this.schemeid = schemeid;
	}
}
