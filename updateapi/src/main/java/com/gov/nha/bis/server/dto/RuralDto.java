package com.gov.nha.bis.server.dto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
public class RuralDto {
	
	
	
  
	private String  tin_npr ;
	private String  hhd_no ;
	private String  name ;
	private String  fathername ;
	private String  spousenm ;
	private String dob;
	private String genderid;
	private String mothername;
	private String relation;
	
	
	public RuralDto(String tin_npr, String hhd_no,String name  ,String fathername,String mothername,
			String relation,String dob,String genderid,String spousenm) {
		
		this.tin_npr =tin_npr;
		this.hhd_no=hhd_no;
		this.name=name;
		this.fathername=fathername;
		this.spousenm=spousenm;
		this.dob=dob;
		this.genderid=genderid;
		this.mothername=mothername;
		this.relation=relation;
		
	}
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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
	
	
	
}
