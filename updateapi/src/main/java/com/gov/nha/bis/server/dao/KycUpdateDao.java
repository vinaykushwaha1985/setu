package com.gov.nha.bis.server.dao;
import com.gov.nha.bis.server.dto.KycDataDto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import com.gov.nha.bis.server.model.AadhaarDetailsEntity;
import com.gov.nha.bis.server.model.AadhaarPhotoEntity;
import com.gov.nha.bis.server.model.MapperDetailsEntity;

public interface KycUpdateDao {
	
	public void saveKycUpdate(AadhaarDetailsEntity aadhaarDetailsEntity,AadhaarPhotoEntity aadhaarPhotoEntity, 
			MapperDetailsEntity mapperDetailsEntity,String ruralUrban);
	public void saveKycUpdate(AadhaarDetailsEntity aadhaarDetailsEntity,AadhaarPhotoEntity aadhaarPhotoEntity,
			String ruralUrban);
	
	public KycDataDto getAadhaarDetailsEntity(Long referenceId);
}
