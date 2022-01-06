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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.RuralEntity;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.BeneficiaryAdcdDataService;
import com.gov.nha.bis.server.rest.service.BeneficiaryDataService;

@CrossOrigin
@Controller
public class BeneficiaryStateSchemeController {

	private static final Logger logger = LogManager.getLogger(BeneficiaryStateSchemeController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public AppHistoryService appHistoryService;


	@Autowired
	public BeneficiaryAdcdDataService beneficiaryAdcdDataService;

	@RequestMapping(value="/stateSchemeSearch",method = RequestMethod.GET)
	public ModelAndView stateSchemeSearchGET(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		return new ModelAndView("beneficiarySchemeSearch", "command",applicationForm);
	}


	@RequestMapping(value="/stateSchemeSearch",method = RequestMethod.POST)
	public ModelAndView stateSchemeSearchPOST(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{



		return new ModelAndView("beneficiarySchemeSearch", "command",applicationForm);
	}


	@RequestMapping(value="/beneficiarySchemeSearch",method = RequestMethod.POST)
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


		stateCode=applicationForm.getStateCodeRural();
		districtCode=applicationForm.getDistrictCodeRural();
		request.getSession(true).setAttribute("stateCode",applicationForm.getStateCodeRural());
		villageList= beneficiaryAdcdDataService.getRuralVillList(applicationForm.getStateCodeRural(),applicationForm.getDistrictCodeRural(),
				applicationForm.getAdcdRation(),applicationForm.getAdcdMobile(),applicationForm.getStateScheme(), applicationForm.getRuralUrbanFlag(),applicationConstantConfig.STATE_SCHEME_ADCD_URL);


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
				userId,userType,roleId,"STATE_BENEFICIARY_SCHEME_SEARCH",ip,name);



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
