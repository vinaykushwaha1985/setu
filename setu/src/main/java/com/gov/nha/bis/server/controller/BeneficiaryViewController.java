package com.gov.nha.bis.server.controller;
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
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONArray;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiarySeccForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.CardDownloadService;
import com.gov.nha.bis.server.rest.service.RationCardDataService;
import com.gov.nha.bis.server.rest.service.SaveDemoDataService;
import com.gov.nha.bis.server.util.CheckRedisEnbleFunction;
import com.gov.nha.bis.server.util.CopyUtility;


@CrossOrigin
@Controller
public class BeneficiaryViewController {


	private static final Logger logger = LogManager.getLogger(BeneficiaryViewController.class);

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
	

	@RequestMapping(value="/beneficiaryView",method = RequestMethod.POST)
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
		
		session =request.getSession(true);

		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		CopyUtility.copyProperties(applicationForm, beneficiarySeccForm, false);
		session.setAttribute("beneficiarySeccForm", beneficiarySeccForm);
		session.setAttribute("viewAction", "Y");
		
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
				userId,userType,roleId,"BENEFICIARY_VIEW",ip,name);
				
	
		return new ModelAndView("beneficiaryView", "command",applicationForm);
	}

	@RequestMapping(value="/beneficiaryDemoKycView",method = RequestMethod.POST)
	public ModelAndView beneficiaryDemoKycView(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		try {
			
			CopyUtility.copyProperties(applicationForm, beneficiarySeccForm, false);

			String fetchRes=	saveDemoDataService.fetchDemoDeclareData(applicationForm.getGuid(), applicationForm.getStateCode(), applicationForm.getRuralUrbanFlag(), 0);

			if(!ObjectUtils.isEmpty(fetchRes)) {
				JSONObject demoRes= new JSONObject(fetchRes);
				if(!ObjectUtils.isEmpty(demoRes)) {
					JSONArray demoArr=	demoRes.getJSONArray("demoAuthData");
					if(!ObjectUtils.isEmpty(demoArr)) {
						beneficiarySeccForm.setRefernceid_d(demoArr.getJSONObject(0).getLong("refernceid"));
						beneficiarySeccForm.setName_d(demoArr.getJSONObject(0).getString("name"));
						beneficiarySeccForm.setGuardian_d(demoArr.getJSONObject(0).getString("guardian"));
						beneficiarySeccForm.setGender_d(demoArr.getJSONObject(0).getString("gender"));

						if(demoArr.getJSONObject(0).has("pncode"))
							beneficiarySeccForm.setPncode_d(demoArr.getJSONObject(0).getString("pncode"));
						if(demoArr.getJSONObject(0).has("mobile"))
							beneficiarySeccForm.setMobile_d(demoArr.getJSONObject(0).getString("mobile"));

						beneficiarySeccForm.setDob_d(demoArr.getJSONObject(0).getString("dob"));
						beneficiarySeccForm.setAadhaar_d(new String(Base64.decode(demoArr.getJSONObject(0).getString("aadhaar"))));

					}

				}

			}

			if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.RATION_CARD_SERVICE_ENABLE_LIST.split(",")).contains(applicationForm.getStateCode())) {
				
				String fetchRationRes=	rationCardDataService.fetchRationData(applicationForm.getGuid(), applicationForm.getStateCode(), applicationForm.getRuralUrbanFlag(), 0);

				if(!ObjectUtils.isEmpty(fetchRationRes)) {
					JSONObject rationRes= new JSONObject(fetchRationRes);
					if(!ObjectUtils.isEmpty(rationRes)) {
						JSONArray demoArr=	rationRes.getJSONArray("rationCardData");
						if(!ObjectUtils.isEmpty(demoArr)) {
							beneficiarySeccForm.setFamily_id(demoArr.getJSONObject(0).getString("family_id"));
							beneficiarySeccForm.setFamily_mem_id(demoArr.getJSONObject(0).getString("family_mem_id"));
							beneficiarySeccForm.setMember_name_eng(demoArr.getJSONObject(0).getString("member_name_eng"));
							beneficiarySeccForm.setMother_name_eng(demoArr.getJSONObject(0).getString("mother_name_eng"));
							beneficiarySeccForm.setFather_name_eng(demoArr.getJSONObject(0).getString("father_name_eng"));
							beneficiarySeccForm.setSpouse_name_eng(demoArr.getJSONObject(0).getString("spouse_name_eng"));
							beneficiarySeccForm.setRelation_name(demoArr.getJSONObject(0).getString("relation_name"));
							if(demoArr.getJSONObject(0).has("year_of_birth"))
								beneficiarySeccForm.setYear_of_birth(demoArr.getJSONObject(0).getInt("year_of_birth"));
							if(demoArr.getJSONObject(0).has("mobile"))
								beneficiarySeccForm.setMobile_d(demoArr.getJSONObject(0).getString("mobile"));
							beneficiarySeccForm.setGender(demoArr.getJSONObject(0).getString("gender"));

						}

					}

				}
			}

		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
		}
		request.getSession(true).setAttribute("beneficiarySeccForm", beneficiarySeccForm);
		request.getSession(true).setAttribute("viewAction", "Y");

		return new ModelAndView("beneficiaryDemoKycView", "command",applicationForm);
	}


	@RequestMapping(value="/getUidToken",method = RequestMethod.POST)
	public @ResponseBody String getUidToken(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		HashMap<String, String> tokenMap = new  HashMap<String, String> ();

	
		String	response=cardDownloadService.getUidToken(applicationForm.getStateCode(),applicationForm.getGuid(), applicationConstantConfig.NHA_BIS_UID_TOKEN_URL);

		logger.info("response===="+response);
		if(!ObjectUtils.isEmpty(response)) {
			JSONObject demoRes= new JSONObject(response);
			if(!ObjectUtils.isEmpty(demoRes.getString("status"))) {
				if(demoRes.getString("status").equalsIgnoreCase("Y")) {
					if(applicationConstantConfig.DEMO_AUTH_UID_TOKEN.equalsIgnoreCase(demoRes.getString("uidtoken"))){
						tokenMap.put("status", "D");
						tokenMap.put("uidtoken", demoRes.getString("uidtoken"));
					}else {
						tokenMap.put("status", "Y");
						tokenMap.put("uidtoken", demoRes.getString("uidtoken"));
					}
				}else {
					tokenMap.put("status", "N");
				}
			}else {
				tokenMap.put("status", "N");
			}
		}else {
			tokenMap.put("status", "N");
		}
		try {
			res = mapper.writeValueAsString(tokenMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return res;
	}






}
