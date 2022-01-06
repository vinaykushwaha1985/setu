package com.gov.nha.bis.server.dto;

public class StateSubSchemeDocumentDto {
	
	private String  documentid ;
	private String  documentname ;
	
	
	public StateSubSchemeDocumentDto(String documentid, String documentname) {
		super();
		this.documentid = documentid;
		this.documentname = documentname;
	}
	public String getDocumentid() {
		return documentid;
	}
	public void setDocumentid(String documentid) {
		this.documentid = documentid;
	}
	public String getDocumentname() {
		return documentname;
	}
	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}
	
	
}
