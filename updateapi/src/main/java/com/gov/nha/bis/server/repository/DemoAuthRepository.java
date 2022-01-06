package com.gov.nha.bis.server.repository;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gov.nha.bis.server.dto.DemoDto;
import com.gov.nha.bis.server.model.DemoAuthEntity;

public interface DemoAuthRepository extends CrudRepository<DemoAuthEntity, Long> {
	
	@Query("SELECT new com.gov.nha.bis.server.dto.DemoDto(R.refernceid,R.name,R.gender,R.dob,R.pncode,R.guardian,R.mobile,R.aadhaar,"
			+ "R.statecode,R.ruflag,R.status) FROM DemoAuthEntity"
			+ "  R WHERE R.refernceid=:refernceId and R.statecode=:stateCode and R.ruflag=:ruFlag and R.status=:status")
	public List<DemoDto> findDemoEntity(@Param("refernceId") Long refernceId,@Param("stateCode") String stateCode,
			@Param("ruFlag") String ruFlag, @Param("status") Integer status);

	
}
