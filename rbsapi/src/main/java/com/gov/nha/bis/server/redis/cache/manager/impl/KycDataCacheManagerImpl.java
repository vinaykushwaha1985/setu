/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.gov.nha.bis.server.dto.KycDataDto;
import com.gov.nha.bis.server.redis.cache.NhaBisRedisUtil;
import com.gov.nha.bis.server.redis.cache.manager.KycDataCacheManager;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@Configuration
public class KycDataCacheManagerImpl implements KycDataCacheManager {
	
	 public NhaBisRedisUtil<KycDataDto> redisUtilState;
	 
	 @Autowired
		public KycDataCacheManagerImpl(NhaBisRedisUtil<KycDataDto> redisUtilState) {
			this.redisUtilState = redisUtilState;
		}


	@Override
	public void save(String tableKey, Long redisGuid, KycDataDto kycDataDto) {
		redisUtilState.putMap(tableKey,redisGuid,kycDataDto);
		
	}

	@Override
	public List<KycDataDto> getKycList(String tableKey) {
		// TODO Auto-generated method stub
		return redisUtilState.getKeyValues(tableKey);
	}

	@Override
	public KycDataDto getKycDataDto(String tableKey, Long redisGuid) {
		// TODO Auto-generated method stub
		return redisUtilState.getMapAsSingleEntry(tableKey, redisGuid);
	}

	@Override
	public Long update(String tableKey, Long redisGuid, KycDataDto kycDataDto) {
		// TODO Auto-generated method stub
		Long updateId =	redisUtilState.deleteKeyValue(tableKey, redisGuid);
		redisUtilState.putMap(tableKey, redisGuid, kycDataDto);
		return updateId;
	}

}
