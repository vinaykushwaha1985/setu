package com.gov.nha.bis.server.model;

public class BeneficiarySchemeForm {

	
	private String  tin_npr ;
	private String  hhd_no ;
	private String  name ;
	private String  fathername ;
	private String  spousenm ;
	private String dob;
	private String genderid;
	private String mothername;
	private String relation;
	private String guid;
	
	
	private String  stateName ;
	private String  districtName;
	private String  villageName;
	private String  townName;
	
	
	private String stateCodeRural;
	
	private String districtCodeRural;
	private String blockCodeRural;
	
	private String villageCodeRural;
	
	private String stateCodeUrban;
	private String districtCodeUrban;
	private String townCodeUrban;
	private String WardCodeUrban;
	
	
	
	private String ruralUrbanFlag;
	
	private String card_status;
	
	
	private Long  refernceid_d;
	private String name_d;
	private String gender_d;
	private String dob_d;
	private String pncode_d;
	private String guardian_d;
	private String mobile_d;
	private String aadhaar_d;
	
	
	
	private String family_id;
	private String family_mem_id;
	private String member_name_eng;
	private String mother_name_eng;
	private String father_name_eng;
	private String spouse_name_eng;
	private int year_of_birth;
	private String relation_name;
	private String gender;
	
	private String blockName;
	private String wardName;
	
	
	
	
	
	
	
	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
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

	public Long getRefernceid_d() {
		return refernceid_d;
	}

	public void setRefernceid_d(Long refernceid_d) {
		this.refernceid_d = refernceid_d;
	}

	public String getName_d() {
		return name_d;
	}

	public void setName_d(String name_d) {
		this.name_d = name_d;
	}

	public String getGender_d() {
		return gender_d;
	}

	public void setGender_d(String gender_d) {
		this.gender_d = gender_d;
	}

	public String getDob_d() {
		return dob_d;
	}

	public void setDob_d(String dob_d) {
		this.dob_d = dob_d;
	}

	public String getPncode_d() {
		return pncode_d;
	}

	public void setPncode_d(String pncode_d) {
		this.pncode_d = pncode_d;
	}

	public String getGuardian_d() {
		return guardian_d;
	}

	public void setGuardian_d(String guardian_d) {
		this.guardian_d = guardian_d;
	}

	public String getMobile_d() {
		return mobile_d;
	}

	public void setMobile_d(String mobile_d) {
		this.mobile_d = mobile_d;
	}

	public String getAadhaar_d() {
		return aadhaar_d;
	}

	public void setAadhaar_d(String aadhaar_d) {
		this.aadhaar_d = aadhaar_d;
	}

	public String getStateCodeRural() {
		return stateCodeRural;
	}
	
	public void setStateCodeRural(String stateCodeRural) {
		this.stateCodeRural = stateCodeRural;
	}
	public String getDistrictCodeRural() {
		return districtCodeRural;
	}
	public void setDistrictCodeRural(String districtCodeRural) {
		this.districtCodeRural = districtCodeRural;
	}
	public String getBlockCodeRural() {
		return blockCodeRural;
	}
	public void setBlockCodeRural(String blockCodeRural) {
		this.blockCodeRural = blockCodeRural;
	}
	public String getVillageCodeRural() {
		return villageCodeRural;
	}
	public void setVillageCodeRural(String villageCodeRural) {
		this.villageCodeRural = villageCodeRural;
	}
	public String getStateCodeUrban() {
		return stateCodeUrban;
	}
	public void setStateCodeUrban(String stateCodeUrban) {
		this.stateCodeUrban = stateCodeUrban;
	}
	public String getDistrictCodeUrban() {
		return districtCodeUrban;
	}
	public void setDistrictCodeUrban(String districtCodeUrban) {
		this.districtCodeUrban = districtCodeUrban;
	}
	public String getTownCodeUrban() {
		return townCodeUrban;
	}
	public void setTownCodeUrban(String townCodeUrban) {
		this.townCodeUrban = townCodeUrban;
	}
	public String getWardCodeUrban() {
		return WardCodeUrban;
	}
	public void setWardCodeUrban(String wardCodeUrban) {
		WardCodeUrban = wardCodeUrban;
	}
	public String getCard_status() {
		return card_status;
	}
	public void setCard_status(String card_status) {
		this.card_status = card_status;
	}
	public String getRuralUrbanFlag() {
		return ruralUrbanFlag;
	}
	public void setRuralUrbanFlag(String ruralUrbanFlag) {
		this.ruralUrbanFlag = ruralUrbanFlag;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getTin_npr() {
		return tin_npr;
	}
	public void setTin_npr(String tin_npr) {
		this.tin_npr = tin_npr;
	}
	public String getHhd_no() {
		return hhd_no;
	}
	public void setHhd_no(String hhd_no) {
		this.hhd_no = hhd_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getSpousenm() {
		return spousenm;
	}
	public void setSpousenm(String spousenm) {
		this.spousenm = spousenm;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGenderid() {
		return genderid;
	}
	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}
	public String getMothername() {
		return mothername;
	}
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	
	


}
