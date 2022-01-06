/**
 * 
 */
package com.gov.nha.bis.server.dto;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RationDto9 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5296735381426874255L;
	private Header header;
	private List<Details9> details;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public List<Details9> getDetails() {
		return details;
	}
	public void setDetails(List<Details9> details) {
		this.details = details;
	}
	
	

}
