package com.gov.nha.bis.server.service.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gov.nha.bis.server.dao.KycUpdateDao;
import com.gov.nha.bis.server.dto.KycDataDto;
import com.gov.nha.bis.server.model.AadhaarDetailsEntity;
import com.gov.nha.bis.server.model.AadhaarPhotoEntity;
import com.gov.nha.bis.server.model.MapperDetailsEntity;
import com.gov.nha.bis.server.service.KycUpdateService;

@Service
public class KycUpdateServiceImpl implements KycUpdateService {

	@Autowired
	public KycUpdateDao kycUpdateDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveKycUpdate(AadhaarDetailsEntity aadhaarDetailsEntity,AadhaarPhotoEntity aadhaarPhotoEntity,
			MapperDetailsEntity mapperDetailsEntity,String ruralUrban){
		// TODO Auto-generated method stub
		kycUpdateDao.saveKycUpdate(aadhaarDetailsEntity,aadhaarPhotoEntity,mapperDetailsEntity,ruralUrban);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveKycUpdate(AadhaarDetailsEntity aadhaarDetailsEntity, AadhaarPhotoEntity aadhaarPhotoEntity,
			String ruralUrban) {
		// TODO Auto-generated method stub
		kycUpdateDao.saveKycUpdate(aadhaarDetailsEntity,aadhaarPhotoEntity,ruralUrban);
	}

	@Override
	public KycDataDto getAadhaarDetailsEntity(Long referenceId) {
		// TODO Auto-generated method stub
		return kycUpdateDao.getAadhaarDetailsEntity(referenceId);
	}

}
