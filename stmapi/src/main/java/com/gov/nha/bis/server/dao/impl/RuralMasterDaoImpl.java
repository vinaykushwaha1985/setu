package com.gov.nha.bis.server.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gov.nha.bis.server.dao.RuralMasterDao;
import com.gov.nha.bis.server.dto.BlockDto;
import com.gov.nha.bis.server.dto.DistrictDto;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.VillageDto;
import com.gov.nha.bis.server.repository.RuralMasterRepository;

@Repository
public class RuralMasterDaoImpl implements RuralMasterDao {
	
	@Autowired
	public RuralMasterRepository ruralMasterRepository;

	@Override
	public List<StateDto> getState() {
		// TODO Auto-generated method stub
		return ruralMasterRepository.findState();
	}

	@Override
	public List<DistrictDto> getDistict(int state) {
		// TODO Auto-generated method stub
		return ruralMasterRepository.findDistrict(state);
	}

	@Override
	public List<BlockDto> getBlock(int state, int district) {
		// TODO Auto-generated method stub
		return ruralMasterRepository.findBlock(state, district);
	}

	@Override
	public List<VillageDto> getVillage(int state, int district, int block) {
		// TODO Auto-generated method stub
		return ruralMasterRepository.findVillage(state, district, block);
	}

	

}
