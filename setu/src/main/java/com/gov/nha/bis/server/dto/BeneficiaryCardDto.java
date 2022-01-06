/**
 * 
 */
package com.gov.nha.bis.server.dto;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public class BeneficiaryCardDto {
	
	private String  nfsaid;
	private String   memberid ;
	private BigInteger   guid;
	private String  uidtoken;
	private String  uidenc;
	private int  statecode;
	private String districtcode;
	private int  ekyctype;
	private int  status;
	private String  rural_urban;
	private String  mobile ;
	private String  pmjayid ;
	private Date createdate;
	private Date updatedate;
	private String name;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param nfsaid
	 * @param memberid
	 * @param guid
	 * @param uidtoken
	 * @param uidenc
	 * @param statecode
	 * @param districtcode
	 * @param ekyctype
	 * @param status
	 * @param rural_urban
	 * @param mobile
	 * @param pmjayid
	 * @param createdate
	 * @param updatedate
	 */
	public BeneficiaryCardDto(String nfsaid, String memberid, BigInteger guid, String uidtoken, String uidenc,
			int statecode, String districtcode, int ekyctype, int status, String rural_urban, String mobile,
			String pmjayid, Date createdate, Date updatedate,String name) {
		
		this.nfsaid = nfsaid;
		this.memberid = memberid;
		this.guid = guid;
		this.uidtoken = uidtoken;
		this.uidenc = uidenc;
		this.statecode = statecode;
		this.districtcode = districtcode;
		this.ekyctype = ekyctype;
		this.status = status;
		this.rural_urban = rural_urban;
		this.mobile = mobile;
		this.pmjayid = pmjayid;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.name=name;
	}
	public String getNfsaid() {
		return nfsaid;
	}
	public void setNfsaid(String nfsaid) {
		this.nfsaid = nfsaid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public BigInteger getGuid() {
		return guid;
	}
	public void setGuid(BigInteger guid) {
		this.guid = guid;
	}
	public String getUidtoken() {
		return uidtoken;
	}
	public void setUidtoken(String uidtoken) {
		this.uidtoken = uidtoken;
	}
	public String getUidenc() {
		return uidenc;
	}
	public void setUidenc(String uidenc) {
		this.uidenc = uidenc;
	}
	public int getStatecode() {
		return statecode;
	}
	public void setStatecode(int statecode) {
		this.statecode = statecode;
	}
	public String getDistrictcode() {
		return districtcode;
	}
	public void setDistrictcode(String districtcode) {
		this.districtcode = districtcode;
	}
	public int getEkyctype() {
		return ekyctype;
	}
	public void setEkyctype(int ekyctype) {
		this.ekyctype = ekyctype;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRural_urban() {
		return rural_urban;
	}
	public void setRural_urban(String rural_urban) {
		this.rural_urban = rural_urban;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPmjayid() {
		return pmjayid;
	}
	public void setPmjayid(String pmjayid) {
		this.pmjayid = pmjayid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	
	

}
