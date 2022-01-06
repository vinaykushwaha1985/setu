package com.gov.nha.bis.server.dto;

import java.io.Serializable;
import java.util.List;

public class RationDtoEnc  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2196646457205905395L;
	
	private HeaderEnc Header;
	private List<DetailsEnc> Details;
	
	public HeaderEnc getHeader() {
		return Header;
	}
	public void setHeader(HeaderEnc header) {
		Header = header;
	}
	public List<DetailsEnc> getDetails() {
		return Details;
	}
	public void setDetails(List<DetailsEnc> details) {
		Details = details;
	}
	
	
		

	

}
