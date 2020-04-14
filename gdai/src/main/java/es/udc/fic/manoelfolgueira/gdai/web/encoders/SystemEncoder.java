package es.udc.fic.manoelfolgueira.gdai.web.encoders;

import org.apache.tapestry5.ValueEncoder;

import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * An encoder for the System Entity
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SystemEncoder.java
 */
public class SystemEncoder implements ValueEncoder<SystemDetails> {

	private SystemService ss;

	public SystemEncoder(SystemService ss) {
		this.ss = ss;
	}

	@Override
	public String toClient(SystemDetails value) {
		return String.valueOf(value.getSystemId());
	}

	@Override
	public SystemDetails toValue(String id) {
		try {
			return ss.findSystem(Long.parseLong(id));
		} catch (NumberFormatException | InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}