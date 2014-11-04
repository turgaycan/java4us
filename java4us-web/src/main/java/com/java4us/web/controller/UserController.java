/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.web.controller;

import com.java4us.domain.User;
import com.java4us.commons.service.member.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author turgay
 */
@Controller
@RequestMapping(value = "/admin")
public class UserController {

    @Autowired
    private UserService adminService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAdmin() {
        ModelAndView mav = new ModelAndView("admin");
        User admin = adminService.findById(1L);
        mav.addObject("admin", admin);
        return mav;
    }

}
