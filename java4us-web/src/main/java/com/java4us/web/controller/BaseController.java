package com.java4us.web.controller;

import com.java4us.commons.utils.DateUtils;
import com.java4us.web.model.SeoMetaData;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by turgaycan on 12/21/14.
 */
public abstract class BaseController {

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response, String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("currentYear", DateUtils.getCurrentYear());
        return modelAndView;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("currentYear", DateUtils.getCurrentYear());
        return modelAndView;
    }

    protected ModelAndView handleRequestInternal(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("currentYear", DateUtils.getCurrentYear());
        return modelAndView;
    }

    protected ModelAndView handleRequestInternal(String viewName, SeoMetaData seoMetaData) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("currentYear", DateUtils.getCurrentYear());
        modelAndView.addObject("seoMetaData", seoMetaData);
        return modelAndView;
    }

}
