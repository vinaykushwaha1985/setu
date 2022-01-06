/**
 * 
 */
package com.gov.nha.bis.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gov.nha.bis.server.dto.FaceDto;
import com.gov.nha.bis.server.redis.cache.manager.FaceCacheManager;
import com.gov.nha.bis.server.response.FaceCacheServiceResponse;
import com.gov.nha.bis.server.util.TransactionManager;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@RestController
@RequestMapping("/bis")
@CrossOrigin
public class FaceCacheController {
	
	@Autowired
	public FaceCacheManager faceCacheManager;
	
	private static final Logger logger = LoggerFactory.getLogger(AddFamilySeccController.class);
	
	@PostMapping(value ="/face/data/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String storeFaceId(@RequestBody  FaceDto  reqParam) {

		try {
			
			logger.info("reqParam.getFaceid()=="+reqParam.getFaceid());
			
			logger.info("reqParam.getPht()=="+reqParam.getPht());
			
			
			
			logger.info("reqParam.getPidData()"+reqParam.getPidData());
			
			
			logger.info("reqParam.getUserid()"+reqParam.getUserid());
			
			
			if(!ObjectUtils.isEmpty(reqParam.getFaceid())
					&& !ObjectUtils.isEmpty(reqParam.getPht()) 
				//	&& !ObjectUtils.isEmpty(reqParam.getUserid())
					&& !ObjectUtils.isEmpty(reqParam.getPidData())) {
				
				logger.info("reqParam.getFaceid()=="+reqParam.getFaceid());
				
				logger.info("reqParam.getPht()=="+reqParam.getPht());
				
				
				
				logger.info("reqParam.getPidData()"+reqParam.getPidData());
				
				
				logger.info("reqParam.getUserid()"+reqParam.getUserid());
				
				
				faceCacheManager.cacheFaceId(reqParam.getFaceid(), reqParam);
				
					return FaceCacheServiceResponse.faceServiceResponse(TransactionManager.getTransactionId(), true, "", "");
			}
		}
		catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
		}
		
		return FaceCacheServiceResponse.faceServiceResponse(TransactionManager.getTransactionId(), false, "", "");
	}
	
	@PostMapping(value ="/face/fetch/1.0" , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getFaceId(@RequestBody  FaceDto  reqParam) {
		FaceDto faceDto= new FaceDto();
		try {
			
			if(!ObjectUtils.isEmpty(reqParam.getFaceid())) {
				logger.info("reqParam.getFaceid()=="+reqParam.getFaceid());
				
				faceDto=faceCacheManager.getFaceId(reqParam.getFaceid());
				if(!ObjectUtils.isEmpty(faceDto)) {
					return FaceCacheServiceResponse.faceDtoResponse(faceDto, reqParam.getFaceid(), true, "", "");
				}
			}
		}
		catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
		}
		
		return FaceCacheServiceResponse.faceDtoResponse(faceDto, reqParam.getFaceid(), false, "Face Id not Exist or Expired", "");
	}
	


}
