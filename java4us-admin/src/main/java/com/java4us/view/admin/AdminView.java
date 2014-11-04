package com.java4us.view.admin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.shiro.SecurityUtils;

import com.java4us.jsf.views.BaseView;

@ManagedBean(name = "adminView")
@ViewScoped
public class AdminView extends BaseView {

	private static final long serialVersionUID = 8954458119522787458L;

	public boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}

}
