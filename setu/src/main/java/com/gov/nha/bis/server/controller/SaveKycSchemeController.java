package com.gov.nha.bis.server.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
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
import com.gov.nha.bis.server.dto.PushCardDto;
import com.gov.nha.bis.server.dto.RationParam;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiarySeccForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.BenSaveCardStatusService;
import com.gov.nha.bis.server.rest.service.BeneficiaryAadhaarFetchSchemeDataService;
import com.gov.nha.bis.server.rest.service.BeneficiaryCreateCardService;
import com.gov.nha.bis.server.rest.service.BeneficiaryMmjaUpdateStatusService;
import com.gov.nha.bis.server.rest.service.BeneficiarySaveSchemeDataService;
import com.gov.nha.bis.server.rest.service.BeneficiaryUpdateStatusService;
import com.gov.nha.bis.server.rest.service.GenerateGuidPmjayIdService;
import com.gov.nha.bis.server.rest.service.RationCardDataService;
import com.gov.nha.bis.server.rest.service.SaveDemoDataService;
import com.gov.nha.bis.server.rest.service.SaveKycDataService;
import com.gov.nha.bis.server.rest.service.SaveSubSchemeDataService;
import com.gov.nha.bis.server.rest.service.SmsSenderService;
import com.gov.nha.bis.server.rest.service.VerifyCardStatusService;
import com.gov.nha.bis.server.util.CheckRedisEnbleFunction;
import com.gov.nha.bis.server.util.CopyUtility;

