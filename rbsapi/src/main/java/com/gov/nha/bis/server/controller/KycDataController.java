/**
 * 
 */
package com.gov.nha.bis.server.controller;

import org.json.JSONObject;
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

import com.gov.nha.bis.server.dto.KycDataDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.redis.cache.manager.KycDataCacheManager;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.rest.service.SaveKycDataService;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@RestController
@RequestMapping("/bis")
@CrossOrigin
public class KycDataController extends NhaBisBaseController{
	private static final Logger logger = LoggerFactory.getLogger(KycDataController.class);

	@Autowired
	public KycDataCacheManager kycCacheManager;



	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public SaveKycDataService saveKycDataService;


	@PostMapping(value="/aadhaar/kyc/save/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String saveKycDataRequest(@RequestBody KycDataDto reqParam) {

		try {
			if(!ObjectUtils.isEmpty(reqParam)) {

				if(!ObjectUtils.isEmpty(reqParam.getStateCode()) 
						&& !ObjectUtils.isEmpty(reqParam.getRefernceid())
						&& !ObjectUtils.isEmpty(reqParam.getRuralUrban()) ) {

					kycCacheManager.save("KYC_"+reqParam.getRuralUrban()+"_"+reqParam.getStateCode(),
							reqParam.getRefernceid(),reqParam);
					String kycRes=	saveKycDataService.saveKyc(reqParam, applicationConstantConfig.KAFKA_BEN_KYC_DATA_SAVE_URL);

					if(!ObjectUtils.isEmpty(kycRes)) {
						JSONObject kyeJson=new JSONObject(kycRes);
						if(kyeJson.getBoolean("status")) {
							return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), 
									true,"200","Kyc Data Save Successfully",
									reqParam.getStateCode(),reqParam.getRuralUrban());
						}
					}



				}
			}
			return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), 
					false,"201","Invalid Request Format.",
					reqParam.getStateCode(),reqParam.getRuralUrban());

		}catch (Exception e) {
			logger.info(e.getMessage());
			return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), 
					false,"201","Invalid Request Format.",
					reqParam.getStateCode(),reqParam.getRuralUrban());
		}

	}


	@PostMapping(value="/kyc/fetch/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String fetchKycDataRequest(@RequestBody KycDataDto reqParam) {

		KycDataDto kycDataDto=null;
		try {


			if(!ObjectUtils.isEmpty(reqParam.getStateCode()) 
					&& !ObjectUtils.isEmpty(reqParam.getGuid())
					&& !ObjectUtils.isEmpty(reqParam.getRuralUrban())) {

				kycDataDto=	kycCacheManager.getKycDataDto("KYC_"+reqParam.getRuralUrban()+"_"+reqParam.getStateCode(), reqParam.getGuid());

				if(!ObjectUtils.isEmpty(kycDataDto)) {

					return	KycUpdateResponse.kycDataDtoResponse(kycDataDto, String.valueOf(reqParam.getGuid()), true, "200", "", reqParam.getRuralUrban());
				}
			}
			return	KycUpdateResponse.kycDataDtoResponse(kycDataDto, String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameter.", reqParam.getRuralUrban());


		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
			return	KycUpdateResponse.kycDataDtoResponse(kycDataDto, String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameter.", reqParam.getRuralUrban());
		}


	}

}
