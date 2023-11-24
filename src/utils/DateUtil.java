package utils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class DateUtil {
	
	public static String toString(GregorianCalendar date) {
		
		SimpleDateFormat fmtDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		fmtDateFormat.setCalendar(date);
		String dateFormatted = fmtDateFormat.format(date.getTime());
		return dateFormatted;
	}
}
