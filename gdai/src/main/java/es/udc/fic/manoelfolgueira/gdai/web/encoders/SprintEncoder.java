package es.udc.fic.manoelfolgueira.gdai.web.encoders;

import org.apache.tapestry5.ValueEncoder;

import es.udc.fic.manoelfolgueira.gdai.model.sprint.Sprint;
import es.udc.fic.manoelfolgueira.gdai.model.sprintservice.SprintService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public class SprintEncoder implements ValueEncoder<Sprint> {
	
	private SprintService ss;

    public SprintEncoder (SprintService ss) {
    	this.ss = ss;
    }

    @Override
    public String toClient(Sprint value) {
        return String.valueOf(value.getSprintId()); 
    }

    @Override
    public Sprint toValue(String id) { 
        try {
			return ss.findSprint(Long.parseLong(id));
		} catch (NumberFormatException | InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
    }

} 