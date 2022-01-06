package com.gov.nha.bis.server.service.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dao.StateSchemeDao;
import com.gov.nha.bis.server.model.SchemeCardDetailsEntity;
import com.gov.nha.bis.server.model.StateSchemeDetailsEntity;
import com.gov.nha.bis.server.service.StateSchemeService;

@Service
public class StateSchemeServiceImpl implements StateSchemeService{
	private static final Logger logger = LoggerFactory.getLogger(StateSchemeServiceImpl.class);

	@Autowired
	public StateSchemeDao stateSchemeDao;

	@Override
	public Object saveStateSchemeDetail(StateSchemeDetailsEntity stateSchemeDetailsEntity) {
		try {
			return stateSchemeDao.saveStateSchemeDetail(stateSchemeDetailsEntity);
		}catch (Exception e) {
			logger.error("Exception in StateSchemeServiceImpl "+e);
		}
		return "Error during state detail scheme save";
	}

	@Override
	public Object saveSchemeCardDetail(SchemeCardDetailsEntity schemeCardDetailsEntity) {
		try {
			return stateSchemeDao.saveSchemeCardDetail(schemeCardDetailsEntity);
		}catch (Exception e) {
			logger.error("Exception in saveSchemeCardDetail "+e);
		}
		return "Error during scheme card detail save";
	}

	@Override
	public Object getSchemeCardDetail(SchemeCardDetailsEntity schemeCardDetailsEntity) {
		try {
			return stateSchemeDao.getSchemeCardDetail(schemeCardDetailsEntity);
		}catch (Exception e) {
			logger.error("Exception in getSchemeCardDetail "+e);
		}
		return "Error during scheme card detail fetch";
	}
	
}
