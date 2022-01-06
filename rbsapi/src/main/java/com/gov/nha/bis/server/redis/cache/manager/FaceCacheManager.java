/**
 * 
 */
package com.gov.nha.bis.server.redis.cache.manager;

import com.gov.nha.bis.server.dto.FaceDto;


/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public interface FaceCacheManager {
	
	public void cacheFaceId(String faceid,FaceDto faceDto);
	public FaceDto getFaceId(String key);
	
	public boolean checkEmpty();
	public void deleteFaceId(String key,FaceDto faceDto);

}
