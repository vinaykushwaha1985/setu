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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiarySeccForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.DemoAuthService;

@CrossOrigin
@Controller
public class DemoAuthController {
	
	
	private static final Logger logger = LogManager.getLogger(DemoAuthController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	@Autowired
	public AppHistoryService appHistoryService;
	
	@RequestMapping(value="/demoAuth",method = RequestMethod.POST)
	public @ResponseBody String demoAuth(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		String res="N";
		HttpSession session= null;
		String userId="";
		DemoAuthService demoAuthService= new DemoAuthService();
		session =request.getSession(true);
		userId=(String)session.getAttribute("USERID");
	String	response=demoAuthService.demoAuth(applicationForm.getAadhar().replaceAll(" ", ""), applicationForm.getDemoName(), applicationForm.getDemoGender(), 
				applicationForm.getDemoDob(), applicationForm.getDemoMobile(), applicationForm.getDemoEmail(),applicationForm.getDemoFname(), applicationConstantConfig.DEMO_AUTH_URL);
		
	logger.info("response===="+response);
		JSONObject demoRes= new JSONObject(response);
		if(Boolean.valueOf(demoRes.getString("status"))) {
			res="Y";
		}else {
			res="N";
		}
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
		int schemeid=1;
		
			
		
		String update=appHistoryService.saveAadharAuthDetails(Integer.valueOf(stateCode), districtCode,
				Base64.getEncoder().encodeToString(applicationForm.getAadhar().getBytes()),new BigInteger(beneficiarySeccForm.getGuid()),
				userId,"10",res,demoRes.getString("errCode"),schemeid,beneficiarySeccForm.getRuralUrbanFlag(),"SETU");
		logger.info("update::"+update);
		} catch (Exception e) {
			logger.info("Error for Demo Auth::"+e.getMessage());
		}
		return res;
	}


}
