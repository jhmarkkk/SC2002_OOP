package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Date;

/**
 * The {@code DateUtil} class provides utility methods for working with dates.
 * It includes methods to convert {@link GregorianCalendar} objects to strings and vice versa.
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 */
public class DateUtil {

    /**
     * Converts a {@link GregorianCalendar} object to a formatted string.
     * 
     * @param date the {@link GregorianCalendar} object to be converted
     * @return a formatted string representing the date
     */	
	public static String toString(GregorianCalendar date) {
		SimpleDateFormat fmtDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		fmtDateFormat.setCalendar(date);
		String dateFormatted = fmtDateFormat.format(date.getTime());
		return dateFormatted;
	}

    /**
     * Converts a string representation of a date to a {@link GregorianCalendar} object.
     * 
     * @param dateString the string representing the date
     * @return a {@link GregorianCalendar} object representing the date
     */	
	public static GregorianCalendar toDate(String dateString) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar cal = new GregorianCalendar();
		try{
			Date d = df.parse(dateString);
			cal.setTime(d);
		} catch (java.text.ParseException e){
			e.printStackTrace();
		}
		return cal;
	}
}
