package com.gov.nha.bis.server.dto;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.math.BigInteger;

public class VerifyUrbanDto {
	
	private BigInteger population_urban_pkey;
	private BigInteger  population_urban_PrimaryId;
	private String  status;
	private String  checkedby;
	
	public VerifyUrbanDto(BigInteger population_urban_pkey, BigInteger population_urban_PrimaryId, String status,
			String checkedby) {
		this.population_urban_pkey = population_urban_pkey;
		this.population_urban_PrimaryId = population_urban_PrimaryId;
		this.status = status;
		this.checkedby = checkedby;
	}

	public BigInteger getPopulation_urban_pkey() {
		return population_urban_pkey;
	}

	public void setPopulation_urban_pkey(BigInteger population_urban_pkey) {
		this.population_urban_pkey = population_urban_pkey;
	}

	public BigInteger getPopulation_urban_PrimaryId() {
		return population_urban_PrimaryId;
	}

	public void setPopulation_urban_PrimaryId(BigInteger population_urban_PrimaryId) {
		this.population_urban_PrimaryId = population_urban_PrimaryId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckedby() {
		return checkedby;
	}

	public void setCheckedby(String checkedby) {
		this.checkedby = checkedby;
	}
	
	

}
