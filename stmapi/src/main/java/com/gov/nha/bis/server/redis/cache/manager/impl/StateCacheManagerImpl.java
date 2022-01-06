package com.gov.nha.bis.server.redis.cache.manager.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.redis.cache.NhaBisRedisUtil;
import com.gov.nha.bis.server.redis.cache.manager.StateCacheManager;

@Configuration
public class StateCacheManagerImpl implements StateCacheManager {
	
	public static final String TABLE_STATE = "TABLE_STATE";
    public static final String STATE = "STATE_";
    private NhaBisRedisUtil<StateDto> redisUtilState;

	
	@Autowired
     public StateCacheManagerImpl(NhaBisRedisUtil<StateDto> redisUtilState) {
         this.redisUtilState = redisUtilState;
     }
     @Override
     public void cacheStateDetails(StateDto stateDto){
    	 redisUtilState.putMap(TABLE_STATE,STATE+stateDto.getState_code(),stateDto);
    	 redisUtilState.setExpire(TABLE_STATE,1,TimeUnit.HOURS);
     }
	@SuppressWarnings("deprecation")
	@Override
	public boolean checkEmpty() {
		if(StringUtils.isEmpty(redisUtilState))
		return true;
		else
		return false;
	}

}
