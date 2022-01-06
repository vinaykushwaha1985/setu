package com.gov.nha.bis.server.dao.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gov.nha.bis.server.dao.DemoAuthDao;
import com.gov.nha.bis.server.dto.DemoDto;
import com.gov.nha.bis.server.model.DemoAuthEntity;
import com.gov.nha.bis.server.model.MapperDetailsEntity;
import com.gov.nha.bis.server.repository.DemoAuthRepository;
import com.gov.nha.bis.server.repository.MapperDetailsRepository;

@Repository
public class DemoAuthDaoImpl implements DemoAuthDao {

	@Autowired
	public DemoAuthRepository demoAuthRepository;
	
	@Autowired
	public MapperDetailsRepository mapperDetailsRepository;
	
	@Override
	public void saveDemoAuthEntity(DemoAuthEntity demoAuthEntity) {
		// TODO Auto-generated method stub
		demoAuthRepository.save(demoAuthEntity);
	}
	
	@Override
	public void saveDemoAuthEntity(DemoAuthEntity demoAuthEntity,MapperDetailsEntity mapperDetailsEntity) {
		// TODO Auto-generated method stub
		demoAuthRepository.save(demoAuthEntity);
		mapperDetailsRepository.save(mapperDetailsEntity);
	}

	@Override
	public List<DemoDto> getDemoAuthEntity(long referenceId , String stateCode,String urFlag,int status) {
		// TODO Auto-generated method stub
		return demoAuthRepository.findDemoEntity(referenceId,stateCode, urFlag, status);
	}

}
