package es.udc.fic.manoelfolgueira.gdai.model.util.exceptions;

@SuppressWarnings("serial")
public class DuplicateInstanceException extends InstanceException {

	/**
	 * When to instances are duplicated, tries to insert and entity that is already
	 * stored in the db
	 * 
	 * @param key
	 *            instance id
	 * @param className
	 *            entity class name
	 */
	public DuplicateInstanceException(Object key, String className) {
		super("Duplicate instance", key, className);
	}

}
