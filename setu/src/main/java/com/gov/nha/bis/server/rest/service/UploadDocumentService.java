package com.gov.nha.bis.server.rest.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.gov.nha.bis.server.dto.DocPhtParam;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class UploadDocumentService {


	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(UploadDocumentService.class);

	public String saveDocument(DocPhtParam seccParam) throws IllegalStateException, IOException {

	
		return requestSave(multipartToFile(seccParam.getDocFile(),StringUtils.cleanPath(seccParam.getDocFile().getOriginalFilename())),String.valueOf(seccParam.getStateCode()),
				String.valueOf(seccParam.getGuid()),String.valueOf(seccParam.getStatus()),seccParam.getRural_urban(), applicationConstantConfig.FEM_UPLOAD_DOCUMENT_URL);
	}


	
	
	public  String requestSave(File docFile,String stateCode,String guid, String status, String rural_urban ,
			String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{
			
			HttpHeaders headers = new HttpHeaders();
			HttpMethod requestMethod = HttpMethod.POST;


			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			
			
			MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
            ContentDisposition contentDisposition = ContentDisposition
                    .builder("form-data")
                    .name("docFile")
                    .filename(docFile.getName())
                    .build();

            fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
            HttpEntity<byte[]> fileEntity = new HttpEntity<>(Files.readAllBytes(docFile.toPath()), fileMap);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("docFile", fileEntity);
            body.add("rural_urban", rural_urban);
            body.add("stateCode", stateCode);
            body.add("guid", guid);
            body.add("status", status);
          
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, requestMethod, requestEntity, String.class);

            
            returnStr=response.getBody();

			
		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return returnStr;
	}


	public   File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
	    File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
	    multipart.transferTo(convFile);
	    return convFile;
	}






}
