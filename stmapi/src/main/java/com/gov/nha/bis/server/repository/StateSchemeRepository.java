package com.gov.nha.bis.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gov.nha.bis.server.dto.StateSchemeDto;
import com.gov.nha.bis.server.model.StateSchemeEntity;


public interface StateSchemeRepository extends CrudRepository<StateSchemeEntity, Long> {
	
	/*
	 * @Query("SELECT distinct new com.gov.nha.bis.server.dto.StateSchemeDto(schemeid ,schemename,subschemeflag) FROM StateSchemeEntity "
	 * + "WHERE state_code=:state_code and status=1 order by schemename asc") public
	 * List<StateSchemeDto> findStateScheme(@Param("state_code") Integer
	 * state_code);
	 */
	@Query("SELECT distinct new com.gov.nha.bis.server.dto.StateSchemeDto(schemeid ,schemename) FROM StateSchemeEntity "
			+ "WHERE state_code=:state_code and status=1 order by schemename asc")
	public List<StateSchemeDto> findStateScheme(@Param("state_code") Integer state_code);


}
