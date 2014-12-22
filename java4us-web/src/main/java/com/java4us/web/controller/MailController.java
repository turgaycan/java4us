package com.java4us.web.controller;

import com.java4us.commons.service.url.UrlService;
import com.java4us.service.feedmessage.FeedMessageJ4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class MailController extends BaseController{

    @Autowired
    private UrlService urlService;

    @Autowired
    private FeedMessageJ4Service findWeeklyFeedMessages;

    @RequestMapping(value = "/weekly-feedmessages", method = RequestMethod.GET)
    public ModelAndView mailTemplate(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = super.handleRequestInternal("/mail/mailtemplate");
        modelAndView.addObject("rootUrl", urlService.getRootUrl());
        modelAndView.addObject("currentDate", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        modelAndView.addObject("feedMessages", findWeeklyFeedMessages.findWeeklyFeedMessages());
        return modelAndView;
    }
}
