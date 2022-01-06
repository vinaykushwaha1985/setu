package com.gov.nha.bis.server.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import com.gov.nha.bis.server.dto.RationDto;
import com.gov.nha.bis.server.model.RationCardEntity;
import com.gov.nha.bis.server.model.SchemeCardDetailsEntity;
import com.gov.nha.bis.server.model.StateSchemeDetailsEntity;

public interface StateSchemeService {

	Object saveStateSchemeDetail(StateSchemeDetailsEntity stateSchemeDetailsEntity);

	Object saveSchemeCardDetail(SchemeCardDetailsEntity schemeCardDetailsEntity);

	Object getSchemeCardDetail(SchemeCardDetailsEntity schemeCardDetailsEntity);

}
