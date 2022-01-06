package com.gov.nha.bis.server.service.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gov.nha.bis.server.dao.KycPhotoUpdateDao;
import com.gov.nha.bis.server.dto.KycPhotoDto;
import com.gov.nha.bis.server.model.AadhaarPhotoEntity;
import com.gov.nha.bis.server.service.KycPhotoUpdateService;

@Service
public class KycPhotoUpdateServiceImpl implements KycPhotoUpdateService{

	@Autowired
	public KycPhotoUpdateDao  kycPhotoUpdateDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveKycPhotoUpdate(AadhaarPhotoEntity aadhaarPhotoEntity) {
		// TODO Auto-generated method stub
		kycPhotoUpdateDao.saveKycPhotoUpdate(aadhaarPhotoEntity);
	}

	@Override
	public KycPhotoDto findByRefernceid(Long refernceid) {
		// TODO Auto-generated method stub
		return kycPhotoUpdateDao.findByRefernceid(refernceid);
	}
	

}
