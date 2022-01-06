/**
 * 
 */
package com.gov.nha.bis.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gov.nha.bis.server.dto.RuralDto;
import com.gov.nha.bis.server.dto.SearchParam;
import com.gov.nha.bis.server.dto.UrbanDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.redis.cache.manager.RuralCacheManager;
import com.gov.nha.bis.server.redis.cache.manager.UrbanCacheManager;
import com.gov.nha.bis.server.response.BeneficiaryServiceResponse;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@RestController
@RequestMapping("/bis")
@CrossOrigin
public class FetchBeneficiaryGuidController {

	@Autowired
	public RuralCacheManager ruralCacheManager;
	
	@Autowired
	public UrbanCacheManager urbanCacheManager;

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;



	private static final Logger logger = LoggerFactory.getLogger(FetchBeneficiaryGuidController.class);

	@ResponseBody
	@PostMapping(value ="/beneficiary/guid/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	public String vschRequest(@RequestBody  SearchParam  reqParam) {


		String sRturnResponse =null;
		RuralDto ruralEntity= null;
		UrbanDto urbanEntity= null;
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
							) {

						ruralEntity=ruralCacheManager.getRuralDto("S"+reqParam.getStateCode()+"_"+reqParam.getUrFlag()
						+"_"+reqParam.getStateCode()
						+reqParam.getDistrictCode()+reqParam.getBlockCode()+reqParam.getVillageCode(),
						reqParam.getGuid());


						if(!ObjectUtils.isEmpty(ruralEntity)) {
							
							sRturnResponse=BeneficiaryServiceResponse.ruralDtoResponse(ruralEntity,String.valueOf(reqParam.getGuid()), true, "200", "Success",reqParam.getUrFlag());

						}else{
							sRturnResponse=BeneficiaryServiceResponse.ruralDtoResponse(ruralEntity,String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());

						}


					}else {
						sRturnResponse=BeneficiaryServiceResponse.ruralDtoResponse(ruralEntity,String.valueOf(reqParam.getGuid()), false, "202", "Guid Not Exist.",reqParam.getUrFlag());

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
						
								sRturnResponse=BeneficiaryServiceResponse.urbanDtoResponse(urbanEntity,String.valueOf(reqParam.getGuid()), true, "200", "Success",reqParam.getUrFlag());
						}else{
							sRturnResponse=BeneficiaryServiceResponse.urbanDtoResponse(urbanEntity,String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());

						}


					}else {
						sRturnResponse=BeneficiaryServiceResponse.urbanDtoResponse(urbanEntity,String.valueOf(reqParam.getGuid()), false, "202", "Guid Not Exist.",reqParam.getUrFlag());

					}


				}else{
					sRturnResponse=BeneficiaryServiceResponse.urbanDtoResponse(urbanEntity,String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());

				}
				
			}else{
				sRturnResponse=BeneficiaryServiceResponse.urbanDtoResponse(urbanEntity,String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameters.",reqParam.getUrFlag());

			}

		}catch(Exception e){
			logger.info(e.getMessage());
			sRturnResponse=BeneficiaryServiceResponse.urbanDtoResponse(urbanEntity,String.valueOf(reqParam.getGuid()), false, "202", "Guid Not Exist.",reqParam.getUrFlag());

		}

		logger.info(sRturnResponse.toString());

		return sRturnResponse.toString();

	
	}


}
