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

import com.gov.nha.bis.server.dto.FamilySeccParam;
import com.gov.nha.bis.server.kafka.producer.service.SeccProducerService;
import com.gov.nha.bis.server.response.CardStatusResponse;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class SeccDataSaveController {

	@Autowired
	public SeccProducerService seccProducerService;

	private static final Logger logger = LoggerFactory.getLogger(RationCardSaveController.class);

	@ResponseBody
	@PostMapping(value ="/beneficiary/family/data/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String updateData(@RequestBody  FamilySeccParam  reqDto) {

		try {
			if(!(ObjectUtils.isEmpty(reqDto.getGuid()) && ObjectUtils.isEmpty(reqDto.getRural_urban())) ) {

				seccProducerService.send(reqDto);

				return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(reqDto.getGuid()), true);

			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("RationCardSaveController====="+e);

			return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(reqDto.getGuid()), false);
		}

		return  CardStatusResponse.getCardStatusUpdateResponse(String.valueOf(reqDto.getGuid()), false);
	}









}
