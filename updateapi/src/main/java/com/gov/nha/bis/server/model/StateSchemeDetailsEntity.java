package com.gov.nha.bis.server.model;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="stateschemedetails")
@Access(value=AccessType.FIELD)
@SequenceGenerator(name="stateschemedetailsseq" , sequenceName="stateschemedetailsseq", allocationSize=1)
public class StateSchemeDetailsEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stateschemedetailsseq")
	private Long id;
	
	private String urban_rural;
	private Long state_code;
	private Long district_code;
	private String schemename;
	private String schemeid;
	private String rationcardno;
	private String hhid;
	private String  guid;
	private String memberid;
	private String name;
	private String fathername;
	private String mothername;
	private String spousename;
	private String yob;
    private String gender;
    private String relation;
	private String datasource;
    private String apisource;
    private String createdby;
    private Date createdon;
    private Long status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrban_rural() {
		return urban_rural;
	}
	public void setUrban_rural(String urban_rural) {
		this.urban_rural = urban_rural;
	}
	public Long getState_code() {
		return state_code;
	}
	public void setState_code(Long state_code) {
		this.state_code = state_code;
	}
	public Long getDistrict_code() {
		return district_code;
	}
	public void setDistrict_code(Long district_code) {
		this.district_code = district_code;
	}
	public String getSchemename() {
		return schemename;
	}
	public void setSchemename(String schemename) {
		this.schemename = schemename;
	}
	public String getSchemeid() {
		return schemeid;
	}
	public void setSchemeid(String schemeid) {
		this.schemeid = schemeid;
	}
	public String getRationcardno() {
		return rationcardno;
	}
	public void setRationcardno(String rationcardno) {
		this.rationcardno = rationcardno;
	}
	public String getHhid() {
		return hhid;
	}
	public void setHhid(String hhid) {
		this.hhid = hhid;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
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
	public String getSpousename() {
		return spousename;
	}
	public void setSpousename(String spousename) {
		this.spousename = spousename;
	}
	public String getYob() {
		return yob;
	}
	public void setYob(String yob) {
		this.yob = yob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public String getApisource() {
		return apisource;
	}
	public void setApisource(String apisource) {
		this.apisource = apisource;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
    
    
    
    
}
