/**
 * 
 */
package com.gov.nha.bis.server.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.gov.nha.bis.server.dto.DocPhtParam;
import com.gov.nha.bis.server.dto.PushCardDto;
import com.gov.nha.bis.server.dto.RationParam;
import com.gov.nha.bis.server.dto.SeccParam;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiarySeccForm;
import com.gov.nha.bis.server.model.RuralEntity;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.BenSaveCardStatusService;
import com.gov.nha.bis.server.rest.service.BeneficiaryCreateCardService;
import com.gov.nha.bis.server.rest.service.BeneficiaryFamilyDetailService;
import com.gov.nha.bis.server.rest.service.BeneficiaryGuidService;
import com.gov.nha.bis.server.rest.service.BeneficiaryUpdateStatusService;
import com.gov.nha.bis.server.rest.service.RationCardDataService;
import com.gov.nha.bis.server.rest.service.SaveDemoDataService;
import com.gov.nha.bis.server.rest.service.SaveKycDataService;
import com.gov.nha.bis.server.rest.service.SaveSeccFamilyDataService;
import com.gov.nha.bis.server.rest.service.SmsSenderService;
import com.gov.nha.bis.server.rest.service.TxnSaveDataService;
import com.gov.nha.bis.server.rest.service.UploadDocumentService;
import com.gov.nha.bis.server.rest.service.VerifyCardStatusService;
import com.gov.nha.bis.server.util.CheckRedisEnbleFunction;
import com.gov.nha.bis.server.util.CopyUtility;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@CrossOrigin
@Controller
public class BeneficiaryFamilyDetailsController {