@Controller
public class SaveKycSchemeController {
	private static final Logger logger = LogManager.getLogger(SaveKycSchemeController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public BeneficiaryCreateCardService beneficiaryCreateCardService;

	@Autowired
	public SaveKycDataService saveKycDataService;

	@Autowired
	public SaveDemoDataService saveDemoDataService;

	@Autowired
	public BeneficiaryUpdateStatusService beneficiaryUpdateStatusService;


	@Autowired
	public VerifyCardStatusService  verifyCardStatusService;

	@Autowired
	public RationCardDataService rationCardDataService;

	@Autowired
	public BenSaveCardStatusService benSaveCardStatusService;

	@Autowired
	public  GenerateGuidPmjayIdService generateGuidPmjayIdService;


	@Autowired
	public BeneficiarySaveSchemeDataService beneficiarySaveSchemeDataService;

	@Autowired
	public BeneficiaryMmjaUpdateStatusService beneficiaryMmjaUpdateStatusService;
	
	@Autowired
	BeneficiaryAadhaarFetchSchemeDataService beneficiaryAadhaarFetchSchemeDataService;
	
	@Autowired
	public SaveSubSchemeDataService saveSubSchemeDoc;
	
	@Autowired
	public SmsSenderService smsSenderService;
	@RequestMapping(value="/saveKycSchemeData",method = RequestMethod.POST)
	public @ResponseBody String  saveKycData(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		String userType="";
		HashMap<String, String> saveMap = new  HashMap<String, String> ();

		SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd");
		BeneficiarySeccForm beneficiarySeccForm= new BeneficiarySeccForm();
		logger.info("applicationForm"+applicationForm);
		logger.info("Save Guid ::"+applicationForm.getGuid());
		Long guid=null;
		String pmjayId="";
		String nfsaMemId="";
		String datasource="";
		String apisource="";
		String pmjayIdother="";
		try {

			String userId=(String)request.getSession(true).getAttribute("USERID");

			if(request.getSession(true).getAttribute("userType")!=null)
				userType=(String)request.getSession(true).getAttribute("userType");

			if(request.getSession(true).getAttribute("beneficiarySeccForm")!=null)
				beneficiarySeccForm=(BeneficiarySeccForm)request.getSession(true).getAttribute("beneficiarySeccForm");

			if(beneficiarySeccForm.getSchemeid().equalsIgnoreCase("4")){
				datasource="SECC";
				apisource="ACDC-RATION";
			}else  if(beneficiarySeccForm.getSchemeid().equalsIgnoreCase("5")) {
				datasource="SECC";
				apisource="ACDC-MOBILE";
			}else  if(beneficiarySeccForm.getSchemeid().equalsIgnoreCase("2")) {
				datasource="NFSA";
				apisource="NFSA-RATION";
			}else  if(beneficiarySeccForm.getSchemeid().equalsIgnoreCase("3")) {
				datasource="MMJAA";
				apisource="MMJAA UP";
			}else  if(beneficiarySeccForm.getSchemeid().equalsIgnoreCase("6")) {
				datasource="BOCW";
				apisource="BOCW";
			}

			List<String> guidList = new ArrayList<String>();
			
			String aadhaarFetchRes=beneficiaryAadhaarFetchSchemeDataService.getAadhaarFetch(applicationForm.getUid_token());
			if(!ObjectUtils.isEmpty(aadhaarFetchRes)) {
				JSONObject aadhaartokenJon= new JSONObject(aadhaarFetchRes)	;

				if(aadhaartokenJon.getString("status").equalsIgnoreCase("SUCCESS")) {
					saveMap.put("status", "NN");
					try {
						res = mapper.writeValueAsString(saveMap);
						return res;
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						logger.info(e.getMessage());
					}
				}
			}



			if(applicationForm.getSchemeid().equalsIgnoreCase("2") ||applicationForm.getSchemeid().equalsIgnoreCase("6") || applicationForm.getState_code().equalsIgnoreCase("24")) {			

				String guidRes=generateGuidPmjayIdService.getGuidPmjayId(applicationForm.getAadhar(), 
						applicationForm.getFamily_mem_id(), applicationForm.getGuid(), "", userId);
				if(!ObjectUtils.isEmpty(guidRes)) {
					JSONObject guidJon= new JSONObject(guidRes)	;

					if(guidJon.getString("status").equalsIgnoreCase("SUCCESS")) {

						JSONObject dataJon= guidJon.getJSONObject("data")	;


						guid=dataJon.getLong("guid");
						pmjayId=dataJon.getString("pmjid");

						nfsaMemId=applicationForm.getGuid();

						applicationForm.setGuid(String.valueOf(guid));
						applicationForm.setRefernceid(String.valueOf(guid));

					}
				}else {
					return "null";
				}

			}			
			guidList.add(applicationForm.getGuid());

			String verifyStaty=	verifyCardStatusService.verifyCardStatus(applicationForm.getState_code(),guidList);

			if(!ObjectUtils.isEmpty(verifyStaty)) {


				JSONArray verifyStatyJson= new JSONArray(verifyStaty);
				if(verifyStatyJson.getJSONObject(0).getString("status").equalsIgnoreCase("Y")) {
					if(verifyStatyJson.getJSONObject(0).getString("verification_status").equalsIgnoreCase("A")) {
						saveMap.put("status", "A");
					}else if(verifyStatyJson.getJSONObject(0).getString("verification_status").equalsIgnoreCase("P")) {
						saveMap.put("status", "P");
					}else if(verifyStatyJson.getJSONObject(0).getString("verification_status").equalsIgnoreCase("R")) {
						saveMap.put("status", "R");
					} 

					//saveMap.put("pmjayID", cardRes.getString("pmjayID"));
				}else {
					PushCardDto cardDto = new PushCardDto();

					CopyUtility.copyProperties(applicationForm, cardDto, false);

					cardDto.setUid_auth_date(changeFormat.format(new Date()));


					cardDto.setGuid(cardDto.getGuid()+"_S"+cardDto.getState_code());
					cardDto.setDob_s(cardDto.getDob_s().substring(0,4));
					cardDto.setDob_d(cardDto.getDob_d().substring(0,4));

					String createCardResponse=	beneficiaryCreateCardService.pushCardRequestToBis(cardDto);

					logger.info("createCardResponse==="+createCardResponse);
					if(!ObjectUtils.isEmpty(createCardResponse)) {
						JSONObject cardRes= new JSONObject(createCardResponse);
						if(cardRes.getString("status").equalsIgnoreCase("Y")) {
							saveMap.put("pmjayID", cardRes.getString("pmjayID"));
							pmjayIdother=cardRes.getString("pmjayID");
						}else {
							saveMap.put("pmjayID", "N");
						}
					}else {
						saveMap.put("pmjayID", "N");
					}

					String 	Demoresponse=saveDemoDataService.saveDemoDeclareData(applicationForm.getGuid(),applicationForm.getName_d(),applicationForm.getGender_d(),
							applicationForm.getDob_d(),applicationForm.getPin_code_d(),applicationForm.getFather_name_d(),applicationForm.getMobile_d(),
							applicationForm.getUid_aadhaar(),
							applicationForm.getState_code(),applicationForm.getRuralUrbanFlag(),
							applicationForm.getDistrictCode(),applicationForm.getBlockCode(),
							applicationForm.getVillageCode(),
							applicationForm.getVtcCode(),applicationForm.getWardCode(),applicationForm.getUid_auth_type());
					logger.info("Demoresponse===="+Demoresponse);
					if(!ObjectUtils.isEmpty(Demoresponse)) {
						JSONObject demoRes= new JSONObject(Demoresponse);
						if(demoRes.getBoolean("status")) {
							saveMap.put("refId", demoRes.getString("refernceid"));
							saveMap.put("status", "Y");
						}else {
							saveMap.put("status", "N");
							saveMap.put("refId", demoRes.getString("refernceid"));
						}
					}else {
						saveMap.put("status", "N");
						saveMap.put("refId", applicationForm.getRefernceid());
					}
					String	responseSoc="";
					if(applicationForm.getSubSchemeId()!=null && !applicationForm.getSubSchemeId().equalsIgnoreCase("")) {
						responseSoc=saveSubSchemeDoc.saveSubScheme(applicationForm.getRuralUrbanFlag(),
							applicationForm.getState_code(),applicationForm.getDistrictCode(),
							applicationForm.getStateScheme(),applicationForm.getSubSchemeId(),
							applicationForm.getGuid(),applicationForm.getRefernceid(),
							applicationForm.getDocumentTypeId(),applicationForm.getPhoto(),userId);
					logger.info("response===="+responseSoc);
					if(!ObjectUtils.isEmpty(responseSoc)) {
						JSONObject guidJon= new JSONObject(responseSoc)	;

						if(guidJon.getString("status").equalsIgnoreCase("SUCCESS")) {
							saveMap.put("status", "Y");
						}else {
							saveMap.put("status", "N");
						}
						
						}
					}
					//applicationForm.getImageFile();
					byte[] byteArr = null;
					try {
						byteArr = applicationForm.getImageFile().getBytes();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String encodedfile = Base64.getEncoder().encodeToString(byteArr);
					String	response=saveKycDataService.saveKyc(applicationForm.getRefernceid(),applicationForm.getName_s(), 
							applicationForm.getFather_name_s().substring(0,3),applicationForm.getFather_name_s().substring(4),
							applicationForm.getGender_s(),applicationForm.getDob_s(),applicationForm.getAddress_s(),
							applicationForm.getState_name_s(),	applicationForm.getDistrict_name_s(), 
							applicationForm.getUid_auth_type(),
							userId,userType,applicationForm.getUid_token(),
							applicationForm.getStateScheme(),applicationForm.getRuralUrbanFlag(),applicationForm.getPhoto_s(),
							applicationForm.getState_code(),
							applicationForm.getDistrictCode(),applicationForm.getBlockCode(),applicationForm.getVillageCode(),
							applicationForm.getVtcCode(),applicationForm.getWardCode(),encodedfile,1);

					logger.info("response===="+response);
					if(!ObjectUtils.isEmpty(response)) {
						JSONObject demoRes= new JSONObject(response);
						if(demoRes.getBoolean("status")) {
							saveMap.put("refId", demoRes.getString("refernceid"));
							saveMap.put("status", "Y");

							benSaveCardStatusService.benSaveCardStatus(applicationForm.getRefernceid(),
									applicationForm.getState_code(), "96",applicationForm.getRuralUrbanFlag(),userId);


							if(beneficiarySeccForm.getSchemeid().equalsIgnoreCase("4")||beneficiarySeccForm.getSchemeid().equalsIgnoreCase("5")){

								if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {

									beneficiaryUpdateStatusService.beneficiaryUpdateStatus(applicationForm.getRefernceid(), 
											applicationForm.getState_code(),beneficiarySeccForm.getDistrictCodeRural(),beneficiarySeccForm.getBlockCodeRural(),
											beneficiarySeccForm.getVillageCodeRural(),"","","49", applicationForm.getRuralUrbanFlag(),userId);

								}else if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("U")) {
									beneficiaryUpdateStatusService.beneficiaryUpdateStatus(applicationForm.getRefernceid(), 
											applicationForm.getState_code(),beneficiarySeccForm.getDistrictCodeUrban(),
											beneficiarySeccForm.getTownCodeUrban(),
											beneficiarySeccForm.getWardCodeUrban(),"","","49", applicationForm.getRuralUrbanFlag(),userId);
								}

							}else if(beneficiarySeccForm.getSchemeid().equalsIgnoreCase("3")) {

								beneficiaryMmjaUpdateStatusService.beneficiaryUpdateStatus(applicationForm.getGuid(),
										applicationForm.getState_code(), "49", beneficiarySeccForm.getRuralUrbanFlag());
							}

							if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {
								beneficiarySaveSchemeDataService.benSchemeSave(beneficiarySeccForm.getStateCodeRural(), beneficiarySeccForm.getDistrictCodeRural(),
										beneficiarySeccForm.getSchemeName(), beneficiarySeccForm.getSchemeid(), beneficiarySeccForm.getAdcdRation(), applicationForm.getHhdid(),
										applicationForm.getGuid(), applicationForm.getRefernceid(), beneficiarySeccForm.getName(), beneficiarySeccForm.getFathername(),
										beneficiarySeccForm.getMothername(), applicationForm.getSpousenm(),
										String.valueOf(beneficiarySeccForm.getYear_of_birth()), beneficiarySeccForm.getGender(), 
										beneficiarySeccForm.getRelation(),
										datasource,apisource, userId, beneficiarySeccForm.getRuralUrbanFlag());
							}else if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("U")) {
								beneficiarySaveSchemeDataService.benSchemeSave(beneficiarySeccForm.getStateCodeUrban(), beneficiarySeccForm.getDistrictCodeUrban(),
										beneficiarySeccForm.getSchemeName(), beneficiarySeccForm.getSchemeid(), beneficiarySeccForm.getAdcdRation(), applicationForm.getHhdid(),
										applicationForm.getGuid(), applicationForm.getRefernceid(), beneficiarySeccForm.getName(), beneficiarySeccForm.getFathername(),
										beneficiarySeccForm.getMothername(), applicationForm.getSpousenm(),
										String.valueOf(beneficiarySeccForm.getYear_of_birth()), beneficiarySeccForm.getGender(), beneficiarySeccForm.getRelation(),
										datasource,apisource, userId, beneficiarySeccForm.getRuralUrbanFlag());

							}


							if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.RATION_CARD_SERVICE_ENABLE_LIST.split(",")).contains(applicationForm.getState_code())) {

								RationParam rPram= new RationParam();
								CopyUtility.copyProperties(applicationForm, rPram, false);
								rPram.setCreationby(userId);
								rPram.setGuid(applicationForm.getRefernceid());
								rPram.setStatus(0);

								rationCardDataService.saveRationData(rPram);
							}
							//Add save scheme card
							String stateCode="";
							String distCode="";
							if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {
								stateCode=beneficiarySeccForm.getStateCodeRural();
								distCode=beneficiarySeccForm.getDistrictCodeRural();
							}else if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("U")) {
								stateCode=beneficiarySeccForm.getStateCodeUrban();
								distCode=beneficiarySeccForm.getDistrictCodeUrban();
								
							}
							String responseCard="";
							if(applicationForm.getSchemeid().equalsIgnoreCase("2") ||applicationForm.getSchemeid().equalsIgnoreCase("6")) {
								responseCard=beneficiarySaveSchemeDataService.saveSchemeCard(stateCode,distCode,
									beneficiarySeccForm.getSchemeid(),nfsaMemId, beneficiarySeccForm.getGuid(),  userId, beneficiarySeccForm.getRuralUrbanFlag());
							}else {
								responseCard=beneficiarySaveSchemeDataService.saveSchemeCard(stateCode,distCode,
										beneficiarySeccForm.getSchemeid(),beneficiarySeccForm.getGuid() ,beneficiarySeccForm.getGuid(),userId, beneficiarySeccForm.getRuralUrbanFlag());
							}
							if(!ObjectUtils.isEmpty(responseCard)) {
								JSONObject resDecryptJsonRes = new JSONObject(responseCard);

								if (resDecryptJsonRes.getString("status").equalsIgnoreCase("SUCCESS")) {
									saveMap.put("refId", demoRes.getString("refernceid"));
									saveMap.put("status", "Y");
								}else {
									saveMap.put("status", "N");
									saveMap.put("refId", demoRes.getString("refernceid"));
								}
							}
							
							
							String uidenc=applicationForm.getUid_aadhaar()==null?"":Base64.getEncoder().encodeToString(applicationForm.getUid_aadhaar().getBytes());
														
							String responseCard1="";
							String ekyc=applicationForm.getUid_auth_type();
							if(applicationForm.getSchemeid().equalsIgnoreCase("2") ||applicationForm.getSchemeid().equalsIgnoreCase("6")) {
								responseCard1=beneficiarySaveSchemeDataService.saveSchemeCardUpdate(stateCode,distCode,
									beneficiarySeccForm.getSchemeid(),nfsaMemId, beneficiarySeccForm.getGuid(),  userId,
									beneficiarySeccForm.getRuralUrbanFlag(),applicationForm.getRefernceid(),applicationForm.getUid_token(),
									uidenc,Integer.parseInt(ekyc),49,0,0,applicationForm.getHhdid(),
									applicationForm.getMobile_member(),pmjayId,applicationForm.getName_d());
							}else {
								responseCard1=beneficiarySaveSchemeDataService.saveSchemeCardUpdate(stateCode,distCode,
										beneficiarySeccForm.getSchemeid(),beneficiarySeccForm.getGuid() ,beneficiarySeccForm.getGuid(),userId, 
										beneficiarySeccForm.getRuralUrbanFlag(),applicationForm.getRefernceid(),applicationForm.getUid_token(),
										uidenc,Integer.parseInt(ekyc),49,0,0,applicationForm.getHhdid(),
										applicationForm.getMobile_member(),pmjayIdother,applicationForm.getName_d());
							}
							if(!ObjectUtils.isEmpty(responseCard1)) {
								JSONObject resDecryptJsonRes = new JSONObject(responseCard1);

								if (resDecryptJsonRes.getString("status").equalsIgnoreCase("SUCCESS")) {
									saveMap.put("refId", demoRes.getString("refernceid"));
									saveMap.put("status", "Y");
								}else {
									saveMap.put("status", "N");
									saveMap.put("refId", demoRes.getString("refernceid"));
								}
							}
							
							logger.info("responseCard =="+responseCard);
							String smsSendRes =	smsSenderService.smsSender(applicationForm.getDemoMobile(),"1007163593307833597", 
									applicationForm.getDemoName().split(" ")[0],applicationForm.getRefernceid());

							logger.info("smsSendRes =="+smsSendRes);

						}else {
							saveMap.put("status", "N");
							saveMap.put("refId", demoRes.getString("refernceid"));
						}
					}else {
						saveMap.put("status", "N");
						saveMap.put("refId", applicationForm.getRefernceid());
					}

				}
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
}
