package com.java4us.service.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.java4us.domain.User;

@Service
public class AuthenticationService {

	public boolean isJ4Authenticated() {
		return getJ4Authentication().isAuthenticated();
	}

	public boolean isJ4NotAuthenticated() {
		return !isJ4Authenticated();
	}

	public User getCurrentUser() {
		return (User) getJ4Authentication().getPrincipal();
	}

	public Authentication getJ4Authentication() {
		return (UsernamePasswordAuthenticationToken) getContext()
				.getAuthentication();
	}

	private SecurityContext getContext() {
		return SecurityContextHolder.getContext();
	}

}
