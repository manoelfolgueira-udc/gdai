package es.udc.fic.manoelfolgueira.gdai.web.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public final class Utils {
		
	public static String getFormattedDate(Date date, Locale locale) {
		
		if (date == null) return "";
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		String formattedDate = df.format(date);
		
		return formattedDate;
	}
	
	/*public static Date parseDate(String date, Locale locale) {
		
		if ("".equals(date)) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", locale);

		
		return formattedDate;
	}*/

}
