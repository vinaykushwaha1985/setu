package com.gov.nha.bis.server.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.RuralEntity;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.BeneficiaryDataService;

@Controller
public class BeneficiarySearchMmjaController {
	private static final Logger logger = LogManager.getLogger(BeneficiarySearchMmjaController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	@Autowired
	public AppHistoryService appHistoryService;
	
	@RequestMapping(value="/beneficiarySearchMmja",method = RequestMethod.POST)
	public @ResponseBody  String homeVillageSearchJson(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model) throws IOException
	{
		HttpSession session= null;
		String userId="";
		String roleId="";
		String userType="";
		String stateCode="";
		String districtCode="";
		String ip="";
		
		session =request.getSession(true);

		List<RuralEntity> villageList= null;
		session.setAttribute("urFlag",applicationForm.getRuralUrbanFlag());
		
		
		if(applicationForm.getRuralUrbanFlag().equalsIgnoreCase("R")){
			stateCode=applicationForm.getStateCodeRural();
			districtCode=applicationForm.getDistrictCodeRural();
			request.getSession(true).setAttribute("stateCode",applicationForm.getStateCodeRural());
			villageList= BeneficiaryDataService.getRuralVillList(applicationForm.getStateCodeRural(),applicationForm.getDistrictCodeRural(),
					applicationForm.getBlockCodeRural(),applicationForm.getVillageCodeRural(),applicationForm.getRuralUrbanFlag(),
					applicationConstantConfig.STATE_SCHEME_MMJA_URL);
			
		}else if(applicationForm.getRuralUrbanFlag().equalsIgnoreCase("U")){
			stateCode=applicationForm.getStateCodeUrban();
			districtCode=applicationForm.getDistrictCodeUrban();
			
			request.getSession(true).setAttribute("stateCode",applicationForm.getStateCodeUrban());
			//if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.SEARCH_SERVICE_REDIS_ENABLE_LIST.split(",")).contains(applicationForm.getStateCodeUrban())) {
				villageList= BeneficiaryDataService.getUrbanVillList(applicationForm.getStateCodeUrban(),applicationForm.getDistrictCodeUrban(),"",
						applicationForm.getTownCodeUrban(),applicationForm.getRuralUrbanFlag(),applicationConstantConfig.STATE_SCHEME_MMJA_URL);

		}
		
	if(session.getAttribute("USERID")!=null) {
		userId=(String)session.getAttribute("USERID");
	}if(session.getAttribute("userType")!=null) {
		userType=(String)session.getAttribute("userType");
	}if(session.getAttribute("roleId")!=null) {
		roleId=(String)session.getAttribute("roleId");
	}if(session.getAttribute("IP")!=null) {
		ip=(String)session.getAttribute("IP");
	}	
	String name="";
	if(session.getAttribute("USERNAME")!=null) {
		name=(String)session.getAttribute("USERNAME");
	}	
	appHistoryService.saveEventDetails(Integer.valueOf(stateCode), districtCode,applicationForm.getRuralUrbanFlag(),userId,
			userId,userType,roleId,"beneficiarySearchMmja",ip,name);
			
	
	
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(villageList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return json.replace("\'", " ");


	}
}
