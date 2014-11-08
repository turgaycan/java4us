/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.web.controller;

import com.java4us.commons.service.feed.FeederService;
import com.java4us.commons.utils.Clock;
import com.java4us.domain.Feed;
import com.java4us.domain.Feeder;
import com.java4us.domain.User;
import com.java4us.domain.UserRoles;
import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;
import com.java4us.domain.common.enums.FeederStatus;
import com.java4us.domain.common.enums.UserType;
import com.java4us.service.json.JsonWriterService;
import com.java4us.service.user.J4UserService;
import com.java4us.web.controller.util.Java4UsUtils;
import com.java4us.web.controller.util.RegexUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/kayit")
public class FeederController {

	private static Logger LOGGER = LoggerFactory
			.getLogger(FeederController.class);
	private static final String HTTP = "http://";
	private static final int PASSWORDLENGTH = 8;

	@Autowired
	private FeederService feederService;

	@Autowired
	private JsonWriterService jsonWriterService;

	@Autowired
	private J4UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("currentYear", 2014);
		return mav;
	}

	@RequestMapping(value = "/newfeeder", method = RequestMethod.GET)
	public void addNewFeeder(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String domain = request.getParameter("domain");
		if (checkDomain(domain, result, response)) {
			LOGGER.info("Domain is not proper {}" + domain);
			return;
		}
		String email = request.getParameter("email");
		if (checkEmail(email, result, response)) {
			LOGGER.info("Email is not proper {}" + email);
			return;
		}
		String javaRssUrl = request.getParameter("javaRssUrl");
		String androidRssUrl = request.getParameter("androidRssUrl");
		List<Feed> entries = new ArrayList<Feed>();
		if (checkRssUrls(javaRssUrl, androidRssUrl, result, response, entries)) {
			LOGGER.info("Java RSS Url is not proper {}" + javaRssUrl);
			return;
		}
		String firstname = request.getParameter("firstname");
		String surname = request.getParameter("lastname");
		domain = Java4UsUtils.getNullCheckTrim(domain);
		if (checkDomain(domain)) {
			saveNewFeeder(domain, email, firstname, surname, entries);
			try {
				userService
						.saveWithPasswordEncrypting(prepareUserToFeeder(email));
			} catch (Exception ex) {
				result.put("success", false);
				jsonWriterService.writeResponse(response, result);
				LOGGER.info("Success {}" + javaRssUrl);
				return;
			}

			result.put("success", true);
			jsonWriterService.writeResponse(response, result);
			LOGGER.info("Success {}" + javaRssUrl);
		} else {
			result.put("domainFormat", false);
			jsonWriterService.writeResponse(response, result);
			LOGGER.info("Domain format is not proper {}" + domain);
		}
	}

	private User prepareUserToFeeder(String email) {
		User user = new User(email, "notlogin",
				Java4UsUtils.randomString(PASSWORDLENGTH), Clock.getTime(),
				BaseStatus.Deactive);
		Set<UserRoles> roleList = new HashSet<>();
		UserRoles roles = new UserRoles();
		roles.setRole(UserType.ROLE_USER);
		roles.setUserId(user);
		roleList.add(roles);
		user.setUserRoles(roleList);
		return user;
	}

	private boolean checkDomain(String domain, Map<String, Object> result,
			HttpServletResponse response) {
		if (StringUtils.isEmpty(domain)) {
			result.put("domain", false);
			jsonWriterService.writeResponse(response, result);
			return true;
		}
		return false;
	}

	private boolean checkEmail(String email, Map<String, Object> result,
			HttpServletResponse response) {
		if (StringUtils.isEmpty(email) || !RegexUtil.checkEmail(email)) {
			result.put("email", false);
			jsonWriterService.writeResponse(response, result);
			return true;
		}
		return false;
	}

	private boolean checkRssUrls(String javaRssUrl, String androidRssUrl,
			Map<String, Object> result, HttpServletResponse response,
			List<Feed> entries) {
		if (StringUtils.isEmpty(javaRssUrl)
				&& StringUtils.isEmpty(androidRssUrl)) {
			result.put("rssUrls", false);
			jsonWriterService.writeResponse(response, result);
			return true;
		}
		if ((StringUtils.isEmpty(javaRssUrl) || !RegexUtil.isUrl(javaRssUrl))
				&& StringUtils.isEmpty(androidRssUrl)) {
			result.put("javaUrl", false);
			jsonWriterService.writeResponse(response, result);
			return true;
		} else if (StringUtils.isNotEmpty(javaRssUrl)) {
			javaRssUrl = appendHttp(javaRssUrl);
			Feed javaFeed = new Feed(javaRssUrl, "TR", false,
					BaseStatus.Deactive, Clock.getTime(), Clock.getTime());
			javaFeed.setCategory(Category.JAVA);
			entries.add(javaFeed);
		}
		if ((StringUtils.isEmpty(androidRssUrl) || !RegexUtil
				.isUrl(androidRssUrl)) && StringUtils.isEmpty(javaRssUrl)) {
			result.put("androidUrl", false);
			jsonWriterService.writeResponse(response, result);
			return true;
		} else if (StringUtils.isNotEmpty(androidRssUrl)) {
			androidRssUrl = appendHttp(androidRssUrl);
			Feed androidFeed = new Feed(androidRssUrl, "TR", false,
					BaseStatus.Deactive, Clock.getTime(), Clock.getTime());
			androidFeed.setCategory(Category.ANDROID);
			entries.add(androidFeed);
		}
		return false;
	}

	private boolean checkDomain(String domain) {
		return (RegexUtil.isUrl(domain) && feederService.isProperDomain(domain));
	}

	private void saveNewFeeder(String domain, String email, String firstname,
			String surname, List<Feed> entries) {
		Feeder feeder = new Feeder(domain, firstname, surname, email,
				FeederStatus.WAITING, Clock.getTime());
		for (Feed feed : entries) {
			feed.setFeeder(feeder);
		}
		feeder.setFeeds(entries);
		feederService.save(feeder);
	}

	private String appendHttp(String url) {
		if (!url.startsWith(HTTP)) {
			return (HTTP + url);
		} else {
			return url;
		}
	}
}
