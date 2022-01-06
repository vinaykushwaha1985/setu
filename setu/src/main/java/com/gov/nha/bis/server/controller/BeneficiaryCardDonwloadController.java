package com.gov.nha.bis.server.controller;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */

import java.io.ByteArrayInputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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
public class BeneficiaryCardDonwloadController {


	private static final Logger logger = LogManager.getLogger(BeneficiaryCardDonwloadController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public AppHistoryService appHistoryService;
	
	@Autowired
	public TxnSaveDataService txnSaveDataService;

	@RequestMapping(value="/beneficiaryCardDonwload",method = RequestMethod.POST)
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
				userId,userType,roleId,"BENEFICIARY_CARD_DOWNLOAD",ip,name);
				
	
		
		

		return new ModelAndView("beneficiaryCardDonwload", "command",applicationForm);
	}
	
	@RequestMapping(value="/beneficiaryCardDonwload",method = RequestMethod.GET)
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
				userId,userType,roleId,"BENEFICIARY_CARD_DOWNLOAD",ip,name);
				
	
		return new ModelAndView("beneficiaryCardDonwload", "command",applicationForm);
	}


	@RequestMapping(value="/benCardDonwload",method = RequestMethod.POST)
	public ResponseEntity<Resource>  benCardDonwload(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		String contentType = "application/octet-stream";;
		InputStreamResource resource=null;
		byte[] cardPht=null;
		String fileName="";
		String userid="";
		try {

			userid=(String)request.getSession(true).getAttribute("USERID");

			logger.info(applicationForm.getStateCode().split(",")[0]+"    "+applicationForm.getRefernceid().split(",")[0]+"   "+applicationForm.getCardPht());

			logger.info(applicationForm.getStateCode().split(",")[0]+"    "+applicationForm.getRefernceid().split(",")[0]+"   "+applicationForm.getCard_status());

			fileName="eAyushmanCard_"+applicationForm.getRefernceid().split(",")[0]+".pdf";

			if(request.getAttribute("cardPht")!=null) {
				String cardPhtDetails=(String)request.getAttribute("cardPht");
				logger.info("cardPhtDetails==="+cardPhtDetails);
				cardPht=Base64.decode(cardPhtDetails);
				if(!ObjectUtils.isEmpty(cardPht)) {
					logger.info("cardPht==="+cardPht);
					if(applicationForm.getOtpType().split(",")[0] .equalsIgnoreCase("M"))	
						txnSaveDataService.saveDownCardStatus(applicationForm.getRefernceid().split(",")[0], applicationForm.getAadhar().split(",")[0], "", applicationForm.getStateCode().split(",")[0], userid);
					else
						txnSaveDataService.saveDownCardStatus(applicationForm.getRefernceid().split(",")[0], "", applicationForm.getAadhar().split(",")[0], applicationForm.getStateCode().split(",")[0], userid);

				}
				resource = new InputStreamResource(new ByteArrayInputStream(cardPht));	
				HttpHeaders headers = new HttpHeaders();
				headers.set("Content-Disposition", String.format("attachment; filename="+fileName));    
				return ResponseEntity.ok()
						.headers(headers)
						.contentLength(cardPht.length)
						.contentType(MediaType.parseMediaType(contentType))
						.body(resource);
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());// TODO: handle exception
		}
		return ResponseEntity.noContent().build();


	}
}