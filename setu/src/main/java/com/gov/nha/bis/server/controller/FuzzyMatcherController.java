package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.FuzzyMatcherService;

@Controller
public class FuzzyMatcherController {



	private static final Logger logger = LogManager.getLogger(FuzzyMatcherController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;


	@Autowired
	public FuzzyMatcherService fuzzyMatcherService;


	@RequestMapping(value="/fuzzyMatcher",method = RequestMethod.POST)
	public @ResponseBody String demoAuth(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		String res="Y";
		
		if(ObjectUtils.isEmpty(applicationForm.getGenderBy())){

			String	response=fuzzyMatcherService.getFuzzyMatcherScore(applicationForm.getNameBy(),applicationForm.getGenderBy(),applicationForm.getFatherBy(),
					applicationForm.getNameWith(),applicationForm.getGenderWith(),applicationForm.getFatherWith());

			logger.info("Fuzzy Response=="+response);
			if(!ObjectUtils.isEmpty(response)) {
				JSONObject fuzzyRes= new JSONObject(response);
				if(fuzzyRes.getBoolean("status")) {
					if(fuzzyRes.getInt("matchScore")>applicationConstantConfig.NHA_BIS_FUZZY_MATCH_SCORE)
						res="Y"; 
				}
			}
			
		}else if(!ObjectUtils.isEmpty(applicationForm.getGenderBy()) && applicationForm.getGenderBy().substring(0, 1).equalsIgnoreCase("O")){

			String	response=fuzzyMatcherService.getFuzzyMatcherScore(applicationForm.getNameBy(),applicationForm.getGenderBy(),applicationForm.getFatherBy(),
					applicationForm.getNameWith(),applicationForm.getGenderWith(),applicationForm.getFatherWith());

			logger.info("Fuzzy Response=="+response);
			if(!ObjectUtils.isEmpty(response)) {
				JSONObject fuzzyRes= new JSONObject(response);
				if(fuzzyRes.getBoolean("status")) {
					if(fuzzyRes.getInt("matchScore")>applicationConstantConfig.NHA_BIS_FUZZY_MATCH_SCORE)
						res="N"; 
				}
			}
			
		}else if(!ObjectUtils.isEmpty(applicationForm.getGenderBy()) && !ObjectUtils.isEmpty(applicationForm.getGenderWith())) {
			if(applicationForm.getGenderBy().substring(0, 1).equalsIgnoreCase(applicationForm.getGenderWith().substring(0, 1))) {

				String	response=fuzzyMatcherService.getFuzzyMatcherScore(applicationForm.getNameBy(),applicationForm.getGenderBy(),applicationForm.getFatherBy(),
						applicationForm.getNameWith(),applicationForm.getGenderWith(),applicationForm.getFatherWith());

				logger.info("Fuzzy Response=="+response);
				if(!ObjectUtils.isEmpty(response)) {
					JSONObject fuzzyRes= new JSONObject(response);
					if(fuzzyRes.getBoolean("status")) {
						if(fuzzyRes.getInt("matchScore")>applicationConstantConfig.NHA_BIS_FUZZY_MATCH_SCORE)
							res="Y"; 
					}
				}
			}
		}
		return res;
	}

}
