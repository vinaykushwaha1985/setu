package com.gov.nha.bis.server.dto;

public class SearchParam {
	
	
	
	private String stateCode;
	private String districtCode;
	private int blockCode;
	private String villageCode;
	private String townCode;
	private String wardCode;
	private String urFlag;
	private String schemeId;
	private String subSchemeId;
	
	public String getSubSchemeId() {
		return subSchemeId;
	}
	public void setSubSchemeId(String subSchemeId) {
		this.subSchemeId = subSchemeId;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public int getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(int blockCode) {
		this.blockCode = blockCode;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getUrFlag() {
		return urFlag;
	}
	public void setUrFlag(String urFlag) {
		this.urFlag = urFlag;
	}
	public String getTownCode() {
		return townCode;
	}
	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	
	
}
