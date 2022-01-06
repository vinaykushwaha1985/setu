/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager;

import java.util.List;

import com.gov.nha.bis.server.dto.UrbanDto;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public interface UrbanCacheManager {

	
	public void save(String tableKey,Long redisGuid ,UrbanDto urbanDto);
	public List<UrbanDto> getUrbanList(String tableKey);
	public UrbanDto getUrbanDto(String tableKey,Long redisGuid);
	
	public Long update(String tableKey,Long redisGuid ,UrbanDto urbanDto);
	

	


}
