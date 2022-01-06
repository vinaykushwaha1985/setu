/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.gov.nha.bis.server.dto.UrbanDto;
import com.gov.nha.bis.server.redis.cache.NhaBisRedisUtil;
import com.gov.nha.bis.server.redis.cache.manager.UrbanCacheManager;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@Configuration
public class UrbanCacheManagerImpl implements UrbanCacheManager{

	public NhaBisRedisUtil<UrbanDto> redisUtilState;


	@Autowired
	public UrbanCacheManagerImpl(NhaBisRedisUtil<UrbanDto> redisUtilState) {
		this.redisUtilState = redisUtilState;
	}


	@Override
	public void save(String tableKey, Long redisGuid, UrbanDto urbanDto) {
		// TODO Auto-generated method stub
		redisUtilState.putMap(tableKey,redisGuid,urbanDto);
	}


	@Override
	public List<UrbanDto> getUrbanList(String tableKey) {
		// TODO Auto-generated method stub
		return redisUtilState.getKeyValues(tableKey);
	}


	@Override
	public UrbanDto getUrbanDto(String tableKey, Long redisGuid) {
		// TODO Auto-generated method stub
		return redisUtilState.getMapAsSingleEntry(tableKey, redisGuid);
	}


	@Override
	public Long update(String tableKey, Long redisGuid, UrbanDto urbanDto) {
		// TODO Auto-generated method stub
		Long updateId =	redisUtilState.deleteKeyValue(tableKey, redisGuid);
		redisUtilState.putMap(tableKey, redisGuid, urbanDto);
		return updateId;
	}

}
