package com.gov.nha.bis.server.dto;

import com.gov.nha.bis.server.util.RationDataDecryptUtil;

public class miscMemberDetailsEnc {
	
    private String memberType;
    private String mfield1;
    private String mfield2;
	public String getMemberType() {
		return RationDataDecryptUtil.DecryptDataStr(memberType);
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMfield1() {
		return RationDataDecryptUtil.DecryptDataStr(mfield1);
	}
	public void setMfield1(String mfield1) {
		this.mfield1 = mfield1;
	}
	public String getMfield2() {
		return RationDataDecryptUtil.DecryptDataStr(mfield2);
	}
	public void setMfield2(String mfield2) {
		this.mfield2 = mfield2;
	}
    
    




}
