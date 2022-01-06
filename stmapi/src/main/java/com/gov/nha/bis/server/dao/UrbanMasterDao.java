package com.gov.nha.bis.server.dao;

import java.util.List;

import com.gov.nha.bis.server.dto.DistrictUrbanDto;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.TownDto;
import com.gov.nha.bis.server.dto.WardDto;

public interface UrbanMasterDao {

	
	public List<StateDto> getState();
	
	public List<DistrictUrbanDto> getDistict(int state);
	
	public List<TownDto> getTown(int state,String district);
	
	public List<WardDto> getWard(int state,String district,String town);



}
