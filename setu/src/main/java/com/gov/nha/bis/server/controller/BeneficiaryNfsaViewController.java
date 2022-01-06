package com.gov.nha.bis.server.controller;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gov.nha.bis.server.dto.BeneficiaryCardDto;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiarySeccForm;
import com.gov.nha.bis.server.rest.service.CardStatusService;
import com.gov.nha.bis.server.util.CopyUtility;


@CrossOrigin
@Controller
public class BeneficiaryNfsaViewController {

	private static final Logger logger = LogManager.getLogger(BeneficiaryNfsaViewController.class);

	@Autowired
	public CardStatusService cardStatusService;


	@RequestMapping(value="/beneficiaryNfsaView",method = RequestMethod.GET)
	public ModelAndView beneficiaryViewGET(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		CopyUtility.copyProperties(applicationForm, beneficiarySeccForm, false);
		request.getSession(true).setAttribute("beneficiarySeccForm", beneficiarySeccForm);
		request.getSession(true).setAttribute("viewAction", "Y");

		return new ModelAndView("beneficiaryNfsaView", "command",applicationForm);
	}


	@RequestMapping(value="/beneficiaryNfsaView",method = RequestMethod.POST)
	public ModelAndView beneficiaryViewPOST(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		CopyUtility.copyProperties(applicationForm, beneficiarySeccForm, false);
		request.getSession(true).setAttribute("beneficiarySeccForm", beneficiarySeccForm);
		request.getSession(true).setAttribute("viewAction", "Y");

		return new ModelAndView("beneficiaryNfsaView", "command",applicationForm);
	}


	@RequestMapping(value="/checkCardStatus",method = RequestMethod.POST)
	public @ResponseBody String demoAuth(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		String res="N";

		if(!ObjectUtils.isEmpty(applicationForm.getRationCard())
				&& !ObjectUtils.isEmpty(applicationForm.getFamily_mem_id())
				&& !ObjectUtils.isEmpty(applicationForm.getUid_aadhaar())
				&& !ObjectUtils.isEmpty(applicationForm.getState_code())
				&& !ObjectUtils.isEmpty(applicationForm.getDistrict_code())
				){
			String	response=cardStatusService.getCardStatus(applicationForm.getState_code(),
					applicationForm.getDistrict_code(),applicationForm.getFamily_mem_id(),
					applicationForm.getUid_aadhaar(),applicationForm.getRationCard(),applicationForm.getRural_urban());

			logger.info("Card Status Response=="+response);
			if(!ObjectUtils.isEmpty(response)) {
				JSONObject cardRes= new JSONObject(response);
				if(cardRes.getBoolean("status")) {
					res="C"; 
				}
			}	
		}
		return res;
	}

	@RequestMapping(value="/getCardDetails",method = RequestMethod.POST)
	public @ResponseBody String getCardDetails(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		String json = "";

		List<BeneficiaryCardDto> cardList = new ArrayList<BeneficiaryCardDto>();

		if(!ObjectUtils.isEmpty(applicationForm.getRationCard())
				&& !ObjectUtils.isEmpty(applicationForm.getAadhar_auth())
				&& !ObjectUtils.isEmpty(applicationForm.getState_code())
				&& !ObjectUtils.isEmpty(applicationForm.getDistrict_code())
				){

			String	response=cardStatusService.getAyushmanCard(applicationForm.getRationCard(),
					applicationForm.getAadhar_auth().replaceAll(" ", ""),applicationForm.getState_code(),applicationForm.getDistrict_code());


			if(!ObjectUtils.isEmpty(response)) {
				JSONObject obj= new JSONObject(response);

				if(obj.getBoolean("status")){
					JSONArray content = obj.getJSONArray("cardList");

					Reader reader = new InputStreamReader(new ByteArrayInputStream(content.toString().getBytes()));
					GsonBuilder gsonBuilder = new GsonBuilder();

					Gson gson = gsonBuilder.create();

					cardList = Arrays.asList(gson.fromJson(reader, BeneficiaryCardDto[].class));

				}
			}

		}
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			json = mapper.writeValueAsString(cardList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return json.replace("\'", " ");
	}

}
