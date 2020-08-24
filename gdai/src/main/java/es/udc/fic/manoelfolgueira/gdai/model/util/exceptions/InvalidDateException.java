package es.udc.fic.manoelfolgueira.gdai.model.util.exceptions;

/**
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file DateInstanceException.java
 */
@SuppressWarnings("serial")
public class InvalidDateException extends InstanceException {

	/**
	 * @param specificMessage
	 * @param key
	 * @param className
	 */
	public InvalidDateException(Object key, String className) {
		super("Invalid date specified", key, className);
	}

}
