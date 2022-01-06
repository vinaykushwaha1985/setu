package com.gov.nha.bis.server.dto;

public class WardDto {


	private String  ward_code  ;

	private String  ward_name  ;
	
	
	public WardDto(String ward_code, String ward_name) {
		this.ward_code=ward_code;
		this.ward_name=ward_name;
		
	}
	

	public String getWard_code() {
		return ward_code;
	}

	public void setWard_code(String ward_code) {
		this.ward_code = ward_code;
	}

	public String getWard_name() {
		return ward_name;
	}

	public void setWard_name(String ward_name) {
		this.ward_name = ward_name;
	}
	
	

	
}
