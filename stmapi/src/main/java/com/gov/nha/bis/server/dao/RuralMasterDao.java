package com.gov.nha.bis.server.dao;

import java.util.List;

import com.gov.nha.bis.server.dto.BlockDto;
import com.gov.nha.bis.server.dto.DistrictDto;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.VillageDto;

public interface RuralMasterDao {
	
	public List<StateDto> getState();
	
	public List<DistrictDto> getDistict(int state);
	
	public List<BlockDto> getBlock(int state,int district);
	
	public List<VillageDto> getVillage(int state,int district,int block);
	
	

}
