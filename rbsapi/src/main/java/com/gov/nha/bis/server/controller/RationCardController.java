/**
 * 
 */
package com.gov.nha.bis.server.controller;

import java.text.SimpleDateFormat;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gov.nha.bis.server.dto.RationCardDto;
import com.gov.nha.bis.server.dto.SearchParam;
import com.gov.nha.bis.server.redis.cache.manager.RationCradCacheManager;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.rest.service.RationCardDataService;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@RestController
@RequestMapping("/bis")
@CrossOrigin
public class RationCardController {

	@Autowired
	public RationCradCacheManager rationCradCacheManager;

	@Autowired
	public RationCardDataService rationCardDataService;

	public static SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX:ss");

	private static final Logger logger = LoggerFactory.getLogger(RationCardController.class);

	@ResponseBody
	@PostMapping(value ="/ration/save/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String saveRationDetail(@RequestBody  RationCardDto  reqParam) {


		try {
			if(!ObjectUtils.isEmpty(reqParam)) {

				if(!ObjectUtils.isEmpty(reqParam.getGuid()) 
						&& !ObjectUtils.isEmpty(reqParam.getState_lgd_code())
						&& !ObjectUtils.isEmpty(reqParam.getRural_urban()) ) {
					reqParam.setStatus(0);
					logger.info("\"RTN_\"+reqParam.getRural_urban()+\"_\"+reqParam.getState_lgd_code()=="+"RTN_"+reqParam.getRural_urban()+"_"+reqParam.getState_lgd_code());

					rationCradCacheManager.save("RTN_"+reqParam.getRural_urban()+"_"+reqParam.getState_lgd_code(),
							reqParam.getGuid(),reqParam);
					logger.info("Store Ration cardData");
					String res=	rationCardDataService.saveRationData(reqParam);

					if(!ObjectUtils.isEmpty(res)) {
						JSONObject resJson=new JSONObject(res);
						if(resJson.getBoolean("status")) {
							return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getGuid()), 
									true,"200","RationCard Data Save Successfully",
									String.valueOf(reqParam.getState_lgd_code()),reqParam.getRural_urban());
						}
					}

				}


			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NhaRationCardController====="+e);

			return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getGuid()), 
					true,"201","Invalid Request Format.",
					String.valueOf(reqParam.getState_lgd_code()),reqParam.getRural_urban());
			// TODO: handle exception
		}

		return   KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getGuid()), 
				true,"201","Invalid Request Format.",
				String.valueOf(reqParam.getState_lgd_code()),reqParam.getRural_urban());
	}


	@ResponseBody
	@PostMapping(value ="/ration/data/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	public String demoProcessData(@RequestBody  SearchParam  reqParam) {
		RationCardDto	rationCardDto =new RationCardDto();

		try {
			if(!ObjectUtils.isEmpty(reqParam.getRefernceid()) ) {

				rationCardDto =rationCradCacheManager.getDataDto("RTN_"+reqParam.getRuflag()+"_"+reqParam.getStatecode(),
						reqParam.getRefernceid());

				logger.info("\"RTN_\"+reqParam.getRural_urban()+\"_\"+reqParam.getState_lgd_code()=="+"RTN_"+reqParam.getRuflag()+"_"+
				reqParam.getStatecode());


				if(!ObjectUtils.isEmpty(rationCardDto))
					return  KycUpdateResponse.rationCardListResponse(rationCardDto,reqParam.getRefernceid(),true,"200", "success" );
				else
					return  KycUpdateResponse.rationCardListResponse(rationCardDto,reqParam.getRefernceid(),false,"202", "Guid not exist." );

			}else
			{
				return  KycUpdateResponse.rationCardListResponse(rationCardDto,reqParam.getRefernceid(),false,"201", "Invalid Request Parameters." );

			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("kycUpdateProcessData====="+e);

			return  KycUpdateResponse.rationCardListResponse(rationCardDto,reqParam.getRefernceid(),false,"201", "Invalid Request Parameters." );

			// TODO: handle exception
		}


	}




}
