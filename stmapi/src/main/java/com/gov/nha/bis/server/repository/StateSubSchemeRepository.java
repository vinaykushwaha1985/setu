package com.gov.nha.bis.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gov.nha.bis.server.dto.StateSubSchemeDto;
import com.gov.nha.bis.server.model.StateSubSchemeEntity;


public interface StateSubSchemeRepository extends CrudRepository<StateSubSchemeEntity, Long> {
	
	@Query("SELECT distinct new com.gov.nha.bis.server.dto.StateSubSchemeDto(subschemename ,subschemeid) FROM StateSubSchemeEntity  "
			+ "WHERE schemeid=:schemeid and state_code=:stateCode and status=1 order by subschemename asc")
	public List<StateSubSchemeDto> findSubSchemeDts(@Param("schemeid") String schemeid,@Param("stateCode") Integer stateCode);


}
