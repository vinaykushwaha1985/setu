package com.gov.nha.bis.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gov.nha.bis.server.dto.StateSubSchemeDocumentDto;
import com.gov.nha.bis.server.model.StateSubSchemeDocumentEntity;



public interface StateSubSchemeDocumentRepository extends CrudRepository<StateSubSchemeDocumentEntity, Long> {
	
	@Query("SELECT distinct new com.gov.nha.bis.server.dto.StateSubSchemeDocumentDto(documentid ,documentname) FROM StateSubSchemeDocumentEntity  "
			+ "WHERE schemeid=:schemeid and subschemeid=:subschemeid and status=1 order by documentname asc")
	public List<StateSubSchemeDocumentDto> findSubSchemeDocumentDts(@Param("schemeid") String schemeid,@Param("subschemeid") String subschemeid);


}
