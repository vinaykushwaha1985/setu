package com.gov.nha.bis.server.dto;

public class SchemeCardDetailDto {
	
	private String urban_rural;
	private Long state_code;
	private String district_code;
	private String beneficiaryid;
	private String guid;
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	private String schemeid;
	
	
	
	public SchemeCardDetailDto(String urban_rural, Long state_code, String district_code, String beneficiaryid,
			String schemeid,String guid) {
		super();
		this.urban_rural = urban_rural;
		this.state_code = state_code;
		this.district_code = district_code;
		this.beneficiaryid = beneficiaryid;
		this.schemeid = schemeid;
		this.guid= guid;
	}
	public String getUrban_rural() {
		return urban_rural;
	}
	public void setUrban_rural(String urban_rural) {
		this.urban_rural = urban_rural;
	}
	public Long getState_code() {
		return state_code;
	}
	public void setState_code(Long state_code) {
		this.state_code = state_code;
	}
	public String getDistrict_code() {
		return district_code;
	}
	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}
	public String getBeneficiaryid() {
		return beneficiaryid;
	}
	public void setBeneficiaryid(String beneficiaryid) {
		this.beneficiaryid = beneficiaryid;
	}
	public String getSchemeid() {
		return schemeid;
	}
	public void setSchemeid(String schemeid) {
		this.schemeid = schemeid;
	}
	

}
