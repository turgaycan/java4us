/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.jsf.exceptions;

import com.java4us.domain.User;
import com.java4us.jsf.util.FacesHelper;
import org.omnifaces.exceptionhandler.FullAjaxExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author turgay
 */
public class CustomExceptionHandler extends FullAjaxExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    private final FacesHelper facesHelper = new FacesHelper();

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle() {
        Iterator<ExceptionQueuedEvent> eventIterator = getUnhandledExceptionQueuedEvents().iterator();
        for (Iterator<ExceptionQueuedEvent> it = eventIterator; it.hasNext(); ) {
            ExceptionQueuedEvent event = eventIterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable throwable = context.getException();

            FacesContext facesContext = FacesContext.getCurrentInstance();

            boolean viewExpiredException = isViewExpiredException(throwable);
            PhaseId phaseId = event.getContext().getPhaseId();
            if (LOGGER.isErrorEnabled() && !viewExpiredException) {
                logException(throwable, facesContext);
            }

            if (viewExpiredException) {
                renderRedirect(facesContext, facesContext.getExternalContext().getRequestServletPath());
            } else if (applicationInvoked(phaseId)) {
                handleExceptionForFacesHelper("errorCode", "parameter");
            } else if (facesContext.getPartialViewContext().isAjaxRequest()) {
                putToSession(throwable);
                redirectTo(facesContext, facesContext.getExternalContext().getInitParameter("error500page"));
            } else {
                facesHelper.addMessageKeyFromBundle(FacesMessage.SEVERITY_ERROR, null, "system.error");
                putToSession(throwable);
                renderRedirect(facesContext, facesContext.getExternalContext().getInitParameter("error500page") + ".xhtml");
            }
            eventIterator.remove();
        }
    }

    private void logException(Throwable throwable, FacesContext facesContext) {
        StringBuilder userInfo = new StringBuilder();
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && User.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass())) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userInfo.append("Current User Id: ").append(user.getId()).append(" and Type: ").append(user.getEmail());
        } else {
            userInfo.append("Current User is not authenticated.");
        }

        LOGGER.error("Exception occurred at page: " + facesContext.getExternalContext().getRequestServletPath() + " . " + userInfo.toString() + ". ", throwable);
    }

    private void renderRedirect(FacesContext context, String relativePath) {
        ResponseWriter writer = context.getResponseWriter();
        String path = facesHelper.getRequestContextPath() + relativePath;
        String redirectScript = "window.location = '" + path + "';";
        try {
            if (writer != null && context.getCurrentPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
                writer.startElement("script", null);
                writer.write(redirectScript);
                writer.endElement("script");
            } else {
                HttpServletResponse response = ((HttpServletResponse) context.getExternalContext().getResponse());
                response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                response.setHeader("Location", path);
            }
        } catch (IOException e) {
            LOGGER.error("Exception occured.", e);
        } finally {
            context.responseComplete();
        }
    }

    private void handleExceptionForFacesHelper(String errorCode, Object... params) {
        facesHelper.addMessageKeyFromBundle(FacesMessage.SEVERITY_ERROR, null, errorCode, params);
    }

    private boolean applicationInvoked(PhaseId phaseId) {
        return PhaseId.INVOKE_APPLICATION.equals(phaseId);
    }

    private boolean isViewExpiredException(Throwable throwable) {
        return getExceptionOfType(throwable, ViewExpiredException.class) != null;
    }

    private <T extends Throwable> T getExceptionOfType(Throwable throwable, Class<T> clazz) {
        Throwable control = throwable;
        do {
            if (clazz.isAssignableFrom(control.getClass())) {
                return clazz.cast(control);
            }
            control = control.getCause();
        } while (control != null);

        return null;
    }

    private void putToSession(Throwable error) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.setAttribute("error", error);
        }
    }

    private void redirectTo(FacesContext fc, String redirectUrl) {
        NavigationHandler navHandler = fc.getApplication().getNavigationHandler();
        navHandler.handleNavigation(fc, null, redirectUrl + "?faces-redirect=true");
    }
}
