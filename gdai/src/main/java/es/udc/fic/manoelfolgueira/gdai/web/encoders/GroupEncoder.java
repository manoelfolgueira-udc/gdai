package es.udc.fic.manoelfolgueira.gdai.web.encoders;

import org.apache.tapestry5.ValueEncoder;

import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.groupservice.GroupService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * An encoder for the Group Entity
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file GroupEncoder.java
 */
public class GroupEncoder implements ValueEncoder<GroupDetails> {

	private GroupService gs;

	public GroupEncoder(GroupService gs) {
		this.gs = gs;
	}

	@Override
	public String toClient(GroupDetails value) {
		return String.valueOf(value.getGroupId());
	}

	@Override
	public GroupDetails toValue(String id) {
		try {
			return gs.findGroup(Long.parseLong(id));
		} catch (NumberFormatException | InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}