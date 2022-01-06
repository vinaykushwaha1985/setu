package com.gov.nha.bis.server.repository;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gov.nha.bis.server.dto.RationDto;
import com.gov.nha.bis.server.model.RationCardEntity;

public interface RationCardRepository extends CrudRepository<RationCardEntity, Long>{
	
	@Query("SELECT new com.gov.nha.bis.server.dto.RationDto(R.guid,R.family_id,R.family_mem_id,R.member_name_eng,"
			+ "R.mother_name_eng,R.father_name_eng,R.spouse_name_eng,R.relation_name,R.gender,"
			+ "R.year_of_birth,R.district_lgd_code,R.state_lgd_code,R.subdistrict_lgd_code,"
			+ "R.village_town_lgd_code,R.pincode,R.rural_urban,R.creationby,"
			+ "	R.status) FROM RationCardEntity"
			+ "  R WHERE R.guid=:refernceId and R.state_lgd_code=:stateCode and R.rural_urban=:ruFlag and R.status=:status")
	public List<RationDto> findRationCardEntity(@Param("refernceId") Long refernceId,@Param("stateCode") Integer stateCode,
			@Param("ruFlag") String ruFlag, @Param("status") Integer status);

}
