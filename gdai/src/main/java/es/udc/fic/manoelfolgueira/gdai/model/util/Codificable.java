package es.udc.fic.manoelfolgueira.gdai.model.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Codificable {
	
	public String getGDAICode() {
				
		return this.getGDAICode(
				Integer.parseInt((String)Config.getInstance().getProperties().get(
						this.getClass().getSimpleName().toUpperCase() + "_CODE_LENGTH"))
				);
	}
	
	public String getGDAICode(int length) {
				
		Long id = null;
		
		Class<?> c = null;
		try {
			c = Class.forName(this.getClass().getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method method = null;
		try {
			method = c.getDeclaredMethod("get" + this.getClass().getSimpleName() + "Id");
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			id = (Long) method.invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String initial =
				(String)Config.getInstance().getProperties().get(this.getClass().getSimpleName().toUpperCase() + "_CODE_INITIAL");
		
		String currentCode = id.toString();
		if (currentCode.length() >= length) return initial + "-" + currentCode;
		else return initial + "-" + new String(new char[length-currentCode.length()]).replace('\0', '0') + currentCode;
		
	}
	
	public static Long decode(String code) {
		return Long.parseLong((code.substring(2, code.length() - 1)));
	}
}
