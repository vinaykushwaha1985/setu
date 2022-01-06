package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.Date;

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

import com.gov.nha.bis.server.dto.CardStatusParam;
import com.gov.nha.bis.server.model.BeneficiaryCardStatusEntity;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.service.BeneficiaryCardStatusService;
import com.gov.nha.bis.server.util.CopyUtility;
import com.gov.nha.bis.server.util.JSONResponse;
import com.gov.nha.bis.server.util.JSONUtil;
import com.gov.nha.bis.server.util.MessageConstant;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class BeneficiaryCardStatusController extends NhaBisBaseController{

	@Autowired
	public BeneficiaryCardStatusService beneficiaryCardStatusService;

	
	
	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryCardStatusController.class);

	@ResponseBody
	@PostMapping(value ="/card/update/status/2.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public JSONResponse updateBeneficiaryCardStatus(@RequestBody  CardStatusParam  reqParam) {

		BeneficiaryCardStatusEntity beneficiaryCardStatusEntity= new BeneficiaryCardStatusEntity();
		JSONResponse jSONResponse=null;
		
		try {
			if(!(ObjectUtils.isEmpty(reqParam.getGuid()) || ObjectUtils.isEmpty(reqParam.getCreatecardstatus()) 
					|| ObjectUtils.isEmpty(reqParam.getUidtoken()) || ObjectUtils.isEmpty(reqParam.getUidenc()) ) ) {

				CopyUtility.copyProperties(reqParam, beneficiaryCardStatusEntity, true);
				beneficiaryCardStatusEntity.setCreatedate(new Date());
				
						
				beneficiaryCardStatusService.saveBeneficiaryCardStatusEntity(beneficiaryCardStatusEntity);
		
				jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_SUCCESS, true,MessageConstant.successSave);
				
			}else {
				jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,MessageConstant.Mandatory_Fields_Null);
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NhaRationCardController====="+e);
			
			jSONResponse=JSONUtil.setJSONResonse(MessageConstant.RESPONSE_FAILED, false,MessageConstant.IssueDuringSave);
			// TODO: handle exception
		}

	 return  jSONResponse;
	}
	
	
	
}
