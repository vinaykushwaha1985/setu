/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.gov.nha.bis.server.dto.RationCardDto;
import com.gov.nha.bis.server.redis.cache.NhaBisRedisUtil;
import com.gov.nha.bis.server.redis.cache.manager.RationCradCacheManager;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@Configuration
public class RationCradCacheManagerImpl implements RationCradCacheManager {

	
	public NhaBisRedisUtil<RationCardDto> redisUtilState;
	 
	 @Autowired
		public RationCradCacheManagerImpl(NhaBisRedisUtil<RationCardDto> redisUtilState) {
			this.redisUtilState = redisUtilState;
		}

	
	@Override
	public void save(String tableKey, Long redisGuid, RationCardDto dataDto) {
		// TODO Auto-generated method stub
		redisUtilState.putMap(tableKey, redisGuid, dataDto);
	}

	@Override
	public List<RationCardDto> getList(String tableKey) {
		// TODO Auto-generated method stub
		return redisUtilState.getKeyValues(tableKey);
	}

	@Override
	public RationCardDto getDataDto(String tableKey, Long redisGuid) {
		// TODO Auto-generated method stub
		return redisUtilState.getMapAsSingleEntry(tableKey, redisGuid);
	}

	@Override
	public Long update(String tableKey, Long redisGuid, RationCardDto dataDto) {
		// TODO Auto-generated method stub
		Long updateId =	redisUtilState.deleteKeyValue(tableKey, redisGuid);
		redisUtilState.putMap(tableKey, redisGuid, dataDto);
		return updateId;
	}

}
