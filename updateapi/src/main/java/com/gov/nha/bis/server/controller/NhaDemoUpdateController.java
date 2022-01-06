package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.Date;

import org.bouncycastle.util.encoders.Base64;
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

import com.gov.nha.bis.server.dto.SearchParam;
import com.gov.nha.bis.server.model.DemoAuthEntity;
import com.gov.nha.bis.server.model.MapperDetailsEntity;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.service.DemoAuthService;
import com.gov.nha.bis.server.util.CopyUtility;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class NhaDemoUpdateController extends NhaBisBaseController{

	@Autowired
	public DemoAuthService demoAuthService;



	private static final Logger logger = LoggerFactory.getLogger(NhaDemoUpdateController.class);

	@ResponseBody
	@PostMapping(value ="/aadhaar/demo/update/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	public String demoUpdateProcessData(@RequestBody  SearchParam  reqParam) {

		DemoAuthEntity demoAuthEntity= new DemoAuthEntity();
		MapperDetailsEntity mapperDetailsEntity = new MapperDetailsEntity();
		try {
			if(!(ObjectUtils.isEmpty(reqParam.getRefernceid()) && ObjectUtils.isEmpty(reqParam.getName())) ) {

				CopyUtility.copyProperties(reqParam, demoAuthEntity, true);

				demoAuthEntity.setCreatedate(new Date());
				demoAuthEntity.setStatus(0);
				demoAuthEntity.setAadhaar(new String(Base64.encode(demoAuthEntity.getAadhaar().getBytes())));

				if(reqParam.getEkyctype()==4) {
					mapperDetailsEntity.setRefernceid(reqParam.getRefernceid());
					mapperDetailsEntity.setPriorty(Long.valueOf(0));
					mapperDetailsEntity.setStatus(Long.valueOf(0));
					mapperDetailsEntity.setEkyctype(reqParam.getEkyctype());
					mapperDetailsEntity.setState_code(reqParam.getStatecode());
					mapperDetailsEntity.setDistrict_code(reqParam.getDistrictCode());
					if(!ObjectUtils.isEmpty(reqParam.getBlockCode()))
						mapperDetailsEntity.setBlock_code(reqParam.getBlockCode());
					if(!ObjectUtils.isEmpty(reqParam.getVillageCode()))
						mapperDetailsEntity.setVillage_code(reqParam.getVillageCode());
					if(!ObjectUtils.isEmpty(reqParam.getTownCode()))
						mapperDetailsEntity.setTown_code(reqParam.getTownCode());
					if(!ObjectUtils.isEmpty(reqParam.getWardCode()))
						mapperDetailsEntity.setWard_code(reqParam.getWardCode());
					mapperDetailsEntity.setRural_urban(reqParam.getRuflag());


					demoAuthService.saveDemoAuthEntity(demoAuthEntity,mapperDetailsEntity);
				}else {
					demoAuthService.saveDemoAuthEntity(demoAuthEntity);
				}

				return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), "true");

			}



		}catch (Exception e) {
			e.printStackTrace();
			logger.error("kycUpdateProcessData====="+e);

			return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), "false");
			// TODO: handle exception
		}

		return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), "false");
	}





}
