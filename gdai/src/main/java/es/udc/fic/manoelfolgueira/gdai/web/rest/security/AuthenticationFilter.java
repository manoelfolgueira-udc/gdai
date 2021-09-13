package es.udc.fic.manoelfolgueira.gdai.web.rest.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Priority(Priorities.AUTHENTICATION)
@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext reqContext) throws IOException {
		
		String authHeader = reqContext
				.getHeaderString(HttpHeaders.AUTHORIZATION);
		System.out.println("H058 authHeader: " + authHeader); 
		if (authHeader == null || !verifyCredentials(authHeader)) {				
			throw new NotAuthorizedException(
					"Basic realm=\"Catalog Example\"");
		}
	}

	private static boolean verifyCredentials(String authHeader) {
		if (authHeader.startsWith("Basic ")) {
			String userName = Base64.decodeAsString(authHeader.split(" ")[1]).split(":")[0];
			String password = Base64.decodeAsString(authHeader.split(" ")[1]).split(":")[1];
			if ((userName == null || "".contentEquals(userName))
					|| (password == null || "".contentEquals(password))) {
				return false;
			} else if (userName.contentEquals(password)) return true;
		}
		
		return false;
	}
}

