/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.jsf.util;

import com.java4us.domain.common.enums.LabeledEnum;
import com.java4us.domain.core.LabeledEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.myfaces.component.visit.FullVisitContext;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author turgay
 */
@ManagedBean(name = "facesHelper")
@ApplicationScoped
public class FacesHelper implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 946891407001808075L;

    private static final Logger LOGGER = LoggerFactory.getLogger(FacesHelper.class);

    //    private static final String TYPE_LABEL = "i18n.typeLabel";
    private static final String LABELS = "i18n.labels";
    private static final String MESSAGES = "i18n.messages";
    private static final String COMMONS = "i18n.commons";

    @ManagedProperty(value = "#{enumUtil}")
    private EnumUtil enumUtil;

    public FacesContext getFacesContext() {
        return Faces.getContext();
    }

    public ExternalContext getExternalContext() {
        return Faces.getExternalContext();
    }

    public ServletContext getServletContext() {
        return Faces.getServletContext();
    }

    public RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }

    public void executeJS(String js) {
        getRequestContext().execute(js);
    }

    public void update(String path) {
        getRequestContext().update(path);
    }

    public UIViewRoot getViewRoot() {
        return Faces.getViewRoot();
    }

    public String getCurrentViewId() {
        return Faces.getViewId();
    }

    public void redirect(String url) throws Exception {
        try {
            Faces.redirect(url);
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    public void redirectContextRelative(String url) throws Exception {
        redirect(getRequestContextPath() + url);
    }

    public void forward(String path) throws IOException, ServletException {
        RequestDispatcher dispatcher = getRequest().getRequestDispatcher(path);
        dispatcher.forward(getRequest(), getResponse());
        if (getFacesContext() != null) {
            getFacesContext().responseComplete();
        }
    }

    public void navigate(String outcome) {
        Faces.navigate(outcome);
    }

    public Locale getLocale() {
        return LocaleUtil.getLocale();
    }

    public String getRequestContextPath() {
        return Faces.getRequestContextPath();
    }

    public String getCurrentPageUrl() {
        return getRequestContextPath() + getCurrentViewId();
    }

    public void addMessage(Severity severity, String clientId, String message, Object... params) {
        Messages.add(severity, clientId, message, params);
    }

    public void addFacesMessage(String clientId, FacesMessage message) {
        Messages.add(clientId, message);
    }

    public void addMessageKeyFromBundle(Severity severity, String clientId, String messageKey, Object... params) {
        Messages.add(severity, clientId, getMessageFromBundle(messageKey), params);
    }

    public String getMessageFromCommonsBundle(String messageKey, Object... params) {
        return getStringFromBundle(COMMONS, messageKey, params);
    }

    public String getMessageFromBundle(String messageKey, Object... params) {
        return getStringFromBundle(MESSAGES, messageKey, params);
    }

    public String getLabelFromBundle(String messageKey, Object... params) {
        return getStringFromBundle(LABELS, messageKey, params);
    }

    public String getCreateHeaderLabel(String tag) {
        return getLabelFromBundle("header." + tag + ".createLegendHeader");
    }

    public String getInitialHeaderLabel(String tag) {
        return getLabelFromBundle("header." + tag + ".initialLegendHeader");
    }

    public String getHeaderLabel(String tag) {
        return getLabelFromBundle("header." + tag + ".legendHeader");
    }

    //    public String getTypeLabelFromBundle(String messageKey, Object... params) {
//        return getStringFromBundle(TYPE_LABEL, messageKey, params);
//    }
    public String getStringFromBundle(String bundleName, String messageKey, Object... params) {

        Locale locale = getLocale();
        ClassLoader loader = getClassLoader();

        if (StringUtils.isBlank(bundleName)) {
            LOGGER.error("couldn't find the bundle name : {}", bundleName);
            return "???" + messageKey + "???";
        }
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, loader);
        String message;
        try {
            message = bundle.getString(messageKey);
        } catch (MissingResourceException mre) {
            LOGGER.error("couldn't find the value for {} in bundle {}", messageKey, bundleName);
            return "???" + messageKey + "???";
        }

        if (params == null || params.length == 0) {
            return message;
        }

        MessageFormat formatter = new MessageFormat(message, locale);
        return formatter.format(params);
    }

    public String getEnumLabelFromBundle(Enum<? extends LabeledEnum> value) {
        String enumLabel = null;
        EnumUtil util = getEnumUtil();
        try {
            enumLabel = util.getEnumLabel(value, getLocale());
        } catch (IllegalStateException e) {
//            enumLabel = getTypeLabelFromBundle(util.getEnumClassLabel(value));
        }
        return enumLabel;
    }

    public List<SelectItem> toSelectItemsFromLabeledEntity(Collection<? extends LabeledEntity> entities) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        if (entities == null) {
            return selectItems;
        }
        Locale locale = getLocale();
        for (LabeledEntity eachEntity : entities) {
            selectItems.add(new SelectItem(eachEntity, eachEntity.getLabel(locale)));
        }
        return selectItems;
    }

    private ClassLoader getClassLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = ClassLoader.getSystemClassLoader();
        }
        return loader;
    }

    public void addCallbackParam(String param, Object value) {
        getRequestContext().addCallbackParam(param, value);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public String getRequestParameter(String name) {
        return getRequest().getParameter(name);
    }

    public Flash getFlash() {
        return getFacesContext().getExternalContext().getFlash();
    }

    public Object flashGet(String parameter) {
        return getFlash().get(parameter);
    }

    public void flashPut(String parameter, Object value) {
        getFlash().put(parameter, value);
    }

    public void flashRemove(String parameter) {
        getFlash().remove(parameter);
    }

    public void flashKeep(String parameter) {
        getFlash().keep(parameter);
    }

    public String getRequestIp() {
        String clientIp = getRequest().getRemoteAddr();

        if ("0:0:0:0:0:0:0:1%0".equals(clientIp) || "fe80:0:0:0:0:0:0:1%1".equals(clientIp)
                || "0:0:0:0:0:0:0:1".equals(clientIp)) {
            clientIp = "127.0.0.1";
        }

        return clientIp;
    }

    //    public static String getTypeLabel() {
//        return TYPE_LABEL;
//    }
    public Object getAttributeByComponent(String attributeName) {
        return UIComponent.getCurrentComponent(getFacesContext()).getAttributes().get(attributeName);
    }

    public void setEnumUtil(EnumUtil enumUtil) {
        this.enumUtil = enumUtil;
    }

    private EnumUtil getEnumUtil() {
        if (enumUtil == null) {
            enumUtil = (EnumUtil) getExternalContext().getApplicationMap().get("enumUtil");
        }
        return enumUtil;
    }

    public MethodExpression getMethodExpression(String expression, Class<?> returnType, Class<?>[] argTypes) {
        Application app = getFacesContext().getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = getFacesContext().getELContext();
        return elFactory.createMethodExpression(elContext, expression, returnType, argTypes);
    }

    private ExpressionFactory getExpressionFactory() {
        Application app = getFacesContext().getApplication();
        return app.getExpressionFactory();
    }

    public ValueExpression createValueExpression(String expression, Class<?> expectedType) {
        return getExpressionFactory().createValueExpression(getFacesContext().getELContext(), expression, expectedType);
    }

    @SuppressWarnings("unchecked")
    public <T> T getObjectFor(String expression, Class<T> clazz) {
        FacesContext context = getFacesContext();
        return (T) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), expression, clazz).getValue(context.getELContext());

    }

    public UIComponent findComponent(final String id) {
        final UIComponent[] found = new UIComponent[1];
        getViewRoot().visitTree(new FullVisitContext(getFacesContext()), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if (component.getId().equals(id)) {
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;
            }
        });
        return found[0];
    }

}
