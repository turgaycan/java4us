package com.java4us.web.controller;

import com.java4us.web.controller.util.Java4UsUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/android")
@Controller
public class AndroidController extends CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findAndroidFeedMessages() {
        return prepareJavaModelAndView(ANDROIDVIEWNAME, DEFAULTPAGENUM);
    }

    @RequestMapping(value = {"/P/{pageNum:\\d+$}", "/p/{pageNum:\\d+$}"}, method = RequestMethod.GET)
    public ModelAndView findAndroidFeedMessagesByPaging(@PathVariable("pageNum") String pageNum,
                                                        HttpServletRequest request) {
        if (!NumberUtils.isNumber(pageNum)) {
            LOGGER.error("Page Number format exception {}", pageNum);
            return Java4UsUtils.toModelAndView("/error/301");
        }
        int pageNumber = Integer.parseInt(pageNum);
        return prepareJavaModelAndView(ANDROIDVIEWNAME, pageNumber);
    }
}
