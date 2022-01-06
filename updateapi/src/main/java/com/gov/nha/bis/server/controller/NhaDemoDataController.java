package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
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

import com.gov.nha.bis.server.dto.DemoDto;
import com.gov.nha.bis.server.dto.SearchParam;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.service.DemoAuthService;


@RestController
@RequestMapping("/bis")
@CrossOrigin
public class NhaDemoDataController extends NhaBisBaseController {


	@Autowired
	public DemoAuthService demoAuthService;



	private static final Logger logger = LoggerFactory.getLogger(NhaDemoDataController.class);

	@ResponseBody
	@PostMapping(value ="/aadhaar/demo/data/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	public String demoProcessData(@RequestBody  SearchParam  reqParam) {
		List<DemoDto>	demoDtoList =null;

		try {
			if(!ObjectUtils.isEmpty(reqParam.getRefernceid()) ) {

				demoDtoList = demoAuthService.getDemoAuthEntity(reqParam.getRefernceid(),reqParam.getStatecode(), reqParam.getRuflag(),reqParam.getStatus());
				if(!ObjectUtils.isEmpty(demoDtoList))
					return  KycUpdateResponse.demoAuthDataListResponse(demoDtoList,reqParam.getRefernceid(),true,"200", "success" );
				else
					return  KycUpdateResponse.demoAuthDataListResponse(demoDtoList,reqParam.getRefernceid(),false,"202", "ReferenceId not exist." );

			}else
			{
				return  KycUpdateResponse.demoAuthDataListResponse(demoDtoList,reqParam.getRefernceid(),false,"201", "Invalid Request Parameters." );

			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("kycUpdateProcessData====="+e);

			return  KycUpdateResponse.demoAuthDataListResponse(demoDtoList,reqParam.getRefernceid(),false,"201", "Invalid Request Parameters." );

			// TODO: handle exception
		}


	}

}
