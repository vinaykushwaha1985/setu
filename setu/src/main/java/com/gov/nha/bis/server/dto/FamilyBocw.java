package com.gov.nha.bis.server.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FamilyBocw implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -262881601478888443L;
	private String family_mem_guid_id;
	private String family_mem_id;
	private String member_name_eng;
	private String member_name_regional;
	private String father_name_eng;
	private String father_name_regional;
	private String spouse_name_eng;
	private String spouse_name_regional;
	private String mother_name_eng;
	private String mother_name_regional;
	private String relation_name;
	private String gender;
	private String mobile_number;
	private String year_of_birth;
	private String address;
	private miscMemberDetails miscMemberDetails;
	
	private String ahl_TIN;
	public String getFamily_mem_guid_id() {
		return family_mem_guid_id;
	}
	public void setFamily_mem_guid_id(String family_mem_guid_id) {
		this.family_mem_guid_id = family_mem_guid_id;
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
	public String getMember_name_regional() {
		return member_name_regional;
	}
	public void setMember_name_regional(String member_name_regional) {
		this.member_name_regional = member_name_regional;
	}
	public String getFather_name_eng() {
		return father_name_eng;
	}
	public void setFather_name_eng(String father_name_eng) {
		this.father_name_eng = father_name_eng;
	}
	public String getFather_name_regional() {
		return father_name_regional;
	}
	public void setFather_name_regional(String father_name_regional) {
		this.father_name_regional = father_name_regional;
	}
	public String getSpouse_name_eng() {
		return spouse_name_eng;
	}
	public void setSpouse_name_eng(String spouse_name_eng) {
		this.spouse_name_eng = spouse_name_eng;
	}
	public String getSpouse_name_regional() {
		return spouse_name_regional;
	}
	public void setSpouse_name_regional(String spouse_name_regional) {
		this.spouse_name_regional = spouse_name_regional;
	}
	public String getMother_name_eng() {
		return mother_name_eng;
	}
	public void setMother_name_eng(String mother_name_eng) {
		this.mother_name_eng = mother_name_eng;
	}
	public String getMother_name_regional() {
		return mother_name_regional;
	}
	public void setMother_name_regional(String mother_name_regional) {
		this.mother_name_regional = mother_name_regional;
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
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getYear_of_birth() {
		return year_of_birth;
	}
	public void setYear_of_birth(String year_of_birth) {
		this.year_of_birth = year_of_birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public miscMemberDetails getMiscMemberDetails() {
		return miscMemberDetails;
	}
	public void setMiscMemberDetails(miscMemberDetails miscMemberDetails) {
		this.miscMemberDetails = miscMemberDetails;
	}
	public String getAhl_TIN() {
		return ahl_TIN;
	}
	public void setAhl_TIN(String ahl_TIN) {
		this.ahl_TIN = ahl_TIN;
	}
	
	
	

}
