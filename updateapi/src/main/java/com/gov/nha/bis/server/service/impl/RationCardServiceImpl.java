package com.gov.nha.bis.server.service.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dao.RationCardDao;
import com.gov.nha.bis.server.dto.RationDto;
import com.gov.nha.bis.server.model.RationCardEntity;
import com.gov.nha.bis.server.service.RationCardService;

@Service
public class RationCardServiceImpl implements RationCardService{

	
	@Autowired
	public RationCardDao rationCardDao;
	
	
	@Override
	public void saveRationCardEntity(RationCardEntity rationCardEntity) {
		// TODO Auto-generated method stub
		rationCardDao.saveRationCardEntity(rationCardEntity);
	}

	@Override
	public List<RationDto> getRationCard(long referenceId, int stateCode, String urFlag, int status) {
		// TODO Auto-generated method stub
		return rationCardDao.getRationCard(referenceId, stateCode, urFlag, status);
	}

}
