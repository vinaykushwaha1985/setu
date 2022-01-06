package com.gov.nha.bis.server.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statesubscheme")
@Access(value=AccessType.FIELD)
public class StateSubSchemeEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String subschemeid; 
	private String subschemename;
	private String schemeid;
	private Integer state_code;
	public Integer getState_code() {
		return state_code;
	}
	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubschemeid() {
		return subschemeid;
	}
	public void setSubschemeid(String subschemeid) {
		this.subschemeid = subschemeid;
	}
	public String getSubschemename() {
		return subschemename;
	}
	public void setSubschemename(String subschemename) {
		this.subschemename = subschemename;
	}
	public String getSchemeid() {
		return schemeid;
	}
	public void setSchemeid(String schemeid) {
		this.schemeid = schemeid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
