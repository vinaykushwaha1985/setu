package com.gov.nha.bis.server.dto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
public class StateDto {
	
	private String  state_code ;
	private String  district_code ;
	private String  state_name_english ;
	private String  district_name_english ;
	private String  block_name_english ;
	private String  village_name_english ;
	private int  block_code ;
	private String  village_code ;
	private String  towncode ;
	private String  wardid ;
	
	
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getDistrict_code() {
		return district_code;
	}
	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}
	public String getState_name_english() {
		return state_name_english;
	}
	public void setState_name_english(String state_name_english) {
		this.state_name_english = state_name_english;
	}
	public String getDistrict_name_english() {
		return district_name_english;
	}
	public void setDistrict_name_english(String district_name_english) {
		this.district_name_english = district_name_english;
	}
	public String getBlock_name_english() {
		return block_name_english;
	}
	public void setBlock_name_english(String block_name_english) {
		this.block_name_english = block_name_english;
	}
	public String getVillage_name_english() {
		return village_name_english;
	}
	public void setVillage_name_english(String village_name_english) {
		this.village_name_english = village_name_english;
	}
	public int getBlock_code() {
		return block_code;
	}
	public void setBlock_code(int block_code) {
		this.block_code = block_code;
	}
	public String getVillage_code() {
		return village_code;
	}
	public void setVillage_code(String village_code) {
		this.village_code = village_code;
	}
	public String getTowncode() {
		return towncode;
	}
	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}
	public String getWardid() {
		return wardid;
	}
	public void setWardid(String wardid) {
		this.wardid = wardid;
	}
	
	
	

}
