/**
 * 
 */
package com.gov.nha.bis.server.dto;

import java.io.Serializable;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public class DemoDataDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 601397773277232842L;
	
	private Long  refernceid;
	private String name;
	private String gender;
	private String dob;
	private String pncode;
	private String guardian;
	private String mobile;
	private String aadhaar;
	private String statecode;
	private String ruflag;
	private int status;
	
	private String reqip;
	private String  districtCode ;
	private String  blockCode ;
	private String villageCode;
	private String  townCode ;
	private String wardCode;
	private Long ekyctype;
	
	public Long getRefernceid() {
		return refernceid;
	}
	public void setRefernceid(Long refernceid) {
		this.refernceid = refernceid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPncode() {
		return pncode;
	}
	public void setPncode(String pncode) {
		this.pncode = pncode;
	}
	public String getGuardian() {
		return guardian;
	}
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public String getRuflag() {
		return ruflag;
	}
	public void setRuflag(String ruflag) {
		this.ruflag = ruflag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReqip() {
		return reqip;
	}
	public void setReqip(String reqip) {
		this.reqip = reqip;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
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
	public Long getEkyctype() {
		return ekyctype;
	}
	public void setEkyctype(Long ekyctype) {
		this.ekyctype = ekyctype;
	}
	
	

}
