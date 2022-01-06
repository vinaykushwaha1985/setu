package com.gov.nha.bis.server.dto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
public class SearchParam {



	private String stateCode;
	private String districtCode;
	private int blockCode;
	private String villageCode;
	private String townCode;
	private String wardCode;
	private String urFlag;
	private Long guid;
	private int card_status;
	private String userid;
	private Long refernceid;
	private String statecode;
	
	private String ruflag;


	
	public String getRuflag() {
		return ruflag;
	}
	public void setRuflag(String ruflag) {
		this.ruflag = ruflag;
	}
	public Long getRefernceid() {
		return refernceid;
	}
	public void setRefernceid(Long refernceid) {
		this.refernceid = refernceid;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public int getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(int blockCode) {
		this.blockCode = blockCode;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getUrFlag() {
		return urFlag;
	}
	public void setUrFlag(String urFlag) {
		this.urFlag = urFlag;
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



}
