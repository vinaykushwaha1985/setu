package com.gov.nha.bis.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gov.nha.bis.server.dto.DistrictUrbanDto;
import com.gov.nha.bis.server.dto.MasterParam;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.TownDto;
import com.gov.nha.bis.server.dto.WardDto;
import com.gov.nha.bis.server.response.VillageServiceResponse;
import com.gov.nha.bis.server.service.UrbanMasterService;


@RestController
@RequestMapping("/bis")
@CrossOrigin
public class UrbanMasterController extends NhaBisBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(RuralMasterController.class);
	
	
	@Autowired
	public UrbanMasterService urbanMasterService;
	

	
	@PostMapping(value="/urban/master/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String dschRequest(@RequestBody MasterParam masterParam) {

		String sRturnResponse =null;
		List<StateDto> stateDto= null;
		List<DistrictUrbanDto> districtDto= null;
		List<TownDto> townDto= null;
		List<WardDto> wardDto= null;
		
		try{

			if(masterParam.getType().equalsIgnoreCase("S")){
				stateDto=urbanMasterService.getState();
				sRturnResponse=VillageServiceResponse.stateListResponse(stateDto, masterParam.getType(),"true", "", "");

			}else if(masterParam.getType().equalsIgnoreCase("D")){
				districtDto=urbanMasterService.getDistict(masterParam.getStateCode());
				
				sRturnResponse=VillageServiceResponse.districtUrbanListResponse(districtDto, masterParam.getType(),"true", "", "");

			} 
			else if(masterParam.getType().equalsIgnoreCase("T")){
				townDto=urbanMasterService.getTown(masterParam.getStateCode(),String.valueOf(masterParam.
						getDistrictCode()));
				sRturnResponse=VillageServiceResponse.townListResponse(townDto, masterParam.getType(),"true", "", "");

			}else if(masterParam.getType().equalsIgnoreCase("W")){
				wardDto=urbanMasterService.getWard(masterParam.getStateCode(),String.valueOf(masterParam.
						getDistrictCode()), masterParam.getTownCode());
				
				sRturnResponse=VillageServiceResponse.wardListResponse(wardDto, masterParam.getType(),"true", "", "");

			}

		}catch(Exception e){
			logger.info(e.getMessage());
			sRturnResponse=VillageServiceResponse.stateListResponse(stateDto,"", "false", "201", "Invalid Request Parameters.");
			
		}

		return sRturnResponse.toString();
	}

	



}
