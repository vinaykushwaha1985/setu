package com.gov.nha.bis.server.controller;

import java.math.BigInteger;
import java.util.Base64;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiarySeccForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.BioKycService;
import com.gov.nha.bis.server.rest.service.CardStatusService;

@Controller
public class BioBasedAuthController {


	private static final Logger logger = LogManager.getLogger(BioBasedAuthController.class);

	@Autowired
	public BioKycService bioKycService;


	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public CardStatusService cardStatusService;
	@Autowired
	public AppHistoryService appHistoryService;
	
	@RequestMapping(value="/bioBasedAuth",method = RequestMethod.POST)
	public @ResponseBody String bioBasedKyc(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		HashMap<String, String> kycMap = new  HashMap<String, String> ();


		String	response=bioKycService.bioAuth(applicationForm.getAadhar().replaceAll(" ", ""),applicationForm.getPidData(),
				applicationForm.getBioType(), applicationConstantConfig.BIO_AUTH_URL);
		String bioT=applicationForm.getBioType();
		String authtype=bioT.equalsIgnoreCase("P")?"6":bioT.equalsIgnoreCase("I")?"5":"4";
		logger.info("authtype===="+authtype);
		logger.info("response===="+response);
		if(!ObjectUtils.isEmpty(response)) {
			JSONObject demoRes= new JSONObject(response);
			//Add for save auth
			String resp="";
			HttpSession session= null;
			String stateCode="";
			String districtCode="";
			try {	
			BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
			beneficiarySeccForm=(BeneficiarySeccForm) request.getSession(true).getAttribute("beneficiarySeccForm");
			if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {
				stateCode=beneficiarySeccForm.getStateCodeRural();
				districtCode=beneficiarySeccForm.getDistrictCodeRural();
			}else if(applicationForm.getRuralUrbanFlag().equalsIgnoreCase("U")) {
				stateCode=beneficiarySeccForm.getStateCodeRural();
				districtCode=beneficiarySeccForm.getDistrictCodeRural();
			}
			String schemeid=beneficiarySeccForm.getSchemeid()==null? "1":beneficiarySeccForm.getSchemeid();
			String userId="";
			session =request.getSession(true);
			userId=(String)session.getAttribute("USERID");
					
			resp=demoRes.getString("status").equalsIgnoreCase("true")?"Y":"N";
			String update=appHistoryService.saveAadharAuthDetails(Integer.valueOf(stateCode), districtCode,
					Base64.getEncoder().encodeToString(applicationForm.getAadhar().getBytes()),new BigInteger(beneficiarySeccForm.getGuid()),
					userId,authtype,resp,demoRes.getString("errCode"),Integer.valueOf(schemeid),beneficiarySeccForm.getRuralUrbanFlag(),"SETU");
			logger.info("update::"+update);
			} catch (Exception e) {
				logger.info("Error for OTP Auth::"+e.getMessage());
			}
			if(Boolean.valueOf(demoRes.getString("status"))) {

				kycMap.put("status", "Y");

			}else {
				kycMap.put("status", "N");
			}
		}else {
			kycMap.put("status", "N");
		}
		
		try {
			res = mapper.writeValueAsString(kycMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return res;
	}










}
