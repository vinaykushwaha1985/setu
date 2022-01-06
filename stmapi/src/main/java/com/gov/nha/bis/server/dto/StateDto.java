package com.gov.nha.bis.server.dto;

public class StateDto {
	
	private int  state_code ;
	private String  state_name_english ;

	
	public StateDto(int  state_code,String state_name_english) {
			this.state_code=state_code;
			this.state_name_english=state_name_english;
		
	}
	
	
	public int getState_code() {
		return state_code;
	}
	public void setState_code(int state_code) {
		this.state_code = state_code;
	}
	
	public String getState_name_english() {
		return state_name_english;
	}
	public void setState_name_english(String state_name_english) {
		this.state_name_english = state_name_english;
	}
		

}
