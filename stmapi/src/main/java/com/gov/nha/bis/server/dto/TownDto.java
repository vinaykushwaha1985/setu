package com.gov.nha.bis.server.dto;

public class TownDto {
	
	private String  town_code ;
	private String  town_name;
	
	
	public TownDto(String town_code,String town_name) {
		
		this.town_code=town_code;
		this.town_name=town_name;
		
		
	}
	
	public String getTown_code() {
		return town_code;
	}
	public void setTown_code(String town_code) {
		this.town_code = town_code;
	}
	public String getTown_name() {
		return town_name;
	}
	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}
	
	

}
