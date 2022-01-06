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

import com.gov.nha.bis.server.dto.KycDataDto;
import com.gov.nha.bis.server.dto.KycPhotoDto;
import com.gov.nha.bis.server.dto.KycUpdateParam;
import com.gov.nha.bis.server.model.AadhaarDetailsEntity;
import com.gov.nha.bis.server.model.AadhaarPhotoEntity;
import com.gov.nha.bis.server.model.MapperDetailsEntity;
import com.gov.nha.bis.server.response.KycUpdateResponse;
import com.gov.nha.bis.server.service.KycPhotoUpdateService;
import com.gov.nha.bis.server.service.KycUpdateService;
import com.gov.nha.bis.server.util.CopyUtility;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class NhaKycUpdateController extends NhaBisBaseController{

	@Autowired
	public KycUpdateService kycUpdateService;
	
	@Autowired
	public KycPhotoUpdateService kycPhotoUpdateService;



	private static final Logger logger = LoggerFactory.getLogger(NhaKycUpdateController.class);

	@ResponseBody
	@PostMapping(value ="/aadhaar/kyc/update/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	public String kycUpdateProcessData(@RequestBody  KycUpdateParam  reqParam) {

		AadhaarDetailsEntity aadhaarDetailsEntity= new AadhaarDetailsEntity();
		AadhaarPhotoEntity aadhaarPhotoEntity = new AadhaarPhotoEntity();


		try {
			if(!(ObjectUtils.isEmpty(reqParam.getRefernceid()) && ObjectUtils.isEmpty(reqParam.getName()) && ObjectUtils.isEmpty(reqParam.getMode())) ) {

				CopyUtility.copyProperties(reqParam, aadhaarDetailsEntity, true);
				aadhaarDetailsEntity.setCreationdate(new Date());
				aadhaarDetailsEntity.setStatus(Long.valueOf(0));

				aadhaarPhotoEntity.setAadhaar(reqParam.getUidtoken());
				aadhaarPhotoEntity.setPhoto(reqParam.getPht());
				aadhaarPhotoEntity.setStatus(Long.valueOf(0));
				aadhaarPhotoEntity.setRefernceid(reqParam.getRefernceid());

				if(reqParam.getMode()==1) {

					MapperDetailsEntity mapperDetailsEntity = new MapperDetailsEntity();
					mapperDetailsEntity.setRefernceid(reqParam.getRefernceid());
					mapperDetailsEntity.setPriorty(Long.valueOf(0));
					mapperDetailsEntity.setStatus(Long.valueOf(0));
					mapperDetailsEntity.setEkyctype(reqParam.getEkyctype());
					mapperDetailsEntity.setState_code(reqParam.getStateCode());
					mapperDetailsEntity.setDistrict_code(reqParam.getDistrictCode());
					if(!ObjectUtils.isEmpty(reqParam.getBlockCode()))
						mapperDetailsEntity.setBlock_code(reqParam.getBlockCode());
					if(!ObjectUtils.isEmpty(reqParam.getVillageCode()))
						mapperDetailsEntity.setVillage_code(reqParam.getVillageCode());
					if(!ObjectUtils.isEmpty(reqParam.getTownCode()))
						mapperDetailsEntity.setTown_code(reqParam.getTownCode());
					if(!ObjectUtils.isEmpty(reqParam.getWardCode()))
						mapperDetailsEntity.setWard_code(reqParam.getWardCode());

					mapperDetailsEntity.setRural_urban(reqParam.getRuralUrban());


					kycUpdateService.saveKycUpdate(aadhaarDetailsEntity,aadhaarPhotoEntity,mapperDetailsEntity,reqParam.getRuralUrban());

				}else {
					kycUpdateService.saveKycUpdate(aadhaarDetailsEntity, aadhaarPhotoEntity,  reqParam.getRuralUrban());
				}
				return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), "true");

			}



		}catch (Exception e) {

			logger.error("kycUpdateProcessData====="+e);

			return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), "false");
			// TODO: handle exception
		}

		return  KycUpdateResponse.getKycUpdateResponse(String.valueOf(reqParam.getRefernceid()), "false");
	}
	
	
	@PostMapping(value="/kyc/fetch/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String fetchKycDataRequest(@RequestBody KycUpdateParam reqParam) {

		KycDataDto kycDataDto=null;
		try {


			if(!ObjectUtils.isEmpty(reqParam.getRefernceid())) {

				kycDataDto=	kycUpdateService.getAadhaarDetailsEntity(reqParam.getRefernceid());

				if(!ObjectUtils.isEmpty(kycDataDto)) {

					return	KycUpdateResponse.kycDataDtoResponse(kycDataDto, String.valueOf(reqParam.getRefernceid()), true, "200", "", reqParam.getRuralUrban());
				}
			}
			return	KycUpdateResponse.kycDataDtoResponse(kycDataDto, String.valueOf(reqParam.getRefernceid()), false, "201", "Invalid Request Parameter.", reqParam.getRuralUrban());


		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
			return	KycUpdateResponse.kycDataDtoResponse(kycDataDto, String.valueOf(reqParam.getRefernceid()), false, "201", "Invalid Request Parameter.", reqParam.getRuralUrban());
		}


	}

	
	
	@PostMapping(value="/kyc/photo/fetch/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String fetchKycPhtDataRequest(@RequestBody KycUpdateParam reqParam) {

		KycPhotoDto kycPhotoDto=null;
		try {


			if(!ObjectUtils.isEmpty(reqParam.getRefernceid())) {

				kycPhotoDto=	kycPhotoUpdateService.findByRefernceid(reqParam.getRefernceid());

				if(!ObjectUtils.isEmpty(kycPhotoDto)) {

					return	KycUpdateResponse.kycPhotoDtoResponse(kycPhotoDto, String.valueOf(reqParam.getRefernceid()), true, "200", "", reqParam.getRuralUrban());
				}
			}
			return	KycUpdateResponse.kycPhotoDtoResponse(kycPhotoDto, String.valueOf(reqParam.getRefernceid()), false, "201", "Invalid Request Parameter.", reqParam.getRuralUrban());


		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
			return	KycUpdateResponse.kycPhotoDtoResponse(kycPhotoDto, String.valueOf(reqParam.getRefernceid()), false, "201", "Invalid Request Parameter.", reqParam.getRuralUrban());
		}


	}

}
