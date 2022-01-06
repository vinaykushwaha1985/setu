package com.gov.nha.bis.server.service.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gov.nha.bis.server.dao.DemoAuthDao;
import com.gov.nha.bis.server.dto.DemoDto;
import com.gov.nha.bis.server.model.DemoAuthEntity;
import com.gov.nha.bis.server.model.MapperDetailsEntity;
import com.gov.nha.bis.server.service.DemoAuthService;

@Service
public class DemoAuthServiceImpl implements DemoAuthService {
	
	@Autowired
	public DemoAuthDao demoAuthDao;

	@Override
	public void saveDemoAuthEntity(DemoAuthEntity demoAuthEntity) {
		// TODO Auto-generated method stub
		demoAuthDao.saveDemoAuthEntity(demoAuthEntity);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDemoAuthEntity(DemoAuthEntity demoAuthEntity,MapperDetailsEntity mapperDetailsEntity) {
		// TODO Auto-generated method stub
		demoAuthDao.saveDemoAuthEntity(demoAuthEntity, mapperDetailsEntity);
	}

	@Override
	public List<DemoDto> getDemoAuthEntity(long stateCode, String referenceId, String urFlag, int status) {
		// TODO Auto-generated method stub
		return demoAuthDao.getDemoAuthEntity(stateCode, referenceId, urFlag, status);
	}

}
