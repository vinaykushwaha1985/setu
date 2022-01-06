package com.gov.nha.bis.server.dto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.io.Serializable;
import java.math.BigInteger;

public class RuralDto implements Serializable{
	
	
	
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -2181971698664703375L;
	private String  tin_npr ;
	private String  hhd_no ;
	private String  name ;
	private String  fathername ;
	private String  spousenm ;
	private String dob;
	private String genderid;
	private String mothername;
	private String relation;
	private Long guid;
	private int card_status;
	private int state_code;
	private String district_code;
	private int block_code;
	private String village_code;
	private String rural_urban;
	
	private String addressline1;
	private String addressline2;
	private String addressline3;
	private String addressline4;
	private String addressline5;
	private String pincode;
	private String state_name_english;
	private String district_name_english;
	private String add_family_flag;
	
	
	
	public String getAddressline1() {
		return addressline1;
	}





	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}





	public String getAddressline2() {
		return addressline2;
	}





	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}





	public String getAddressline3() {
		return addressline3;
	}





	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}





	public String getAddressline4() {
		return addressline4;
	}





	public void setAddressline4(String addressline4) {
		this.addressline4 = addressline4;
	}





	public String getAddressline5() {
		return addressline5;
	}





	public void setAddressline5(String addressline5) {
		this.addressline5 = addressline5;
	}





	public String getPincode() {
		return pincode;
	}





	public void setPincode(String pincode) {
		this.pincode = pincode;
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





	/**
	 * @return the tin_npr
	 */
	public String getTin_npr() {
		return tin_npr;
	}


	/**
	 * @param tin_npr the tin_npr to set
	 */
	public void setTin_npr(String tin_npr) {
		this.tin_npr = tin_npr;
	}


	/**
	 * @return the hhd_no
	 */
	public String getHhd_no() {
		return hhd_no;
	}


	/**
	 * @param hhd_no the hhd_no to set
	 */
	public void setHhd_no(String hhd_no) {
		this.hhd_no = hhd_no;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the fathername
	 */
	public String getFathername() {
		return fathername;
	}


	/**
	 * @param fathername the fathername to set
	 */
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}


	/**
	 * @return the spousenm
	 */
	public String getSpousenm() {
		return spousenm;
	}


	/**
	 * @param spousenm the spousenm to set
	 */
	public void setSpousenm(String spousenm) {
		this.spousenm = spousenm;
	}


	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}


	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}


	/**
	 * @return the genderid
	 */
	public String getGenderid() {
		return genderid;
	}


	/**
	 * @param genderid the genderid to set
	 */
	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}


	/**
	 * @return the mothername
	 */
	public String getMothername() {
		return mothername;
	}


	/**
	 * @param mothername the mothername to set
	 */
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}


	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}


	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}


	/**
	 * @return the guid
	 */
	public Long getGuid() {
		return guid;
	}


	/**
	 * @param guid the guid to set
	 */
	public void setGuid(Long guid) {
		this.guid = guid;
	}


	/**
	 * @return the card_status
	 */
	public int getCard_status() {
		return card_status;
	}


	/**
	 * @param card_status the card_status to set
	 */
	public void setCard_status(int card_status) {
		this.card_status = card_status;
	}


	/**
	 * @return the state_code
	 */
	public int getState_code() {
		return state_code;
	}


	/**
	 * @param state_code the state_code to set
	 */
	public void setState_code(int state_code) {
		this.state_code = state_code;
	}


	/**
	 * @return the district_code
	 */
	public String getDistrict_code() {
		return district_code;
	}


	/**
	 * @param district_code the district_code to set
	 */
	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}


	/**
	 * @return the block_code
	 */
	public int getBlock_code() {
		return block_code;
	}


	/**
	 * @param block_code the block_code to set
	 */
	public void setBlock_code(int block_code) {
		this.block_code = block_code;
	}


	/**
	 * @return the village_code
	 */
	public String getVillage_code() {
		return village_code;
	}


	/**
	 * @param village_code the village_code to set
	 */
	public void setVillage_code(String village_code) {
		this.village_code = village_code;
	}


	/**
	 * @return the rural_urban
	 */
	public String getRural_urban() {
		return rural_urban;
	}


	/**
	 * @param rural_urban the rural_urban to set
	 */
	public void setRural_urban(String rural_urban) {
		this.rural_urban = rural_urban;
	}
	
	
	
	public String getAdd_family_flag() {
		return add_family_flag;
	}





	public void setAdd_family_flag(String add_family_flag) {
		this.add_family_flag = add_family_flag;
	}







	
}
