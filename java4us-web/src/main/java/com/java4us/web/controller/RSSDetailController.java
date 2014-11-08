package com.java4us.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java4us.domain.FeedMessage;
import com.java4us.service.feedmessage.FeedMessageJ4Service;
import com.java4us.web.controller.util.Java4UsUtils;

@Controller
public class RSSDetailController {

	@Autowired
	private FeedMessageJ4Service feedMessageJ4Service;

	@RequestMapping(value = "/rssdetail-r{rssId}", method = RequestMethod.GET)
	public ModelAndView findRssDetailById(@PathVariable("rssId") String rssId,
			HttpServletRequest request) throws Exception {
		if (!NumberUtils.isNumber(rssId)) {
			return Java4UsUtils.toModelAndView("error/301");
		}
		ModelAndView mav = new ModelAndView("rssdetail");
		Long id = Long.valueOf(rssId);
		FeedMessage feedMessage = feedMessageJ4Service.findFeedMessageById(id);
		if (feedMessage == null) {
			return Java4UsUtils.toModelAndView("error/301");
		}
		mav.addObject("feedMessage", feedMessage);
		return mav;
	}

}
