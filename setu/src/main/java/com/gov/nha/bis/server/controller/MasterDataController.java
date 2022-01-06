package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.MasterDataService;

@CrossOrigin
@Controller
public class MasterDataController {
	
	private static final Logger logger = LogManager.getLogger(MasterDataController.class);
	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	
	private HashMap<String, String> stateMap;
	
	
	public HashMap<String, String> getStateMap() {
		return stateMap;
	}




	public void setStateMap(HashMap<String, String> stateMap) {
		this.stateMap = stateMap;
	}




	@RequestMapping(value="/ruralMaster",method = RequestMethod.POST)
	public @ResponseBody String ruralMasterSearch(HttpServletRequest request,@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			ModelMap model)
	{
		ObjectMapper mapper = new ObjectMapper();
		 HashMap<String, String> stateMap = new  HashMap<String, String> ();
		 HashMap<String, String> sortedIdNameMap = new  HashMap<String, String> ();
		
		if(applicationForm.getModeFlag().equalsIgnoreCase("S")){
		
			sortedIdNameMap=MasterDataService.getStateList(null, null, null, null, null, "S",applicationConstantConfig.STATE_MASTER_RURAL_URL);
			
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
	 
			
		}else if(applicationForm.getModeFlag().equalsIgnoreCase("D")){
			sortedIdNameMap=MasterDataService.getStateList(applicationForm.getStateCode(), null, null, null, null, "D",applicationConstantConfig.STATE_MASTER_RURAL_URL);
			
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
			
		}else if(applicationForm.getModeFlag().equalsIgnoreCase("B")){
			
			sortedIdNameMap=MasterDataService.getStateList(applicationForm.getStateCode(), applicationForm.getDistrictCode(), null, null, null, "B",applicationConstantConfig.STATE_MASTER_RURAL_URL);
			
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
			
			
		}else if(applicationForm.getModeFlag().equalsIgnoreCase("V")){
			
			sortedIdNameMap=MasterDataService.getStateList(applicationForm.getStateCode(), applicationForm.getDistrictCode(), String.valueOf(applicationForm.getBlockCode()), null, null, "V",applicationConstantConfig.STATE_MASTER_RURAL_URL);
			
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
		}
		
		
		String json = "";
        try {
            json = mapper.writeValueAsString(stateMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            logger.info(e.getMessage());
        }
        return json;
		
	}

	
	@RequestMapping(value="/urbanMaster",method = RequestMethod.POST)
	public @ResponseBody String homePage(HttpServletRequest request,@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			ModelMap model)
	{
		ObjectMapper mapper = new ObjectMapper();
		 HashMap<String, String> stateMap = new  HashMap<String, String> ();
		 HashMap<String, String> sortedIdNameMap = new  HashMap<String, String> ();
		
		if(applicationForm.getModeFlag().equalsIgnoreCase("S")){
		
			sortedIdNameMap=MasterDataService.getStateList(null, null, null, null, null, "S",applicationConstantConfig.STATE_MASTER_URBAN_URL);
		logger.info("sortedIdNameMap==="+sortedIdNameMap.toString());
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
	
			logger.info("stateMap==="+stateMap.toString());
			
		}else if(applicationForm.getModeFlag().equalsIgnoreCase("D")){
			sortedIdNameMap=MasterDataService.getStateList(applicationForm.getStateCode(), null, null, null, null, "D",applicationConstantConfig.STATE_MASTER_URBAN_URL);
			
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
		}else if(applicationForm.getModeFlag().equalsIgnoreCase("T")){
			
			sortedIdNameMap=MasterDataService.getStateList(applicationForm.getStateCode(), applicationForm.getDistrictCode(), null, null, null, "T",applicationConstantConfig.STATE_MASTER_URBAN_URL);
			
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
		}else if(applicationForm.getModeFlag().equalsIgnoreCase("W")){
			
			sortedIdNameMap=MasterDataService.getStateList(applicationForm.getStateCode(), applicationForm.getDistrictCode(),  null, null, applicationForm.getTownCode(),"W",applicationConstantConfig.STATE_MASTER_URBAN_URL);
			
			stateMap = sortedIdNameMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByKey())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
		}
		
		
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
