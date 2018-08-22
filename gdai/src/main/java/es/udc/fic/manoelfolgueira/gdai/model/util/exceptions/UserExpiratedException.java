package es.udc.fic.manoelfolgueira.gdai.model.util.exceptions;

@SuppressWarnings("serial")
public class UserExpiratedException extends InstanceException {

    public UserExpiratedException(Object key, String className) {
        super("User Expired", key, className);
    }
    
}
