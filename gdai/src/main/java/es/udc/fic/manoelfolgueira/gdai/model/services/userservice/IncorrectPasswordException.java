package es.udc.fic.manoelfolgueira.gdai.model.services.userservice;

@SuppressWarnings("serial")
public class IncorrectPasswordException extends Exception {

	private String loginName;

	/**
	 * Creates an exception when the user enters and invalid password
	 * 
	 * @param loginName
	 *            the user's login name
	 */
	public IncorrectPasswordException(String loginName) {
		super("Incorrect password exception => loginName = " + loginName);
		this.loginName = loginName;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

}
