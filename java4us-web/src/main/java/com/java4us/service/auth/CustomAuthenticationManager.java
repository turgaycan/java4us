/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.service.auth;

import com.java4us.component.utils.PasswordUtils;
import com.java4us.domain.User;
import com.java4us.service.user.J4UserService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author TCAN
 */
@Service("customAuthenticationManager")
public class CustomAuthenticationManager implements AuthenticationManager {

	protected static Logger LOGGER = LoggerFactory
			.getLogger(CustomAuthenticationManager.class);

	@Autowired
	private J4UserService java4usUserService;

	@Autowired
	private PasswordUtils passwordUtils;

	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {

		LOGGER.debug("Performing custom authentication");
		String username = auth.getName();
		String password = (String) auth.getCredentials();
		if (!StringUtils.isNotBlank(username)
				|| !StringUtils.isNotBlank(password)) {
			LOGGER.error("Username && Password not proper..");
			throw new UsernameNotFoundException("Username && Password is empty");
		}

		User user = java4usUserService.findByUserName(username);
		if (user == null) {
			LOGGER.error("User not found!");
			throw new UsernameNotFoundException("user not found");
		}

		final List<GrantedAuthority> buildUserAuthorities = user
				.buildUserAuthority();
		UserDetails userDetails = new UserDetailsAdapter(user,
				buildUserAuthorities);

		if (!passwordUtils.isPasswordValid(userDetails, user.getPassword(),
				password)) {
			LOGGER.error("Wrong password!");
			throw new BadCredentialsException("Wrong password!");
		}

		if (auth.getName().equals(auth.getCredentials())) {
			LOGGER.debug("Entered username and password are the same!");
			throw new BadCredentialsException(
					"Entered username and password are the same!");

		} else {
			LOGGER.debug("User details are good and ready to go");
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					auth.getName(), auth.getCredentials(), buildUserAuthorities);

			LOGGER.debug("Logging in with [{}]", auth.getPrincipal());
			SecurityContextHolder.getContext().setAuthentication(token);
			return token;
		}
	}
}
