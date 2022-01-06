package com.gov.nha.bis.server.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiarySeccForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.CardDownloadService;
import com.gov.nha.bis.server.rest.service.CardSchemeStatusService;
import com.gov.nha.bis.server.rest.service.RationCardDataService;
import com.gov.nha.bis.server.rest.service.SaveDemoDataService;
import com.gov.nha.bis.server.util.CopyUtility;

@Controller
public class BeneficiarySchemeViewController {
	
	private static final Logger logger = LogManager.getLogger(BeneficiarySchemeViewController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;


	@Autowired
	public CardDownloadService cardDownloadService;

	@Autowired
	public SaveDemoDataService saveDemoDataService;

	@Autowired
	public RationCardDataService rationCardDataService;

	@Autowired
	public AppHistoryService appHistoryService;
	
	@Autowired
	public CardSchemeStatusService cardSchemeStatusService;

	
	@RequestMapping(value="/beneficiarySchemeView",method = RequestMethod.POST)
	public ModelAndView beneficiaryView(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		
		HttpSession session= null;
		String userId="";
		String roleId="";
		String userType="";
		String stateCode="";
		String districtCode="";
		String ip="";
		String fileName = StringUtils.cleanPath(applicationForm.getFormFile().getOriginalFilename());
	
		
	
		byte[] byteArr = null;
		try {
			byteArr = applicationForm.getFormFile().getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encodedfile = Base64.getEncoder().encodeToString(byteArr);
		session =request.getSession(true);
		logger.info("View RefrenceId:"+applicationForm.getGuid());
		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		
		CopyUtility.copyProperties(applicationForm, beneficiarySeccForm, false);
		
		beneficiarySeccForm.setPhoto(encodedfile);
		beneficiarySeccForm.setPhotoPath(fileName);		
		//Check status
		String	response=cardSchemeStatusService.getCardStatus(applicationForm.getStateCodeRural(),
				applicationForm.getDistrictCodeRural(),applicationForm.getSchemeid(),
				applicationForm.getGuid(),applicationForm.getRuralUrbanFlag());
		logger.info("get Response:"+response);
		
		if(!ObjectUtils.isEmpty(response)) {
			JSONObject resDecryptJsonRes = new JSONObject(response);

			if (resDecryptJsonRes.getString("status").equalsIgnoreCase("SUCCESS")) {
				beneficiarySeccForm.setCard_status("In progress");
			}
		}
		
		//end check status
		session.setAttribute("beneficiarySeccForm", beneficiarySeccForm);
		session.setAttribute("viewAction", "S");
		
		if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {
			stateCode=beneficiarySeccForm.getStateCodeRural();
			districtCode=beneficiarySeccForm.getDistrictCodeRural();
		}else {
			stateCode=beneficiarySeccForm.getStateCodeUrban();
			districtCode=beneficiarySeccForm.getDistrictCodeUrban();
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
				userId,userType,roleId,"BENEFICIARY_SCHEME_VIEW",ip,name);
				
	
		return new ModelAndView("beneficiarySchemeView", "command",applicationForm);
	}


}
