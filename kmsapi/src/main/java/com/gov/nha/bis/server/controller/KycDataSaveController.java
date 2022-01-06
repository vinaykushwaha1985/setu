/**
 * 
 */
package com.gov.nha.bis.server.controller;

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
import com.gov.nha.bis.server.kafka.producer.service.KycDataProducerService;
import com.gov.nha.bis.server.response.CardStatusResponse;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class KycDataSaveController extends NhaBisBaseController {

	
	@Autowired
	public KycDataProducerService kycDataProducerService;
	
	private static final Logger logger = LoggerFactory.getLogger(KycDataSaveController.class);

	@ResponseBody
	@PostMapping(value ="/kyc/save/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String updateBeneficiaryCardStatus(@RequestBody  KycDataDto  kycDataDto) {

		
		try {
			if(!(ObjectUtils.isEmpty(kycDataDto.getRefernceid()) && ObjectUtils.isEmpty(kycDataDto.getRuralUrban())) ) {
						
				kycDataProducerService.send(kycDataDto);
				
			return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(kycDataDto.getRefernceid()), true);
				
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NhaRationCardController====="+e);
			
			return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(kycDataDto.getRefernceid()), false);
			// TODO: handle exception
		}

	 return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(kycDataDto.getRefernceid()), false);
	}
	
	
	
}
