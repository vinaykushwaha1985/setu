package com.gov.nha.bis.server.dto;

public class VillageDto {
	
	private String  village_name_english ;
	private int  village_code ;
	
	public VillageDto(int village_code,String village_name_english) {
		this.village_code=village_code;
		this.village_name_english=village_name_english;
		
	}
	
	
	public String getVillage_name_english() {
		return village_name_english;
	}
	public void setVillage_name_english(String village_name_english) {
		this.village_name_english = village_name_english;
	}
	public int getVillage_code() {
		return village_code;
	}
	public void setVillage_code(int village_code) {
		this.village_code = village_code;
	}


}
