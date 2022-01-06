package com.gov.nha.bis.server.service.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dao.BeneficiaryCardStatusDao;
import com.gov.nha.bis.server.model.BeneficiaryCardStatusEntity;
import com.gov.nha.bis.server.service.BeneficiaryCardStatusService;

@Service
public class BeneficiaryCardStatusServiceImpl implements BeneficiaryCardStatusService {

	
	@Autowired
	public BeneficiaryCardStatusDao beneficiaryCardStatusDao;
	
	@Override
	public void saveBeneficiaryCardStatusEntity(BeneficiaryCardStatusEntity beneficiaryCardStatusEntity) {
		// TODO Auto-generated method stub
		beneficiaryCardStatusDao.saveBeneficiaryCardStatusEntity(beneficiaryCardStatusEntity);
	}

}
