package com.gov.nha.bis.server.controller;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.BioKycService;
import com.gov.nha.bis.server.rest.service.FaceIdDetectService;

@Controller
public class AuthFacePhotoController extends NhaBisBaseController{



	private static final Logger logger = LogManager.getLogger(AuthFacePhotoController.class);

	@Autowired
	public BioKycService bioKycService;

	@Autowired
	public FaceIdDetectService faceIdDetectService;


	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;


	@RequestMapping(value="/authfacePhoto",method = RequestMethod.POST)
	public @ResponseBody String faceBasedKyc(
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		HashMap<String, String> kycMap = new  HashMap<String, String> ();
		String pidData="";
		String facePht="";
		String faceId="";
		HttpSession session=null;
		session=request.getSession(true);
		if(session.getAttribute("AUTH_FACEID")!=null){
			faceId =(String)session.getAttribute("AUTH_FACEID");
		}

		if(!ObjectUtils.isEmpty(faceId))	{	

			String faceIdData=	faceIdDetectService.getFaceId(faceId, applicationConstantConfig.FACE_ID_BARCODE_DETECT_URL);

			logger.info("auth faceIdData=="+faceIdData);

			if(!ObjectUtils.isEmpty(faceIdData)) {
				JSONObject faceRes= new JSONObject(faceIdData);

				if(faceRes.getBoolean("status")) {

					JSONObject faceJon =faceRes.getJSONObject("faceData");

					pidData=faceJon.getString("pidData");
					facePht=faceJon.getString("pht");

					kycMap.put("facePht", facePht);
					kycMap.put("pidData", pidData);
					kycMap.put("status", "Y");
				}else {
					kycMap.put("status", "N");
				}
			}else {
				kycMap.put("status", "N");
			}

		}else {
			kycMap.put("status", "N");
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
