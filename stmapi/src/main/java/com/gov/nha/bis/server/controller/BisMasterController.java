package com.gov.nha.bis.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;

import com.gov.nha.bis.server.dto.SearchParam;
import com.gov.nha.bis.server.dto.StateSchemeDto;
import com.gov.nha.bis.server.service.BisMasterService;
import com.gov.nha.bis.server.util.JSONResponse;
import com.gov.nha.bis.server.util.JSONUtil;
import com.gov.nha.bis.server.util.MessageConstant;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class BisMasterController extends NhaBisBaseController{
	private static final Logger logger = LoggerFactory.getLogger(BisMasterController.class);
	
	@Autowired
	BisMasterService bisMasterService;
	
	@GetMapping("/getAllBank")
	public JSONResponse getAllBank()
	{
		JSONResponse jSONResponse=null;
		try {
		Object object=bisMasterService.getAllBank();
		if(!StringUtils.isEmpty(object) && object instanceof List){
			jSONResponse=JSONUtil.setJSONApproverResonse(MessageConstant.successFetch, true,object);
		}else{
			jSONResponse = JSONUtil.setJSONResonse(MessageConstant.failToFetch, MessageConstant.FALSE,
					"No data found");
		}
		
		}catch (Exception e) {
			logger.info(e.getMessage());
			jSONResponse = JSONUtil.setJSONResonse(MessageConstant.failToFetch, MessageConstant.FALSE,
					"Exception during fetch ");
		}
		return jSONResponse;
	}	
	
	
	@PostMapping(value ="/getStateSchemeDetails/1.0")
	public JSONResponse getStateSchemeDetails(@RequestBody  SearchParam  searchParam) {

		JSONResponse jSONResponse=null;
		String sRturnResponse =null;
		Object object=null;
		try{
			if(searchParam.getStateCode()!=null) {
				object=bisMasterService.getStateSchemeDetails(Integer.valueOf(searchParam.getStateCode().trim())); 
				if(object!=null && object instanceof List){
					jSONResponse=JSONUtil.setJSONApproverResonse(MessageConstant.successFetch, true,object);
				}else{
					jSONResponse = JSONUtil.setJSONResonse(MessageConstant.failToFetch, MessageConstant.FALSE,
							"No data found");
				}
			}
			
		}catch(Exception e){
			logger.info(e.getMessage());
			jSONResponse = JSONUtil.setJSONResonse(MessageConstant.failToFetch, MessageConstant.FALSE,
					"Exception during fetch ");
			
		}
		
		logger.info("Returned response - "+jSONResponse.toString());

		return jSONResponse;
	}
	
	/*
	 * @PostMapping(value ="/getStateSubSchemeDetails/1.0") public JSONResponse
	 * getStateSubSchemeDetails(@RequestBody SearchParam searchParam) {
	 * 
	 * JSONResponse jSONResponse=null; String sRturnResponse =null; Object
	 * object=null; try{ if(searchParam.getSchemeId()!=null &&
	 * searchParam.getStateCode()!=null) {
	 * object=bisMasterService.getStateSubSchemeDetails(searchParam.getSchemeId(),
	 * Integer.valueOf(searchParam.getStateCode().trim())); if(object!=null &&
	 * object instanceof List){
	 * jSONResponse=JSONUtil.setJSONApproverResonse(MessageConstant.successFetch,
	 * true,object); }else{ jSONResponse =
	 * JSONUtil.setJSONResonse(MessageConstant.failToFetch, MessageConstant.FALSE,
	 * "No data found"); } }
	 * 
	 * }catch(Exception e){ logger.info(e.getMessage()); jSONResponse =
	 * JSONUtil.setJSONResonse(MessageConstant.failToFetch, MessageConstant.FALSE,
	 * "Exception during fetch ");
	 * 
	 * }
	 * 
	 * logger.info("Returned response - "+jSONResponse.toString());
	 * 
	 * return jSONResponse; }
	 * 
	 * @PostMapping(value ="/getStateSubSchemeDocumentDetails/1.0") public
	 * JSONResponse getStateSubSchemeDocumentDetails(@RequestBody SearchParam
	 * searchParam) {
	 * 
	 * JSONResponse jSONResponse=null; String sRturnResponse =null; Object
	 * object=null; try{ if(searchParam.getSchemeId()!=null &&
	 * searchParam.getSubSchemeId()!=null) {
	 * object=bisMasterService.getStateSubSchemeDocumentDetails(searchParam.
	 * getSchemeId(),searchParam.getSubSchemeId()); if(object!=null && object
	 * instanceof List){
	 * jSONResponse=JSONUtil.setJSONApproverResonse(MessageConstant.successFetch,
	 * true,object); }else{ jSONResponse =
	 * JSONUtil.setJSONResonse(MessageConstant.failToFetch, MessageConstant.FALSE,
	 * "No data found"); } }
	 * 
	 * }catch(Exception e){ logger.info(e.getMessage()); jSONResponse =
	 * JSONUtil.setJSONResonse(MessageConstant.failToFetch, MessageConstant.FALSE,
	 * "Exception during fetch ");
	 * 
	 * }
	 * 
	 * logger.info("Returned response - "+jSONResponse.toString());
	 * 
	 * return jSONResponse; }
	 * 
	 */
}
