package com.gov.nha.bis.server.dto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
public class DemoDto {

	
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
	
	public DemoDto(Long refernceid,String name,String gender,String dob,String pncode,String guardian,String mobile,String aadhaar,
			String statecode,String ruflag,int status) {
		this.refernceid=refernceid;
		this.name=name;
		this.gender=gender;
		this.dob=dob;
		this.pncode=pncode;
		this.guardian=guardian;
		this.mobile=mobile;
		this.aadhaar=aadhaar;
		this.statecode=statecode;
		this.ruflag=ruflag;
		this.status=status;
		
		
		
		
	}
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRuflag() {
		return ruflag;
	}
	public void setRuflag(String ruflag) {
		this.ruflag = ruflag;
	}
	
	
	
	public String getStatecode() {
		return statecode;
	}


	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}


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
	
	
	
	


}
