package com.gov.nha.bis.server.global.function;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;

public class StateApiTokenFunction {
	
	private static final Logger logger = LogManager.getLogger(StateApiTokenFunction.class);
	
	
	public static String getStateToken(int stateCode, String stTokenValue ) {
	
		try {
			
			switch(stateCode) {
				case 24:
					return getS24Token(stTokenValue);
			
				case 9:
					return getS9Token(stTokenValue);
			
			}
			
			
		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
		}
		
		return null;
		
	}

	private static String getS24Token(String stTokenValue) {
		String returnToken="" ;
		
		try {

			 GregorianCalendar gcal = new GregorianCalendar();
			 SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMYYYYHHmmss");
			 String date = DATE_FORMAT.format(gcal.getTime());
		     String token=stTokenValue+date;
		     
		     returnToken=new String(Base64.encode(token.getBytes()));
		    
		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
		}
		
		
		return returnToken;
	}
	
	private static String getS9Token(String stTokenValue) {
		String returnToken="" ;
		
		try {
		     returnToken=stTokenValue;
		    
		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
		}
		
		
		return returnToken;
	}
	

}
