package com.gov.nha.bis.server.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

import com.gov.nha.bis.server.dto.RationDto;
import com.gov.nha.bis.server.model.RationCardEntity;

public interface RationCardService {


	public void saveRationCardEntity(RationCardEntity rationCardEntity);

	public List<RationDto> getRationCard(long referenceId , int stateCode,String urFlag,int status);



}
