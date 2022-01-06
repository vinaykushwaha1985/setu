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
public class KycDataDto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1697534631133647125L;
	private Long refernceid;
	private String name;
	private String relationshiptype;
	private String relationshipname;
	private String gender;
	private String  dob_d;
	private String dob_m;
	private String dob_y;
	private String address;
	private String statename;
	private String districtname;
	private String pncode;
    private Long ekyctype;
    private Long status;
    private String claimedby;
    private String usertype;
    private String uidtoken;
    private Long schemeid;
    private Date creationdate;
  
    private String aadhaar;
	
	private String pht;
	private String reqip;
	private String ruralUrban;
	private String stateCode;
	private String  districtCode ;
	private String  blockCode ;
	private String villageCode;
	private String townCode ;
	private String wardCode;
	private Long guid;
	
	private int mode;
	
	
  
	
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public Long getGuid() {
		return guid;
	}
	public void setGuid(Long guid) {
		this.guid = guid;
	}
	public String getPht() {
		return pht;
	}
	public void setPht(String pht) {
		this.pht = pht;
	}
	public String getReqip() {
		return reqip;
	}
	public void setReqip(String reqip) {
		this.reqip = reqip;
	}
	public String getRuralUrban() {
		return ruralUrban;
	}
	public void setRuralUrban(String ruralUrban) {
		this.ruralUrban = ruralUrban;
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
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getTownCode() {
		return townCode;
	}
	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public Long getRefernceid() {
		return refernceid;
	}
	public void setRefernceid(Long refernceid) {
		this.refernceid = refernceid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationshiptype() {
		return relationshiptype;
	}
	public void setRelationshiptype(String relationshiptype) {
		this.relationshiptype = relationshiptype;
	}
	public String getRelationshipname() {
		return relationshipname;
	}
	public void setRelationshipname(String relationshipname) {
		this.relationshipname = relationshipname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob_d() {
		return dob_d;
	}
	public void setDob_d(String dob_d) {
		this.dob_d = dob_d;
	}
	public String getDob_m() {
		return dob_m;
	}
	public void setDob_m(String dob_m) {
		this.dob_m = dob_m;
	}
	public String getDob_y() {
		return dob_y;
	}
	public void setDob_y(String dob_y) {
		this.dob_y = dob_y;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getDistrictname() {
		return districtname;
	}
	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	public String getPncode() {
		return pncode;
	}
	public void setPncode(String pncode) {
		this.pncode = pncode;
	}
	public Long getEkyctype() {
		return ekyctype;
	}
	public void setEkyctype(Long ekyctype) {
		this.ekyctype = ekyctype;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getClaimedby() {
		return claimedby;
	}
	public void setClaimedby(String claimedby) {
		this.claimedby = claimedby;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUidtoken() {
		return uidtoken;
	}
	public void setUidtoken(String uidtoken) {
		this.uidtoken = uidtoken;
	}
	public Long getSchemeid() {
		return schemeid;
	}
	public void setSchemeid(Long schemeid) {
		this.schemeid = schemeid;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	

    
    
}
