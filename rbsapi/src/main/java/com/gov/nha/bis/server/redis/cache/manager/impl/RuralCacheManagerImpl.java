
package com.gov.nha.bis.server.redis.cache.manager.impl;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.gov.nha.bis.server.dto.RuralDto;
import com.gov.nha.bis.server.redis.cache.NhaBisRedisUtil;
import com.gov.nha.bis.server.redis.cache.manager.RuralCacheManager;


@Configuration
public class RuralCacheManagerImpl implements RuralCacheManager {
	
    public NhaBisRedisUtil<RuralDto> redisUtilState;
	
    
    @Autowired
    public RuralCacheManagerImpl(NhaBisRedisUtil<RuralDto> redisUtilState) {
        this.redisUtilState = redisUtilState;
    }
    
    
    @Override
    public void save(String tableKey,Long redisGuid ,RuralDto ruralDto){
   	 redisUtilState.putMap(tableKey,redisGuid,ruralDto);
    }


	@Override
	public List<RuralDto> getRuralList(String tableKey) {
		return redisUtilState.getKeyValues(tableKey);
	}

	@Override
	public RuralDto getRuralDto(String tableKey, Long redisGuid) {
		// TODO Auto-generated method stub
		return redisUtilState.getMapAsSingleEntry(tableKey, redisGuid);
	}


	@Override
	public Long update(String tableKey, Long redisGuid, RuralDto ruralDto) {
		// TODO Auto-generated method stub
		
		Long updateId =	redisUtilState.deleteKeyValue(tableKey, redisGuid);
	  	redisUtilState.putMap(tableKey, redisGuid, ruralDto);
	
		return updateId;
	}
    
    
    
		
}
