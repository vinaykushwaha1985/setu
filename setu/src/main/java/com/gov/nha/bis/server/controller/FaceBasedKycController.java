package com.gov.nha.bis.server.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
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
import com.gov.nha.bis.server.rest.service.FaceIdDetectService;

@Controller
public class FaceBasedKycController extends NhaBisBaseController{


	private static final Logger logger = LogManager.getLogger(FaceBasedKycController.class);

	@Autowired
	public BioKycService bioKycService;

	@Autowired
	public FaceIdDetectService faceIdDetectService;


	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	@Autowired
	public AppHistoryService appHistoryService;

	@RequestMapping(value="/faceBasedKyc",method = RequestMethod.POST)
	public @ResponseBody String faceBasedKyc(
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		HashMap<String, String> kycMap = new  HashMap<String, String> ();
		String pidData="";
		String facePht="";
		String faceResonse="";
		String faceId="";
		HttpSession session=null;
		session=request.getSession(true);
		if(session.getAttribute("FACEID")!=null){
			faceId =(String)session.getAttribute("FACEID");
		}
		JSONObject faceRes=null;
		if(!ObjectUtils.isEmpty(faceId))	{	

			String faceIdData=	faceIdDetectService.getFaceId(faceId, applicationConstantConfig.FACE_ID_BARCODE_DETECT_URL);

			logger.info("faceIdData=="+faceIdData);
			
			if(!ObjectUtils.isEmpty(faceIdData)) {
				faceRes= new JSONObject(faceIdData);

				if(faceRes.getBoolean("status")) {
					JSONObject faceJon =faceRes.getJSONObject("faceData");

					pidData=faceJon.getString("pidData");
					facePht=faceJon.getString("pht");

					String	response=bioKycService.bioKyc(applicationForm.getAadhar().replaceAll(" ", ""),pidData ,
							applicationForm.getBioType(), applicationConstantConfig.BIO_KYC_URL);
					faceResonse=response;
					logger.info("response===="+response);
					if(!ObjectUtils.isEmpty(response)) {
						JSONObject demoRes= new JSONObject(response);
						if(Boolean.valueOf(demoRes.getString("status"))) {

							JSONObject UidData =demoRes.getJSONObject("UidData");
							JSONObject poi =UidData.getJSONObject("poi");

							kycMap.put("kycName", poi.isNull("name")?" ":poi.getString("name"));
							kycMap.put("kycGender",poi.isNull("gender")?" ": poi.getString("gender"));
							kycMap.put("kycDob", poi.isNull("dob")?" ":poi.getString("dob"));
							kycMap.put("uidToken", UidData.isNull("tkn")?" ":UidData.getString("tkn"));
							kycMap.put("pht", UidData.isNull("pht")?" ":UidData.getString("pht"));
							JSONObject poa =UidData.getJSONObject("poa");
							kycMap.put("kycFname", poa.isNull("co")?" ":poa.getString("co"));
//							kycMap.put("kycName", poi.getString("name"));
//							kycMap.put("kycGender", poi.getString("gender"));
//							kycMap.put("kycDob", poi.getString("dob"));
//
//
//
//							kycMap.put("uidToken", UidData.getString("tkn"));
//							kycMap.put("pht", UidData.getString("pht"));
//
//							JSONObject poa =UidData.getJSONObject("poa");
//							kycMap.put("kycFname", poa.getString("co"));

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

							kycMap.put("facePht", facePht);
							kycMap.put("status", "Y");

						}else {
							kycMap.put("status", "N");
						}
					}else {
						kycMap.put("status", "N");
					}

				}
			}




		}else {
			kycMap.put("status", "N");
		}
		
		String bioT=applicationForm.getBioType();
		String authtype=bioT.equalsIgnoreCase("P")?"9":bioT.equalsIgnoreCase("I")?"8":"7";
		String resp="";
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
		String error=null;
		System.out.println(faceResonse);
		JSONObject demoRes1= new JSONObject(faceResonse);
		System.out.println(demoRes1);
		if(demoRes1!=null) {
			res1=demoRes1.getString("status").equalsIgnoreCase("true")?"Y":"N";	
			error=demoRes1.getString("kycErrCode")==null?"":demoRes1.getString("kycErrCode");
		}
		String update=appHistoryService.saveAadharAuthDetails(Integer.valueOf(stateCode), districtCode,
				java.util.Base64.getEncoder().encodeToString(applicationForm.getAadhar().getBytes()),new BigInteger(beneficiarySeccForm.getGuid()),
				userId,authtype,res1,error,Integer.valueOf(schemeid),beneficiarySeccForm.getRuralUrbanFlag(),"SETU");
		logger.info("update::"+update);
		} catch (Exception e) {
			logger.info("Error for OTP Auth::"+e.getMessage());
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
