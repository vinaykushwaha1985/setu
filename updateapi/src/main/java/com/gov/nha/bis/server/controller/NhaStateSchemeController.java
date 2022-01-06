package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.Date;
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

import com.gov.nha.bis.server.dto.RationDto;
import com.gov.nha.bis.server.dto.RationParam;
import com.gov.nha.bis.server.dto.SchemeCardDetailDto;
import com.gov.nha.bis.server.dto.SearchParam;
import com.gov.nha.bis.server.model.RationCardEntity;
import com.gov.nha.bis.server.model.SchemeCardDetailsEntity;
import com.gov.nha.bis.server.model.StateSchemeDetailsEntity;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.service.RationCardService;
import com.gov.nha.bis.server.service.StateSchemeService;
import com.gov.nha.bis.server.util.CopyUtility;
import com.gov.nha.bis.server.util.JSONResponse;
import com.gov.nha.bis.server.util.JSONUtil;
import com.gov.nha.bis.server.util.MessageConstant;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class NhaStateSchemeController extends NhaBisBaseController{

	@Autowired
	public StateSchemeService stateSchemeService;
	
	private static final Logger logger = LoggerFactory.getLogger(NhaStateSchemeController.class);

	@ResponseBody
	@PostMapping(value ="/stateschemedtl/save/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public JSONResponse saveStateSchemeDetail(@RequestBody  StateSchemeDetailsEntity  stateSchemeDetailsEntity) {
		JSONResponse jSONResponse=null;
		Object obj=null;
		try {
			if(!ObjectUtils.isEmpty(stateSchemeDetailsEntity.getUrban_rural()) && !ObjectUtils.isEmpty(stateSchemeDetailsEntity.getState_code()) ) {

				//CopyUtility.copyProperties(reqParam, rationCardEntity, true);
				stateSchemeDetailsEntity.setCreatedon(new Date());
				stateSchemeDetailsEntity.setStatus(1L);
				obj=stateSchemeService.saveStateSchemeDetail(stateSchemeDetailsEntity);
				if(obj!=null && obj instanceof StateSchemeDetailsEntity) {
					jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_SUCCESS, true,MessageConstant.successSave);	
				}else if(obj!=null && obj instanceof String) {
					jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,obj);	
				}
				
			}else {
				jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,"Request object mandatory field missing");
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NhaStateSchemeController====="+e);
			jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,"Exception occured");
		}

	 return  jSONResponse;
	}
	
	@PostMapping(value ="/SchemeCardDtl/save/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public JSONResponse saveSchemeCardDetail(@RequestBody  SchemeCardDetailsEntity  schemeCardDetailsEntity) {
		JSONResponse jSONResponse=null;
		Object obj=null;
		try {
			if(!ObjectUtils.isEmpty(schemeCardDetailsEntity.getRural_urban()) && !ObjectUtils.isEmpty(schemeCardDetailsEntity.getState_code()) 
					&& !ObjectUtils.isEmpty(schemeCardDetailsEntity.getDistrict_code()) && !ObjectUtils.isEmpty(schemeCardDetailsEntity.getBeneficiaryid()) ) {

				//CopyUtility.copyProperties(reqParam, rationCardEntity, true);
				schemeCardDetailsEntity.setCreatedate(new Date());
				schemeCardDetailsEntity.setStatus(0L);
				obj=stateSchemeService.saveSchemeCardDetail(schemeCardDetailsEntity);
				if(obj!=null && obj instanceof SchemeCardDetailsEntity) {
					jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_SUCCESS, true,MessageConstant.successSave);	
				}else if(obj!=null && obj instanceof String) {
					jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,obj);	
				}
				
			}else {
				jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,"Request object mandatory field missing");
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NhaStateSchemeController====="+e);
			jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,"Exception occured");
		}

	 return  jSONResponse;
	}
	
	
	@PostMapping(value ="/SchemeCardDtl/fetch/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public JSONResponse getSchemeCardDetail(@RequestBody  SchemeCardDetailsEntity  schemeCardDetailsEntity) {
		JSONResponse jSONResponse=null;
		Object obj=null;
		try {
			if(!ObjectUtils.isEmpty(schemeCardDetailsEntity.getRural_urban()) && 
					!ObjectUtils.isEmpty(schemeCardDetailsEntity.getState_code()) 
					&& !ObjectUtils.isEmpty(schemeCardDetailsEntity.getDistrict_code()) 
					&& !ObjectUtils.isEmpty(schemeCardDetailsEntity.getSchemeid())
					&& !ObjectUtils.isEmpty(schemeCardDetailsEntity.getBeneficiaryid()) ) {

				obj=stateSchemeService.getSchemeCardDetail(schemeCardDetailsEntity);
				if(obj!=null && obj instanceof SchemeCardDetailDto) {
					jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_SUCCESS, true,obj);	
				}else if(obj!=null && obj instanceof String) {
					jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,obj);	
				}else {
					jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,MessageConstant.NoDataToFetch);
				}
				
			}else {
				jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,MessageConstant.failToFetch);
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NhaStateSchemeController====="+e);
			jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,"Exception occured");
		}

	 return  jSONResponse;
	}


}
