package com.gov.nha.bis.server.dto;

public class UserProfileDto {
	
	private String mobNumber;
	//private String loginId="7075892319";
	private String loginId;
	private String applicationType="TMS";
	private String loginMode="MOBILE";
	//private String loginMode="LOGIN_ID";
	private String  stateCode="42";
	private String userName;
	private String password;
	private String sResult;
	
	private String ip;
	private String operationType="BIS2.0_GUID";
	
	private String guid ="0901510361932";

	
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
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
	public String getsResult() {
		return sResult;
	}
	public void setsResult(String sResult) {
		this.sResult = sResult;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getMobNumber() {
		return mobNumber;
	}
	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getLoginMode() {
		return loginMode;
	}
	public void setLoginMode(String loginMode) {
		this.loginMode = loginMode;
	}
	
}
