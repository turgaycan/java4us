package com.java4us.web.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "/301", method = RequestMethod.GET)
    public ModelAndView error301Page(HttpServletRequest request) {
        return new ModelAndView("/error/301");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView error404Page(HttpServletRequest request) {
        return new ModelAndView("/error/403");
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public ModelAndView error500Page(HttpServletRequest request) {
        return new ModelAndView("/error/500");
    }
}
