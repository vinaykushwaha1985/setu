package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gov.nha.bis.server.model.BeneficiaryForm;
import com.gov.nha.bis.server.model.KycForm;
import com.gov.nha.bis.server.model.SeccForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.PendingReqIdService;

@CrossOrigin
@Controller
public class BeneficiaryUpdateStatusController {

	private static final Logger logger = LogManager.getLogger(BeneficiaryUpdateStatusController.class);
	

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;


	@RequestMapping(value="/updateBenStatus",method = RequestMethod.POST)
	public ModelAndView userLogin(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("BeneficiaryForm") BeneficiaryForm beneficiaryForm,BindingResult result) {

		PendingReqIdService pendingReqIdService = new PendingReqIdService();
		String userId =(String)request.getSession(true).getAttribute("USERID");
		SeccForm sccForm=new SeccForm();

		KycForm kycForm = new KycForm();

		try {


			String updateStatusRes=pendingReqIdService.getUpdateBenCheckStatusRefData(
					getUpdateRqJosRequest(userId,beneficiaryForm.getRefId(),beneficiaryForm.getMatchStatus(),""),applicationConstantConfig.BEN_APPROVE_STATUS_URL);


			logger.info(updateStatusRes);
			JSONObject updateStatusJosnRes= new JSONObject(updateStatusRes);
			if(updateStatusJosnRes.getString("status").equalsIgnoreCase("SUCCESS")) {


				String pendRqIdRes=	pendingReqIdService.getPendingReqIdData(getPenRqJosRequest(userId),applicationConstantConfig.PENDING_REQ_ID_APP_URL);

				logger.info(pendRqIdRes);
				JSONObject pendJosnRqIdRes= new JSONObject(pendRqIdRes);
				if(pendJosnRqIdRes.getString("status").equalsIgnoreCase("SUCCESS")) {

					JSONObject dataRefData=pendJosnRqIdRes.getJSONObject("data");

					if(!ObjectUtils.isEmpty(dataRefData)) {

						request.getSession(true).setAttribute("RefId", String.valueOf(dataRefData.getBigInteger("refernceid")));

						String	seccDataRes=	pendingReqIdService.getSeccEkycRefData(
								getSeccKycRefRequest(String.valueOf(dataRefData.getBigInteger("refernceid")),
										dataRefData.getString("state_code"),dataRefData.getString("rural_urban")),applicationConstantConfig.SECC_KYC_REF_ID_URL);

						logger.info(seccDataRes);
						JSONObject seccDataJosnRes= new JSONObject(seccDataRes);
						if(!ObjectUtils.isEmpty(seccDataJosnRes)) {
							if(seccDataJosnRes.getString("status").equalsIgnoreCase("SUCCESS")) {

								JSONObject seccDataJosn= seccDataJosnRes.getJSONObject("data");

								if(seccDataJosn.has("SECDATA")) {
									JSONObject seccDataJon=seccDataJosn.getJSONObject("SECDATA"); 

									if(!ObjectUtils.isEmpty(seccDataJon)) {

										sccForm.setSeccName(seccDataJon.getString("name"));
										sccForm.setSeccGender(seccDataJon.getString("genderid"));
										if(seccDataJon.getString("dob")!=null) {
											String dobY=seccDataJon.getString("dob").substring(0,4);
											sccForm.setSeccDob(dobY);
										}

										sccForm.setSeccFName((seccDataJon.isNull("fathername")?" ": seccDataJon.getString("fathername"))+" / "+(seccDataJon.isNull("spousenm")?"": seccDataJon.getString("spousenm")) );
										sccForm.setSeccAdr(seccDataJon.isNull("addressline1") ? " ": seccDataJon.getString("addressline1")
												+" "+ (seccDataJon.isNull("addressline2")?" ": seccDataJon.getString("addressline2") )
												+" "+(seccDataJon.isNull("addressline3")?" ": seccDataJon.getString("addressline3"))
												+" "+(seccDataJon.isNull("addressline4")?" ": seccDataJon.getString("addressline4") )
												+" "+(seccDataJon.isNull("addressline5")?" ": seccDataJon.getString("addressline5"))
										+" "+ (seccDataJon.isNull("district_name_english")?" ": seccDataJon.getString("district_name_english")) 
										+" "+ (seccDataJon.isNull("state_name_english")?" ": seccDataJon.getString("state_name_english")) 
										+" "+(seccDataJon.isNull("pincode")?" ": seccDataJon.getString("pincode")));

									}
								}
								if(seccDataJosn.has("EKYCDATA")) {
									JSONObject kycDataJon=seccDataJosn.getJSONObject("EKYCDATA"); 
									if(!ObjectUtils.isEmpty(kycDataJon)) {

										kycForm.setKycName(kycDataJon.getString("name_ben"));
										kycForm.setKycGender(kycDataJon.getString("gender_ben"));
										if(kycDataJon.getString("dob_ben")!=null) {
											if(kycDataJon.getString("dob_ben").length()>4) {
												String dobY=kycDataJon.getString("dob_ben").substring(0,4);
												kycForm.setKycDob(dobY);
											}else {
												kycForm.setKycDob(kycDataJon.getString("dob_ben"));
											}
										}
										kycForm.setKycFName(kycDataJon.getString("care_of_dec"));
										kycForm.setPht(kycDataJon.getString("doc_pic"));
										kycForm.setKycAdr(kycDataJon.getString("address_ben") +" "+kycDataJon.getString("vtc_ben")
														+" "+kycDataJon.getString("sub_dist_ben") +" "+kycDataJon.getString("district_name_ben")	
														+" "+kycDataJon.getString("state_name_ben") +" "+kycDataJon.getString("pin_code_ben")	
												);
									}
								}

							}

						}
					}
				}

				request.getSession(true).setAttribute("seccForm", sccForm);
				request.getSession(true).setAttribute("kycForm", kycForm);


				request.setAttribute("STATUS","Y");

				request.getSession(true).setAttribute("Bis_Login",  "Bis_Login");

				return new ModelAndView("ekyupdate", "command", new BeneficiaryForm());





			}


		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
		}




		return new ModelAndView("ekyupdate", "command", new BeneficiaryForm());



	}



	private String getUpdateRqJosRequest(String userId, String refId, String matchStatus, String ip) {
		JSONObject finalData= new JSONObject();
		JSONObject checkDetailsEntity= new JSONObject();
		checkDetailsEntity.put("refernceid", refId);
		checkDetailsEntity.put("checkedby", userId);
		checkDetailsEntity.put("checkstatusid", matchStatus);
		checkDetailsEntity.put("ip", ip);



		JSONObject aadhaarDetails= new JSONObject();
		aadhaarDetails.put("refernceid", refId);
		aadhaarDetails.put("status", matchStatus);


		JSONObject mapperDetailsEntity= new JSONObject();

		mapperDetailsEntity.put("refernceid",refId );

		mapperDetailsEntity.put("status", matchStatus);
		mapperDetailsEntity.put("checkedby", userId);


		finalData.put("checkDetailsEntity", checkDetailsEntity);

		finalData.put("aadhaarDetails", aadhaarDetails);
		finalData.put("mapperDetailsEntity", mapperDetailsEntity);


		return finalData.toString();
	}



	private String getSeccKycRefRequest(String refId, String stateCode, String ruFlag) {
		JSONObject data= new JSONObject();
		data.put("refernceid", refId);
		data.put("state_code", stateCode);
		data.put("rural_urban", ruFlag);

		return data.toString();
	}

	public static String getPenRqJosRequest(String userId){

		JSONObject data= new JSONObject();
		data.put("userId", userId);

		return data.toString();
	}

}
