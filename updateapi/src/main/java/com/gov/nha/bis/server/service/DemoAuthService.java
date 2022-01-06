package com.gov.nha.bis.server.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import com.gov.nha.bis.server.dto.DemoDto;
import com.gov.nha.bis.server.model.DemoAuthEntity;
import com.gov.nha.bis.server.model.MapperDetailsEntity;

public interface DemoAuthService {

	public void saveDemoAuthEntity(DemoAuthEntity demoAuthEntity);
	public void saveDemoAuthEntity(DemoAuthEntity demoAuthEntity,MapperDetailsEntity mapperDetailsEntity);
	public List<DemoDto> getDemoAuthEntity(long referenceId , String stateCode,String urFlag,int status);
}
