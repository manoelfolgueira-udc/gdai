package es.udc.fic.manoelfolgueira.gdai.web.encoders;

import org.apache.tapestry5.ValueEncoder;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public class GroupEncoder implements ValueEncoder<Group> {
	
	private GroupService gs;

    public GroupEncoder (GroupService gs) {
    	this.gs = gs;
    }

    @Override
    public String toClient(Group value) {
        return String.valueOf(value.getGroupId()); 
    }

    @Override
    public Group toValue(String id) { 
        try {
			return gs.findGroup(Long.parseLong(id));
		} catch (NumberFormatException | InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
    }

} 