package in.gov.nha.bis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bouncycastle.util.encoders.Base64;

/**
 *
 * Java program to show how to format date in Java using SimpleDateFormat
 * Examples. Java allows to include date, time and timezone information
 * while formatting dates in Java.
 *
 * @author http://java67.blogspot.com
 */
public class DateFormatExample {
	
	
	

    public static void main(String args[]) {
     
        // This is how to get today's date in Java
        Date today = new Date();
     
        //If you print Date, you will get un formatted output
        System.out.println("Today is : " + today);
        
        
        Calendar cal = Calendar.getInstance();
        
        /* Creating an object of
             GregorianCalendar Class */
        GregorianCalendar gcal = new GregorianCalendar();
 
        /* Displaying Current Date using
             Calendar Class */
        System.out.println("Calendar date: "
                           + cal.getTime());
 
        /* Displaying Current Date using
             GregorianCalendar Class */
        System.out.print("Gregorian date: "
                         + gcal.getTime());
        
        
     
        //formatting date in Java using SimpleDateFormat
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMYYYYHHmmss");
        String date = DATE_FORMAT.format(gcal.getTime());
      
        System.out.println("Today in DDMMYYYYHHMMSS format : " + date);
        
        
        String token="1D2s3Y8e7U9o7N34"+date;
        System.out.println("token==="+token);
        
        //1D2s3Y8e7U9o7N34
        //06102021161000
        //07102021104449
        //071020211010989
        //2801020211010697
        System.out.println(new String(Base64.encode(token.getBytes())));
        
        
     
           
    }
 
}
