/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.jsf.exceptions;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import org.omnifaces.exceptionhandler.FullAjaxExceptionHandlerFactory;

/**
 *
 * @author turgay
 */
public class CustomExceptionHandlerFactory extends FullAjaxExceptionHandlerFactory {

    public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        super(parent);
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler eh = getWrapped().getExceptionHandler();
        return new CustomExceptionHandler(eh);
    }

}
