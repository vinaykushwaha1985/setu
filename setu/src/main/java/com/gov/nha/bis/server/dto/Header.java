package com.gov.nha.bis.server.dto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
public class Header {

	private String ID_Type;
	private String ID_Number;
	private String  error_code;
	private String error_msg;
	public String getID_Type() {
		return ID_Type;
	}
	public void setID_Type(String iD_Type) {
		ID_Type = iD_Type;
	}
	public String getID_Number() {
		return ID_Number;
	}
	public void setID_Number(String iD_Number) {
		ID_Number = iD_Number;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	
	
}
