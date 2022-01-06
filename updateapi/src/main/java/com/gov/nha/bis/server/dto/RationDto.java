package com.gov.nha.bis.server.dto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
public class RationDto {

	
	private int state_lgd_code;
	private int district_lgd_code;
	private int subdistrict_lgd_code;
	private int village_town_lgd_code;
	private String pincode;
	private String rural_urban;
	private String family_id;
	private Long guid;
	private String family_mem_id;
	private String member_name_eng;
	private String mother_name_eng;
	private String father_name_eng;
	private String spouse_name_eng;
	private int year_of_birth;
	private String relation_name;
	private String gender;
	private String creationby;
	private int status;
	
	
	public RationDto(Long guid,String family_id,String family_mem_id,String member_name_eng,
			String mother_name_eng,String father_name_eng,String spouse_name_eng,String relation_name,
			String gender,int year_of_birth,int district_lgd_code,int state_lgd_code,int subdistrict_lgd_code,
			int village_town_lgd_code,String pincode,String rural_urban,String creationby,int status) {
		
		this.guid=guid;
		this.family_id=family_id;
		this.family_mem_id=family_mem_id;
		this.member_name_eng=member_name_eng;
		this.mother_name_eng=mother_name_eng;
		this.father_name_eng=father_name_eng;
		this.spouse_name_eng=spouse_name_eng;
		this.relation_name=relation_name;
		this.gender=gender;
		this.year_of_birth=year_of_birth;
		this.district_lgd_code=district_lgd_code;
		this.state_lgd_code=state_lgd_code;
		this.subdistrict_lgd_code=subdistrict_lgd_code;
		this.village_town_lgd_code=village_town_lgd_code;
		this.pincode=pincode;
		this.rural_urban=rural_urban;
		this.creationby=creationby;
		this.status=status;
		
		
		
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



	public String getFamily_id() {
		return family_id;
	}
	public void setFamily_id(String family_id) {
		this.family_id = family_id;
	}
	public Long getGuid() {
		return guid;
	}
	public void setGuid(Long guid) {
		this.guid = guid;
	}
	public int getState_lgd_code() {
		return state_lgd_code;
	}
	public void setState_lgd_code(int state_lgd_code) {
		this.state_lgd_code = state_lgd_code;
	}
	public int getDistrict_lgd_code() {
		return district_lgd_code;
	}
	public void setDistrict_lgd_code(int district_lgd_code) {
		this.district_lgd_code = district_lgd_code;
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
	public int getYear_of_birth() {
		return year_of_birth;
	}
	public void setYear_of_birth(int year_of_birth) {
		this.year_of_birth = year_of_birth;
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

	
	
	
	


}
