package com.gov.nha.bis.server.service.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dao.SmsOtpDao;
import com.gov.nha.bis.server.model.SmsOtpEntity;
import com.gov.nha.bis.server.service.SmsOtpService;

@Service
public class SmsOtpServiceImpl implements SmsOtpService {

	
	@Autowired
	public SmsOtpDao smsOtpDao;



	@Override
	public void saveSmsOtpEntity(SmsOtpEntity smsOtpEntity) {
		// TODO Auto-generated method stub
		smsOtpDao.saveSmsOtpEntity(smsOtpEntity);
	}

}
