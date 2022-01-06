package com.gov.nha.bis.server.redis.cache.manager;

import com.gov.nha.bis.server.dto.StateDto;

public interface StateCacheManager {
	
	void cacheStateDetails(StateDto stateDto);

	boolean checkEmpty();

}
