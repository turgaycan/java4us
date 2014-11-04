/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.view;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.omnifaces.util.Faces;

/**
 *
 * @author turgay
 */
@ManagedBean(name = "logOutView")
@RequestScoped
public class LogOutView {

	public static final String HOME_URL = "login";

	public void submit() throws IOException {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			currentUser.logout();
		}
		Faces.redirect(HOME_URL);
	}
}
