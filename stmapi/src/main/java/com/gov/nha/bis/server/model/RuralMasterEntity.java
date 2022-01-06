package com.gov.nha.bis.server.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_lgd_consolidated")
@Access(value=AccessType.FIELD)
public class RuralMasterEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer state_code;
	
	@Column(name="state_name_english")
	private String state_name_english;
	
	@Column(name="district_code")
	private Integer district_code;
	
	@Column(name="district_name_english")
	private String district_name_english;
	
	@Column(name="block_code")
	private Integer block_code;
	
	@Column(name="block_name_english")
	private String block_name_english;
	
	@Column(name="village_code")
	private Integer village_code;
	
	@Column(name="village_name_english")
	private String village_name_english;
	
	@Column(name="village_mdds")
	private String village_mdds;
	
	@Column(name="status")
	private Integer  status;

	
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getState_code() {
		return state_code;
	}

	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}

	public String getState_name_english() {
		return state_name_english;
	}

	public void setState_name_english(String state_name_english) {
		this.state_name_english = state_name_english;
	}

	public Integer getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(Integer district_code) {
		this.district_code = district_code;
	}

	public String getDistrict_name_english() {
		return district_name_english;
	}

	public void setDistrict_name_english(String district_name_english) {
		this.district_name_english = district_name_english;
	}

	public Integer getBlock_code() {
		return block_code;
	}

	public void setBlock_code(Integer block_code) {
		this.block_code = block_code;
	}

	public String getBlock_name_english() {
		return block_name_english;
	}

	public void setBlock_name_english(String block_name_english) {
		this.block_name_english = block_name_english;
	}

	public Integer getVillage_code() {
		return village_code;
	}

	public void setVillage_code(Integer village_code) {
		this.village_code = village_code;
	}

	public String getVillage_name_english() {
		return village_name_english;
	}

	public void setVillage_name_english(String village_name_english) {
		this.village_name_english = village_name_english;
	}

	public String getVillage_mdds() {
		return village_mdds;
	}

	public void setVillage_mdds(String village_mdds) {
		this.village_mdds = village_mdds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	



}
