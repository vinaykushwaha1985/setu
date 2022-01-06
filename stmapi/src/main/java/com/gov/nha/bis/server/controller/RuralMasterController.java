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

import com.gov.nha.bis.server.dto.BlockDto;
import com.gov.nha.bis.server.dto.DistrictDto;
import com.gov.nha.bis.server.dto.MasterParam;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.VillageDto;
import com.gov.nha.bis.server.response.VillageServiceResponse;
import com.gov.nha.bis.server.service.RuralMasterService;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class RuralMasterController extends NhaBisBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(RuralMasterController.class);
	
	
	@Autowired
	public RuralMasterService ruralMasterService;
	
	@PostMapping(value="/rural/master/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String dschRequest(@RequestBody MasterParam masterParam) {

		String sRturnResponse =null;
		List<StateDto> stateDto= null;
		List<DistrictDto> districtDto= null;

		List<BlockDto> blockDto= null;

		List<VillageDto> villageDto= null;

		try{

			if(masterParam.getType().equalsIgnoreCase("S")){
				stateDto=ruralMasterService.getState();
				sRturnResponse=VillageServiceResponse.stateListResponse(stateDto, masterParam.getType(),"true", "", "");

			}else if(masterParam.getType().equalsIgnoreCase("D")){
				districtDto=ruralMasterService.getDistict(masterParam.getStateCode());
				sRturnResponse=VillageServiceResponse.districtListResponse(districtDto, masterParam.getType(),"true", "", "");

			}else if(masterParam.getType().equalsIgnoreCase("B")){
				blockDto=ruralMasterService.getBlock(masterParam.getStateCode(),masterParam.getDistrictCode());
				sRturnResponse=VillageServiceResponse.blockListResponse(blockDto, masterParam.getType(),"true", "", "");

			}else if(masterParam.getType().equalsIgnoreCase("V")){
				villageDto=ruralMasterService.getVillage(masterParam.getStateCode(),masterParam.getDistrictCode(),masterParam.getBlockCode());
				sRturnResponse=VillageServiceResponse.villageListResponse(villageDto, masterParam.getType(),"true", "", "");
			}


		}catch(Exception e){
			logger.info(e.getMessage());
			sRturnResponse=VillageServiceResponse.stateListResponse(stateDto,"", "false", "201", "Invalid Request Parameters.");

		}

		return sRturnResponse.toString();
	}

	



}
