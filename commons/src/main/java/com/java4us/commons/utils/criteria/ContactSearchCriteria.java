package com.java4us.commons.utils.criteria;

/**
 * Created by turgaycan on 11/15/14.
 */
public class ContactSearchCriteria extends CommonSearchCriteria {

    private static final long serialVersionUID = 113407395150889603L;
    private String email;
    private String type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
