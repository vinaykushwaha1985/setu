package com.gov.nha.bis.server.dto;

public class BlockDto {
	
	private String  block_name_english ;
	private int  block_code ;
	
	
	public BlockDto(int block_code,String block_name_english) {
		this.block_code=block_code;
		this.block_name_english=block_name_english;
	}
	
	public String getBlock_name_english() {
		return block_name_english;
	}
	public void setBlock_name_english(String block_name_english) {
		this.block_name_english = block_name_english;
	}
	public int getBlock_code() {
		return block_code;
	}
	public void setBlock_code(int block_code) {
		this.block_code = block_code;
	}
	
	

}
