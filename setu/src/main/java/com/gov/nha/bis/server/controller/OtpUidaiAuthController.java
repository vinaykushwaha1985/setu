package com.gov.nha.bis.server.controller;
import java.math.BigInteger;
import java.util.Base64;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
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
import org.springframework.web.servlet.ModelAndView;

import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiarySeccForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.CardDownloadService;
import com.gov.nha.bis.server.rest.service.DemoAuthService;
import com.gov.nha.bis.server.rest.service.OtpUidaiAuthService;

@CrossOrigin
@Controller
public class OtpUidaiAuthController {



	private static final Logger logger = LogManager.getLogger(OtpBasedKycController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public CardDownloadService cardDownloadService;
	@Autowired
	public AppHistoryService appHistoryService;

	@RequestMapping(value="/otpUidaiAuth",method = RequestMethod.POST)
	public ModelAndView otpUidaiAuth(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		String	response=OtpUidaiAuthService.otpUidaiAuth(applicationForm.getAadhar().replaceAll(" ", ""),
				applicationForm.getOtp(),applicationForm.getOtpTxn(), applicationConstantConfig.OTP_UIDAI_AUTH_URL);

		logger.info("response===="+response);
		JSONObject demoRes= new JSONObject(response);
		String res="";
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
		String res1=null;		
		res1=demoRes.getString("status").equalsIgnoreCase("true")?"Y":"N";
		
		String update=appHistoryService.saveAadharAuthDetails(Integer.valueOf(stateCode), districtCode,
				Base64.getEncoder().encodeToString(applicationForm.getAadhar().getBytes()),new BigInteger(beneficiarySeccForm.getGuid()),
				userId,"1",res1,demoRes.getString("errCode"),Integer.valueOf(schemeid),beneficiarySeccForm.getRuralUrbanFlag(),"SETU");
		logger.info("update::"+update);
		} catch (Exception e) {
			logger.info("Error for OTP Auth::"+e.getMessage());
		}
		
		if(Boolean.valueOf(demoRes.getString("status"))) {

			logger.info(applicationForm.getStateCode()+"    "+applicationForm.getRefernceid()+"   "+applicationForm.getGuid());


			String cardResponse =cardDownloadService.cardDownload(applicationForm.getStateCode(), applicationForm.getRefernceid(),null, "BIS2.0",applicationConstantConfig.NHA_BIS_CARD_DOWNLOAD_URL);
			logger.info("card response "+cardResponse);
			JSONObject jRes= new JSONObject(cardResponse);
			if(jRes.getBoolean("status")) {

				applicationForm.setCardPht(jRes.getString("card"));

				request.setAttribute("cardPht", jRes.getString("card"));
				return new ModelAndView("forward:/benCardDonwload", "command",applicationForm);
			}else {
				request.setAttribute("status", "F");
			}

		}else {
			request.setAttribute("status", "N");
		}
		
		return new ModelAndView("beneficiaryCardDonwload", "command",applicationForm);
	}



}
