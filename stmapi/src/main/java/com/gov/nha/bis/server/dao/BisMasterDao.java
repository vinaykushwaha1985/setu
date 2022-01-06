package com.gov.nha.bis.server.dao;

public interface BisMasterDao {

	Object getAllBank();

	Object getStateSchemeDetails(Integer stateCode);

	Object getStateSubSchemeDetails(String schemeId,Integer stateCode);

	Object getStateSubSchemeDocumentDetails(String schemeId, String subSchemeId);

}
