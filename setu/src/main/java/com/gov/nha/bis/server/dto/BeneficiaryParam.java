/**
 * 
 */
package com.gov.nha.bis.server.dto;

import java.math.BigInteger;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public class BeneficiaryParam {

	
	private BigInteger PrimaryId;
	
    private BigInteger  ahl_tin ;
	
	private String  tin_npr ;
	private String  hhd_no ;
	
	
	private String  tehsilcode ;
	private String  towncode ;
	private String  wardid ;
	
	private String  grampanchayatname ;
	
	private String  name ;
	private String  fathername ;
	private String  mothername ;
	private String  relation ;
	private String  dob ;
	private String  genderid ;
	
	private String  spousenm ;
	
	private String  mstatusid ;
	
	private String  member_status_npr ;
	
	private String  addressline1 ;
	
	private String  pincode ;
	private String  ration_card_no ;
	
	
	private String  rural_urban ;
	
	
	private BigInteger guid;
	
	private int  state_code ;
	private String  district_code ;
	private String  state_name_english ;
	private String  district_name_english ;
	private String  block_name_english ;
	private String  village_name_english ;
	
	private int  block_code ;
	private String  village_code ;
	
	
	private Integer card_status;
	
	
	private String add_family_flag;


	public BigInteger getPrimaryId() {
		return PrimaryId;
	}


	public void setPrimaryId(BigInteger primaryId) {
		PrimaryId = primaryId;
	}


	public BigInteger getAhl_tin() {
		return ahl_tin;
	}


	public void setAhl_tin(BigInteger ahl_tin) {
		this.ahl_tin = ahl_tin;
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


	public String getTehsilcode() {
		return tehsilcode;
	}


	public void setTehsilcode(String tehsilcode) {
		this.tehsilcode = tehsilcode;
	}


	public String getTowncode() {
		return towncode;
	}


	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}


	public String getWardid() {
		return wardid;
	}


	public void setWardid(String wardid) {
		this.wardid = wardid;
	}


	public String getGrampanchayatname() {
		return grampanchayatname;
	}


	public void setGrampanchayatname(String grampanchayatname) {
		this.grampanchayatname = grampanchayatname;
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


	public String getSpousenm() {
		return spousenm;
	}


	public void setSpousenm(String spousenm) {
		this.spousenm = spousenm;
	}


	public String getMstatusid() {
		return mstatusid;
	}


	public void setMstatusid(String mstatusid) {
		this.mstatusid = mstatusid;
	}


	public String getMember_status_npr() {
		return member_status_npr;
	}


	public void setMember_status_npr(String member_status_npr) {
		this.member_status_npr = member_status_npr;
	}


	public String getAddressline1() {
		return addressline1;
	}


	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getRation_card_no() {
		return ration_card_no;
	}


	public void setRation_card_no(String ration_card_no) {
		this.ration_card_no = ration_card_no;
	}


	public String getRural_urban() {
		return rural_urban;
	}


	public void setRural_urban(String rural_urban) {
		this.rural_urban = rural_urban;
	}


	public BigInteger getGuid() {
		return guid;
	}


	public void setGuid(BigInteger guid) {
		this.guid = guid;
	}


	public int getState_code() {
		return state_code;
	}


	public void setState_code(int state_code) {
		this.state_code = state_code;
	}


	public String getDistrict_code() {
		return district_code;
	}


	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}


	public String getState_name_english() {
		return state_name_english;
	}


	public void setState_name_english(String state_name_english) {
		this.state_name_english = state_name_english;
	}


	public String getDistrict_name_english() {
		return district_name_english;
	}


	public void setDistrict_name_english(String district_name_english) {
		this.district_name_english = district_name_english;
	}


	public String getBlock_name_english() {
		return block_name_english;
	}


	public void setBlock_name_english(String block_name_english) {
		this.block_name_english = block_name_english;
	}


	public String getVillage_name_english() {
		return village_name_english;
	}


	public void setVillage_name_english(String village_name_english) {
		this.village_name_english = village_name_english;
	}


	public int getBlock_code() {
		return block_code;
	}


	public void setBlock_code(int block_code) {
		this.block_code = block_code;
	}


	public String getVillage_code() {
		return village_code;
	}


	public void setVillage_code(String village_code) {
		this.village_code = village_code;
	}


	public Integer getCard_status() {
		return card_status;
	}


	public void setCard_status(Integer card_status) {
		this.card_status = card_status;
	}


	public String getAdd_family_flag() {
		return add_family_flag;
	}


	public void setAdd_family_flag(String add_family_flag) {
		this.add_family_flag = add_family_flag;
	}
	
	
}
