/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import com.gov.nha.bis.server.dto.FaceDto;
import com.gov.nha.bis.server.redis.cache.NhaBisRedisUtil;
import com.gov.nha.bis.server.redis.cache.manager.FaceCacheManager;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@Configuration
public class FaceCacheManagerImpl implements FaceCacheManager {
	
	
    public NhaBisRedisUtil<FaceDto> redisUtilState;

	
	@Autowired
     public FaceCacheManagerImpl(NhaBisRedisUtil<FaceDto> redisUtilState) {
         this.redisUtilState = redisUtilState;
     }

	

	@Override
	public void cacheFaceId(String faceid, FaceDto faceDto) {
		// TODO Auto-generated method stub
		 redisUtilState.putValueWithExpireTime(faceid,faceDto,10, TimeUnit.MINUTES);
	}

	@Override
	public FaceDto getFaceId(String key) {
		// TODO Auto-generated method stub
		return redisUtilState.getValue(key);
	}

	@Override
	public boolean checkEmpty() {
		// TODO Auto-generated method stub
		if(ObjectUtils.isEmpty(redisUtilState))
			return true;
			else
			return false;
	}

	@Override
	public void deleteFaceId(String key, FaceDto faceDto) {
		// TODO Auto-generated method stub
		redisUtilState.putValueWithExpireTime(key,faceDto,1, TimeUnit.MILLISECONDS);
	}

}
