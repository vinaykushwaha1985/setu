package com.gov.nha.bis.server.dao.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.gov.nha.bis.server.dao.KycPhotoUpdateDao;
import com.gov.nha.bis.server.dto.KycPhotoDto;
import com.gov.nha.bis.server.model.AadhaarPhotoEntity;
import com.gov.nha.bis.server.repository.AadhaarPhotoRepository;
import com.gov.nha.bis.server.util.CopyUtility;

@Repository
public class KycPhotoUpdateDaoImpl implements KycPhotoUpdateDao {

	@Autowired
	public AadhaarPhotoRepository aadhaarPhotoRepository;
	
	@Override
	public void saveKycPhotoUpdate(AadhaarPhotoEntity aadhaarPhotoEntity) {
		// TODO Auto-generated method stub
		aadhaarPhotoRepository.save(aadhaarPhotoEntity);
		
	}

	@Override
	public KycPhotoDto findByRefernceid(Long refernceid) {
		// TODO Auto-generated method stub
		KycPhotoDto kycPhotoDto = new KycPhotoDto();
		
		AadhaarPhotoEntity aadhaarPhotoEntity=aadhaarPhotoRepository.findByRefernceid(refernceid);
		if(!ObjectUtils.isEmpty(aadhaarPhotoEntity)) {
			CopyUtility.copyProperties(aadhaarPhotoEntity, kycPhotoDto);
		}
		return kycPhotoDto;
	}

}
