package com.java4us.web.controller;

import com.java4us.domain.common.enums.Category;
import com.java4us.service.feedmessage.FeedMessageJ4Service;
import com.java4us.web.controller.util.Java4UsUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/java")
@Controller
public class JavaController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaController.class);

    private static final int DEFAULTPAGENUM = 1;

    @Autowired
    private FeedMessageJ4Service feedMessageJ4Service;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findJavaFeedMessages() {
        ModelAndView mav = super.handleRequestInternal("/java");
        mav.addObject("pagingMessages", feedMessageJ4Service.pagingFeedMessages(Category.JAVA, DEFAULTPAGENUM));
        return mav;
    }

    @RequestMapping(value = {"/P/{pageNum:\\d+$}", "/p/{pageNum:\\d+$}"}, method = RequestMethod.GET)
    public ModelAndView findJavaFeedMessagesByPaging(@PathVariable("pageNum") String pageNum,
                                                     HttpServletRequest request) {
        ModelAndView mav = super.handleRequestInternal("/java");
        if (!NumberUtils.isNumber(pageNum)) {
            LOGGER.error("Page Number format exception {}", pageNum);
            return Java4UsUtils.toModelAndView("/error/301");
        }
        int pageNumber = Integer.parseInt(pageNum);
        mav.addObject("pagingMessages", feedMessageJ4Service.pagingFeedMessages(Category.JAVA, pageNumber));
        return mav;
    }

}
