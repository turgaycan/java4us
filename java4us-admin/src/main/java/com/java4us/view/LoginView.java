/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.view;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omnifaces.util.Faces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java4us.domain.common.enums.UserType;

/**
 *
 * @author turgay
 */
@ManagedBean(name = "loginView")
@ViewScoped
public class LoginView {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginView.class);
	private static final String HOME_URL = "login";
	private static final String ADMIN_URL = "admin/index";
	private static final String USER_URL = "pages/main";

	private String username;
	private String password;
	private boolean remember;

	public void submit() throws IOException {
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(remember);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
			if (currentUser.hasRole(UserType.ROLE_ADMIN.name())) {
				Faces.redirect(ADMIN_URL);
			} else if (currentUser.hasRole(UserType.ROLE_USER.name())) {
				Faces.redirect(USER_URL);
			} else {
				Faces.redirect(HOME_URL);
			}
		} catch (UnknownAccountException uae) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Giriş başarısız", "kullanıcı adınız yanlış"));
			return;
		} catch (IncorrectCredentialsException ice) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Giriş başarısız", "parolanız yanlış"));
			return;
		} catch (LockedAccountException lae) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Giriş başarısız", "Bu kullanıcı adı kilitli"));
			return;
		} catch (AuthenticationException aex) {
			LOGGER.error(username + " not valid!");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Giriş başarısız", aex.toString()));
			return;
		} finally {
			token.clear();
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

}
