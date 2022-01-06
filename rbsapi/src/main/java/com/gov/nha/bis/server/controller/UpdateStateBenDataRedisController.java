/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
package com.gov.nha.bis.server.controller;

import org.json.JSONObject;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gov.nha.bis.server.dto.RuralDto;
import com.gov.nha.bis.server.dto.SearchParam;
import com.gov.nha.bis.server.dto.UrbanDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.redis.cache.manager.RuralCacheManager;
import com.gov.nha.bis.server.redis.cache.manager.UrbanCacheManager;
import com.gov.nha.bis.server.response.BeneficiaryServiceResponse;
import com.gov.nha.bis.server.rest.service.BenUpdateCardStatusService;


@RestController
@RequestMapping("/bis")
@CrossOrigin
public class UpdateStateBenDataRedisController extends NhaBisBaseController{



	private static final Logger logger = LoggerFactory.getLogger(UpdateStateBenDataRedisController.class);

	@Autowired
	public RuralCacheManager ruralCacheManager;

	@Autowired
	public UrbanCacheManager urbanCacheManager;

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public BenUpdateCardStatusService benUpdateCardStatusService;

	@PostMapping(value="/update/beneficiary/cardStatus/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String updateCardStatusRequest(@RequestBody SearchParam reqParam) {

		String sRturnResponse =null;
		RuralDto ruralEntity= null;
		UrbanDto urbanEntity= null;
		Long updateId=null;
		try{


			if(!ObjectUtils.isEmpty(reqParam.getGuid()) && !ObjectUtils.isEmpty(reqParam.getUrFlag())) {

				logger.info("S"+reqParam.getStateCode()+"_"+reqParam.getUrFlag()
				+"_"+reqParam.getStateCode()
				+reqParam.getDistrictCode()+reqParam.getBlockCode()+reqParam.getVillageCode());

				if(reqParam.getUrFlag().equalsIgnoreCase("R")) {	
					if(!ObjectUtils.isEmpty(reqParam.getStateCode()) 
							&& !ObjectUtils.isEmpty(reqParam.getGuid())
							&& !ObjectUtils.isEmpty(reqParam.getDistrictCode())
							&& !ObjectUtils.isEmpty(reqParam.getBlockCode())
							&& !ObjectUtils.isEmpty(reqParam.getVillageCode())
							&& !ObjectUtils.isEmpty(reqParam.getUrFlag())
							&& !ObjectUtils.isEmpty(reqParam.getCard_status())
							) {

						ruralEntity=ruralCacheManager.getRuralDto("S"+reqParam.getStateCode()+"_"+reqParam.getUrFlag()
						+"_"+reqParam.getStateCode()
						+reqParam.getDistrictCode()+reqParam.getBlockCode()+reqParam.getVillageCode(),
						reqParam.getGuid());


						if(!ObjectUtils.isEmpty(ruralEntity)) {
							ruralEntity.setCard_status(reqParam.getCard_status());

							updateId=	ruralCacheManager.update("S"+reqParam.getStateCode()+"_"+reqParam.getUrFlag()
							+"_"+reqParam.getStateCode()
							+reqParam.getDistrictCode()+reqParam.getBlockCode()+reqParam.getVillageCode(),
							reqParam.getGuid(),ruralEntity);


							//	sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), true, "200", "Success",reqParam.getUrFlag());

						}else{
							sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());

						}


					}else {
						sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), false, "202", "Guid Not Exist.",reqParam.getUrFlag());

					}

				}else if(reqParam.getUrFlag().equalsIgnoreCase("U")) {

					logger.info("S"+reqParam.getStateCode()+"_"+reqParam.getUrFlag()
					+"_"+reqParam.getStateCode()
					+reqParam.getDistrictCode()+reqParam.getTownCode()+reqParam.getWardCode());

					if(!ObjectUtils.isEmpty(reqParam.getStateCode()) 
							&& !ObjectUtils.isEmpty(reqParam.getGuid())
							&& !ObjectUtils.isEmpty(reqParam.getDistrictCode())
							&& !ObjectUtils.isEmpty(reqParam.getTownCode())
							&& !ObjectUtils.isEmpty(reqParam.getWardCode())
							&& !ObjectUtils.isEmpty(reqParam.getUrFlag())
							&& !ObjectUtils.isEmpty(reqParam.getCard_status())
							) {

						urbanEntity=urbanCacheManager.getUrbanDto("S"+reqParam.getStateCode()+"_"+reqParam.getUrFlag()
						+"_"+reqParam.getStateCode()
						+reqParam.getDistrictCode()+reqParam.getTownCode()+reqParam.getWardCode(),
						reqParam.getGuid());


						if(!ObjectUtils.isEmpty(urbanEntity)) {
							urbanEntity.setCard_status(reqParam.getCard_status());

							updateId=	urbanCacheManager.update("S"+reqParam.getStateCode()+"_"+reqParam.getUrFlag()
							+"_"+reqParam.getStateCode()
							+reqParam.getDistrictCode()+reqParam.getTownCode()+reqParam.getWardCode(),
							reqParam.getGuid(),urbanEntity);


							//	sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), true, "200", "Success",reqParam.getUrFlag());
						}else{
							sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());

						}


					}else {
						sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), false, "202", "Guid Not Exist.",reqParam.getUrFlag());

					}


				}else{
					sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());

				}
				if(!ObjectUtils.isEmpty(updateId)) {
					if(updateId>0) {

						String updateRes=		benUpdateCardStatusService.benUpdateCardStatus(reqParam.getGuid(), 
								reqParam.getStateCode(), reqParam.getCard_status(), reqParam.getUrFlag(), 
								reqParam.getUserid());
						if(!ObjectUtils.isEmpty(updateRes)) {
							JSONObject upRes=new JSONObject(updateRes);
							if(upRes.getBoolean("status"))		
								sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), true, "200", "Success",reqParam.getUrFlag());
							else
								sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());

						}else {
							sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());
						}
					}
				}
			}else{
				sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());

			}

		}catch(Exception e){
			logger.info(e.getMessage());
			sRturnResponse=BeneficiaryServiceResponse.updateBenStatusResponse(String.valueOf(reqParam.getGuid()), false, "202", "Guid Not Exist.",reqParam.getUrFlag());

		}

		logger.info(sRturnResponse.toString());

		return sRturnResponse.toString();

	}




}
