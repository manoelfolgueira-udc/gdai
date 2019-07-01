package es.udc.fic.manoelfolgueira.gdai.web.encoders;

import org.apache.tapestry5.ValueEncoder;

import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * An encoder for the System Entity
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file   SystemEncoder.java
 */
public class SystemEncoder implements ValueEncoder<System> {
	
	private SystemService ss;

    public SystemEncoder (SystemService ss) {
    	this.ss = ss;
    }

    @Override
    public String toClient(System value) {
        return String.valueOf(value.getSystemId()); 
    }

    @Override
    public System toValue(String id) { 
        try {
			return ss.findSystem(Long.parseLong(id));
		} catch (NumberFormatException | InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
    }

} 