/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author turgay
 */
public class DeniedUsersModel implements Serializable {

	private static final long serialVersionUID = 3354587395733827332L;

    public DeniedUsersModel() {
    }

    public DeniedUsersModel(List<String> deniedUserList) {
        this.deniedUserList = deniedUserList;
    }

    private List<String> deniedUserList;

    public List<String> getDeniedUserList() {
        return deniedUserList;
    }

    public void setDeniedUserList(List<String> deniedUserList) {
        this.deniedUserList = deniedUserList;
    }

}
