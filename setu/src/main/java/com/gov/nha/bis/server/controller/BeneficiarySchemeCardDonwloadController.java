package com.gov.nha.bis.server.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiarySeccForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.TxnSaveDataService;
import com.gov.nha.bis.server.util.CopyUtility;

@CrossOrigin
@Controller
public class BeneficiarySchemeCardDonwloadController {
	private static final Logger logger = LogManager.getLogger(BeneficiarySchemeCardDonwloadController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public AppHistoryService appHistoryService;
	
	@Autowired
	public TxnSaveDataService txnSaveDataService;
	
	@RequestMapping(value="/beneficiarySchemeCardDonwload",method = RequestMethod.POST)
	public ModelAndView beneficiaryCardDonwloadPost(HttpServletRequest request,
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
		
		session =request.getSession(true);
		
		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		CopyUtility.copyProperties(applicationForm, beneficiarySeccForm, false);
		request.getSession(true).setAttribute("beneficiarySeccForm", beneficiarySeccForm);
		request.getSession(true).setAttribute("viewAction", "Y");
		
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
			
		appHistoryService.saveEventDetails(Integer.valueOf(stateCode), districtCode,beneficiarySeccForm.getRuralUrbanFlag(),userId,
				userId,userType,roleId,"BENEFICIARY_SCHEME_CARD_DOWNLOAD",ip,name);
				
	
		
		

		return new ModelAndView("beneficiarySchemeCardDonwload", "command",applicationForm);
	}
	
	@RequestMapping(value="/beneficiarySchemeCardDonwload",method = RequestMethod.GET)
	public ModelAndView beneficiaryCardDonwloadGet(HttpServletRequest request,
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
		
		session =request.getSession(true);
		
		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		CopyUtility.copyProperties(applicationForm, beneficiarySeccForm, false);
		request.getSession(true).setAttribute("beneficiarySeccForm", beneficiarySeccForm);
		request.getSession(true).setAttribute("viewAction", "Y");
		
		
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
		appHistoryService.saveEventDetails(Integer.valueOf(stateCode), districtCode,beneficiarySeccForm.getRuralUrbanFlag(),userId,
				userId,userType,roleId,"BENEFICIARY_SCHEME_CARD_DOWNLOAD",ip,name);
				
	
		return new ModelAndView("beneficiarySchemeCardDonwload", "command",applicationForm);
	}
}
