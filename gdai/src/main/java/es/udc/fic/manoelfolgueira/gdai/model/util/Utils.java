/**
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   Utils.java
 */
package es.udc.fic.manoelfolgueira.gdai.model.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file Utils.java
 */
public class Utils {

	public static String getFormattedDate(Date date) {

		if (date == null)
			return "";

		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
		String formattedDate = df.format(date);

		return formattedDate;
	}

	public static String getSQLWildcardedParam(String param) {
		if (param == null)
			return null;
		return param.replace("*", "%").replace("?", "_");
	}

}
