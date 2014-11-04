package com.java4us.web.controller.feeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.java4us.service.auth.AuthenticationService;

@Service
@RequestMapping(value = "/feeders")
public class FeederAccountController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView getAccount(){
		
		if(authenticationService.isJ4NotAuthenticated()){
			new RedirectView("error/403");
		}
		ModelAndView mav = new ModelAndView("feeders/account");
		mav.addObject("username", authenticationService.getCurrentUser().getUsername());
		return mav;
	}
}
