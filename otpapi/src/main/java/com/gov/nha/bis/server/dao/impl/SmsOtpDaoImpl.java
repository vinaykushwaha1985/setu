package com.gov.nha.bis.server.dao.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gov.nha.bis.server.dao.SmsOtpDao;
import com.gov.nha.bis.server.model.SmsOtpEntity;
import com.gov.nha.bis.server.repository.SmsOtpRepository;

@Repository
public class SmsOtpDaoImpl implements SmsOtpDao {
	
	@Autowired
	public SmsOtpRepository smsOtpRepository;

	@Override
	public void saveSmsOtpEntity(SmsOtpEntity smsOtpEntity) {
		// TODO Auto-generated method stub
		smsOtpRepository.save(smsOtpEntity);
	}

}
