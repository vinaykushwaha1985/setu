package com.gov.nha.bis.server.dao;
import com.gov.nha.bis.server.dto.KycPhotoDto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import com.gov.nha.bis.server.model.AadhaarPhotoEntity;

public interface KycPhotoUpdateDao {

	public void saveKycPhotoUpdate(AadhaarPhotoEntity aadhaarPhotoEntity);
	public KycPhotoDto findByRefernceid(Long refernceid);
}
