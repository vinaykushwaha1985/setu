package com.gov.nha.bis.server.dto;

import java.util.List;

import com.gov.nha.bis.server.util.RationDataDecryptUtil;

public class DetailsEnc {


	private String family_guid;
	private String family_id;
	private String SECC_HHD;
	private String state_lgd_code;
	private String district_lgd_code;
	private String subdistrict_lgd_code;
	private String village_town_lgd_code;
	private String pincode;
	private String rural_urban;
	private List<FamilyEnc> family;
	private String field1;
	
	
	
	public String getField1() {
		return RationDataDecryptUtil.DecryptDataStr(field1);
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getFamily_guid() {
		return RationDataDecryptUtil.DecryptDataStr(family_guid);
	}
	public void setFamily_guid(String family_guid) {
		this.family_guid = family_guid;
	}
	public String getFamily_id() {
		return RationDataDecryptUtil.DecryptDataStr(family_id);
	}
	public void setFamily_id(String family_id) {
		this.family_id = family_id;
	}
	public String getSECC_HHD() {
		return RationDataDecryptUtil.DecryptDataStr(SECC_HHD);
	}
	public void setSECC_HHD(String sECC_HHD) {
		SECC_HHD = sECC_HHD;
	}
	public String getState_lgd_code() {
		return RationDataDecryptUtil.DecryptDataStr(state_lgd_code);
	}
	public void setState_lgd_code(String state_lgd_code) {
		this.state_lgd_code = state_lgd_code;
	}
	public String getDistrict_lgd_code() {
		return RationDataDecryptUtil.DecryptDataStr(district_lgd_code);
	}
	public void setDistrict_lgd_code(String district_lgd_code) {
		this.district_lgd_code = district_lgd_code;
	}
	public String getSubdistrict_lgd_code() {
		return RationDataDecryptUtil.DecryptDataStr(subdistrict_lgd_code);
	}
	public void setSubdistrict_lgd_code(String subdistrict_lgd_code) {
		this.subdistrict_lgd_code = subdistrict_lgd_code;
	}
	public String getVillage_town_lgd_code() {
		return RationDataDecryptUtil.DecryptDataStr(village_town_lgd_code);
	}
	public void setVillage_town_lgd_code(String village_town_lgd_code) {
		this.village_town_lgd_code = village_town_lgd_code;
	}
	public String getPincode() {
		return RationDataDecryptUtil.DecryptDataStr(pincode);
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getRural_urban() {
		return RationDataDecryptUtil.DecryptDataStr(rural_urban);
	}
	public void setRural_urban(String rural_urban) {
		this.rural_urban = rural_urban;
	}
	public List<FamilyEnc> getFamily() {
		return family;
	}
	public void setFamily(List<FamilyEnc> family) {
		this.family = family;
	}
	
	
		




}
