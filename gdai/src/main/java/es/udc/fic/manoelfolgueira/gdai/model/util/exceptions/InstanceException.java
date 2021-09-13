package es.udc.fic.manoelfolgueira.gdai.model.util.exceptions;

@SuppressWarnings("serial")
public abstract class InstanceException extends Exception {

	private Object key;
	private String className;

	/**
	 * @see es.udc.fic.manoelfolgueira.gdai.test.util.exceptions.InstanceNotFoundException.java
	 */
	protected InstanceException(String specificMessage, Object key, String className) {

		super(specificMessage + " (key = '" + key + "' - className = '" + className + "')");
		this.key = key;
		this.className = className;

	}

	/**
	 * @return the key
	 */
	public Object getKey() {
		return key;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

}
