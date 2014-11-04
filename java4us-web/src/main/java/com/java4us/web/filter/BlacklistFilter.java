/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.web.filter;

import com.java4us.common.model.DeniedUsersModel;
import com.java4us.service.user.J4UserService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author TCAN
 */
@Service("blacklistFilter")
public class BlacklistFilter extends OncePerRequestFilter {

    protected static Logger LOGGER = LoggerFactory.getLogger(BlacklistFilter.class);

    @Autowired
    private J4UserService java4usUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        LOGGER.debug("Running blacklist filter");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            String authUsername = authentication.getName();
            if (StringUtils.isEmpty(authUsername)) {
                LOGGER.error("username is empty!");
                return;
            }

            DeniedUsersModel deniedUsersModel = null;
            try {
                deniedUsersModel = java4usUserService.findDeniedUsers();
            } catch (Exception ex) {
                LOGGER.error("error : " + ex.getMessage());
            }
            if (deniedUsersModel != null && deniedUsersModel.getDeniedUserList().contains(authUsername)) {
                LOGGER.error("Username and password match. Access denied!");
                throw new AccessDeniedException("Username and password match. Access denied!");
            }
        }
        LOGGER.debug("Continue with remaining filters");
        filterChain.doFilter(request, response);
    }
}
