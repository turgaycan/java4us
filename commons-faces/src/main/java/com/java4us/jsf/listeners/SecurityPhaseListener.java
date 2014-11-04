/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.jsf.listeners;

import com.java4us.jsf.security.AuthenticationProcessingFilterEntryPoint;
import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author turgay
 */
public class SecurityPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();
        String loginPage = (String) fc.getExternalContext().getRequestMap().get(AuthenticationProcessingFilterEntryPoint.ATTRIBUTE_LOGIN_PAGE);
        if (StringUtils.isNotBlank(loginPage)) {
            doRedirect(fc, loginPage);
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    public void doRedirect(FacesContext fc, String redirectPage) {
        ExternalContext ec = fc.getExternalContext();

        try {
            if (ec.isResponseCommitted()) {
                return;
            }

            ec.redirect(ec.getRequestContextPath() + (redirectPage != null ? redirectPage : ""));

        } catch (IOException e) {
            throw new FacesException(e);
        }
    }
}
