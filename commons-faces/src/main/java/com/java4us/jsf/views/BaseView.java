/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.jsf.views;

/**
 *
 * @author turgay
 */

import com.java4us.domain.core.BaseEntity;
import com.java4us.jsf.util.FacesHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

public abstract class BaseView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3241856412532329190L;

    @ManagedProperty("#{facesHelper}")
    private FacesHelper facesHelper;

    private Subject subject = SecurityUtils.getSubject();

    public FacesHelper getFacesHelper() {
        return facesHelper;
    }

    public void setFacesHelper(FacesHelper facesHelper) {
        this.facesHelper = facesHelper;
    }

    public Subject getSubject() {
        return subject;
    }

    protected void addDeleteMessage(BaseEntity... entities) {
        if (entities == null || entities.length == 0) {
            return;
        }
        if (entities.length > 1) {
            addMessage("message.default.deletedAll");
        } else {
            addMessage("message.default.deleted");
        }
    }

    public void addMessage(String key) {
        getFacesHelper().addMessageKeyFromBundle(FacesMessage.SEVERITY_INFO,
                null, key);
    }

    protected void addErrorMessage(String key, Object... params) {
        getFacesHelper().addMessageKeyFromBundle(FacesMessage.SEVERITY_ERROR,
                null, key, params);
    }

    protected void addErrorMessage(String key, String id, Object... params) {
        getFacesHelper().addMessageKeyFromBundle(FacesMessage.SEVERITY_ERROR,
                id, key, params);
    }

    protected void addUpdateMessage(BaseEntity... entities) {
        if (entities == null || entities.length == 0) {
            return;
        }
        if (entities.length > 1) {
            addMessage("message.default.updatedAll");
        } else {
            addMessage("message.default.updated");
        }
    }

    protected void addUpdateFailedMessage(BaseEntity... entities) {
        if (entities == null || entities.length == 0) {
            return;
        }
        addMessage("message.default.updatedFailed");
    }

    protected void addPersistMessage(BaseEntity object) {
        if (object == null) {
            return;
        }
        if (object.isPersisted()) {
            addMessage("message.default.updated");
        } else {
            addMessage("message.default.created");
        }
    }

    protected void addPersistMessage() {
        addMessage("message.default.created");
    }

    protected void addUpdateMessage() {
        addMessage("message.default.updated");
    }

    protected void addSaveMessage() {
        addMessage("message.default.saved");
    }

}
