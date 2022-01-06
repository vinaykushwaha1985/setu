package com.gov.nha.bis.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gov.nha.bis.server.dto.BlockDto;
import com.gov.nha.bis.server.dto.DistrictDto;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.VillageDto;
import com.gov.nha.bis.server.model.RuralMasterEntity;

public interface RuralMasterRepository extends CrudRepository<RuralMasterEntity, Long> {
	
	@Query("SELECT distinct new com.gov.nha.bis.server.dto.StateDto(state_code ,state_name_english) FROM RuralMasterEntity where status=1 "
			+ "order by state_name_english asc")
	public List<StateDto> findState();
	
	@Query("SELECT distinct new com.gov.nha.bis.server.dto.DistrictDto(district_code ,district_name_english) FROM RuralMasterEntity "
			+ "WHERE district_code is not null and state_code=:stateCode and status=1 order by district_name_english asc")
	public List<DistrictDto> findDistrict(@Param("stateCode") Integer stateCode);
	
	
	@Query("SELECT distinct new com.gov.nha.bis.server.dto.BlockDto(block_code ,block_name_english) FROM RuralMasterEntity "
			+ "WHERE district_code is not null and block_code is not null and state_code=:stateCode "
			+ "and district_code=:districtCode and status=1 order by block_name_english asc")
	public List<BlockDto> findBlock(@Param("stateCode") Integer stateCode,@Param("districtCode") Integer districtCode);

	@Query("SELECT distinct new com.gov.nha.bis.server.dto.VillageDto(village_code ,village_name_english) FROM RuralMasterEntity "
			+ "WHERE district_code is not null and block_code is not null and village_code is not null "
			+ "and state_code=:stateCode and district_code=:districtCode and block_code=:blockCode and status=1 order by village_name_english asc")
	public List<VillageDto> findVillage(@Param("stateCode") Integer stateCode,@Param("districtCode") Integer districtCode,@Param("blockCode") Integer blockCode);


}
