package es.udc.fic.manoelfolgueira.gdai.web.services;

import java.io.IOException;

import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.Cookies;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;

import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.ConfigPropertyKeys;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.IncorrectPasswordException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.util.CookiesManager;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

public class SessionFilter implements RequestFilter {

	private ApplicationStateManager applicationStateManager;
	private Cookies cookies;
	private UserService userService;

	public SessionFilter(ApplicationStateManager applicationStateManager, Cookies cookies, UserService userService) {

		this.applicationStateManager = applicationStateManager;
		this.cookies = cookies;
		this.userService = userService;

	}

	public boolean service(Request request, Response response, RequestHandler handler) throws IOException {

		if (!applicationStateManager.exists(UserSession.class)) {

			String loginName = CookiesManager.getLoginName(cookies);
			if (loginName != null) {

				String encryptedPassword = CookiesManager.getEncryptedPassword(cookies);
				if (encryptedPassword != null) {

					try {

						UserDetails userDetails = userService.login(loginName, encryptedPassword, true);
						UserSession userSession = new UserSession();
						userSession.setUserId(userDetails.getUserId());
						userSession.setLoginName(userDetails.getLoginName());
						userSession.setManager(userDetails.getIsManager());
						userSession.setAdministrator(userDetails.getGroup().getGroupName().equals(Config.getInstance()
								.getProperties().getProperty(ConfigPropertyKeys.ADMINISTRATORS_GROUP_NAME)));
						applicationStateManager.set(UserSession.class, userSession);

					} catch (InstanceNotFoundException e) {
						CookiesManager.removeCookies(cookies);
					} catch (IncorrectPasswordException e) {
						CookiesManager.removeCookies(cookies);
					}

				}

			}

		}

		handler.service(request, response);

		return true;
	}

}
