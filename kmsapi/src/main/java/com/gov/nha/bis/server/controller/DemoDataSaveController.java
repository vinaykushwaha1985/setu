package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
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

import com.gov.nha.bis.server.dto.DemoDataDto;
import com.gov.nha.bis.server.kafka.producer.service.DemoDataProducerService;
import com.gov.nha.bis.server.response.CardStatusResponse;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class DemoDataSaveController {

	
	@Autowired
	public DemoDataProducerService demoDataProducerService;
	
	private static final Logger logger = LoggerFactory.getLogger(DemoDataSaveController.class);

	@ResponseBody
	@PostMapping(value ="/demo/save/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String updateDemoAuthData(@RequestBody  DemoDataDto  reqDto) {

		
		try {
			if(!(ObjectUtils.isEmpty(reqDto.getRefernceid()) && ObjectUtils.isEmpty(reqDto.getRuflag())) ) {
						
				demoDataProducerService.send(reqDto);
				
			return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(reqDto.getRefernceid()), true);
				
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NhaRationCardController====="+e);
			
			return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(reqDto.getRefernceid()), false);
			// TODO: handle exception
		}

	 return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(reqDto.getRefernceid()), false);
	}
	
	
	


}
