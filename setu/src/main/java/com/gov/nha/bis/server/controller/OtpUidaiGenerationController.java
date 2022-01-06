package com.gov.nha.bis.server.controller;
import java.math.BigInteger;
import java.util.Base64;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.gov.nha.bis.server.rest.service.DemoAuthService;
import com.gov.nha.bis.server.rest.service.OtpUidaiGenerationService;

@CrossOrigin
@Controller
public class OtpUidaiGenerationController {

	
	
	private static final Logger logger = LogManager.getLogger(OtpUidaiGenerationController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	@Autowired
	public AppHistoryService appHistoryService;
	
	@RequestMapping(value="/genOtp",method = RequestMethod.POST)
	public @ResponseBody String genOtp(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		HttpSession session= null;
		String userId="";
		session =request.getSession(true);
		userId=(String)session.getAttribute("USERID");
		HashMap<String, String> otpMap = new  HashMap<String, String> ();
		
		OtpUidaiGenerationService otpAuthService= new OtpUidaiGenerationService();
		
	String	response=otpAuthService.genOtp(applicationForm.getAadhar().replaceAll(" ", ""), applicationConstantConfig.OTP_GEN_URL);
		
	logger.info("response===="+response);
		JSONObject demoRes= new JSONObject(response);
		if(Boolean.valueOf(demoRes.getString("status"))) {
			otpMap.put("status", "Y");
			otpMap.put("txn", demoRes.getString("txn"));
			otpMap.put("mobile", demoRes.getString("mobile"));
		}else {
			otpMap.put("status", "N");
		}
		String stateCode="";
		String districtCode="";
		try {
		//res=demoRes.getString("status").equalsIgnoreCase("true")?"Y":"N";
		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		beneficiarySeccForm=(BeneficiarySeccForm) request.getSession(true).getAttribute("beneficiarySeccForm");
		if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {
			stateCode=beneficiarySeccForm.getStateCodeRural();
			districtCode=beneficiarySeccForm.getDistrictCodeRural();
		}else if(applicationForm.getRuralUrbanFlag().equalsIgnoreCase("U")) {
			stateCode=beneficiarySeccForm.getStateCodeRural();
			districtCode=beneficiarySeccForm.getDistrictCodeRural();
		}
		String schemeid=beneficiarySeccForm.getSchemeid()==null? "1":beneficiarySeccForm.getSchemeid();;
		
		String res1=null;
		String error=null;
		if(demoRes!=null) {
			res1=demoRes.getString("status").equalsIgnoreCase("true")?"Y":"N";	
			error=demoRes.getString("errCode")==null?"":demoRes.getString("errCode");
		}	
		
		String update=appHistoryService.saveAadharAuthDetails(Integer.valueOf(stateCode), districtCode,
				Base64.getEncoder().encodeToString(applicationForm.getAadhar().getBytes()),new BigInteger(beneficiarySeccForm.getGuid()),
				userId,"1",res1,error,Integer.valueOf(schemeid),beneficiarySeccForm.getRuralUrbanFlag(),"SETU");
		logger.info("update::"+update);
		} catch (Exception e) {
			logger.info("Error for Demo Auth::"+e.getMessage());
		}
		 try {
	            res = mapper.writeValueAsString(otpMap);
	        } catch (JsonProcessingException e) {
	            // TODO Auto-generated catch block
	            logger.info(e.getMessage());
	        }
	        return res;
	}




}
