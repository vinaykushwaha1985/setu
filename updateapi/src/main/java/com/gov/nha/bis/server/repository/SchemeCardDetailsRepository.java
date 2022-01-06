package com.gov.nha.bis.server.repository;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gov.nha.bis.server.dto.SchemeCardDetailDto;
import com.gov.nha.bis.server.model.SchemeCardDetailsEntity;


public interface SchemeCardDetailsRepository extends CrudRepository<SchemeCardDetailsEntity, Long> {
	
	@Query("SELECT new com.gov.nha.bis.server.dto.SchemeCardDetailDto(R.rural_urban, R.state_code, R.district_code, R.beneficiaryid,"
			+ "R.schemeid,R.guid) FROM SchemeCardDetailsEntity R "
			+ "WHERE R.rural_urban=:rural_urban and R.state_code=:state_code and R.district_code=:district_code "
			+ "and R.schemeid=:schemeid and R.beneficiaryid=:beneficiaryid")
	public SchemeCardDetailDto findSchemeCardDetail(@Param("rural_urban") String rural_urban,@Param("state_code") Long state_code,
			@Param("district_code") String district_code, @Param("schemeid") String schemeid, @Param("beneficiaryid") String beneficiaryid);
	
}
