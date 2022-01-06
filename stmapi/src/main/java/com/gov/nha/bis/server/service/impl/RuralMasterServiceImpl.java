package com.gov.nha.bis.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dao.RuralMasterDao;
import com.gov.nha.bis.server.dto.BlockDto;
import com.gov.nha.bis.server.dto.DistrictDto;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.VillageDto;
import com.gov.nha.bis.server.redis.cache.manager.StateCacheManager;
import com.gov.nha.bis.server.service.RuralMasterService;

@Service
public class RuralMasterServiceImpl implements RuralMasterService {

	private StateCacheManager stateCacheManager;
	
	@Autowired
    public RuralMasterServiceImpl(StateCacheManager stateCacheManager)    {
        this.stateCacheManager = stateCacheManager;
    }
	
	@Autowired
	public RuralMasterDao ruralMasterDao;

	
	 @Override
	 public void cacheStateDetails(boolean checkFlag) throws Exception {
	        if(stateCacheManager.checkEmpty()) {// If cache is empty the put the data
	          List<StateDto> stateDto= ruralMasterDao.getState();
	          stateDto.forEach(state->stateCacheManager.cacheStateDetails(state));
	        }
	    }
	
	@Override
	public List<StateDto> getState() {
		// TODO Auto-generated method stub
		return ruralMasterDao.getState();
	}

	@Override
	public List<DistrictDto> getDistict(int state) {
		// TODO Auto-generated method stub
		return ruralMasterDao.getDistict(state);
	}

	@Override
	public List<BlockDto> getBlock(int state, int district) {
		// TODO Auto-generated method stub
		return ruralMasterDao.getBlock(state, district);
	}

	@Override
	public List<VillageDto> getVillage(int state, int district, int block) {
		// TODO Auto-generated method stub
		return ruralMasterDao.getVillage(state, district, block);
	}

}
