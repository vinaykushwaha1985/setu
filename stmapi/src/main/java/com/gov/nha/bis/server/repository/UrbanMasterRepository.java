package com.gov.nha.bis.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gov.nha.bis.server.dto.DistrictUrbanDto;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.TownDto;
import com.gov.nha.bis.server.dto.WardDto;
import com.gov.nha.bis.server.model.UrbanMasterEntity;

public interface UrbanMasterRepository extends CrudRepository<UrbanMasterEntity, Long> {
	
	@Query("SELECT distinct new com.gov.nha.bis.server.dto.StateDto(state_code ,state_name as state_name_english) "
			+ "FROM UrbanMasterEntity where status=1  order by state_name asc")
	public List<StateDto> findState();
	
	@Query("SELECT distinct new com.gov.nha.bis.server.dto.DistrictUrbanDto(district_code_lgd as district_code ,district_name_lgd as district_name_english) "
			+ "FROM UrbanMasterEntity WHERE district_code_lgd is not null and district_name_lgd is not null "
			+ "and state_code=:stateCode and status=1 order by district_name_lgd asc")
	public List<DistrictUrbanDto> findDistrict(@Param("stateCode") Integer stateCode);
	
	@Query("SELECT distinct new com.gov.nha.bis.server.dto.TownDto(vt_code as town_code ,vt_name as town_name) FROM UrbanMasterEntity "
			+ "WHERE district_code_lgd is not null and district_name_lgd is not null and vt_code is not null "
			+ "and state_code=:stateCode and district_code_lgd=:districtCode and status=1 order by vt_name asc")
	public List<TownDto> findTown(@Param("stateCode") Integer stateCode,@Param("districtCode") String districtCode);

	@Query("SELECT distinct new com.gov.nha.bis.server.dto.WardDto(ward_code ,ward_code as ward_name) FROM UrbanMasterEntity "
			+ "WHERE district_code_lgd is not null and district_name_lgd is not null and vt_code is not null and ward_code is not null "
			+ "and state_code=:stateCode and district_code_lgd=:districtCode and vt_code=:townCode and status=1 order by ward_code asc")
	public List<WardDto> findWard(@Param("stateCode") Integer stateCode,@Param("districtCode") String districtCode,@Param("townCode") String townCode);


}
