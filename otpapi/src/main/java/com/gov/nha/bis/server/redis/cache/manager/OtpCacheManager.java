package com.gov.nha.bis.server.redis.cache.manager;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import com.gov.nha.bis.server.dto.SmsOtpDto;

public interface OtpCacheManager {
	
	public void cacheStateDetails(String txn,SmsOtpDto smsOtpDto);
	public SmsOtpDto getStateDetails(String key);
	
	public boolean checkEmpty();
	public void deleteOtp(String key,SmsOtpDto smsOtpDto);
	
	

}
