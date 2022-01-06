package com.gov.nha.bis.server.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gov.nha.bis.server.dao.UrbanMasterDao;
import com.gov.nha.bis.server.dto.DistrictUrbanDto;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.TownDto;
import com.gov.nha.bis.server.dto.WardDto;
import com.gov.nha.bis.server.repository.UrbanMasterRepository;

@Repository
public class UrbanMasterDaoImpl implements UrbanMasterDao {

	@Autowired
	public UrbanMasterRepository urbanMasterRepository;
	
	@Override
	public List<StateDto> getState() {
		// TODO Auto-generated method stub
		return urbanMasterRepository.findState();
	}

	@Override
	public List<DistrictUrbanDto> getDistict(int state) {
		// TODO Auto-generated method stub
		return urbanMasterRepository.findDistrict(state);
	}

	@Override
	public List<TownDto> getTown(int state, String district) {
		// TODO Auto-generated method stub
		return urbanMasterRepository.findTown(state, district);
	}

	@Override
	public List<WardDto> getWard(int state, String district, String town) {
		// TODO Auto-generated method stub
		return urbanMasterRepository.findWard(state, district, town);
	}

}
