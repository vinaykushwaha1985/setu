/**
 * 
 */
package com.gov.nha.bis.server.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public class RationCardDto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3559343239020444163L;
	
	private Long    guid ;
	private String    family_id ;
	private String   family_mem_id;
	private String   member_name_eng;
	private String   mother_name_eng;
	private String   father_name_eng;
	private String   spouse_name_eng;
	private String   relation_name;
	private String   gender;
	private int   year_of_birth ;
	private int   district_lgd_code ;
	private int   state_lgd_code ;
	private int   subdistrict_lgd_code ;
	private int   village_town_lgd_code ;
	private String   pincode;
	private String   rural_urban;
	private Date   creationdate ;
	private String   creationby;
	private int   status ;
	public Long getGuid() {
		return guid;
	}
	public void setGuid(Long guid) {
		this.guid = guid;
	}
	public String getFamily_id() {
		return family_id;
	}
	public void setFamily_id(String family_id) {
		this.family_id = family_id;
	}
	public String getFamily_mem_id() {
		return family_mem_id;
	}
	public void setFamily_mem_id(String family_mem_id) {
		this.family_mem_id = family_mem_id;
	}
	public String getMember_name_eng() {
		return member_name_eng;
	}
	public void setMember_name_eng(String member_name_eng) {
		this.member_name_eng = member_name_eng;
	}
	public String getMother_name_eng() {
		return mother_name_eng;
	}
	public void setMother_name_eng(String mother_name_eng) {
		this.mother_name_eng = mother_name_eng;
	}
	public String getFather_name_eng() {
		return father_name_eng;
	}
	public void setFather_name_eng(String father_name_eng) {
		this.father_name_eng = father_name_eng;
	}
	public String getSpouse_name_eng() {
		return spouse_name_eng;
	}
	public void setSpouse_name_eng(String spouse_name_eng) {
		this.spouse_name_eng = spouse_name_eng;
	}
	public String getRelation_name() {
		return relation_name;
	}
	public void setRelation_name(String relation_name) {
		this.relation_name = relation_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getYear_of_birth() {
		return year_of_birth;
	}
	public void setYear_of_birth(int year_of_birth) {
		this.year_of_birth = year_of_birth;
	}
	public int getDistrict_lgd_code() {
		return district_lgd_code;
	}
	public void setDistrict_lgd_code(int district_lgd_code) {
		this.district_lgd_code = district_lgd_code;
	}
	public int getState_lgd_code() {
		return state_lgd_code;
	}
	public void setState_lgd_code(int state_lgd_code) {
		this.state_lgd_code = state_lgd_code;
	}
	public int getSubdistrict_lgd_code() {
		return subdistrict_lgd_code;
	}
	public void setSubdistrict_lgd_code(int subdistrict_lgd_code) {
		this.subdistrict_lgd_code = subdistrict_lgd_code;
	}
	public int getVillage_town_lgd_code() {
		return village_town_lgd_code;
	}
	public void setVillage_town_lgd_code(int village_town_lgd_code) {
		this.village_town_lgd_code = village_town_lgd_code;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getRural_urban() {
		return rural_urban;
	}
	public void setRural_urban(String rural_urban) {
		this.rural_urban = rural_urban;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	public String getCreationby() {
		return creationby;
	}
	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	

}
