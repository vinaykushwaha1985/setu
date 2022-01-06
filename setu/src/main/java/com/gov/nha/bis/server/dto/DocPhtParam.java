package com.gov.nha.bis.server.dto;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 *
 * @author VinayKushwaha
 *
 *
**/
public class DocPhtParam {

	
	private String phtType;
	private int stateCode;
	private Long guid;
	private byte[] pht;
	private String rural_urban;
	private MultipartFile docFile;
	private int status;
	
	
	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public MultipartFile getDocFile() {
		return docFile;
	}
	public void setDocFile(MultipartFile docFile) {
		this.docFile = docFile;
	}
	public String getPhtType() {
		return phtType;
	}
	public void setPhtType(String phtType) {
		this.phtType = phtType;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public Long getGuid() {
		return guid;
	}
	public void setGuid(Long guid) {
		this.guid = guid;
	}
	public byte[] getPht() {
		return pht;
	}
	public void setPht(byte[] pht) {
		this.pht = pht;
	}
	public String getRural_urban() {
		return rural_urban;
	}
	public void setRural_urban(String rural_urban) {
		this.rural_urban = rural_urban;
	}
	
	



}
