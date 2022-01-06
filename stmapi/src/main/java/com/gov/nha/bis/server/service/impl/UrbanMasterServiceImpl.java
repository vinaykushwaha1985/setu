package com.gov.nha.bis.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dao.UrbanMasterDao;
import com.gov.nha.bis.server.dto.DistrictUrbanDto;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.TownDto;
import com.gov.nha.bis.server.dto.WardDto;
import com.gov.nha.bis.server.service.UrbanMasterService;

@Service
public class UrbanMasterServiceImpl implements UrbanMasterService {
	
	@Autowired
	public UrbanMasterDao urbanMasterDao;

	@Override
	public List<StateDto> getState() {
		// TODO Auto-generated method stub
		return urbanMasterDao.getState();
	}

	@Override
	public List<DistrictUrbanDto> getDistict(int state) {
		// TODO Auto-generated method stub
		return urbanMasterDao.getDistict(state);
	}

	@Override
	public List<TownDto> getTown(int state, String district) {
		// TODO Auto-generated method stub
		return urbanMasterDao.getTown(state, district);
	}

	@Override
	public List<WardDto> getWard(int state, String district, String town) {
		// TODO Auto-generated method stub
		return urbanMasterDao.getWard(state, district, town);
	}

}
