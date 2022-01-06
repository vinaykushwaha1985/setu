package com.gov.nha.bis.server.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statescheme")
@Access(value=AccessType.FIELD)
public class StateSchemeEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	//private String schemeid; 
	private String schemename;
	private Integer schemeid;
	public Integer getSchemeid() {
		return schemeid;
	}

	public void setSchemeid(Integer schemeid) {
		this.schemeid = schemeid;
	}
	private Integer state_code;
	private Integer status;
	//private Integer subschemeflag;
	public Integer getId() {
		return id;
	}

	/*
	 * public Integer getSubschemeflag() { return subschemeflag; } public void
	 * setSubschemeflag(Integer subschemeflag) { this.subschemeflag = subschemeflag;
	 * }
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * public String getSchemeid() { return schemeid; } public void
	 * setSchemeid(String schemeid) { this.schemeid = schemeid; }
	 */
	public String getSchemename() {
		return schemename;
	}
	public void setSchemename(String schemename) {
		this.schemename = schemename;
	}
	public Integer getState_code() {
		return state_code;
	}
	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
