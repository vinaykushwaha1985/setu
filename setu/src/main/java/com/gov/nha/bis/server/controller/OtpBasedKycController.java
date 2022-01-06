package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.io.IOException;
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
import com.gov.nha.bis.server.rest.service.OtpKycService;

@CrossOrigin
@Controller
public class OtpBasedKycController {

	private static final Logger logger = LogManager.getLogger(OtpBasedKycController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	@Autowired
	public AppHistoryService appHistoryService;

	@RequestMapping(value="/otpBasedKyc",method = RequestMethod.POST)
	public @ResponseBody String otpBasedKyc(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		HashMap<String, String> kycMap = new  HashMap<String, String> ();

		OtpKycService otpKycService= new OtpKycService();

		String	response=otpKycService.otpKyc(applicationForm.getAadhar().replaceAll(" ", ""),applicationForm.getOtp(),applicationForm.getOtpTxn(), applicationConstantConfig.OTP_KYC_URL);

		logger.info("response===="+response);
		JSONObject demoRes=null;
		if(!ObjectUtils.isEmpty(response)) {
			demoRes= new JSONObject(response);
			if(Boolean.valueOf(demoRes.getString("status"))) {

				JSONObject UidData =demoRes.getJSONObject("UidData");
				JSONObject poi =UidData.getJSONObject("poi");


//				kycMap.put("kycName", poi.getString("name"));
//				kycMap.put("kycGender", poi.getString("gender"));
//				kycMap.put("kycDob", poi.getString("dob"));
//
//
//
//				kycMap.put("uidToken", UidData.getString("tkn"));
//				kycMap.put("pht", UidData.getString("pht"));
//
//				JSONObject poa =UidData.getJSONObject("poa");
//				kycMap.put("kycFname", poa.getString("co"));
				kycMap.put("kycName", poi.isNull("name")?" ":poi.getString("name"));
				kycMap.put("kycGender",poi.isNull("gender")?" ": poi.getString("gender"));
				kycMap.put("kycDob", poi.isNull("dob")?" ":poi.getString("dob"));
				kycMap.put("uidToken", UidData.isNull("tkn")?" ":UidData.getString("tkn"));
				kycMap.put("pht", UidData.isNull("pht")?" ":UidData.getString("pht"));
				JSONObject poa =UidData.getJSONObject("poa");
				kycMap.put("kycFname", poa.isNull("co")?" ":poa.getString("co"));

				String address=(poa.isNull("house")?" ":poa.getString("house"))
						+" "+(poa.isNull("street")?" ":poa.getString("street"))	
						+" "+(poa.isNull("vtc")?" ":poa.getString("vtc"))	
						+" "+(poa.isNull("dist")?" ":poa.getString("dist"))	
						+" "+(poa.isNull("state")?" ":poa.getString("state"))	
						+" "+(poa.isNull("pc")?" ":poa.getString("pc"));

				kycMap.put("kycAdr", address);

				kycMap.put("vtc_s", (poa.isNull("vtc")?" ":poa.getString("vtc")));
				kycMap.put("district_name_s", (poa.isNull("dist")?" ":poa.getString("dist")));
				kycMap.put("state_name_s", (poa.isNull("state")?" ":poa.getString("state")));
				kycMap.put("post_s", (poa.isNull("pc")?" ":poa.getString("pc")));


				kycMap.put("status", "Y");
			}else {
				kycMap.put("status", "N");
			}
		}else {
			kycMap.put("status", "N");
		}
		String stateCode="";
		String districtCode="";
		HttpSession session= null;
		String userId="";
		session =request.getSession(true);
		userId=(String)session.getAttribute("USERID");
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
		String schemeid=beneficiarySeccForm.getSchemeid()==null? "1":beneficiarySeccForm.getSchemeid();;
		String res1=null;
		String error=null;
		if(demoRes!=null) {
			res1=demoRes.getString("status").equalsIgnoreCase("true")?"Y":"N";	
			error=demoRes.getString("kycErrCode")==null?"":demoRes.getString("kycErrCode");
		}
		String update=appHistoryService.saveAadharAuthDetails(Integer.valueOf(stateCode), districtCode,
				Base64.getEncoder().encodeToString(applicationForm.getAadhar().getBytes()),new BigInteger(beneficiarySeccForm.getGuid()),
				userId,"3",res1,error,Integer.valueOf(schemeid),beneficiarySeccForm.getRuralUrbanFlag(),"SETU");
		logger.info("update::"+update);
		} catch (Exception e) {
			logger.info("Error for OTP EKYC::"+e.getMessage());
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
