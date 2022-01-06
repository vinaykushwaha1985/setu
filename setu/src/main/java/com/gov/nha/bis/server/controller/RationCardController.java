package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
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
import com.gov.nha.bis.server.dto.RationDto24;
import com.gov.nha.bis.server.dto.RationDto9;
import com.gov.nha.bis.server.dto.RationDtoEnc;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.RationCardStateService;
import com.gov.nha.bis.server.util.CheckRedisEnbleFunction;

@CrossOrigin
@Controller
public class RationCardController {

	private static final Logger logger = LogManager.getLogger(RationCardController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;


	@Autowired
	public RationCardStateService rationCardStateService;



	@RequestMapping(value="/getRationCardData",method = RequestMethod.POST)
	public @ResponseBody String getRationCardData(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		String res=null;
		HashMap<String, String> tokenMap = new  HashMap<String, String> ();

		//	String familyData= "";
		String Details="";
		//	List<Family> familyList= null;


		try {
			logger.info("Integer.getInteger(applicationForm.getStateCode())=="+Integer.valueOf(applicationForm.getStateCode()));
			logger.info("applicationForm.getStateCode()=="+applicationForm.getStateCode());

			logger.info("applicationForm.getRationCard()=="+applicationForm.getRationCard());


			String	response=rationCardStateService.rationCardDetails(Integer.valueOf(applicationForm.getStateCode()),
					applicationForm.getRationCard());


			logger.info("response===="+response);

			if(!ObjectUtils.isEmpty(response)) {

				if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.STATE_RATION_RESPONSE_FORMAT_LIST.split(",")).contains(String.valueOf(applicationForm.getStateCode()))) {
					RationDto24 rationDto= new RationDto24();

					rationDto = new Gson().fromJson(response, RationDto24.class);

					if(rationDto.getHeader().getError_msg().equalsIgnoreCase("Success")) {

						//	familyList=rationDto.getDetails().get(0).getFamily();

						try {
							//	familyData = mapper.writeValueAsString(familyList);
							Details=mapper.writeValueAsString(rationDto.getDetails().get(0));
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

				}else if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.STATE_RATION_RESPONSE_FORMAT_LIST_2.split(",")).contains(String.valueOf(applicationForm.getStateCode()))) {


					RationDtoEnc rationDto= new RationDtoEnc();

					rationDto = new Gson().fromJson(response, RationDtoEnc.class);

					if(rationDto.getHeader().getError_msg().equalsIgnoreCase("Success")) {

						//	familyList=rationDto.getDetails().get(0).getFamily();

						try {
							//	familyData = mapper.writeValueAsString(familyList);
							Details=mapper.writeValueAsString(rationDto.getDetails().get(0));
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
				else {

					RationDto9 rationDto= new RationDto9();

					rationDto = new Gson().fromJson(response, RationDto9.class);

					if(rationDto.getHeader().getError_msg().equalsIgnoreCase("Success")) {

						//	familyList=rationDto.getDetails().get(0).getFamily();

						try {
							//	familyData = mapper.writeValueAsString(familyList);
							Details=mapper.writeValueAsString(rationDto.getDetails().get(0));
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
