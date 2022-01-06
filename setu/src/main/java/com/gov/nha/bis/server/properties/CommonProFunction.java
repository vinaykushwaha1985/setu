package com.gov.nha.bis.server.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

public class CommonProFunction {

	private CommonProFunction commonProFunction;

	public synchronized CommonProFunction createAsaAuthPropertiesConstants() {
		if (commonProFunction == null) {
			commonProFunction = new CommonProFunction();
		}
		return commonProFunction;
	}

	public String RATION_CARD_SERVICE_ENABLE_LIST;
	public String SEARCH_SERVICE_REDIS_ENABLE_LIST;
	
	
	public CommonProFunction() {
		FileInputStream is = null;
		try {
			File preferencesFile = ResourceUtils.getFile("classpath:bsapi.properties");
			
			if (preferencesFile.exists()) {
				is = new FileInputStream(preferencesFile);
				Properties p = new Properties();
				p.load(is);

				RATION_CARD_SERVICE_ENABLE_LIST = (String) p.get("RATION_CARD_SERVICE_ENABLE_LIST");
				SEARCH_SERVICE_REDIS_ENABLE_LIST = (String) p.get("SEARCH_SERVICE_REDIS_ENABLE_LIST");
			

			}

		} catch (IOException ex) {

		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException ex) {

			}
		}
	}

}
