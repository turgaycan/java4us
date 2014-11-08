/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.web.controller;

import com.java4us.commons.utils.DateUtils;
import com.java4us.domain.FeedMessage;
import com.java4us.service.feedmessage.FeedMessageJ4Service;
import com.java4us.service.statistics.StatisticsService;
import com.java4us.web.model.StatisticModel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author turgay
 */
@Controller
public class IndexController {

	private static final int CURRENT_PAGE = 1;
	private static final int PAGE_SIZE = 30;

	@Autowired
	private FeedMessageJ4Service feedMessageJ4Service;

	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init2(HttpServletRequest request) throws Exception {
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("currentYear", DateUtils.getCurrentYear());
		mav.addObject("javaStatistics", getJavaStatisticModel());
		mav.addObject("androidStatistics", getAndroidStatisticModel());
		mav.addObject("feedJavaMessageList", getJavaFeedMessages());
		mav.addObject("feedAndroidMessageList", getAndroidFeedMessages());
		return mav;
	}

	private List<FeedMessage> getAndroidFeedMessages() throws Exception {
		return feedMessageJ4Service.getFeedMessages(CURRENT_PAGE, PAGE_SIZE)
				.getAndroidFeedMessages();
	}

	private List<FeedMessage> getJavaFeedMessages() throws Exception {
		return feedMessageJ4Service.getFeedMessages(CURRENT_PAGE, PAGE_SIZE)
				.getJavaFeedMessages();
	}

	private StatisticModel getAndroidStatisticModel() {
		return statisticsService.getAndroidStatisticModel();
	}

	@Cacheable("default")
	private StatisticModel getJavaStatisticModel() {
		return statisticsService.getJavaStatisticModel();
	}

}
