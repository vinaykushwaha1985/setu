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
@Table(name="d_demo")
@Access(value=AccessType.FIELD)
@SequenceGenerator(name="checkdetailspkseq" , sequenceName="checkdetailspkseq", allocationSize=1)
public class DemoAuthEntity implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="checkdetailspkseq")
		private Long id;
		
		private Long  refernceid;
		private String name;
		private String gender;
		private String dob;
		private String pncode;
		private String guardian;
		private String mobile;
		private String aadhaar;
		private Date createdate;
		private String statecode;
		private String ruflag;
		private int status;
		
		
		
		
		
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getRuflag() {
			return ruflag;
		}
		public void setRuflag(String ruflag) {
			this.ruflag = ruflag;
		}
		
		
		
		
		
		public String getStatecode() {
			return statecode;
		}
		public void setStatecode(String statecode) {
			this.statecode = statecode;
		}
		public Date getCreatedate() {
			return createdate;
		}
		public void setCreatedate(Date createdate) {
			this.createdate = createdate;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getPncode() {
			return pncode;
		}
		public void setPncode(String pncode) {
			this.pncode = pncode;
		}
		public String getGuardian() {
			return guardian;
		}
		public void setGuardian(String guardian) {
			this.guardian = guardian;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getAadhaar() {
			return aadhaar;
		}
		public void setAadhaar(String aadhaar) {
			this.aadhaar = aadhaar;
		}
		
		
		
	   
	
	

}