	private static final Logger logger = LogManager.getLogger(BeneficiaryFamilyDetailsController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	@Autowired
	public SaveKycDataService saveKycDataService;
	@Autowired
	public SaveDemoDataService saveDemoDataService;
	@Autowired
	public TxnSaveDataService txnSaveDataService;

	@Autowired
	public BeneficiaryUpdateStatusService beneficiaryUpdateStatusService;
	@Autowired
	public BeneficiaryCreateCardService beneficiaryCreateCardService;

	@Autowired
	public VerifyCardStatusService  verifyCardStatusService;

	@Autowired
	public RationCardDataService rationCardDataService;

	@Autowired
	public BenSaveCardStatusService benSaveCardStatusService;

	@Autowired
	public BeneficiaryGuidService beneficiaryGuidService;

	@Autowired
	public SaveSeccFamilyDataService saveSeccFamilyDataService;
	
	@Autowired
	public UploadDocumentService uploadDocumentService;
	@Autowired
	public SmsSenderService smsSenderService;
	
	@Autowired
	public AppHistoryService appHistoryService;


	@RequestMapping(value="/benFemDetails",method = RequestMethod.POST)
	public ModelAndView beneficiaryFamilyPost(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{	
		List<RuralEntity> hhddatalist= null;
		String hhd_no="";
		HttpSession session= null;
		String userId="";
		String roleId="";
		String userType="";
		String stateCode="";
		String districtCode="";
		String ip="";
		
		session =request.getSession(true);

		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		beneficiarySeccForm=(BeneficiarySeccForm) request.getSession(true).getAttribute("beneficiarySeccForm");
		
		
		
		if(session.getAttribute("USERID")!=null) {
			userId=(String)session.getAttribute("USERID");
		}if(session.getAttribute("userType")!=null) {
			userType=(String)session.getAttribute("userType");
		}if(session.getAttribute("roleId")!=null) {
			roleId=(String)session.getAttribute("roleId");
		}if(session.getAttribute("IP")!=null) {
			ip=(String)session.getAttribute("IP");
		}	
			
		
		
		
		if(!ObjectUtils.isEmpty(beneficiarySeccForm.getGuid())) {

			if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {
				stateCode=beneficiarySeccForm.getStateCodeRural();
				districtCode=beneficiarySeccForm.getDistrictCodeRural();

				String guidRes=	beneficiaryGuidService.getRuralGuidData(beneficiarySeccForm.getGuid(), beneficiarySeccForm.getStateCodeRural(),
						beneficiarySeccForm.getDistrictCodeRural(), beneficiarySeccForm.getBlockCodeRural(), beneficiarySeccForm.getVillageCodeRural(), beneficiarySeccForm.getRuralUrbanFlag());
				if(!ObjectUtils.isEmpty(guidRes)) {
					JSONObject guidJson=new JSONObject(guidRes);
					if(guidJson.getBoolean("status")) {
						JSONObject benJson=guidJson.getJSONObject("beneficiary");
						hhd_no=benJson.getString("hhd_no");
						hhddatalist=  BeneficiaryFamilyDetailService.beneficiaryFamilyHhdData(beneficiarySeccForm.getStateCodeRural(), 
								beneficiarySeccForm.getRuralUrbanFlag(), hhd_no, applicationConstantConfig.BIS_GET_HHID_DATA_URL);

					}
				}
			}else if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("U")) {
				
				stateCode=beneficiarySeccForm.getStateCodeUrban();
				districtCode=beneficiarySeccForm.getDistrictCodeUrban();
				
				String guidRes=	beneficiaryGuidService.getUrbanGuidData(beneficiarySeccForm.getGuid(), beneficiarySeccForm.getStateCodeUrban(),
						beneficiarySeccForm.getDistrictCodeUrban(), beneficiarySeccForm.getTownCodeUrban(), beneficiarySeccForm.getWardCodeUrban(), beneficiarySeccForm.getRuralUrbanFlag());
				if(!ObjectUtils.isEmpty(guidRes)) {
					JSONObject guidJson=new JSONObject(guidRes);
					if(guidJson.getBoolean("status")) {
						JSONObject benJson=guidJson.getJSONObject("beneficiary");
						hhd_no=benJson.getString("hhd_no");
						hhddatalist=  BeneficiaryFamilyDetailService.beneficiaryFamilyHhdData(beneficiarySeccForm.getStateCodeUrban(), 
								beneficiarySeccForm.getRuralUrbanFlag(), hhd_no, applicationConstantConfig.BIS_GET_HHID_DATA_URL);
					}

				}
			}

		}
		String name="";
		if(session.getAttribute("USERNAME")!=null) {
			name=(String)session.getAttribute("USERNAME");
		}
		appHistoryService.saveEventDetails(Integer.valueOf(stateCode), districtCode,beneficiarySeccForm.getRuralUrbanFlag(),userId,
				userId,userType,roleId,"BENEFICIARY_ADD_MEMBER",ip,name);

		request.getSession(true).setAttribute("hhd_no", hhd_no);
		request.getSession(true).setAttribute("hhddatalist", hhddatalist);
		request.getSession(true).setAttribute("viewAction", "Y");
		//model.addAllAttributes(hhddatalist);
		//return new ModelAndView("beneficiaryAddFamilyMemeber", "command",hhddatalist);
		ModelAndView modellist = new ModelAndView();

		modellist.setViewName("beneficiaryAddFamilyMemeber");
		modellist.addObject("hhddatalist", hhddatalist);
		return modellist;
	}


	@RequestMapping(value="/beneficiaryAddFamilyMemeber",method = RequestMethod.POST)
	public ModelAndView beneficiaryFamilyGet(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
		CopyUtility.copyProperties(applicationForm, beneficiarySeccForm, false);
		//request.getSession(true).setAttribute("beneficiarySeccForm", beneficiarySeccForm);
		beneficiarySeccForm=(BeneficiarySeccForm) request.getSession(true).getAttribute("beneficiarySeccForm");
		System.out.println(beneficiarySeccForm);
		request.getSession(true).setAttribute("viewAction", "Y");

		return new ModelAndView("beneficiaryAddFamilyMemeber", "command",applicationForm);
	}

	@RequestMapping(value="/updateFamilyDetails",method = RequestMethod.POST)
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
		beneficiarySeccForm=(BeneficiarySeccForm) request.getSession(true).getAttribute("beneficiarySeccForm");
		List<RuralEntity> hhddatalist= null;
		List<Long> memGuid= new ArrayList<Long>();
		Long familyGuid=null;
		try {

			String userId=(String)request.getSession(true).getAttribute("USERID");
			if(request.getSession(true).getAttribute("userType")!=null)
				userType=(String)request.getSession(true).getAttribute("userType");

			if(request.getSession(true).getAttribute("beneficiarySeccForm")!=null)
				beneficiarySeccForm=(BeneficiarySeccForm)request.getSession(true).getAttribute("beneficiarySeccForm");

			if(request.getSession(true).getAttribute("hhddatalist")!=null) {
				hhddatalist=(List<RuralEntity>)request.getSession(true).getAttribute("hhddatalist");

				if(!ObjectUtils.isEmpty(hhddatalist)) {
					for(RuralEntity ent:hhddatalist) {
						if(applicationForm.getGuid().contains(String.valueOf(ent.getGuid())))
							memGuid.add(ent.getGuid());
					}
				}

			}
			if(!ObjectUtils.isEmpty(memGuid)) {
				if(memGuid.size()==1) {
					familyGuid=Long.valueOf(applicationForm.getGuid()+"001");
				}else {	
					Long maxfGuid=Collections.max(memGuid); 
					familyGuid=maxfGuid+1;
				}
			}

			List<String> guidList = new ArrayList<String>();
			guidList.add(String.valueOf(familyGuid));

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
					applicationForm.setRural_urban(applicationForm.getRuralUrbanFlag().replaceAll(",", ""));
					applicationForm.setRuralUrbanFlag(applicationForm.getRuralUrbanFlag().replaceAll(",", ""));

					SeccParam SeccParam=getSeccParam(applicationForm,familyGuid);

					String seccRes=		saveSeccFamilyDataService.saveSecc(SeccParam);

					if(!ObjectUtils.isEmpty(seccRes)) {
						JSONObject seccJson= new JSONObject(seccRes);

						if(seccJson.getBoolean("status")) {
							DocPhtParam docPram= new DocPhtParam();
							
							docPram.setDocFile(applicationForm.getDocFile());
							docPram.setGuid(familyGuid);
							docPram.setStateCode(Integer.valueOf(applicationForm.getState_code()));
							docPram.setStatus(0);
							docPram.setRural_urban(applicationForm.getRuralUrbanFlag());
							
							
						String docRes=	uploadDocumentService.saveDocument(docPram);
							
						logger.info("docRes==="+docRes);	

							PushCardDto cardDto = new PushCardDto();

							CopyUtility.copyProperties(applicationForm, cardDto, false);

							cardDto.setUid_auth_date(changeFormat.format(new Date()));


							cardDto.setGuid(String.valueOf(familyGuid)+"_S"+cardDto.getState_code());
							cardDto.setDob_s(cardDto.getDob_s().substring(0,4));
							cardDto.setDob_d(cardDto.getDob_d().substring(0,4));

							String createCardResponse=	beneficiaryCreateCardService.pushCardRequestToBis(cardDto);

							logger.info("createCardResponse==="+createCardResponse);
							if(!ObjectUtils.isEmpty(createCardResponse)) {
								JSONObject cardRes= new JSONObject(createCardResponse);
								if(cardRes.getString("status").equalsIgnoreCase("Y")) {
									saveMap.put("pmjayID", cardRes.getString("pmjayID"));
								}else {
									saveMap.put("pmjayID", "N");
								}
							}else {
								saveMap.put("pmjayID", "N");
							}

							String	response=saveKycDataService.saveKyc(String.valueOf(familyGuid),applicationForm.getName_s(), 
									applicationForm.getFather_name_s().substring(0,3),applicationForm.getFather_name_s().substring(4),
									applicationForm.getGender_s(),applicationForm.getDob_s(),applicationForm.getAddress_s(),
									applicationForm.getState_name_s(),	applicationForm.getDistrict_name_s(), 
									applicationForm.getUid_auth_type(),
									userId,userType,applicationForm.getUid_token(),
									"1",applicationForm.getRuralUrbanFlag(),applicationForm.getPhoto_s(),
									applicationForm.getState_code(),
									applicationForm.getDistrictCode(),applicationForm.getBlockCode(),applicationForm.getVillageCode(),
									applicationForm.getVtcCode(),applicationForm.getWardCode(),"",1);

							logger.info("response===="+response);
							if(!ObjectUtils.isEmpty(response)) {
								JSONObject demoRes= new JSONObject(response);
								if(demoRes.getBoolean("status")) {
									saveMap.put("refId", demoRes.getString("refernceid"));
									saveMap.put("status", "Y");

									benSaveCardStatusService.benSaveCardStatus(String.valueOf(familyGuid),
											applicationForm.getState_code(), "96",applicationForm.getRuralUrbanFlag(),userId);


									if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {

										beneficiaryUpdateStatusService.beneficiaryUpdateStatus(String.valueOf(familyGuid), 
												applicationForm.getState_code(),beneficiarySeccForm.getDistrictCodeRural(),beneficiarySeccForm.getBlockCodeRural(),
												beneficiarySeccForm.getVillageCodeRural(),"","","49", applicationForm.getRuralUrbanFlag(),userId);

									}else if(beneficiarySeccForm.getRuralUrbanFlag().equalsIgnoreCase("U")) {
										beneficiaryUpdateStatusService.beneficiaryUpdateStatus(String.valueOf(familyGuid), 
												applicationForm.getState_code(),beneficiarySeccForm.getDistrictCodeUrban(),
												beneficiarySeccForm.getTownCodeUrban(),
												beneficiarySeccForm.getWardCodeUrban(),"","","49", applicationForm.getRuralUrbanFlag(),userId);
									}
									if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.RATION_CARD_SERVICE_ENABLE_LIST.split(",")).contains(applicationForm.getState_code())) {

										RationParam rPram= new RationParam();
										CopyUtility.copyProperties(applicationForm, rPram, false);
										rPram.setCreationby(userId);
										rPram.setGuid(String.valueOf(familyGuid));
										rPram.setStatus(0);

										rationCardDataService.saveRationData(rPram);
									}

							String smsSendRes=		smsSenderService.smsSender(applicationForm.getDemoMobile(),"1007163593317209694", 
											applicationForm.getDemoName().split(" ")[0],String.valueOf(familyGuid));
							
							logger.info("smsSendRes =="+smsSendRes);	
							
								}else {
									saveMap.put("status", "N");
									saveMap.put("refId", demoRes.getString("refernceid"));
								}
							}else {
								saveMap.put("status", "N");
								saveMap.put("refId", String.valueOf(familyGuid));
							}


						}
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


	private SeccParam getSeccParam(ApplicationForm applicationForm, Long familyGuid) {
		// TODO Auto-generated method stub
		SeccParam seccParam =new SeccParam();


		seccParam.setAdd_family_flag("A"); 
		seccParam.setCard_status(0); 
		seccParam.setHhd_no(applicationForm.getHhd_no().replace(",", ""));

		seccParam.setRural_urban(applicationForm.getRuralUrbanFlag());

		seccParam.setStatecode(applicationForm.getState_code()); 
		seccParam.setDistrictcode(applicationForm.getDistrictCode()); 

		seccParam.setState_code(Integer.valueOf(applicationForm.getState_code())); 
		seccParam.setDistrict_code(applicationForm.getDistrictCode());
		seccParam.setState_name_english(applicationForm.getStateName().replaceAll(",", ""));
		seccParam.setDistrict_name_english(applicationForm.getDistrictName().replaceAll(",", "")); 
		seccParam.setBlock_name_english(applicationForm.getBlockName().replaceAll(",", ""));
		seccParam.setVillage_name_english(applicationForm.getVillageName());
		if(applicationForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {
			seccParam.setBlock_code(Integer.valueOf(applicationForm.getBlockCode()));
			seccParam.setVillage_code(applicationForm.getVillageCode());
		}else if(applicationForm.getRuralUrbanFlag().equalsIgnoreCase("R")) {
			seccParam.setTowncode(applicationForm.getVtcCode()); 
			seccParam.setWardid(applicationForm.getWardCode()); 
		}


		seccParam.setTehsilcode(""); 
		seccParam.setTowncode_mdds(""); 
		seccParam.setAhlblockno(""); 
		seccParam.setAhlsubblockno(""); 
		seccParam.setAhlslnohhd("");
		seccParam.setSlnomember(""); 
		seccParam.setGrampanchayatname(""); 

		seccParam.setname(applicationForm.getName_d()); 
		seccParam.setFathername(applicationForm.getFather_name_d());
		seccParam.setMothername(applicationForm.getMother_name_eng()); 

		seccParam.setRelation(applicationForm.getRelation());
		seccParam.setDob(applicationForm.getDob_d()); 
		seccParam.setGenderid((applicationForm.getGender_d().equalsIgnoreCase("M")?"1":applicationForm.getGender_d().equalsIgnoreCase("F")?"2":"0")); 

		if(applicationForm.getRelation().equalsIgnoreCase("SPOUSE"))
			seccParam.setSpousenm(applicationForm.getPeName());

		seccParam.setAddressline1(applicationForm.getAddress_s().replace(",", "")); 


		seccParam.setPincode(applicationForm.getDemoPincode());

		seccParam.setRation_card_no(applicationForm.getRationCard());


		seccParam.setName(applicationForm.getName_d());
		seccParam.setGuid(BigInteger.valueOf(familyGuid));


		return seccParam;
	}

}
