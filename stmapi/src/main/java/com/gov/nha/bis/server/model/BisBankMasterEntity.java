package com.gov.nha.bis.server.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bis_bank_master")
@Access(value=AccessType.FIELD)
public class BisBankMasterEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String bankname;
	private Long status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	

}
