package com.gov.nha.bis.server.dto;

import java.util.List;

public class BocwDto {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5296735381426874255L;
	private HeaderBocw header;
	private List<DetailsBocw> details;
	
	public HeaderBocw getHeader() {
		return header;
	}
	public void setHeader(HeaderBocw header) {
		this.header = header;
	}
	public List<DetailsBocw> getDetails() {
		return details;
	}
	public void setDetails(List<DetailsBocw> details) {
		this.details = details;
	}
	

}
