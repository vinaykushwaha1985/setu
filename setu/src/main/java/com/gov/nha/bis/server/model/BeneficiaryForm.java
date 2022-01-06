package com.gov.nha.bis.server.model;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
public class BeneficiaryForm {
	
	private String RefId;
	private String kycName;
	private String kycGender;
	private String kycDob;
	private String kycFName;
	private String KycAdr;

	private String seccName;
	private String seccGender;
	private String seccDob;
	private String seccFName;
	private String seccAdr;
	
	private String captcha;
	
	private String matchStatus;
	
	
	
	
	
	public String getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getRefId() {
		return RefId;
	}
	public void setRefId(String refId) {
		RefId = refId;
	}
	public String getKycName() {
		return kycName;
	}
	public void setKycName(String kycName) {
		this.kycName = kycName;
	}
	public String getKycGender() {
		return kycGender;
	}
	public void setKycGender(String kycGender) {
		this.kycGender = kycGender;
	}
	public String getKycDob() {
		return kycDob;
	}
	public void setKycDob(String kycDob) {
		this.kycDob = kycDob;
	}
	public String getKycFName() {
		return kycFName;
	}
	public void setKycFName(String kycFName) {
		this.kycFName = kycFName;
	}
	public String getKycAdr() {
		return KycAdr;
	}
	public void setKycAdr(String kycAdr) {
		KycAdr = kycAdr;
	}
	public String getSeccName() {
		return seccName;
	}
	public void setSeccName(String seccName) {
		this.seccName = seccName;
	}
	public String getSeccGender() {
		return seccGender;
	}
	public void setSeccGender(String seccGender) {
		this.seccGender = seccGender;
	}
	public String getSeccDob() {
		return seccDob;
	}
	public void setSeccDob(String seccDob) {
		this.seccDob = seccDob;
	}
	public String getSeccFName() {
		return seccFName;
	}
	public void setSeccFName(String seccFName) {
		this.seccFName = seccFName;
	}
	public String getSeccAdr() {
		return seccAdr;
	}
	public void setSeccAdr(String seccAdr) {
		this.seccAdr = seccAdr;
	}

	
	

}
