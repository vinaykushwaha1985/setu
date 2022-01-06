package com.gov.nha.bis.server.redis.cache.manager;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import com.gov.nha.bis.server.dto.RuralDto;

public interface RuralCacheManager {
	
	public void save(String tableKey,Long redisGuid ,RuralDto RuralDto);
	public List<RuralDto> getRuralList(String tableKey);
	public RuralDto getRuralDto(String tableKey,Long redisGuid);
	
	public Long update(String tableKey,Long redisGuid ,RuralDto RuralDto);
	

	
}
