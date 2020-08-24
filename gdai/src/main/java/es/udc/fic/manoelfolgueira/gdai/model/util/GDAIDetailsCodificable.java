package es.udc.fic.manoelfolgueira.gdai.model.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * All DTOs (which inherite the structure and behaviour from this class) will
 * get an special ID called GDAICode based on their class name.
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GDAICodificable.java
 */
public class GDAIDetailsCodificable {

	/**
	 * Generates a GDAI DTO Code based on the child DTO calling. PLEASE, REMEMBER TO
	 * CONFIGURE properties file located via the macro
	 * es.udc.fic.manoelfolgueira.gdai.model.util.Config.CONFIG_FILE_PATH - adding
	 * the GDAICode length as GDAICODIFICABLECHILD_CODE_LENGTH, example:
	 * PROJECTDETAILS_CODE_LENGTH=6 (PROJECTDETAILS = DTO, 6 = GDAICode length) -
	 * adding the GDAICode initial as GDAICODIFICABLECHILD_CODE_INITIAL, example:
	 * PROJECTDETAILS_CODE_INITIAL=D (PROJECTDETAILS = DTO, D = GDAICode initial)
	 * 
	 * @return the current GDAICode for the calling entity instance.
	 */
	public String getGDAICode() {
		
		return this.getGDAICode(Integer.parseInt((String) Config.getInstance().getProperties()
				.get(this.getClass().getSimpleName().toUpperCase() + ConfigPropertyKeys.GDAICODE_ENTITY_LENGTH_KEY)));
	}

	/**
	 * Method which actually calculates the GDAICode. We leave this method hidden so
	 * it's always called with the configured length and not with any arbitrary
	 * calls with different lengths
	 * 
	 * @param length
	 *            the GDAICode length to generate the code
	 * @return the actual GDAICode
	 */
	private String getGDAICode(int length) {

		Long id = null;

		Class<?> c = null;
		try {
			c = Class.forName(this.getClass().getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Method method = null;
		try {
			method = c.getDeclaredMethod("get" + this.getClass().getSimpleName().replace("Details", "") + "Id");
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			id = (Long) method.invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		String initial = (String) Config.getInstance().getProperties()
				.get(this.getClass().getSimpleName().toUpperCase() + ConfigPropertyKeys.GDAICODE_ENTITY_INITIAL_KEY);

		// TODO test high db id and low GDAICode length
		String currentCode = id.toString();
		if (currentCode.length() >= length)
			return initial + "-" + currentCode;
		else
			return initial + "-" + new String(new char[length - currentCode.length()]).replace('\0', '0') + currentCode;

	}

	/**
	 * Returns the actual database id
	 * 
	 * @param code
	 *            the GDAICode
	 * @return the actual database id as a Long
	 */
	public static Long decode(String code) {
		return Long.parseLong((code.substring(2, code.length() - 1)));
	}

}
