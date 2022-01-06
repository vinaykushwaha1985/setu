package com.gov.nha.bis.server.repository;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.data.repository.CrudRepository;

import com.gov.nha.bis.server.model.SmsOtpEntity;

public interface SmsOtpRepository extends CrudRepository<SmsOtpEntity, Long> {

}
