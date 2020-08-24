package es.udc.fic.manoelfolgueira.gdai.web.encoders;

import org.apache.tapestry5.ValueEncoder;

import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintDetails;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * An encoder for the Group Entity
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file SprintEncoder.java
 */
public class SprintEncoder implements ValueEncoder<SprintDetails> {

	private SprintService ss;

	public SprintEncoder(SprintService ss) {
		this.ss = ss;
	}

	@Override
	public String toClient(SprintDetails value) {
		return String.valueOf(value.getSprintId());
	}

	@Override
	public SprintDetails toValue(String id) {
		try {
			return ss.findSprint(Long.parseLong(id));
		} catch (NumberFormatException | InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}