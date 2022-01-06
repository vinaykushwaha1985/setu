package com.gov.nha.bis.server.dto;

public class DistrictDto {
	
	private int  district_code ;
	private String  district_name_english ;
	
	
	public DistrictDto(int district_code,String district_name_english) {
		this.district_code=district_code;
		this.district_name_english=district_name_english;
	}
	
	public int getDistrict_code() {
		return district_code;
	}
	public void setDistrict_code(int district_code) {
		this.district_code = district_code;
	}
	public String getDistrict_name_english() {
		return district_name_english;
	}
	public void setDistrict_name_english(String district_name_english) {
		this.district_name_english = district_name_english;
	}
	
}
