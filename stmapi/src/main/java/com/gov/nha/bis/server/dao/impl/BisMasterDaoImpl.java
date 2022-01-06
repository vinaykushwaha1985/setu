package com.gov.nha.bis.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gov.nha.bis.server.controller.BisMasterController;
import com.gov.nha.bis.server.dao.BisMasterDao;
import com.gov.nha.bis.server.dto.StateSchemeDto;
import com.gov.nha.bis.server.dto.StateSubSchemeDocumentDto;
import com.gov.nha.bis.server.dto.StateSubSchemeDto;
import com.gov.nha.bis.server.model.BisBankMasterEntity;
import com.gov.nha.bis.server.repository.BisBankMasterReposityry;
import com.gov.nha.bis.server.repository.StateSchemeRepository;
import com.gov.nha.bis.server.repository.StateSubSchemeDocumentRepository;
import com.gov.nha.bis.server.repository.StateSubSchemeRepository;
@Repository
public class BisMasterDaoImpl implements BisMasterDao {
	
	private static final Logger logger = LoggerFactory.getLogger(BisMasterDaoImpl.class);

	@Autowired
	BisBankMasterReposityry bisBankMasterReposityry;
	@Autowired
	StateSchemeRepository stateSchemeRepository;
	@Autowired
	StateSubSchemeRepository stateSubSchemeRepository;
	@Autowired
	StateSubSchemeDocumentRepository stateSubSchemeDocumentRepository;
	
	@Override
	public Object getAllBank() {
		List<BisBankMasterEntity> bankList=new ArrayList<BisBankMasterEntity>();
		try {
			bankList=(List<BisBankMasterEntity>) bisBankMasterReposityry.findAll();
			return bankList;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Object getStateSchemeDetails(Integer stateCode) {
		List<StateSchemeDto> schemeList=new ArrayList<StateSchemeDto>();
		try {
			logger.info("Searching state scheme data for state code " +stateCode);
			schemeList=(List<StateSchemeDto>) stateSchemeRepository.findStateScheme(stateCode);
			logger.info("Data from dao schemeList-- " +schemeList);
			return schemeList;
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Object getStateSubSchemeDetails(String schemeId,Integer stateCode) {
		List<StateSubSchemeDto> subschemeList=new ArrayList<StateSubSchemeDto>();
		try {
			logger.info("Searching state Sub scheme data for scheme id " +schemeId);
			subschemeList=(List<StateSubSchemeDto>) stateSubSchemeRepository.findSubSchemeDts(schemeId,stateCode);
			logger.info("Data from dao subschemeList-- " +subschemeList);
			return subschemeList;
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Object getStateSubSchemeDocumentDetails(String schemeId, String subSchemeId) {
		List<StateSubSchemeDocumentDto> subschemeDocumentList=new ArrayList<StateSubSchemeDocumentDto>();
		try {
			logger.info("Searching state Sub scheme data for scheme id " +schemeId);
			subschemeDocumentList=(List<StateSubSchemeDocumentDto>) stateSubSchemeDocumentRepository.findSubSchemeDocumentDts(schemeId,subSchemeId);
			logger.info("Data from dao subschemeDocumentList-- " +subschemeDocumentList);
			return subschemeDocumentList;
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
