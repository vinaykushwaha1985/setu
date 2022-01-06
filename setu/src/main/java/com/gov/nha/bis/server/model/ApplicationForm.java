package com.gov.nha.bis.server.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
public class ApplicationForm {

	
	private String stateCodeRural;
	
	private String districtCodeRural;
	private String blockCodeRural;
	
	private String villageCodeRural;
	
	private String stateCodeUrban;
	private String districtCodeUrban;
	private String townCodeUrban;
	private String WardCodeUrban;
	private String ruralUrbanFlag;
	
	
	
	private String  stateCode ;
	private String  districtCode ;
	private String  blockCode ;
	private String villageCode;
	private String  townCode ;
	private String wardCode;
	private String modeFlag;
	
	
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
	private String blockName;
	private String wardName;
	
	private String demoName;
	private String demoFname;
	private String demoGender;
	private String demoDob;
	private String demoMobile;
	private String demoEmail;
	private String aadhar;
	
	private String otpTxn;
	
	private String otp;
	
	private String uidtoken;
	private String  refernceid;
	private String  kycName ;
	private String  kycGender;
	private String  kycDob;
	private String  kycAdr;
	private String  pht;
		
	
	private String card_status;
	
	private String cardPht;
	
	private String pidData;
	
	private String bioType;
	
	
	
	

private String operationType;
private String state_code;    

private String ahl_tin;
private String ahl_hhid;
private String verification_status;
private String uid_aadhaar;
private String uid_token;
private String uid_auth_date;
private String uid_auth_type;
private String photo_s;
private String name_s;
private String dob_s;
private String gender_s;
private String address_s;

private String sub_distCode;
private String vtcCode;
private String post_s;
private String name_d;
private String dob_d;
private String gender_d;
private String address_d;
private String state_name_d;
private String district_name_d;
private String sub_dist_d;
private String vtc_d;
private String post_d;
private String pin_code_d;
private String mobile_member;
	
private String authType;	
	
private String aadharMobile;

private String peName;
private String peGurName;
private String peGuid;
private String familyidScheme;



public String getFamilyidScheme() {
	return familyidScheme;
}

public void setFamilyidScheme(String familyidScheme) {
	this.familyidScheme = familyidScheme;
}
private String aadhar_auth;



public String getAadhar_auth() {
	return aadhar_auth;
}

public void setAadhar_auth(String aadhar_auth) {
	this.aadhar_auth = aadhar_auth;
}

public String getPeName() {
	return peName;
}

public void setPeName(String peName) {
	this.peName = peName;
}

public String getPeGurName() {
	return peGurName;
}

public void setPeGurName(String peGurName) {
	this.peGurName = peGurName;
}

public String getPeGuid() {
	return peGuid;
}

public void setPeGuid(String peGuid) {
	this.peGuid = peGuid;
}

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



	public String getAadharMobile() {
	return aadharMobile;
}
public void setAadharMobile(String aadharMobile) {
	this.aadharMobile = aadharMobile;
}
	public String getAuthType() {
	return authType;
}
public void setAuthType(String authType) {
	this.authType = authType;
}
	private String father_name_d;
	private String mobile_d;
	
	private String father_name_s;
	private String state_name_s;
	private String district_name_s;
	private String vtc_s;
	
	
	
	private String otpType;
	private String pmjayId;
	
	private String nameBy;
	private String nameWith;
	private String genderBy;
	private String genderWith;
	private String fatherBy;
	private String fatherWith;
	
	private String rationCard;
	private String familyDetail;
	
	
	
	
	// Ration card properties
	private String family_id;
	private String family_mem_id;
	private String member_name_eng;
	private String mother_name_eng;
	private String father_name_eng;
	private String spouse_name_eng;
	private String year_of_birth;
	private String relation_name;
	private String gender;

	private String state_lgd_code;
	private String district_lgd_code;
	private String subdistrict_lgd_code;
	private String village_town_lgd_code;
	private String pincode;
	private String rural_urban;
	
	
	private String otpAddMem;
	private MultipartFile docFile;	
	private String docName;
	
	private String demoPincode;
	private String pbenname;
	private String gurname;
	private String seguid;
	private String hhdid;
	
	private String faceId;
	private String facePht;
	
	
	
	private String  district_code;
	
	
		
	
	/*
	 * 
	 * 
	 * STATE SCHEME
	 */
		
	private String stateScheme;
	private String adcdRation;
	private String adcdMobile;
	private String nfsaId;
	private String bcowId;
	private String schemeName;
	private String schemeid;

	private String subSchemeId;
	
