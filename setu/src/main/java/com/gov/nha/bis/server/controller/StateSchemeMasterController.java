package com.gov.nha.bis.server.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.RuralEntity;
import com.gov.nha.bis.server.model.StateScheme;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.SchemeDataService;
@CrossOrigin
@Controller
public class StateSchemeMasterController {
private static final Logger logger = LogManager.getLogger(StateSchemeMasterController.class);
	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	@RequestMapping(value="/stateSchemeData",method = RequestMethod.POST)
	public @ResponseBody String stateSchemeData(HttpServletRequest request,@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			ModelMap model)
	{
		ObjectMapper mapper = new ObjectMapper();
		 HashMap<String, String> stateMap = new  HashMap<String, String> ();
		 HashMap<String, String> sortedIdNameMap = new  HashMap<String, String> ();
		 List<StateScheme> stateList= null;
		 stateList=SchemeDataService.getStateSchemeList(applicationForm.getStateCode(),applicationConstantConfig.STATE_SCHEME_LIST_URL);
			
//			stateMap = sortedIdNameMap.entrySet()
//	                   .stream()
//	                   .sorted(Entry.comparingByKey())
//	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
//			

		
		String json = "";
        try {
            json = mapper.writeValueAsString(stateList);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            logger.info(e.getMessage());
        }
        return json.replace("\'", " ");
		
	}
	@RequestMapping(value="/stateSubSchemeData",method = RequestMethod.POST)
	public @ResponseBody String stateSubSchemeData(HttpServletRequest request,@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			ModelMap model)
	{
		ObjectMapper mapper = new ObjectMapper();
		 HashMap<String, String> stateMap = new  HashMap<String, String> ();
		 HashMap<String, String> sortedIdNameMap = new  HashMap<String, String> ();
			sortedIdNameMap=SchemeDataService.getStateSubSchemeList(applicationForm.getStateCode(),applicationForm.getSchemeid(),applicationConstantConfig.STATE_SUB_SCHEME_LIST_URL);
			
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
			

		
		String json = "";
        try {
            json = mapper.writeValueAsString(stateMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            logger.info(e.getMessage());
        }
        return json;
		
	}

	@RequestMapping(value="/stateSubSchemeDucumentTypeData",method = RequestMethod.POST)
	public @ResponseBody String stateSubSchemeSucumentTypeData(HttpServletRequest request,@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			ModelMap model)
	{
		ObjectMapper mapper = new ObjectMapper();
		 HashMap<String, String> stateMap = new  HashMap<String, String> ();
		 HashMap<String, String> sortedIdNameMap = new  HashMap<String, String> ();
			sortedIdNameMap=SchemeDataService.getStateSubSchemeDocumentTypeList(applicationForm.getSubSchemeId(),applicationForm.getSchemeid(),applicationConstantConfig.STATE_SUB_SCHEME_DOCUMENT_LIST_URL);
			
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
			

		
		String json = "";
        try {
            json = mapper.writeValueAsString(stateMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            logger.info(e.getMessage());
        }
        return json;
		
	}
}
