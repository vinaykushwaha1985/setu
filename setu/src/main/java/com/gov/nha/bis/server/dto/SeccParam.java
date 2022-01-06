/**
 * 
 */
package com.gov.nha.bis.server.dto;

import java.math.BigInteger;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public class SeccParam {
	
	
    private BigInteger  ahl_tin ;
	
	private String  tin_npr ;
	private String  hhd_no ;
	private String  statecode ;
	private String  districtcode ;
	private String  tehsilcode ;
	private String  towncode ;
	private String  towncode_mdds ;
	private String  wardid ;
	private String  ahlblockno ;
	private String  ahlsubblockno ;
	private String  ahlslnohhd ;
	private String  slnomember ;
	private String  grampanchayatname ;
	private String  name ;
	private String  fathername ;
	private String  mothername ;
	private String  relation ;
	private String  dob ;
	private String  genderid ;
	private String  name_sl ;
	private String  fathername_sl ;
	private String  mothername_sl ;
	private String  relation_sl ;
	private String  spousenm ;
	private String  spousenmsl ;
	private String  mstatusid ;
	private String  member_status_npr ;
	private String  addressline1 ;
	private String  addressline2 ;
	private String  addressline3 ;
	private String  addressline4 ;
	private String  addressline5 ;
	private String  addressline1sl ;
	private String  addressline2sl ;
	private String  addressline3sl ;
	private String  addressline4sl ;
	private String  addressline5sl ;
	private String  pincode ;
	private String  ration_card_no ;
	private String  caste_group ;
	private String  incomesource_urban ;
	private String  rural_urban ;
	private String  hoh ;
	private String  i_1 ;
	private String  i_2 ;
	private String  i_3 ;
	private String  i_4 ;
	private String  i_5 ;
	private String  d_1 ;
	private String  d_2 ;
	private String  d_3 ;
	private String  d_4 ;
	private String  d_5 ;
	private String  d_7 ;
	private BigInteger guid;
	private String  masterordata ;
	private String  suggestions_words ;
	private int  state_code ;
	private String  district_code ;
	private String  state_name_english ;
	private String  district_name_english ;
	private String  block_name_english ;
	private String  village_name_english ;
	private int  block_code ;
	private String  village_code ;
	private String  name_combined ;
	private String  fathername_combined ;
	private String  mothername_combined ;
	private String  spousename_combined ;
	private String  name_sl_combined ;
	private String  fathername_sl_combined ;
	private String  mothername_sl_combined ;
	private String  spousenmsl_combined ;
	private String  datasource ;
	
	private Integer card_status;
	
	private String add_family_flag;
	
	
	
	
	
	
	public String getAdd_family_flag() {
		return add_family_flag;
	}
	public void setAdd_family_flag(String add_family_flag) {
		this.add_family_flag = add_family_flag;
	}
	public Integer getCard_status() {
		return card_status;
	}
	public void setCard_status(Integer card_status) {
		this.card_status = card_status;
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
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public String getDistrictcode() {
		return districtcode;
	}
	public void setDistrictcode(String districtcode) {
		this.districtcode = districtcode;
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
	public String getTowncode_mdds() {
		return towncode_mdds;
	}
	public void setTowncode_mdds(String towncode_mdds) {
		this.towncode_mdds = towncode_mdds;
	}
	public String getWardid() {
		return wardid;
	}
	public void setWardid(String wardid) {
		this.wardid = wardid;
	}
	public String getAhlblockno() {
		return ahlblockno;
	}
	public void setAhlblockno(String ahlblockno) {
		this.ahlblockno = ahlblockno;
	}
	public String getAhlsubblockno() {
		return ahlsubblockno;
	}
	public void setAhlsubblockno(String ahlsubblockno) {
		this.ahlsubblockno = ahlsubblockno;
	}
	public String getAhlslnohhd() {
		return ahlslnohhd;
	}
	public void setAhlslnohhd(String ahlslnohhd) {
		this.ahlslnohhd = ahlslnohhd;
	}
	public String getSlnomember() {
		return slnomember;
	}
	public void setSlnomember(String slnomember) {
		this.slnomember = slnomember;
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
	public void setname(String name) {
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
	public String getName_sl() {
		return name_sl;
	}
	public void setName_sl(String name_sl) {
		this.name_sl = name_sl;
	}
	public String getFathername_sl() {
		return fathername_sl;
	}
	public void setFathername_sl(String fathername_sl) {
		this.fathername_sl = fathername_sl;
	}
	public String getMothername_sl() {
		return mothername_sl;
	}
	public void setMothername_sl(String mothername_sl) {
		this.mothername_sl = mothername_sl;
	}
	public String getRelation_sl() {
		return relation_sl;
	}
	public void setRelation_sl(String relation_sl) {
		this.relation_sl = relation_sl;
	}
	public String getSpousenm() {
		return spousenm;
	}
	public void setSpousenm(String spousenm) {
		this.spousenm = spousenm;
	}
	public String getSpousenmsl() {
		return spousenmsl;
	}
	public void setSpousenmsl(String spousenmsl) {
		this.spousenmsl = spousenmsl;
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
	public String getAddressline1sl() {
		return addressline1sl;
	}
	public void setAddressline1sl(String addressline1sl) {
		this.addressline1sl = addressline1sl;
	}
	public String getAddressline2sl() {
		return addressline2sl;
	}
	public void setAddressline2sl(String addressline2sl) {
		this.addressline2sl = addressline2sl;
	}
	public String getAddressline3sl() {
		return addressline3sl;
	}
	public void setAddressline3sl(String addressline3sl) {
		this.addressline3sl = addressline3sl;
	}
	public String getAddressline4sl() {
		return addressline4sl;
	}
	public void setAddressline4sl(String addressline4sl) {
		this.addressline4sl = addressline4sl;
	}
	public String getAddressline5sl() {
		return addressline5sl;
	}
	public void setAddressline5sl(String addressline5sl) {
		this.addressline5sl = addressline5sl;
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
	public String getCaste_group() {
		return caste_group;
	}
	public void setCaste_group(String caste_group) {
		this.caste_group = caste_group;
	}
	public String getIncomesource_urban() {
		return incomesource_urban;
	}
	public void setIncomesource_urban(String incomesource_urban) {
		this.incomesource_urban = incomesource_urban;
	}
	public String getRural_urban() {
		return rural_urban;
	}
	public void setRural_urban(String rural_urban) {
		this.rural_urban = rural_urban;
	}
	public String getHoh() {
		return hoh;
	}
	public void setHoh(String hoh) {
		this.hoh = hoh;
	}
	public String getI_1() {
		return i_1;
	}
	public void setI_1(String i_1) {
		this.i_1 = i_1;
	}
	public String getI_2() {
		return i_2;
	}
	public void setI_2(String i_2) {
		this.i_2 = i_2;
	}
	public String getI_3() {
		return i_3;
	}
	public void setI_3(String i_3) {
		this.i_3 = i_3;
	}
	public String getI_4() {
		return i_4;
	}
	public void setI_4(String i_4) {
		this.i_4 = i_4;
	}
	public String getI_5() {
		return i_5;
	}
	public void setI_5(String i_5) {
		this.i_5 = i_5;
	}
	public String getD_1() {
		return d_1;
	}
	public void setD_1(String d_1) {
		this.d_1 = d_1;
	}
	public String getD_2() {
		return d_2;
	}
	public void setD_2(String d_2) {
		this.d_2 = d_2;
	}
	public String getD_3() {
		return d_3;
	}
	public void setD_3(String d_3) {
		this.d_3 = d_3;
	}
	public String getD_4() {
		return d_4;
	}
	public void setD_4(String d_4) {
		this.d_4 = d_4;
	}
	public String getD_5() {
		return d_5;
	}
	public void setD_5(String d_5) {
		this.d_5 = d_5;
	}
	public String getD_7() {
		return d_7;
	}
	public void setD_7(String d_7) {
		this.d_7 = d_7;
	}
	public String getMasterordata() {
		return masterordata;
	}
	public void setMasterordata(String masterordata) {
		this.masterordata = masterordata;
	}
	public String getSuggestions_words() {
		return suggestions_words;
	}
	public void setSuggestions_words(String suggestions_words) {
		this.suggestions_words = suggestions_words;
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
	public String getName_combined() {
		return name_combined;
	}
	public void setName_combined(String name_combined) {
		this.name_combined = name_combined;
	}
	public String getFathername_combined() {
		return fathername_combined;
	}
	public void setFathername_combined(String fathername_combined) {
		this.fathername_combined = fathername_combined;
	}
	public String getMothername_combined() {
		return mothername_combined;
	}
	public void setMothername_combined(String mothername_combined) {
		this.mothername_combined = mothername_combined;
	}
	public String getSpousename_combined() {
		return spousename_combined;
	}
	public void setSpousename_combined(String spousename_combined) {
		this.spousename_combined = spousename_combined;
	}
	public String getName_sl_combined() {
		return name_sl_combined;
	}
	public void setName_sl_combined(String name_sl_combined) {
		this.name_sl_combined = name_sl_combined;
	}
	public String getFathername_sl_combined() {
		return fathername_sl_combined;
	}
	public void setFathername_sl_combined(String fathername_sl_combined) {
		this.fathername_sl_combined = fathername_sl_combined;
	}
	public String getMothername_sl_combined() {
		return mothername_sl_combined;
	}
	public void setMothername_sl_combined(String mothername_sl_combined) {
		this.mothername_sl_combined = mothername_sl_combined;
	}
	public String getSpousenmsl_combined() {
		return spousenmsl_combined;
	}
	public void setSpousenmsl_combined(String spousenmsl_combined) {
		this.spousenmsl_combined = spousenmsl_combined;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getGuid() {
		return guid;
	}
	public void setGuid(BigInteger guid) {
		this.guid = guid;
	} 
	
	




}
