package com.gov.nha.bis.server.service;

public interface BisMasterService {

	Object getAllBank();

	Object getStateSchemeDetails(Integer stateCode);

	Object getStateSubSchemeDetails(String schemeId,Integer stateCode);

	Object getStateSubSchemeDocumentDetails(String schemeId, String subSchemeId);

}
