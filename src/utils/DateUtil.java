package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Date;

public class DateUtil {

	public static String toString(GregorianCalendar date) {

		SimpleDateFormat fmtDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		fmtDateFormat.setCalendar(date);
		String dateFormatted = fmtDateFormat.format(date.getTime());
		return dateFormatted;
	}

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
