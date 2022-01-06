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
@Table(name="aadhaarphoto")
@Access(value=AccessType.FIELD)
@SequenceGenerator(name="checkdetailspkseq" , sequenceName="checkdetailspkseq", allocationSize=1)
public class AadhaarPhotoEntity implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="checkdetailspkseq")
		private Long id;
		
		private Long refernceid;
		private String photo;
		private String aadhaar;
	    private Long status;
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
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		public String getAadhaar() {
			return aadhaar;
		}
		public void setAadhaar(String aadhaar) {
			this.aadhaar = aadhaar;
		}
		public Long getStatus() {
			return status;
		}
		public void setStatus(Long status) {
			this.status = status;
		}

}
