package com.gov.nha.bis.server.repository;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gov.nha.bis.server.model.AadhaarPhotoEntity;


public interface AadhaarPhotoRepository extends CrudRepository<AadhaarPhotoEntity, Long> {
	
	@Query("select s  from AadhaarPhotoEntity s where s.refernceid = ?1 ")
	List<AadhaarPhotoEntity> findbyRefernceid(Long refernceid);
	
	
	 AadhaarPhotoEntity findByRefernceid(Long refernceid);

}
