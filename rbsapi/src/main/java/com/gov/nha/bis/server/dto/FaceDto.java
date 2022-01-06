/**
 * 
 */
package com.gov.nha.bis.server.dto;

import java.io.Serializable;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public class FaceDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6078646047516938612L;
	private String faceid;
	private String userid;
	private String pht;
	private String pidData;
	private String ip;
	
	
	public String getFaceid() {
		return faceid;
	}
	public void setFaceid(String faceid) {
		this.faceid = faceid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPht() {
		return pht;
	}
	public void setPht(String pht) {
		this.pht = pht;
	}
	public String getPidData() {
		return pidData;
	}
	public void setPidData(String pidData) {
		this.pidData = pidData;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	

}
