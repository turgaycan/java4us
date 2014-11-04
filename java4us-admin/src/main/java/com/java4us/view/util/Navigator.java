/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.view.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author turgay
 */
@ManagedBean(name = "navigator")
@SessionScoped
public class Navigator {

    private String pageToNavigate = "";

    public String navigateTo() {
        if ("ToSecure".equalsIgnoreCase(pageToNavigate)) {
            return "Secured";
        } else if ("ToUnSecure".equalsIgnoreCase(pageToNavigate)) {
            return "UnSecured";
        } else {
            //This will never happen but we will use this to extend this application
            return "none";
        }
    }

    public String getPageToNavigate() {
        return pageToNavigate;
    }

    public void setPageToNavigate(String option) {
        this.pageToNavigate = option;
    }
}
