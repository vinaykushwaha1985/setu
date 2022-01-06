package com.gov.nha.bis.server.controller;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.gov.nha.bis.server.dto.BocwDto;
import com.gov.nha.bis.server.dto.RationDto24;
import com.gov.nha.bis.server.dto.RationDto9;
import com.gov.nha.bis.server.dto.RationDtoEnc;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.BocwService;
import com.gov.nha.bis.server.rest.service.RationCardStateService;
import com.gov.nha.bis.server.util.CheckRedisEnbleFunction;

@CrossOrigin
@Controller
public class BeneficiarySearchBocwController {

	private static final Logger logger = LogManager.getLogger(BeneficiarySearchBocwController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;


	@Autowired
	public BocwService bocwService;



	@RequestMapping(value="/getBocwDetails",method = RequestMethod.POST)
	public @ResponseBody String getRationCardData(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		String res=null;
		HashMap<String, String> tokenMap = new  HashMap<String, String> ();

		String Details="";
		
		try {
			logger.info("Integer.getInteger(applicationForm.getStateCode())=="+Integer.valueOf(applicationForm.getStateCode()));
			logger.info("applicationForm.getStateCode()=="+applicationForm.getStateCode());

			logger.info("applicationForm.getRationCard()=="+applicationForm.getBcowId());


			String	response=bocwService.getDetails(Integer.valueOf(applicationForm.getStateCode()),
					applicationForm.getBcowId());


			logger.info("response===="+response);

			if(!ObjectUtils.isEmpty(response)) {

				
				
					BocwDto bocwDto= new BocwDto();

					bocwDto = new Gson().fromJson(response, BocwDto.class);

					if(bocwDto.getHeader().getError_code().equalsIgnoreCase("0")) {
				
						try {
							//	familyData = mapper.writeValueAsString(familyList);
							Details=mapper.writeValueAsString(bocwDto.getDetails().get(0));
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							logger.info(e.getMessage());
						}

						tokenMap.put("status", "Y");
						//tokenMap.put("familyData", familyData);
						tokenMap.put("Details", Details);
					}else {
						tokenMap.put("status", "N");
						tokenMap.put("Details", Details);
					}
				}

			
		}catch (Exception e) {
			tokenMap.put("status", "N");
			tokenMap.put("Details", Details);
			logger.info(e.getMessage());// TODO: handle exception
		}

		try {
			res = mapper.writeValueAsString(tokenMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block

			logger.info(e.getMessage());
		}
		return res;

	}







}
