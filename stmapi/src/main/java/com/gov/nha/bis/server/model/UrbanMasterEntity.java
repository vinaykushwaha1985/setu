package com.gov.nha.bis.server.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_lgd_master_latest_urban")
@Access(value=AccessType.FIELD)
public class UrbanMasterEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer state_code;
	private String state_name;
	private Integer district_code;
	private String district_name;
	private String tehsil_code;
	private String tehsil_name;
	private String vt_code;
	private String vt_name;
	private String ward_code;
	private String rural_urban;
	private String state_code_mdds;
	private String district_code_mdds;
	private String sub_district_code_mdds;
	private String village_code_mddds;
	private String village_code_lgd;
	private String village_name_lgd;
	private String gp_code_lgd;
	private String gp_name_lgd;
	private String block_code_lgd;
	private String block_name_lgd;
	private String sub_district_code_lgd;
	private String sub_district_name_lgd;
	private String district_code_lgd;
	private String district_name_lgd;
	private String state_code_lgd;
	private String state_name_lgd;
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
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public Integer getDistrict_code() {
		return district_code;
	}
	public void setDistrict_code(Integer district_code) {
		this.district_code = district_code;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getTehsil_code() {
		return tehsil_code;
	}
	public void setTehsil_code(String tehsil_code) {
		this.tehsil_code = tehsil_code;
	}
	public String getTehsil_name() {
		return tehsil_name;
	}
	public void setTehsil_name(String tehsil_name) {
		this.tehsil_name = tehsil_name;
	}
	public String getVt_code() {
		return vt_code;
	}
	public void setVt_code(String vt_code) {
		this.vt_code = vt_code;
	}
	public String getVt_name() {
		return vt_name;
	}
	public void setVt_name(String vt_name) {
		this.vt_name = vt_name;
	}
	public String getWard_code() {
		return ward_code;
	}
	public void setWard_code(String ward_code) {
		this.ward_code = ward_code;
	}
	public String getRural_urban() {
		return rural_urban;
	}
	public void setRural_urban(String rural_urban) {
		this.rural_urban = rural_urban;
	}
	public String getState_code_mdds() {
		return state_code_mdds;
	}
	public void setState_code_mdds(String state_code_mdds) {
		this.state_code_mdds = state_code_mdds;
	}
	public String getDistrict_code_mdds() {
		return district_code_mdds;
	}
	public void setDistrict_code_mdds(String district_code_mdds) {
		this.district_code_mdds = district_code_mdds;
	}
	public String getSub_district_code_mdds() {
		return sub_district_code_mdds;
	}
	public void setSub_district_code_mdds(String sub_district_code_mdds) {
		this.sub_district_code_mdds = sub_district_code_mdds;
	}
	public String getVillage_code_mddds() {
		return village_code_mddds;
	}
	public void setVillage_code_mddds(String village_code_mddds) {
		this.village_code_mddds = village_code_mddds;
	}
	public String getVillage_code_lgd() {
		return village_code_lgd;
	}
	public void setVillage_code_lgd(String village_code_lgd) {
		this.village_code_lgd = village_code_lgd;
	}
	public String getVillage_name_lgd() {
		return village_name_lgd;
	}
	public void setVillage_name_lgd(String village_name_lgd) {
		this.village_name_lgd = village_name_lgd;
	}
	public String getGp_code_lgd() {
		return gp_code_lgd;
	}
	public void setGp_code_lgd(String gp_code_lgd) {
		this.gp_code_lgd = gp_code_lgd;
	}
	public String getGp_name_lgd() {
		return gp_name_lgd;
	}
	public void setGp_name_lgd(String gp_name_lgd) {
		this.gp_name_lgd = gp_name_lgd;
	}
	public String getBlock_code_lgd() {
		return block_code_lgd;
	}
	public void setBlock_code_lgd(String block_code_lgd) {
		this.block_code_lgd = block_code_lgd;
	}
	public String getBlock_name_lgd() {
		return block_name_lgd;
	}
	public void setBlock_name_lgd(String block_name_lgd) {
		this.block_name_lgd = block_name_lgd;
	}
	public String getSub_district_code_lgd() {
		return sub_district_code_lgd;
	}
	public void setSub_district_code_lgd(String sub_district_code_lgd) {
		this.sub_district_code_lgd = sub_district_code_lgd;
	}
	public String getSub_district_name_lgd() {
		return sub_district_name_lgd;
	}
	public void setSub_district_name_lgd(String sub_district_name_lgd) {
		this.sub_district_name_lgd = sub_district_name_lgd;
	}
	public String getDistrict_code_lgd() {
		return district_code_lgd;
	}
	public void setDistrict_code_lgd(String district_code_lgd) {
		this.district_code_lgd = district_code_lgd;
	}
	public String getDistrict_name_lgd() {
		return district_name_lgd;
	}
	public void setDistrict_name_lgd(String district_name_lgd) {
		this.district_name_lgd = district_name_lgd;
	}
	public String getState_code_lgd() {
		return state_code_lgd;
	}
	public void setState_code_lgd(String state_code_lgd) {
		this.state_code_lgd = state_code_lgd;
	}
	public String getState_name_lgd() {
		return state_name_lgd;
	}
	public void setState_name_lgd(String state_name_lgd) {
		this.state_name_lgd = state_name_lgd;
	}
	
	



}
