package com.gov.nha.bis.server.dao.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gov.nha.bis.server.dao.RationCardDao;
import com.gov.nha.bis.server.dto.RationDto;
import com.gov.nha.bis.server.model.RationCardEntity;
import com.gov.nha.bis.server.repository.RationCardRepository;

@Repository
public class RationCardDaoImpl implements RationCardDao {

	
	@Autowired
	public  RationCardRepository rationCardRepository;
	
	@Override
	public void saveRationCardEntity(RationCardEntity rationCardEntity) {
		// TODO Auto-generated method stub
		rationCardRepository.save(rationCardEntity);
	}

	@Override
	public List<RationDto> getRationCard(long referenceId, int stateCode, String urFlag, int status) {
		// TODO Auto-generated method stub
		return rationCardRepository.findRationCardEntity(referenceId, stateCode, urFlag, status);
	}

}
