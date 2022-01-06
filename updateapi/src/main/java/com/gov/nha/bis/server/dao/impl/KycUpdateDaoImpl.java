package com.gov.nha.bis.server.dao.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.gov.nha.bis.server.dao.KycUpdateDao;
import com.gov.nha.bis.server.dto.KycDataDto;
import com.gov.nha.bis.server.model.AadhaarDetailsEntity;
import com.gov.nha.bis.server.model.AadhaarPhotoEntity;
import com.gov.nha.bis.server.model.MapperDetailsEntity;
import com.gov.nha.bis.server.repository.AadhaarDetailsRepository;
import com.gov.nha.bis.server.repository.AadhaarPhotoRepository;
import com.gov.nha.bis.server.repository.MapperDetailsRepository;
import com.gov.nha.bis.server.util.CopyUtility;

@Repository
public class KycUpdateDaoImpl implements KycUpdateDao {

	@Autowired
	public AadhaarDetailsRepository aadhaarDetailsRepository;
	
	@Autowired
	public AadhaarPhotoRepository aadhaarPhotoRepository;
	
	@Autowired
	public MapperDetailsRepository mapperDetailsRepository;
	
	
	@Override
	public void saveKycUpdate(AadhaarDetailsEntity aadhaarDetailsEntity,AadhaarPhotoEntity aadhaarPhotoEntity, 
			MapperDetailsEntity mapperDetailsEntity,String ruralUrban)	{
		// TODO Auto-generated method stub
		aadhaarDetailsRepository.save(aadhaarDetailsEntity);
		aadhaarPhotoRepository.save(aadhaarPhotoEntity);
		mapperDetailsRepository.save(mapperDetailsEntity);
		
	
	}


	@Override
	public void saveKycUpdate(AadhaarDetailsEntity aadhaarDetailsEntity, AadhaarPhotoEntity aadhaarPhotoEntity,
			String ruralUrban) {
		// TODO Auto-generated method stub
		aadhaarDetailsRepository.save(aadhaarDetailsEntity);
		aadhaarPhotoRepository.save(aadhaarPhotoEntity);
	}


	@Override
	public KycDataDto getAadhaarDetailsEntity(Long referenceId) {
		// TODO Auto-generated method stub
		KycDataDto kycDataDto= new KycDataDto();
		
		AadhaarDetailsEntity aadhaarDetailsEntity=aadhaarDetailsRepository.findByRefernceid(referenceId);
		if(!ObjectUtils.isEmpty(aadhaarDetailsEntity)) {
			CopyUtility.copyProperties(aadhaarDetailsEntity, kycDataDto);
		}
		 
		 return kycDataDto;
	}

}
