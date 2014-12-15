package com.java4us.web.controller;

import com.java4us.commons.service.url.UrlService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Controller
public class MailController {

    @Autowired
    private UrlService urlService;

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public ModelAndView mailTemplate(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("/mail/mailtemplate");
        modelAndView.addObject("subscriberId", String.valueOf(Base64.encodeBase64(String.valueOf(1234).getBytes())));
        modelAndView.addObject("rootUrl", urlService.getRootUrl());
        modelAndView.addObject("fullname", "Turgay Can");
        modelAndView.addObject("date", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        modelAndView.addObject("feedMessages", Arrays.asList("How to go my home", "How to do it", "Lets go home yankee"));
        return modelAndView;
    }
}
