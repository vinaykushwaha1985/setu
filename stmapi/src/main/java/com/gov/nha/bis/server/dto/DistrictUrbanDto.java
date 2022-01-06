package com.gov.nha.bis.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY, valueFilter = EmptyListFilter.class)
public class DistrictUrbanDto {

	
	private String  district_code ;
	private String  district_name_english ;
	
	
	public DistrictUrbanDto(String district_code,String district_name_english) {
		this.district_code=district_code;
		this.district_name_english=district_name_english;
	}
	
	public String getDistrict_code() {
		return district_code;
	}
	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}
	public String getDistrict_name_english() {
		return district_name_english;
	}
	public void setDistrict_name_english(String district_name_english) {
		this.district_name_english = district_name_english;
	}
	


}
