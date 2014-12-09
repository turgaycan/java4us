/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java4us.view;

import com.java4us.batch.component.AsyncAndroidWorker;
import com.java4us.batch.component.AsyncJavaWorker;
import com.java4us.jsf.views.BaseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * @author turgay
 */
@ManagedBean
@ViewScoped
public class BatchView extends BaseView {

    private static final long serialVersionUID = 4036297749300539248L;
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchView.class);

    @ManagedProperty(value = "#{asyncJavaWorker}")
    private transient AsyncJavaWorker asyncJavaWorker;

    @ManagedProperty(value = "#{asyncAndroidWorker}")
    private transient AsyncAndroidWorker asyncAndroidWorker;

    public void runJavaWorker() {
        if (asyncJavaWorker == null) {
            addMessage("error.message");
        }
        asyncJavaWorker.work();
        addMessage("success.run");
    }

    public void runAndroidWorker() {
        if (asyncAndroidWorker == null) {
            addMessage("error.message");
        }
        asyncAndroidWorker.work();
        addMessage("success.run");
    }

    public void setAsyncJavaWorker(AsyncJavaWorker asyncJavaWorker) {
        this.asyncJavaWorker = asyncJavaWorker;
    }

    public void setAsyncAndroidWorker(AsyncAndroidWorker asyncAndroidWorker) {
        this.asyncAndroidWorker = asyncAndroidWorker;
    }
}
