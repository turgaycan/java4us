/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.component.utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author turgay
 */
@Component
public class UserValidator implements Validator {

    @SuppressWarnings("rawtypes")
	@Override
    public boolean supports(Class arg) {
        return User.class.isAssignableFrom(arg);
    }

    @Override
    public void validate(Object arg, Errors err) {
        User user = (User) arg;
        if (!isValidEmailAddress(user.getUsername())) {
            err.rejectValue("email", "email.notValid", "Not a valid email");
        }

    }

    public boolean isValidEmailAddress(String emailAddress) {
        String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = emailAddress;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

}
