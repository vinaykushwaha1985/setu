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
import com.gov.nha.bis.server.dto.SeccParam;
import com.gov.nha.bis.server.dto.UrbanDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.redis.cache.manager.RuralCacheManager;
import com.gov.nha.bis.server.redis.cache.manager.UrbanCacheManager;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.rest.service.SaveSeccFamilyDataService;
import com.gov.nha.bis.server.util.CopyUtility;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@RestController
@RequestMapping("/bis")
@CrossOrigin
public class AddFamilySeccController {

	@Autowired
	public RuralCacheManager ruralCacheManager;

	@Autowired
	public UrbanCacheManager urbanCacheManager;

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public SaveSeccFamilyDataService saveSeccFamilyDataService;

	private static final Logger logger = LoggerFactory.getLogger(AddFamilySeccController.class);

	
	@PostMapping(value ="/beneficiary/family/data/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addFamilySecc(@RequestBody  SeccParam  reqParam) {

		if(!ObjectUtils.isEmpty(reqParam)) {

			if(reqParam.getRural_urban().equalsIgnoreCase("R")) {
				if(!ObjectUtils.isEmpty(reqParam.getState_code()) 
						&& !ObjectUtils.isEmpty(reqParam.getDistrict_code())
						&& !ObjectUtils.isEmpty(reqParam.getBlock_code())
						&& !ObjectUtils.isEmpty(reqParam.getVillage_code()) ) {

					RuralDto rdto = new RuralDto();

					CopyUtility.copyProperties(reqParam, rdto, false);
					rdto.setGuid(reqParam.getGuid().longValue());
					
					ruralCacheManager.save("S"+reqParam.getState_code()+"_"+reqParam.getRural_urban()+"_"+reqParam.getState_code()
					+reqParam.getDistrict_code()+reqParam.getBlock_code()+reqParam.getVillage_code(),
					reqParam.getGuid().longValue(),rdto);

					
					saveSeccFamilyDataService.saveSecc(reqParam, applicationConstantConfig.KAFKA_BEN_SECC_FAMILY_DATA_SAVE_URL);
					
					return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getGuid()), 
							true,"200","Secc Data Save Successfully",
							String.valueOf(reqParam.getState_code()),reqParam.getRural_urban());
				}

			}else if(reqParam.getRural_urban().equalsIgnoreCase("U")) {

				if(!ObjectUtils.isEmpty(reqParam.getState_code()) 
						&& !ObjectUtils.isEmpty(reqParam.getDistrict_code())
						&& !ObjectUtils.isEmpty(reqParam.getTowncode())
						&& !ObjectUtils.isEmpty(reqParam.getWardid()) ) {

					UrbanDto rdto = new UrbanDto();

					CopyUtility.copyProperties(reqParam, rdto, false);
					rdto.setGuid(reqParam.getGuid().longValue());
					
					urbanCacheManager.save("S"+reqParam.getState_code()+"_"+reqParam.getRural_urban()+"_"+reqParam.getState_code()
					+reqParam.getDistrict_code()+reqParam.getTowncode()+reqParam.getWardid(),
					reqParam.getGuid().longValue(),rdto);

				}
				saveSeccFamilyDataService.saveSecc(reqParam, applicationConstantConfig.KAFKA_BEN_SECC_FAMILY_DATA_SAVE_URL);
				
				return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getGuid()), 
						true,"200","Secc Data Save Successfully",
						String.valueOf(reqParam.getState_code()),reqParam.getRural_urban());	
			}
		}

		return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getGuid()), 
				true,"201","Invalid Request Format.",
				String.valueOf(reqParam.getState_code()),reqParam.getRural_urban());
	}





}
