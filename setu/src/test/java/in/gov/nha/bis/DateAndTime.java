/**
 * 
 */
package in.gov.nha.bis;

import java.text.SimpleDateFormat;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
import java.util.Calendar;
import java.util.Date;

public class DateAndTime {

    public static void main(String[] args) throws Exception {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
        String strDate = sdf.format(cal.getTime());
        System.out.println("Current date in String Format: " + strDate);

        SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("dd/MM/yyyy HH:mm:ss");
        Date date = sdf1.parse(strDate);
        String string=sdf1.format(date);
        System.out.println("Current date in Date Format: " + string.replaceAll("/", "")
        .replaceAll(" ", "").replaceAll(":", ""));

    }
}