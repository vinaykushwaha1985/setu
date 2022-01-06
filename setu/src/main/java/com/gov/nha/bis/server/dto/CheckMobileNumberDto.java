package com.gov.nha.bis.server.dto;

public class CheckMobileNumberDto {
	
	private String  stateCode="9";

	private String operationType="BIS2.0_GUID";
	
	private String guid ="0901510361932";
	
	
	
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	
}