	public String getSubSchemeId() {
		return subSchemeId;
	}

	public void setSubSchemeId(String subSchemeId) {
		this.subSchemeId = subSchemeId;
	}

	public String getSubSchemeName() {
		return subSchemeName;
	}

	public void setSubSchemeName(String subSchemeName) {
		this.subSchemeName = subSchemeName;
	}

	public String getDocumentTypeName() {
		return documentTypeName;
	}

	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}

	public String getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDocumentViewId() {
		return documentViewId;
	}

	public void setDocumentViewId(String documentViewId) {
		this.documentViewId = documentViewId;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getSchemedocumentImage() {
		return SchemedocumentImage;
	}

	public void setSchemedocumentImage(String schemedocumentImage) {
		SchemedocumentImage = schemedocumentImage;
	}

	public MultipartFile getFormFile() {
		return formFile;
	}

	public void setFormFile(MultipartFile formFile) {
		this.formFile = formFile;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	private String subSchemeName;
	
   private String documentTypeName;
	
	private String documentTypeId;
	
   private String documentViewId;
   private String documentId;
   private String SchemedocumentImage;
   private MultipartFile formFile;
   private String photo;
   private String photoPath;
   private String photoName;
   private MultipartFile imageFile; 
	
		

		public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeid() {
		return schemeid;
	}

	public void setSchemeid(String schemeid) {
		this.schemeid = schemeid;
	}

		public String getBcowId() {
		return bcowId;
	}

	public void setBcowId(String bcowId) {
		this.bcowId = bcowId;
	}

		public String getNfsaId() {
		return nfsaId;
	}

	public void setNfsaId(String nfsaId) {
		this.nfsaId = nfsaId;
	}

		public String getAdcdRation() {
		return adcdRation;
	}

	public void setAdcdRation(String adcdRation) {
		this.adcdRation = adcdRation;
	}

	public String getAdcdMobile() {
		return adcdMobile;
	}

	public void setAdcdMobile(String adcdMobile) {
		this.adcdMobile = adcdMobile;
	}

		public String getStateScheme() {
		return stateScheme;
	}

	public void setStateScheme(String stateScheme) {
		this.stateScheme = stateScheme;
	}

		public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

		public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public String getFacePht() {
		return facePht;
	}

	public void setFacePht(String facePht) {
		this.facePht = facePht;
	}

		public String getHhdid() {
		return hhdid;
	}

	public void setHhdid(String hhdid) {
		this.hhdid = hhdid;
	}

		public String getPbenname() {
		return pbenname;
	}

	public void setPbenname(String pbenname) {
		this.pbenname = pbenname;
	}

	public String getGurname() {
		return gurname;
	}

	public void setGurname(String gurname) {
		this.gurname = gurname;
	}

	public String getSeguid() {
		return seguid;
	}

	public void setSeguid(String seguid) {
		this.seguid = seguid;
	}

		public String getDemoPincode() {
		return demoPincode;
	}

	public void setDemoPincode(String demoPincode) {
		this.demoPincode = demoPincode;
	}

		public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	
	
		public MultipartFile getDocFile() {
		return docFile;
	}
	public void setDocFile(MultipartFile docFile) {
		this.docFile = docFile;
	}
		public String getOtpAddMem() {
		return otpAddMem;
	}
	public void setOtpAddMem(String otpAddMem) {
		this.otpAddMem = otpAddMem;
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
	public String getYear_of_birth() {
		return year_of_birth;
	}
	public void setYear_of_birth(String year_of_birth) {
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
	
	
	public String getState_lgd_code() {
		return state_lgd_code;
	}
	public void setState_lgd_code(String state_lgd_code) {
		this.state_lgd_code = state_lgd_code;
	}
	public String getDistrict_lgd_code() {
		return district_lgd_code;
	}
	public void setDistrict_lgd_code(String district_lgd_code) {
		this.district_lgd_code = district_lgd_code;
	}
	public String getSubdistrict_lgd_code() {
		return subdistrict_lgd_code;
	}
	public void setSubdistrict_lgd_code(String subdistrict_lgd_code) {
		this.subdistrict_lgd_code = subdistrict_lgd_code;
	}
	public String getVillage_town_lgd_code() {
		return village_town_lgd_code;
	}
	public void setVillage_town_lgd_code(String village_town_lgd_code) {
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
	public String getRationCard() {
		return rationCard;
	}
	public void setRationCard(String rationCard) {
		this.rationCard = rationCard;
	}
	public String getFamilyDetail() {
		return familyDetail;
	}
	public void setFamilyDetail(String familyDetail) {
		this.familyDetail = familyDetail;
	}
	public String getNameBy() {
		return nameBy;
	}
	public void setNameBy(String nameBy) {
		this.nameBy = nameBy;
	}
	public String getNameWith() {
		return nameWith;
	}
	public void setNameWith(String nameWith) {
		this.nameWith = nameWith;
	}
	public String getGenderBy() {
		return genderBy;
	}
	public void setGenderBy(String genderBy) {
		this.genderBy = genderBy;
	}
	public String getGenderWith() {
		return genderWith;
	}
	public void setGenderWith(String genderWith) {
		this.genderWith = genderWith;
	}
	public String getFatherBy() {
		return fatherBy;
	}
	public void setFatherBy(String fatherBy) {
		this.fatherBy = fatherBy;
	}
	public String getFatherWith() {
		return fatherWith;
	}
	public void setFatherWith(String fatherWith) {
		this.fatherWith = fatherWith;
	}
	public String getPmjayId() {
		return pmjayId;
	}
	public void setPmjayId(String pmjayId) {
		this.pmjayId = pmjayId;
	}
	public String getOtpType() {
		return otpType;
	}
	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}
	public String getFather_name_s() {
		return father_name_s;
	}
	public void setFather_name_s(String father_name_s) {
		this.father_name_s = father_name_s;
	}
	public String getState_name_s() {
		return state_name_s;
	}
	public void setState_name_s(String state_name_s) {
		this.state_name_s = state_name_s;
	}
	public String getDistrict_name_s() {
		return district_name_s;
	}
	public void setDistrict_name_s(String district_name_s) {
		this.district_name_s = district_name_s;
	}
	public String getVtc_s() {
		return vtc_s;
	}
	public void setVtc_s(String vtc_s) {
		this.vtc_s = vtc_s;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getAhl_tin() {
		return ahl_tin;
	}
	public void setAhl_tin(String ahl_tin) {
		this.ahl_tin = ahl_tin;
	}
	public String getAhl_hhid() {
		return ahl_hhid;
	}
	public void setAhl_hhid(String ahl_hhid) {
		this.ahl_hhid = ahl_hhid;
	}
	public String getVerification_status() {
		return verification_status;
	}
	public void setVerification_status(String verification_status) {
		this.verification_status = verification_status;
	}
	public String getUid_aadhaar() {
		return uid_aadhaar;
	}
	public void setUid_aadhaar(String uid_aadhaar) {
		this.uid_aadhaar = uid_aadhaar;
	}
	public String getUid_token() {
		return uid_token;
	}
	public void setUid_token(String uid_token) {
		this.uid_token = uid_token;
	}
	public String getUid_auth_date() {
		return uid_auth_date;
	}
	public void setUid_auth_date(String uid_auth_date) {
		this.uid_auth_date = uid_auth_date;
	}
	public String getUid_auth_type() {
		return uid_auth_type;
	}
	public void setUid_auth_type(String uid_auth_type) {
		this.uid_auth_type = uid_auth_type;
	}
	public String getPhoto_s() {
		return photo_s;
	}
	public void setPhoto_s(String photo_s) {
		this.photo_s = photo_s;
	}
	public String getName_s() {
		return name_s;
	}
	public void setName_s(String name_s) {
		this.name_s = name_s;
	}
	public String getDob_s() {
		return dob_s;
	}
	public void setDob_s(String dob_s) {
		this.dob_s = dob_s;
	}
	public String getGender_s() {
		return gender_s;
	}
	public void setGender_s(String gender_s) {
		this.gender_s = gender_s;
	}
	public String getAddress_s() {
		return address_s;
	}
	public void setAddress_s(String address_s) {
		this.address_s = address_s;
	}
	public String getSub_distCode() {
		return sub_distCode;
	}
	public void setSub_distCode(String sub_distCode) {
		this.sub_distCode = sub_distCode;
	}
	public String getVtcCode() {
		return vtcCode;
	}
	public void setVtcCode(String vtcCode) {
		this.vtcCode = vtcCode;
	}
	public String getPost_s() {
		return post_s;
	}
	public void setPost_s(String post_s) {
		this.post_s = post_s;
	}
	public String getName_d() {
		return name_d;
	}
	public void setName_d(String name_d) {
		this.name_d = name_d;
	}
	public String getDob_d() {
		return dob_d;
	}
	public void setDob_d(String dob_d) {
		this.dob_d = dob_d;
	}
	public String getGender_d() {
		return gender_d;
	}
	public void setGender_d(String gender_d) {
		this.gender_d = gender_d;
	}
	public String getAddress_d() {
		return address_d;
	}
	public void setAddress_d(String address_d) {
		this.address_d = address_d;
	}
	public String getState_name_d() {
		return state_name_d;
	}
	public void setState_name_d(String state_name_d) {
		this.state_name_d = state_name_d;
	}
	public String getDistrict_name_d() {
		return district_name_d;
	}
	public void setDistrict_name_d(String district_name_d) {
		this.district_name_d = district_name_d;
	}
	public String getSub_dist_d() {
		return sub_dist_d;
	}
	public void setSub_dist_d(String sub_dist_d) {
		this.sub_dist_d = sub_dist_d;
	}
	public String getVtc_d() {
		return vtc_d;
	}
	public void setVtc_d(String vtc_d) {
		this.vtc_d = vtc_d;
	}
	public String getPost_d() {
		return post_d;
	}
	public void setPost_d(String post_d) {
		this.post_d = post_d;
	}
	public String getPin_code_d() {
		return pin_code_d;
	}
	public void setPin_code_d(String pin_code_d) {
		this.pin_code_d = pin_code_d;
	}
	public String getMobile_member() {
		return mobile_member;
	}
	public void setMobile_member(String mobile_member) {
		this.mobile_member = mobile_member;
	}
	public String getFather_name_d() {
		return father_name_d;
	}
	public void setFather_name_d(String father_name_d) {
		this.father_name_d = father_name_d;
	}
	public String getMobile_d() {
		return mobile_d;
	}
	public void setMobile_d(String mobile_d) {
		this.mobile_d = mobile_d;
	}
	public String getBioType() {
		return bioType;
	}
	public void setBioType(String bioType) {
		this.bioType = bioType;
	}
	public String getPidData() {
		return pidData;
	}
	public void setPidData(String pidData) {
		this.pidData = pidData;
	}
	public String getCardPht() {
		return cardPht;
	}
	public void setCardPht(String cardPht) {
		this.cardPht = cardPht;
	}
	public String getCard_status() {
		return card_status;
	}
	public void setCard_status(String card_status) {
		this.card_status = card_status;
	}
	public String getRefernceid() {
		return refernceid;
	}
	public void setRefernceid(String refernceid) {
		this.refernceid = refernceid;
	}
	public String getKycName() {
		return kycName;
	}
	public void setKycName(String kycName) {
		this.kycName = kycName;
	}
	public String getKycGender() {
		return kycGender;
	}
	public void setKycGender(String kycGender) {
		this.kycGender = kycGender;
	}
	public String getKycDob() {
		return kycDob;
	}
	public void setKycDob(String kycDob) {
		this.kycDob = kycDob;
	}
	public String getKycAdr() {
		return kycAdr;
	}
	public void setKycAdr(String kycAdr) {
		this.kycAdr = kycAdr;
	}
	public String getPht() {
		return pht;
	}
	public void setPht(String pht) {
		this.pht = pht;
	}
	public String getUidtoken() {
		return uidtoken;
	}
	public void setUidtoken(String uidtoken) {
		this.uidtoken = uidtoken;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOtpTxn() {
		return otpTxn;
	}
	public void setOtpTxn(String otpTxn) {
		this.otpTxn = otpTxn;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}
	public String getDemoFname() {
		return demoFname;
	}
	public void setDemoFname(String demoFname) {
		this.demoFname = demoFname;
	}
	public String getDemoGender() {
		return demoGender;
	}
	public void setDemoGender(String demoGender) {
		this.demoGender = demoGender;
	}
	public String getDemoDob() {
		return demoDob;
	}
	public void setDemoDob(String demoDob) {
		this.demoDob = demoDob;
	}
	public String getDemoMobile() {
		return demoMobile;
	}
	public void setDemoMobile(String demoMobile) {
		this.demoMobile = demoMobile;
	}
	public String getDemoEmail() {
		return demoEmail;
	}
	public void setDemoEmail(String demoEmail) {
		this.demoEmail = demoEmail;
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
	public String getModeFlag() {
		return modeFlag;
	}
	public void setModeFlag(String modeFlag) {
		this.modeFlag = modeFlag;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getTownCode() {
		return townCode;
	}
	public void setTownCode(String townCode) {
		this.townCode = townCode;
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
	public String getRuralUrbanFlag() {
		return ruralUrbanFlag;
	}
	public void setRuralUrbanFlag(String ruralUrbanFlag) {
		this.ruralUrbanFlag = ruralUrbanFlag;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	
	
	
	
	
}
