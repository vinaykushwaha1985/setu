package com.gov.nha.bis.server.properties;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Configuration
@PropertySource({"classpath:application.properties"})
public class ApplicationConstantConfig {
	
	@Value("${nha.bis.sms.userName}")
	public String nha_bis_sms_userName;
	
	@Value("${nha.bis.sms.password}")
	public String nha_bis_sms_password;
	
	@Value("${nha.bis.sms.token}")
	public String nha_bis_sms_token;
	
	@Value("${nha.bis.sms.url}")
	public String nha_bis_sms_url;
	
	
	@Value("${nah.bis.sms.dlt.entity.id}")
	public String nah_bis_sms_dlt_entity_id;
	
	@Value("${nha.bis.sms.dlt.template.id}")
	public String nha_bis_sms_dlt_template_id;
	
	@Value("${nha.bis.sender.id}")
	public String nha_bis_sender_id;
	
	@Value("${nha.bis.sms.template}")
	public String nha_bis_sms_template;
	
	
	@Value("${USE_SMS_SERVICE}")
	public String USE_SMS_SERVICE;
	
	@Value("${TCS_SMS_GATEWAY_URL}")
	public String TCS_SMS_GATEWAY_URL;
	
	
	@Value("${TCS_SMS_GATEWAY_CLIENT_ID}")
	public String TCS_SMS_GATEWAY_CLIENT_ID;
	
	@Value("${TCS_SMS_GATEWAY_CLIENT_SECRET}")
	public String TCS_SMS_GATEWAY_CLIENT_SECRET;
	
	@Value("${TCS_SMS_GATEWAY_TOKEN_URL}")
	public String TCS_SMS_GATEWAY_TOKEN_URL;
	
	@Value("${TCS_SMS_GATEWAY_USERNAME}")
	public String TCS_SMS_GATEWAY_USERNAME;
	
	@Value("${TCS_SMS_GATEWAY_PASSWORD}")
	public String TCS_SMS_GATEWAY_PASSWORD;
	
	@Value("${TCS_SMS_GATEWAY_dlt_template_id}")
	public String TCS_SMS_GATEWAY_dlt_template_id;
	
	@Value("${BIS_SMS_GATEWAY_URL}")
	public String BIS_SMS_GATEWAY_URL;
	
	@Value("${BIS_SMS_GATEWAY_USER}")
	public String BIS_SMS_GATEWAY_USER;
	
	@Value("${BIS_SMS_GATEWAY_SENDER_ID}")
	public String BIS_SMS_GATEWAY_SENDER_ID;
	
	
	@Value("${BIS_SMS_GATEWAY_AUTH_KEY}")
	public String BIS_SMS_GATEWAY_AUTH_KEY;
	
	@Value("${BIS_SMS_GATEWAY_ENTITY_ID}")
	public String BIS_SMS_GATEWAY_ENTITY_ID;
	
	@Value("${BIS_SMS_GATEWAY_OTP_LIST}")
	public String BIS_SMS_GATEWAY_OTP_LIST;
	
	
	@Value("${BIS_SMS_GATEWAY_SMS_LIST}")
	public String BIS_SMS_GATEWAY_SMS_LIST;
	
	@Value("${BIS_TEMPLATE_OTP_1007163593301170578}")
	public String BIS_TEMPLATE_OTP_1007163593301170578;
	
	@Value("${BIS_TEMPLATE_OTP_1007163593280345232}")
	public String BIS_TEMPLATE_OTP_1007163593280345232;
	
	@Value("${BIS_TEMPLATE_OTP_1007163593275058061}")
	public String BIS_TEMPLATE_OTP_1007163593275058061;
	
	@Value("${BIS_TEMPLATE_SMS_1007163593307833597}")
	public String BIS_TEMPLATE_SMS_1007163593307833597;
	
	@Value("${BIS_TEMPLATE_SMS_1007163593317209694}")
	public String BIS_TEMPLATE_SMS_1007163593317209694;
	
	
	
	
}