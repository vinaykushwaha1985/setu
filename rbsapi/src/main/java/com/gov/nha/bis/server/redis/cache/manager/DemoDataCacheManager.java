/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager;

import java.util.List;

import com.gov.nha.bis.server.dto.DemoDataDto;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public interface DemoDataCacheManager {

	public void save(String tableKey,Long redisGuid ,DemoDataDto demoDataDto);
	public List<DemoDataDto> getDemoList(String tableKey);
	public DemoDataDto getDemoDataDto(String tableKey,Long redisGuid);
	
	public Long update(String tableKey,Long redisGuid ,DemoDataDto demoDataDto);



}
