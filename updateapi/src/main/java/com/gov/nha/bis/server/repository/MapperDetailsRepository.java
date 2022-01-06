package com.gov.nha.bis.server.repository;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gov.nha.bis.server.model.AadhaarDetailsEntity;
import com.gov.nha.bis.server.model.MapperDetailsEntity;

public interface MapperDetailsRepository extends CrudRepository<MapperDetailsEntity, Long> {
	
	@Query("select s  from MapperDetailsEntity s where s.status != '3' order by s.status desc ")
	List<MapperDetailsEntity> findUrbanPendingVerifyList();
	
	@Query("select s  from MapperDetailsEntity s where s.refernceid = ?1 ")
	List<MapperDetailsEntity> findbyRefernceid(Long refernceid);
	
	MapperDetailsEntity findByRefernceid(Long refernceid);
	
}