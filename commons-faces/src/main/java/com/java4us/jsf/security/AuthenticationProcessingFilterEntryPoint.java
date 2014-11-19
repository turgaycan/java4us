/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.jsf.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author turgay
 */

/**
 * Extended Spring LoginUrlAuthenticationEntryPoint for playing together with
 * JSF Ajax redirects.
 */
public class AuthenticationProcessingFilterEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public static final String ATTRIBUTE_LOGIN_PAGE = "com.myproject.login.page";

    public AuthenticationProcessingFilterEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if (request.getParameter("javax.faces.partial.ajax") != null) {
            request.setAttribute(ATTRIBUTE_LOGIN_PAGE, getLoginFormUrl());
        }
        super.commence(request, response, authException);
    }
}
