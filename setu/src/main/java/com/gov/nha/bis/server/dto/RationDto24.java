package com.gov.nha.bis.server.dto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RationDto24 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2196646457205905395L;
	
	private Header Header;
	private List<Details> Details;
	public Header getHeader() {
		return Header;
	}
	public void setHeader(Header header) {
		Header = header;
	}
	public List<Details> getDetails() {
		return Details;
	}
	public void setDetails(List<Details> details) {
		Details = details;
	}
	
		

	

}
