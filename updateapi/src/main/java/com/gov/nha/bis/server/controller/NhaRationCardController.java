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
import com.gov.nha.bis.server.dto.SearchParam;
import com.gov.nha.bis.server.model.RationCardEntity;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.service.RationCardService;
import com.gov.nha.bis.server.util.CopyUtility;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class NhaRationCardController extends NhaBisBaseController{

	@Autowired
	public RationCardService rationCardService;

	
	
	private static final Logger logger = LoggerFactory.getLogger(NhaRationCardController.class);

	@ResponseBody
	@PostMapping(value ="/ration/save/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String saveRationDetail(@RequestBody  RationParam  reqParam) {

		RationCardEntity rationCardEntity= new RationCardEntity();
		
		try {
			if(!(ObjectUtils.isEmpty(reqParam.getGuid()) && ObjectUtils.isEmpty(reqParam.getFamily_id())) ) {

				CopyUtility.copyProperties(reqParam, rationCardEntity, true);
				rationCardEntity.setCreationdate(new Date());
				rationCardEntity.setStatus(0);
						
				rationCardService.saveRationCardEntity(rationCardEntity);
		
				return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getGuid()), "true");
				
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NhaRationCardController====="+e);
			
			return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getGuid()), "false");
			// TODO: handle exception
		}

	 return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getGuid()), "false");
	}
	
	
	@ResponseBody
	@PostMapping(value ="/ration/data/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	public String demoProcessData(@RequestBody  SearchParam  reqParam) {
		List<RationDto>	rationParamList =null;

		try {
			if(!ObjectUtils.isEmpty(reqParam.getRefernceid()) ) {

				rationParamList = rationCardService.getRationCard(reqParam.getRefernceid(),Integer.valueOf(reqParam.getStatecode()), reqParam.getRuflag(),reqParam.getStatus());
				if(!ObjectUtils.isEmpty(rationParamList))
					return  KycUpdateResponse.rationCardListResponse(rationParamList,reqParam.getRefernceid(),true,"200", "success" );
				else
					return  KycUpdateResponse.rationCardListResponse(rationParamList,reqParam.getRefernceid(),false,"202", "ReferenceId not exist." );

			}else
			{
				return  KycUpdateResponse.rationCardListResponse(rationParamList,reqParam.getRefernceid(),false,"201", "Invalid Request Parameters." );

			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("kycUpdateProcessData====="+e);

			return  KycUpdateResponse.rationCardListResponse(rationParamList,reqParam.getRefernceid(),false,"201", "Invalid Request Parameters." );

			// TODO: handle exception
		}


	}


}
