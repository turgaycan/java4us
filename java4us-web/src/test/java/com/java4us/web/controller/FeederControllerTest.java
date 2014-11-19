/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java4us.web.controller;

import com.java4us.commons.service.feed.FeederService;
import com.java4us.service.json.JsonWriterService;
import com.java4us.service.user.J4UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author turgay
 */

@RunWith(MockitoJUnitRunner.class)
public class FeederControllerTest {

	@InjectMocks
	private FeederController controller;

	@Mock
	private FeederService feederService;

	@Mock
	private JsonWriterService jsonWriterService;

	@Mock
	private J4UserService userService;

	@Captor
	private ArgumentCaptor<Map<String, Object>> captor;

	private MockHttpServletRequest request = new MockHttpServletRequest();
	private MockHttpServletResponse response = new MockHttpServletResponse();

	@Test
	public void shouldNotAddNewFeederIfDomainIsEmpty() {
		request.setParameter("domain", "");
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("domain"), equalTo(false));
	}

	@Test
	public void shouldNotaddNewFeederIfEmailIsEmpty() {
		request.setParameter("domain", "http://www.java4us.net");
		request.setParameter("email", "");
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("email"), equalTo(false));
	}

	@Test
	public void shouldNotaddNewFeederIfEmailFormatIsNotProper() {
		request.setParameter("domain", "http://www.java4us.net");
		request.setParameter("email", "info");
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("email"), equalTo(false));
	}

	@Test
	public void shouldNotaddNewFeederIfRssUrlsIsEmpty() {
		request.setParameter("domain", "http://www.java4us.net");
		request.setParameter("email", "info@java4us.net");
		request.setParameter("javaRssUrl", "");
		request.setParameter("androidRssUrl", "");
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("rssUrls"), equalTo(false));
	}

	@Ignore
	@Test
	public void shouldNotaddNewFeederIfJavaRssUrlIsNotEmptyButNotWellFormattedAndAdroidUrlIsEmpty() {
		request.setParameter("domain", "http://www.java4us.net");
		request.setParameter("email", "info@java4us.net");
		request.setParameter("javaRssUrl", "http://www.java4us/");
		request.setParameter("androidRssUrl", "");
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("javaUrl"), equalTo(false));
	}

	@Ignore
	@Test
	public void shouldNotaddNewFeederIfAndroidRssUrlIsNotEmptyButNotWellFormattedAndJavaUrlIsEmpty() {
		request.setParameter("domain", "http://www.java4us.net");
		request.setParameter("email", "info@java4us.net");
		request.setParameter("javaRssUrl", "");
		request.setParameter("androidRssUrl", "http://www.java4us");
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("androidUrl"), equalTo(false));
	}

	@Test
	public void shouldNotaddNewFeederIfDomainFormatIsNotProper() {
		request.setParameter("domain", "http://www.java4us");
		request.setParameter("email", "info@java4us.net");
		request.setParameter("javaRssUrl", "");
		request.setParameter("androidRssUrl", "http://www.java4us/android.rss");
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("domainFormat"), equalTo(false));
	}

	@Test
	public void shouldNotaddNewFeederIfDomainIsRegistered() {
		request.setParameter("domain", "http://www.java4us.net");
		request.setParameter("email", "info@java4us.net");
		request.setParameter("javaRssUrl", "");
		request.setParameter("androidRssUrl", "http://www.java4us/android.rss");
		when(feederService.isProperDomain(request.getParameter("domain")))
				.thenReturn(false);
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("domainFormat"), equalTo(false));
	}

	@Test
	public void shouldaddNewFeederIfJavaRssUrlIsWellFormatted() {
		request.setParameter("domain", "http://www.java4us.net");
		request.setParameter("email", "info@java4us.net");
		request.setParameter("javaRssUrl", "http://www.java4us/java.rss");
		request.setParameter("androidRssUrl", "");
		when(feederService.isProperDomain(request.getParameter("domain")))
				.thenReturn(true);
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("success"), equalTo(true));
	}

	@Test
	public void shouldaddNewFeederIfAndroidRssUrlIsWellFormatted() {
		request.setParameter("domain", "http://www.java4us.net");
		request.setParameter("email", "info@java4us.net");
		request.setParameter("javaRssUrl", "");
		request.setParameter("androidRssUrl", "http://www.java4us/android.rss");
		when(feederService.isProperDomain(request.getParameter("domain")))
				.thenReturn(true);
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("success"), equalTo(true));
	}

	@Test
	public void shouldaddNewFeederIfRssUrlsAreWellFormatted() {
		request.setParameter("domain", "http://www.java4us.net");
		request.setParameter("email", "info@java4us.net");
		request.setParameter("javaRssUrl", "http://www.java4us/java.rss");
		request.setParameter("androidRssUrl", "http://www.java4us/android.rss");
		when(feederService.isProperDomain(request.getParameter("domain")))
				.thenReturn(true);
		controller.addNewFeeder(request, response);
		verify(jsonWriterService).writeResponse(
				(HttpServletResponse) anyObject(), captor.capture());
		Map<String, Object> value = captor.getValue();
		assertThat((Boolean) value.get("success"), equalTo(true));
	}

}
