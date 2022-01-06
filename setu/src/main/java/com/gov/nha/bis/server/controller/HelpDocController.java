package com.gov.nha.bis.server.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Controller
public class HelpDocController {

	private static final Logger logger = LogManager.getLogger(HelpDocController.class);


	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@GetMapping(value = "/helpUserDocument")
	public ResponseEntity<InputStreamResource> getTermsConditions() {


		try {
			String filePath =applicationConstantConfig.HELP_DOC_PATH;
			String fileName = applicationConstantConfig.HELP_DOC_NAME;
			File file = new File(filePath+fileName);
			HttpHeaders headers = new HttpHeaders();      
			headers.add("content-disposition", "inline;filename=" +fileName);

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					.headers(headers)
					.contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/pdf"))
					.body(resource);

		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
		}
		return null;
	}



}
