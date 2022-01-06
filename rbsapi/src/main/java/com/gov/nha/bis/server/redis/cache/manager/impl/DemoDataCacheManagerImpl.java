/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.gov.nha.bis.server.dto.DemoDataDto;
import com.gov.nha.bis.server.redis.cache.NhaBisRedisUtil;
import com.gov.nha.bis.server.redis.cache.manager.DemoDataCacheManager;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@Configuration
public class DemoDataCacheManagerImpl implements DemoDataCacheManager {

 public NhaBisRedisUtil<DemoDataDto> redisUtilState;
	 
	 @Autowired
		public DemoDataCacheManagerImpl(NhaBisRedisUtil<DemoDataDto> redisUtilState) {
			this.redisUtilState = redisUtilState;
		}

	
	@Override
	public void save(String tableKey, Long redisGuid, DemoDataDto demoDataDto) {
		// TODO Auto-generated method stub
		redisUtilState.putMap(tableKey,redisGuid,demoDataDto);
	}

	@Override
	public List<DemoDataDto> getDemoList(String tableKey) {
		// TODO Auto-generated method stub
		return redisUtilState.getKeyValues(tableKey);
	}

	@Override
	public DemoDataDto getDemoDataDto(String tableKey, Long redisGuid) {
		// TODO Auto-generated method stub
		return redisUtilState.getMapAsSingleEntry(tableKey, redisGuid);
	}

	@Override
	public Long update(String tableKey, Long redisGuid, DemoDataDto demoDataDto) {
		Long updateId =	redisUtilState.deleteKeyValue(tableKey, redisGuid);
		redisUtilState.putMap(tableKey, redisGuid, demoDataDto);
		return updateId;
	}

}
