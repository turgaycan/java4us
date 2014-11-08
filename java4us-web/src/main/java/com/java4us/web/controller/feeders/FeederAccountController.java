package com.java4us.web.controller.feeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.java4us.commons.service.feed.FeederService;
import com.java4us.domain.Feeder;
import com.java4us.domain.User;
import com.java4us.service.auth.AuthenticationService;
import com.java4us.web.model.FeederAccountModel;

@Service
@RequestMapping(value = "/feeders")
public class FeederAccountController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private FeederService feederService;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView getAccount() {

		if (authenticationService.isJ4NotAuthenticated()) {
			new RedirectView("error/403");
		}
		ModelAndView mav = new ModelAndView("feeders/account");
		mav.addObject("feederAccountModel", prepareModel());
		return mav;
	}

	private FeederAccountModel prepareModel() {
		FeederAccountModel model = FeederAccountModel.createEmptyModel();
		User currentUser = authenticationService.getCurrentUser();
		model.setUser(currentUser);
		Feeder currentFeeder = feederService
				.findByEmail(currentUser.getEmail());
		model.setFeeder(currentFeeder);
		model.setFeedList(currentFeeder.getFeeds());
		return model;
	}
}
