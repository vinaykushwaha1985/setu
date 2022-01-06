package com.gov.nha.bis.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dao.BisMasterDao;
import com.gov.nha.bis.server.service.BisMasterService;
@Service
public class BisMasterServiceImpl implements BisMasterService{

	@Autowired
	BisMasterDao bisMasterDao;
	
	@Override
	public Object getAllBank() {
		return bisMasterDao.getAllBank();
	}

	@Override
	public Object getStateSchemeDetails(Integer stateCode) {
		return bisMasterDao.getStateSchemeDetails(stateCode);
	}

	@Override
	public Object getStateSubSchemeDetails(String schemeId,Integer stateCode) {
		return bisMasterDao.getStateSubSchemeDetails(schemeId,stateCode);
	}

	@Override
	public Object getStateSubSchemeDocumentDetails(String schemeId, String subSchemeId) {
		return bisMasterDao.getStateSubSchemeDocumentDetails(schemeId,subSchemeId);
	}

}
