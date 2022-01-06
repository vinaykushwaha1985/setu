/**
 * 
 */
package com.gov.nha.bis.server.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */

@Entity
@Table(name="beneficiarycarddetaailstatus")
@Access(value=AccessType.FIELD)
@SequenceGenerator(name="beneficiarycardstatus_seq" , sequenceName="beneficiarycardstatus_seq", allocationSize=1)
public class BeneficiaryCardStatusEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="beneficiarycardstatus_seq")
	private Long id;

	private String  nfsaid;
	private String   memberrefid ;
	private BigInteger   guid;
	private String  uidtoken;
	private String  uidenc;
	private int  statecode;
	private String districtcode;
	private int  ekyctype;
	private int  createcardstatus;
	private int deliverycardstatus;
	private int printcardstatus;
	private String schemeid;
	private String hhid;
	private String  rural_urban;
	private String  mobile ;
	private String  pmjayid ;
	private Date createdate;
	private Date updatedate;
	private String createdby;
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNfsaid() {
		return nfsaid;
	}
	public void setNfsaid(String nfsaid) {
		this.nfsaid = nfsaid;
	}
	public String getMemberrefid() {
		return memberrefid;
	}
	public void setMemberrefid(String memberrefid) {
		this.memberrefid = memberrefid;
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
	public int getCreatecardstatus() {
		return createcardstatus;
	}
	public void setCreatecardstatus(int createcardstatus) {
		this.createcardstatus = createcardstatus;
	}
	public int getDeliverycardstatus() {
		return deliverycardstatus;
	}
	public void setDeliverycardstatus(int deliverycardstatus) {
		this.deliverycardstatus = deliverycardstatus;
	}
	public int getPrintcardstatus() {
		return printcardstatus;
	}
	public void setPrintcardstatus(int printcardstatus) {
		this.printcardstatus = printcardstatus;
	}
	public String getSchemeid() {
		return schemeid;
	}
	public void setSchemeid(String schemeid) {
		this.schemeid = schemeid;
	}
	public String getHhid() {
		return hhid;
	}
	public void setHhid(String hhid) {
		this.hhid = hhid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
