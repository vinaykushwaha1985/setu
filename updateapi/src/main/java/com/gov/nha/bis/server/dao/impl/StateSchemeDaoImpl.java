package com.gov.nha.bis.server.dao.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gov.nha.bis.server.dao.StateSchemeDao;
import com.gov.nha.bis.server.dto.SchemeCardDetailDto;
import com.gov.nha.bis.server.model.SchemeCardDetailsEntity;
import com.gov.nha.bis.server.model.StateSchemeDetailsEntity;
import com.gov.nha.bis.server.repository.SchemeCardDetailsRepository;
import com.gov.nha.bis.server.repository.StateSchemeDetailsRepository;

@Repository
public class StateSchemeDaoImpl implements StateSchemeDao {
	
	private static final Logger logger = LoggerFactory.getLogger(StateSchemeDaoImpl.class);
	
	@Autowired
	StateSchemeDetailsRepository stateSchemeDetailsRepository;
	
	@Autowired
	SchemeCardDetailsRepository schemeCardDetailsRepository;

	@Override
	public Object saveStateSchemeDetail(StateSchemeDetailsEntity stateSchemeDetailsEntity) {
		StateSchemeDetailsEntity obj=null;
		try {
			obj=stateSchemeDetailsRepository.save(stateSchemeDetailsEntity);
			return obj;
		}catch (Exception e) {
			logger.error("Exception during state scheme detail save"+e);
		}
		return "Error during state detail scheme save";
	}

	@Override
	public Object saveSchemeCardDetail(SchemeCardDetailsEntity schemeCardDetailsEntity) {
		SchemeCardDetailsEntity obj=null;
		try {
			obj=schemeCardDetailsRepository.save(schemeCardDetailsEntity);
			return obj;
		}catch (Exception e) {
			logger.error("Exception during scheme card detail save"+e);
		}
		return "Error during scheme card detail save";
	}

	@Override
	public Object getSchemeCardDetail(SchemeCardDetailsEntity schemeCardDetailsEntity) {
		SchemeCardDetailDto obj=null;
		try {
			obj=schemeCardDetailsRepository.findSchemeCardDetail(schemeCardDetailsEntity.getRural_urban(),schemeCardDetailsEntity.getState_code(),
					schemeCardDetailsEntity.getDistrict_code(),schemeCardDetailsEntity.getSchemeid(),schemeCardDetailsEntity.getBeneficiaryid());
			return obj;
		}catch (Exception e) {
			logger.error("Exception during scheme card detail fetch "+e);
		}
		return "Error during scheme card detail fetch";
	}
	
}
