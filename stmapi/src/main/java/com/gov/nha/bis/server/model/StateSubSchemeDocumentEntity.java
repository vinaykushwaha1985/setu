package com.gov.nha.bis.server.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statesubschemedocument")
@Access(value=AccessType.FIELD)
public class StateSubSchemeDocumentEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String schemeid;
	private String subschemeid; 
	private String documentid;
	private String documentname;
	private Long status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSchemeid() {
		return schemeid;
	}
	public void setSchemeid(String schemeid) {
		this.schemeid = schemeid;
	}
	public String getSubschemeid() {
		return subschemeid;
	}
	public void setSubschemeid(String subschemeid) {
		this.subschemeid = subschemeid;
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
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
}
