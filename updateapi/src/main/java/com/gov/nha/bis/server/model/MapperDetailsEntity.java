package com.gov.nha.bis.server.model;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="mappercachedetail")
@Access(value=AccessType.FIELD)
@SequenceGenerator(name="checkdetailspkseq" , sequenceName="checkdetailspkseq", allocationSize=1)
public class MapperDetailsEntity implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="checkdetailspkseq")
		private Long id;
		
		private Long refernceid;
		private String checkedby;
		private Long status;
		private Long priorty;
		
		private String rural_urban;
		private String state_code;
		private String district_code;
		private String town_code;
		private String ward_code;
		private String block_code;
		private String village_code;
		private Long ekyctype;
		
		
		
		
		public Long getEkyctype() {
			return ekyctype;
		}
		public void setEkyctype(Long ekyctype) {
			this.ekyctype = ekyctype;
		}
		public String getRural_urban() {
			return rural_urban;
		}
		public void setRural_urban(String rural_urban) {
			this.rural_urban = rural_urban;
		}
		public String getState_code() {
			return state_code;
		}
		public void setState_code(String state_code) {
			this.state_code = state_code;
		}
		public String getDistrict_code() {
			return district_code;
		}
		public void setDistrict_code(String district_code) {
			this.district_code = district_code;
		}
		public String getTown_code() {
			return town_code;
		}
		public void setTown_code(String town_code) {
			this.town_code = town_code;
		}
		public String getWard_code() {
			return ward_code;
		}
		public void setWard_code(String ward_code) {
			this.ward_code = ward_code;
		}
		public String getBlock_code() {
			return block_code;
		}
		public void setBlock_code(String block_code) {
			this.block_code = block_code;
		}
		public String getVillage_code() {
			return village_code;
		}
		public void setVillage_code(String village_code) {
			this.village_code = village_code;
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
		public String getCheckedby() {
			return checkedby;
		}
		public void setCheckedby(String checkedby) {
			this.checkedby = checkedby;
		}
		public Long getStatus() {
			return status;
		}
		public void setStatus(Long status) {
			this.status = status;
		}
		public Long getPriorty() {
			return priorty;
		}
		public void setPriorty(Long priorty) {
			this.priorty = priorty;
		}
		


}
