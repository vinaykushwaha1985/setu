package com.gov.nha.bis.server.dto;

import java.util.List;

public class DetailsBocw {


	 private String family_guid;
	 private String family_id;
	 private String state_lgd_code;
	 private String district_lgd_code;
	 private String subdistrict_lgd_code;
	 private String village_town_lgd_code;
	 private String rural_urban;
	 private String pincode;
	 private String  remarks;
	 private String secc_HHD;
	 
	 private List<FamilyBocw> family;
	 
	 
	 
	public List<FamilyBocw> getFamily() {
		return family;
	}
	public void setFamily(List<FamilyBocw> family) {
		this.family = family;
	}
	public String getFamily_guid() {
		return family_guid;
	}
	public void setFamily_guid(String family_guid) {
		this.family_guid = family_guid;
	}
	public String getFamily_id() {
		return family_id;
	}
	public void setFamily_id(String family_id) {
		this.family_id = family_id;
	}
	public String getState_lgd_code() {
		return state_lgd_code;
	}
	public void setState_lgd_code(String state_lgd_code) {
		this.state_lgd_code = state_lgd_code;
	}
	public String getDistrict_lgd_code() {
		return district_lgd_code;
	}
	public void setDistrict_lgd_code(String district_lgd_code) {
		this.district_lgd_code = district_lgd_code;
	}
	public String getSubdistrict_lgd_code() {
		return subdistrict_lgd_code;
	}
	public void setSubdistrict_lgd_code(String subdistrict_lgd_code) {
		this.subdistrict_lgd_code = subdistrict_lgd_code;
	}
	public String getVillage_town_lgd_code() {
		return village_town_lgd_code;
	}
	public void setVillage_town_lgd_code(String village_town_lgd_code) {
		this.village_town_lgd_code = village_town_lgd_code;
	}
	public String getRural_urban() {
		return rural_urban;
	}
	public void setRural_urban(String rural_urban) {
		this.rural_urban = rural_urban;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSecc_HHD() {
		return secc_HHD;
	}
	public void setSecc_HHD(String secc_HHD) {
		this.secc_HHD = secc_HHD;
	}
	
	
	



}
