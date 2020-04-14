package es.udc.fic.manoelfolgueira.gdai.web.encoders;

import org.apache.tapestry5.ValueEncoder;

import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryDetails;
import es.udc.fic.manoelfolgueira.gdai.model.userstoryservice.UserStoryService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

/**
 * An encoder for the UserStory Entity
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file UserStoryEncoder.java
 */
public class UserStoryEncoder implements ValueEncoder<UserStoryDetails> {

	private UserStoryService ss;

	public UserStoryEncoder(UserStoryService ss) {
		this.ss = ss;
	}

	@Override
	public String toClient(UserStoryDetails value) {
		return String.valueOf(value.getUserStoryId());
	}

	@Override
	public UserStoryDetails toValue(String id) {
		try {
			return ss.findUserStory(Long.parseLong(id));
		} catch (NumberFormatException | InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}