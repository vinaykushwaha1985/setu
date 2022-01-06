package in.gov.nha.bis;

import java.nio.charset.StandardCharsets;

import org.springframework.web.util.UriUtils;

public class ConvertUnicode {

	public static void main(String[] args) {
		
		System.out.println(UriUtils.encode("Dear {#var#},\r\n"
				+ "We received your request to add new member in your family under AB-PMJAY. your request is under verification process. your reference number is:{#var#}.\r\n"
				+ "\r\n"
				+ "NHA", StandardCharsets.UTF_8));
	}
	
	
}
