package com.gov.nha.bis.server.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
import com.gov.nha.bis.server.dto.BeneficiaryParam;
import com.gov.nha.bis.server.dto.RationParam;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.GenerateGuidPmjayIdService;
import com.gov.nha.bis.server.rest.service.RationCardDataService;
import com.gov.nha.bis.server.rest.service.SaveNfsaBeneficiaryService;
import com.gov.nha.bis.server.rest.service.SmsSenderService;
import com.gov.nha.bis.server.util.CopyUtility;

@CrossOrigin
@Controller
public class SaveKycNfsaController {

	private static final Logger logger = LogManager.getLogger(SaveKycDataController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public RationCardDataService rationCardDataService;

	
	@Autowired
	public SmsSenderService smsSenderService;

	@Autowired
	public  GenerateGuidPmjayIdService generateGuidPmjayIdService;

	@Autowired
	public SaveNfsaBeneficiaryService saveNfsaBeneficiaryService;


	@RequestMapping(value="/saveKycNfsa",method = RequestMethod.POST)
	public @ResponseBody String  saveKycData(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		String userType="";
		HashMap<String, String> saveMap = new  HashMap<String, String> ();

	
		Long guid=null;
		String pmjayId="";
		try {
			String userId=(String)request.getSession(true).getAttribute("USERID");

			String guidRes=generateGuidPmjayIdService.getGuidPmjayId(applicationForm.getAadhar(), 
					applicationForm.getFamily_mem_id(), applicationForm.getFamily_id(), "", userId);

			if(!ObjectUtils.isEmpty(guidRes)) {
				JSONObject guidJon= new JSONObject(guidRes)	;

				if(guidJon.getString("status").equalsIgnoreCase("SUCCESS")) {

					JSONObject dataJon= guidJon.getJSONObject("data")	;


					guid=dataJon.getLong("guid");
					pmjayId=dataJon.getString("pmjid");

					BeneficiaryParam beneficiaryParam= getBeneficiaryParam(applicationForm);		
					beneficiaryParam.setGuid(BigInteger.valueOf(guid));

					String saveRes=	saveNfsaBeneficiaryService.saveNfsaBeneficiary(beneficiaryParam);

					if(!ObjectUtils.isEmpty(saveRes)) {

						JSONObject savJon= new JSONObject(saveRes)	;

						if(savJon.getBoolean("status")) {


							String	kycRes=saveNfsaBeneficiaryService.saveKyc(String.valueOf(guid),applicationForm.getName_s(), 
									applicationForm.getFather_name_s().substring(0,3),applicationForm.getFather_name_s().substring(4),
									applicationForm.getGender_s(),applicationForm.getDob_s(),applicationForm.getAddress_s(),
									applicationForm.getState_name_s(),	applicationForm.getDistrict_name_s(), 
									applicationForm.getUid_auth_type(),
									userId,userType,applicationForm.getUid_token(),
									"1",applicationForm.getRuralUrbanFlag(),applicationForm.getPhoto_s(),
									applicationForm.getState_code(),
									applicationForm.getDistrictCode(),1);

							logger.info("response===="+kycRes);
							if(!ObjectUtils.isEmpty(kycRes)) {
								JSONObject demoRes= new JSONObject(kycRes);
								if(demoRes.getBoolean("status")) {

									String ayushRes =		saveNfsaBeneficiaryService.saveAyshmanCard(applicationForm.getFamily_id(), 
											applicationForm.getFamily_mem_id(),BigInteger.valueOf(guid), 
											applicationForm.getUid_token(), applicationForm.getUid_aadhaar(), 
											Integer.valueOf(applicationForm.getState_code()), applicationForm.getDistrict_code(),
											applicationForm.getUid_auth_type(), 1, applicationForm.getRural_urban(),
											applicationForm.getMobile_member(), pmjayId,applicationForm.getMember_name_eng());


									logger.info("ayushRes response===="+ayushRes);
									if(!ObjectUtils.isEmpty(ayushRes)) {
										
										
										RationParam rPram= new RationParam();
										CopyUtility.copyProperties(applicationForm, rPram, false);
										rPram.setCreationby(userId);
										rPram.setGuid(applicationForm.getRefernceid());
										rPram.setStatus(0);
										rPram.setGuid(String.valueOf(guid));
										saveNfsaBeneficiaryService.saveRationData(rPram);
										
										
										smsSenderService.smsSender(applicationForm.getDemoMobile(),"1007163593307833597", 
												applicationForm.getMember_name_eng().split(" ")[0],String.valueOf(guid));
										
										
										saveMap.put("refId", String.valueOf(guid));
										saveMap.put("pmjayID",pmjayId);
										saveMap.put("status", "Y");
									}else {
										saveMap.put("status", "N");
									}


								}else {
									saveMap.put("status", "N");
								}
							}else {
								saveMap.put("status", "N");
							}
						}else {
							saveMap.put("status", "N");
						}

					}else {
						saveMap.put("status", "N");
					}

				}else {
					saveMap.put("status", "N");
				}

			}else {
				saveMap.put("status", "N");
			}




			try {
				res = mapper.writeValueAsString(saveMap);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				logger.info(e.getMessage());
			}

		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
		}
		return res;
	}


	private BeneficiaryParam getBeneficiaryParam(ApplicationForm applicationForm) {
		// TODO Auto-generated method stub
		BeneficiaryParam bParam= new BeneficiaryParam();

		bParam.setState_code(Integer.parseInt(applicationForm.getState_code()));
		bParam.setState_name_english(applicationForm.getStateName());
		bParam.setDistrict_code(applicationForm.getDistrict_code());
		bParam.setDistrict_name_english(applicationForm.getDistrictName());
		bParam.setRation_card_no(applicationForm.getFamily_id());
		bParam.setName(applicationForm.getMember_name_eng());
		bParam.setFathername(applicationForm.getFather_name_eng());
		bParam.setGenderid((applicationForm.getGender().startsWith("M")?"1":applicationForm.getGender().startsWith("F")?"2":"0"));
		bParam.setCard_status(1);
		bParam.setMothername(applicationForm.getMother_name_eng());
		bParam.setRelation(applicationForm.getRelation_name());
		bParam.setRural_urban(applicationForm.getRural_urban());
		bParam.setSpousenm(applicationForm.getSpouse_name_eng());

		return bParam;
	}

}
