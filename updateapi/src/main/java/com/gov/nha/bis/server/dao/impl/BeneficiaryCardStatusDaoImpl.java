package com.gov.nha.bis.server.dao.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gov.nha.bis.server.dao.BeneficiaryCardStatusDao;
import com.gov.nha.bis.server.model.BeneficiaryCardStatusEntity;
import com.gov.nha.bis.server.repository.BeneficiaryCardStatusRepository;

@Repository
public class BeneficiaryCardStatusDaoImpl implements BeneficiaryCardStatusDao {

	@Autowired
	public  BeneficiaryCardStatusRepository beneficiaryCardStatusRepository;
	
	@Override
	public void saveBeneficiaryCardStatusEntity(BeneficiaryCardStatusEntity beneficiaryCardStatusEntity) {
		// TODO Auto-generated method stub
		beneficiaryCardStatusRepository.save(beneficiaryCardStatusEntity);
	}

}
