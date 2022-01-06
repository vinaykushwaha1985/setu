/**
 * 
 */
package com.gov.nha.bis.server.controller;

import org.bouncycastle.util.encoders.Base64;
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

import com.gov.nha.bis.server.dto.DemoDataDto;
import com.gov.nha.bis.server.dto.KycDataDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.redis.cache.manager.DemoDataCacheManager;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.rest.service.SaveDemoDataService;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@RestController
@RequestMapping("/bis")
@CrossOrigin
public class DemoDataController extends NhaBisBaseController{
	private static final Logger logger = LoggerFactory.getLogger(DemoDataController.class);

	@Autowired
	public DemoDataCacheManager demoDataCacheManager;



	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public SaveDemoDataService saveDemoDataService;


	@PostMapping(value="/aadhaar/demo/update/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String saveKycDataRequest(@RequestBody DemoDataDto reqParam) {

		try {
			if(!ObjectUtils.isEmpty(reqParam)) {

				if(!ObjectUtils.isEmpty(reqParam.getStatecode()) 
						&& !ObjectUtils.isEmpty(reqParam.getRefernceid())
						&& !ObjectUtils.isEmpty(reqParam.getRuflag())
						&& !ObjectUtils.isEmpty(reqParam.getAadhaar())) {
					
					reqParam.setAadhaar(new String(Base64.encode(reqParam.getAadhaar().getBytes())));

					demoDataCacheManager.save("DEMO_"+reqParam.getRuflag()+"_"+reqParam.getStatecode(),
							reqParam.getRefernceid(),reqParam);
					String kycRes=	saveDemoDataService.saveKyc(reqParam, applicationConstantConfig.KAFKA_BEN_DEMO_DATA_SAVE_URL);

					if(!ObjectUtils.isEmpty(kycRes)) {
						JSONObject kyeJson=new JSONObject(kycRes);
						if(kyeJson.getBoolean("status")) {
							return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), 
									true,"200","Demo Data Save Successfully",
									reqParam.getStatecode(),reqParam.getRuflag());
						}
					}



				}
			}
			return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), 
					true,"201","Invalid Request Format.",
					reqParam.getStatecode(),reqParam.getRuflag());

		}catch (Exception e) {
			logger.info(e.getMessage());
			return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), 
					true,"201","Invalid Request Format.",
					reqParam.getStatecode(),reqParam.getRuflag());
		}

	}


	@PostMapping(value="/aadhaar/demo/data/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String fetchKycDataRequest(@RequestBody KycDataDto reqParam) {

		DemoDataDto demoDataDto=null;
		try {


			if(!ObjectUtils.isEmpty(reqParam.getStateCode()) 
					&& !ObjectUtils.isEmpty(reqParam.getGuid())
					&& !ObjectUtils.isEmpty(reqParam.getRuralUrban())) {

				demoDataDto=	demoDataCacheManager.getDemoDataDto("DEMO_"+reqParam.getRuralUrban()+"_"+reqParam.getStateCode(), reqParam.getGuid());

				if(!ObjectUtils.isEmpty(demoDataDto)) {

					return	KycUpdateResponse.demoDataDtoResponse(demoDataDto, String.valueOf(reqParam.getGuid()), true, "200", "", reqParam.getRuralUrban());
				}
			}
			return	KycUpdateResponse.demoDataDtoResponse(demoDataDto, String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameter.", reqParam.getRuralUrban());


		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
			return	KycUpdateResponse.demoDataDtoResponse(demoDataDto, String.valueOf(reqParam.getGuid()), false, "201", "Invalid Request Parameter.", reqParam.getRuralUrban());
		}


	}



}
