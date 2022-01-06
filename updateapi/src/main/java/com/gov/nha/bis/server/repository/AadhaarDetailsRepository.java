package com.gov.nha.bis.server.repository;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gov.nha.bis.server.model.AadhaarDetailsEntity;

public interface AadhaarDetailsRepository extends CrudRepository<AadhaarDetailsEntity, Long> {
	
	@Query("select s  from AadhaarDetailsEntity s where s.refernceid = ?1 ")
	List<AadhaarDetailsEntity> findbyRefernceid(Long refernceid);
	
	AadhaarDetailsEntity findByRefernceid(Long refernceid);
	
}
