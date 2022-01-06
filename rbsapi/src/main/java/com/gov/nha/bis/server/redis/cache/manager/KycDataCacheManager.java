/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager;

import java.util.List;

import com.gov.nha.bis.server.dto.KycDataDto;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public interface KycDataCacheManager {
	public void save(String tableKey,Long redisGuid ,KycDataDto kycDataDto);
	public List<KycDataDto> getKycList(String tableKey);
	public KycDataDto getKycDataDto(String tableKey,Long redisGuid);
	
	public Long update(String tableKey,Long redisGuid ,KycDataDto kycDataDto);

}
