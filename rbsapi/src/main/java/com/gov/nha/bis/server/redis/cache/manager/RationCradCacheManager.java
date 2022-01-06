/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager;

import java.util.List;

import com.gov.nha.bis.server.dto.RationCardDto;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public interface RationCradCacheManager {

	public void save(String tableKey,Long redisGuid ,RationCardDto dataDto);
	public List<RationCardDto> getList(String tableKey);
	public RationCardDto getDataDto(String tableKey,Long redisGuid);
	
	public Long update(String tableKey,Long redisGuid ,RationCardDto dataDto);



}
