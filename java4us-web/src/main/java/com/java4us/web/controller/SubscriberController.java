package com.java4us.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java4us.commons.service.member.SubscriberService;
import com.java4us.commons.utils.Clock;
import com.java4us.domain.Subscriber;
import com.java4us.service.json.JsonWriterService;
import com.java4us.web.controller.util.Java4UsUtils;
import com.java4us.web.controller.util.RegexUtil;

@Controller
public class SubscriberController {

	@Autowired
	private JsonWriterService jsonWriterService;

	@Autowired
	private SubscriberService subscriberService;

	@RequestMapping(value = "/newsubscriber", method = RequestMethod.GET)
	public void addNewSubscriber(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String name = request.getParameter("firstname");
		if (StringUtils.isEmpty(name)) {
			result.put("success", false);
			jsonWriterService.writeResponse(response, result);
			return;
		}

		String surname = request.getParameter("lastname");
		if (StringUtils.isEmpty(surname)) {
			result.put("success", false);
			jsonWriterService.writeResponse(response, result);
			return;
		}

		String email = request.getParameter("email");
		if (StringUtils.isEmpty(email)) {
			result.put("success", false);
			jsonWriterService.writeResponse(response, result);
			return;
		}
		email = Java4UsUtils.getNullCheckTrim(email);
		if (checkEmail(email)) {
			saveNewSubscriber(name, surname, email);
			result.put("success", true);
			jsonWriterService.writeResponse(response, result);
		} else {
			result.put("isAlreadyExists", true);
			jsonWriterService.writeResponse(response, result);
		}
	}

	private void saveNewSubscriber(String name, String surname, String email) {
		Subscriber subscriber = new Subscriber();
		subscriber.setName(name);
		subscriber.setSurname(surname);
		subscriber.setEmail(email);
		subscriber.setCreateDate(Clock.getTime());
		subscriberService.save(subscriber);
	}

	private boolean checkEmail(String email) {
		return RegexUtil.checkEmail(email)
				&& subscriberService.isProperEmail(email);
	}
}
