package com.java4us.view.admin;

import com.java4us.jsf.views.BaseView;
import org.apache.shiro.SecurityUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "adminView")
@ViewScoped
public class AdminView extends BaseView {

	private static final long serialVersionUID = 8954458119522787458L;

	public boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}

}
