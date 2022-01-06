/**
     * 
     *
     * @author VinayKushwaha
     *
     *
  **/
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

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

import com.gov.nha.bis.server.dto.CardStatusParam;
import com.gov.nha.bis.server.kafka.producer.service.CardStatusProducerService;
import com.gov.nha.bis.server.response.CardStatusResponse;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class BeneficiaryCardStatusController extends NhaBisBaseController{

	
	@Autowired
	public CardStatusProducerService cardStatusProducerService;
	
	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryCardStatusController.class);

	@ResponseBody
	@PostMapping(value ="/update/card/status/2.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String updateBeneficiaryCardStatus(@RequestBody  CardStatusParam  reqParam) {

		
		try {
			if(!(ObjectUtils.isEmpty(reqParam.getGuid()) && ObjectUtils.isEmpty(reqParam.getCard_status())) ) {
						
				cardStatusProducerService.send(reqParam);
				
			return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(reqParam.getGuid()), true);
				
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NhaRationCardController====="+e);
			
			return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(reqParam.getGuid()), false);
			// TODO: handle exception
		}

	 return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(reqParam.getGuid()), false);
	}
	
	
	
}
