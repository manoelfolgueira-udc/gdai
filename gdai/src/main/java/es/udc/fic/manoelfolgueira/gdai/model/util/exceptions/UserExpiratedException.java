package es.udc.fic.manoelfolgueira.gdai.model.util.exceptions;

@SuppressWarnings("serial")
public class UserExpiratedException extends InstanceException {

	/**
	 * Appears when a user tries to log in and its account is expired
	 * @param key instance id
	 * @param className entity class name
	 */
    public UserExpiratedException(Object key, String className) {
        super("User Expired", key, className);
    }
    
}
