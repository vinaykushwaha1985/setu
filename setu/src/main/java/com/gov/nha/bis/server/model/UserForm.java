package com.gov.nha.bis.server.model;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
public class UserForm {
	
	private String userName;
	private String password;
	private String sResult;
	private String token ;
	private String umSecretKey;
	private String ip;
	
	
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUmSecretKey() {
		return umSecretKey;
	}
	public void setUmSecretKey(String umSecretKey) {
		this.umSecretKey = umSecretKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	
	

}
