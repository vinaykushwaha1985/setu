/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
package com.gov.nha.bis.server.controller;

import java.util.List;

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



@RestController
@RequestMapping("/bis")
@CrossOrigin
public class FetchStateBenDataRedisController {

	@Autowired
	public RuralCacheManager ruralCacheManager;
	
	@Autowired
	public UrbanCacheManager urbanCacheManager;

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;



	private static final Logger logger = LoggerFactory.getLogger(FetchStateBenDataRedisController.class);

	@ResponseBody
	@PostMapping(value ="/state/beneficiary/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	public String vschRequest(@RequestBody  SearchParam  reqParam) {

		String sRturnResponse =null;
		List<RuralDto> ruralEntity= null;
		List<UrbanDto> urbanEntity= null;

		try{
			if(!ObjectUtils.isEmpty(reqParam.getStateCode()) && !ObjectUtils.isEmpty(reqParam.getUrFlag())){

				if(reqParam.getUrFlag().equalsIgnoreCase("R")) {
					ruralEntity=ruralCacheManager.getRuralList("S"+reqParam.getStateCode()+"_"+reqParam.getUrFlag()
					+"_"+reqParam.getStateCode()
					+reqParam.getDistrictCode()+reqParam.getBlockCode()+reqParam.getVillageCode());
				}else if(reqParam.getUrFlag().equalsIgnoreCase("U")) {
					urbanEntity=urbanCacheManager.getUrbanList("S"+reqParam.getStateCode()+"_"+reqParam.getUrFlag()
					+"_"+reqParam.getStateCode()
					+reqParam.getDistrictCode()+reqParam.getTownCode()+reqParam.getWardCode());
				}

				if(!ObjectUtils.isEmpty(ruralEntity) && reqParam.getUrFlag().equalsIgnoreCase("R")){

					sRturnResponse=BeneficiaryServiceResponse.ruralListResponse(ruralEntity, "true", "", "");
				}else if(!ObjectUtils.isEmpty(urbanEntity) && reqParam.getUrFlag().equalsIgnoreCase("U")){

					sRturnResponse=BeneficiaryServiceResponse.urbanListResponse(urbanEntity, "true", "", "");
				}
				else{
					sRturnResponse=BeneficiaryServiceResponse.ruralListResponse(ruralEntity, "false", "201", "Invalid Request Parameters.");

				}




			}else{
				sRturnResponse=BeneficiaryServiceResponse.ruralListResponse(ruralEntity, "false", "201", "Invalid Request Parameters.");

			}

		}catch (Exception e) {
			// TODO: handle exception
		}
		return sRturnResponse;
	}
}
