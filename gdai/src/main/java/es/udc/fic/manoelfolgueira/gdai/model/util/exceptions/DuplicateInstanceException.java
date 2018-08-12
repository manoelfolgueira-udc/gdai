package es.udc.fic.manoelfolgueira.gdai.model.util.exceptions;

@SuppressWarnings("serial")
public class DuplicateInstanceException extends InstanceException {

    public DuplicateInstanceException(Object key, String className) {
        super("Duplicate instance", key, className);
    }
    
}
