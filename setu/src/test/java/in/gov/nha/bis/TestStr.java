package in.gov.nha.bis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.gov.nha.bis.server.dto.Details;
import com.gov.nha.bis.server.dto.Family;
import com.gov.nha.bis.server.dto.RationDto24;

public class TestStr {

	private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private static SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException, JSONException {
		
		
		
		try {
			System.out.println(formatter1.format(formatter.parse("06-03-1985")));
			

			
			
			RationDto24[] rationDto=null;
			List<Family> familyList= null;
			ObjectMapper mapper = new ObjectMapper();
			String familyData= "";
			String Details="";
			
			String str="{\"Header\":{\"ID_Type\":\"RSBY\",\"ID_Number\":\"112003009114108\",\"error_code\":\"200\",\"error_msg\":\"Success\"},\"Details\":[{\"family_guid\":\"112003009114108\",\"family_id\":\"112003009114108\",\"SECC_HHD\":\"\",\"state_lgd_code\":\"24\",\"district_lgd_code\":\"12\",\"subdistrict_lgd_code\":\"003\",\"village_town_lgd_code\":\"0000012\",\"pincode\":\"362310\",\"rural_urban\":\"R\",\"Family\":[{\"family_mem_guid_id\":\"112003009114108001\",\"family_mem_id\":\"112003009114108001\",\"member_name_eng\":\"ASODARIYA DAMJIBHAI DEVJIBHAI\",\"member_name_regional\":\"આસોદરીયા દામજીભાઈ દેવજીભાઈ\",\"mother_name_eng\":\"\",\"mother_name_regional\":\"\",\"father_name_eng\":\"\",\"father_name_regional\":\"\",\"spouse_name_eng\":\"\",\"spouse_name_regional\":\"\",\"year_of_birth\":\"0\",\"mobile_number\":\"\",\"relation_name\":\"Self\",\"gender\":\"M\",\"address\":\"નવા પ્‍લોટ  મુ. ભિયાળ જુનાગઢ\",\"AHL_TIN\":\"\",\"miscMemberDetails\":{\"memberType\":\"S\",\"mfield1\":\"\",\"mfield2\":\"\"}},{\"family_mem_guid_id\":\"112003009114108002\",\"family_mem_id\":\"112003009114108002\",\"member_name_eng\":\"ASODARIYA PRABHA DAMJI\",\"member_name_regional\":\"આસોદરીયા પ્રભા દામજી\",\"mother_name_eng\":\"\",\"mother_name_regional\":\"\",\"father_name_eng\":\"\",\"father_name_regional\":\"\",\"spouse_name_eng\":\"\",\"spouse_name_regional\":\"\",\"year_of_birth\":\"0\",\"mobile_number\":\"\",\"relation_name\":\"Wife\",\"gender\":\"F\",\"address\":\"નવા પ્‍લોટ  મુ. ભિયાળ જુનાગઢ\",\"AHL_TIN\":\"\",\"miscMemberDetails\":{\"memberType\":\"D\",\"mfield1\":\"\",\"mfield2\":\"\"}},{\"family_mem_guid_id\":\"112003009114108003\",\"family_mem_id\":\"112003009114108003\",\"member_name_eng\":\"ASODARIYA MAHESH DAMJI\",\"member_name_regional\":\"આસોદરીયા મહેશ દામજી\",\"mother_name_eng\":\"\",\"mother_name_regional\":\"\",\"father_name_eng\":\"\",\"father_name_regional\":\"\",\"spouse_name_eng\":\"\",\"spouse_name_regional\":\"\",\"year_of_birth\":\"0\",\"mobile_number\":\"\",\"relation_name\":\"Son\",\"gender\":\"M\",\"address\":\"નવા પ્‍લોટ  મુ. ભિયાળ જુનાગઢ\",\"AHL_TIN\":\"\",\"miscMemberDetails\":{\"memberType\":\"D\",\"mfield1\":\"\",\"mfield2\":\"\"}},{\"family_mem_guid_id\":\"112003009114108004\",\"family_mem_id\":\"112003009114108004\",\"member_name_eng\":\"ASODARIYA ASHOK DAMJI\",\"member_name_regional\":\"આસોદરીયા અશોક દામજી\",\"mother_name_eng\":\"\",\"mother_name_regional\":\"\",\"father_name_eng\":\"\",\"father_name_regional\":\"\",\"spouse_name_eng\":\"\",\"spouse_name_regional\":\"\",\"year_of_birth\":\"0\",\"mobile_number\":\"\",\"relation_name\":\"Son\",\"gender\":\"M\",\"address\":\"નવા પ્‍લોટ  મુ. ભિયાળ જુનાગઢ\",\"AHL_TIN\":\"\",\"miscMemberDetails\":{\"memberType\":\"D\",\"mfield1\":\"\",\"mfield2\":\"\"}}],\"remarks\":\"\",\"field1\":\"\",\"field2\":\"010106000\"}]}\r\n"
					+ "";

			JSONObject rationRes= new JSONObject(str);
			
			JSONObject bb=rationRes.getJSONObject("Header");
			
			System.out.println(bb.toString());
			
			JSONArray aa=rationRes.getJSONArray("Details");
			
			System.out.println(aa.toString());
			
			
			Details[]	details12=mapper.readValue(rationRes.getJSONArray("Details").toString(), Details[].class);
			
			System.out.println(details12[0].getDistrict_lgd_code());
			
			RationDto24	rationDto1 =new Gson().fromJson(str, RationDto24.class);
			
				
			if(rationDto1.getHeader().getError_msg().equalsIgnoreCase("Success")) {
				
				
			}


		
			
			
			
			
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
