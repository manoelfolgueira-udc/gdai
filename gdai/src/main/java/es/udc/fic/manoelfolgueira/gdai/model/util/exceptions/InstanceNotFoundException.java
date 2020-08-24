package es.udc.fic.manoelfolgueira.gdai.model.util.exceptions;

@SuppressWarnings("serial")
public class InstanceNotFoundException extends InstanceException {

	/**
	 * Appears when - you try to look for a single object and not a collection. A
	 * collection would be returned empty. - you try to update an object that it is
	 * not stored in GDAI
	 * 
	 * @param key
	 *            instance id
	 * @param className
	 *            entity class name
	 */
	public InstanceNotFoundException(Object key, String className) {
		super("Instance not found", key, className);
	}

}
