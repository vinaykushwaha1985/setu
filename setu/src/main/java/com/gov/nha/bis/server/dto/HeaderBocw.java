package com.gov.nha.bis.server.dto;

public class HeaderBocw {

	private String error_code;
	private String error_messgae;
	private String version;
	private String id_Number;
	private String  id_Type;
	
	
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_messgae() {
		return error_messgae;
	}
	public void setError_messgae(String error_messgae) {
		this.error_messgae = error_messgae;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getId_Number() {
		return id_Number;
	}
	public void setId_Number(String id_Number) {
		this.id_Number = id_Number;
	}
	public String getId_Type() {
		return id_Type;
	}
	public void setId_Type(String id_Type) {
		this.id_Type = id_Type;
	}
	
	

}
