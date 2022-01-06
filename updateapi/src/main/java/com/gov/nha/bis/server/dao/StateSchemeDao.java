package com.gov.nha.bis.server.dao;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import com.gov.nha.bis.server.model.SchemeCardDetailsEntity;
import com.gov.nha.bis.server.model.StateSchemeDetailsEntity;


public interface StateSchemeDao {
	
public Object saveStateSchemeDetail(StateSchemeDetailsEntity stateSchemeDetailsEntity);

public Object saveSchemeCardDetail(SchemeCardDetailsEntity schemeCardDetailsEntity);

public Object getSchemeCardDetail(SchemeCardDetailsEntity schemeCardDetailsEntity);
	
	

}
