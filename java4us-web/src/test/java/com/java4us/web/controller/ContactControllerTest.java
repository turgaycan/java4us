package com.java4us.web.controller;

import com.java4us.commons.service.contact.ContactService;
import com.java4us.commons.service.security.XSSSecurityService;
import com.java4us.domain.Contact;
import com.java4us.domain.builder.ContactBuilder;
import com.java4us.service.auth.AuthenticationService;
import com.java4us.service.json.JsonWriterService;
import com.octo.captcha.service.CaptchaService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest({ContactController.class})
public class ContactControllerTest {

    @InjectMocks
    private ContactController controller;

    @Mock
    private XSSSecurityService xssSecurityService;

    @Mock
    private ContactService contactService;

    @Mock
    private JsonWriterService jsonWriterService;

    @Mock
    private CaptchaService captchaService;

    @Mock
    private AuthenticationService authenticationService;

    @Captor
    private ArgumentCaptor<Map<String, Object>> captor;

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();
    private MockHttpSession mockHttpSession;

    @Before
    public void init() {
        mockHttpSession = new MockHttpSession(request.getServletContext(), "1");
        request.setSession(mockHttpSession);
        when(captchaService.validateResponseForID(request.getSession().getId(), "test")).thenReturn(true);
        when(authenticationService.isJ4Authenticated()).thenReturn(true);
    }

    @Test
    public void shouldNotSendContactMessageIfContactObjectIsNull() {
        controller.saveContactMessage(null, request, response);
        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(false));
    }

    @Test
    public void shouldNotSendContactMessageIfEmailIsEmpty() {
        Contact contact = ContactBuilder.instance().content("").email("").buildWithId();

        controller.saveContactMessage(contact, request, response);

        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(false));
    }

    @Test
    public void shouldNotSendContactMessageIfEmailIsNotValid() {
        Contact contact = ContactBuilder.instance().content("").email("sdfsfsf..com").buildWithId();

        controller.saveContactMessage(contact, request, response);

        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(false));
    }

    @Test
    public void shouldNotSendContactMessageIfContentIsEmpty() {
        Contact contact = ContactBuilder.instance().content("").email("a@b.com").buildWithId();
        when(xssSecurityService.cleanTextForXSS(contact.getContent())).thenReturn("");

        controller.saveContactMessage(contact, request, response);

        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(false));
    }

    @Ignore
    @Test
    public void shouldNotSendContactMessageIfContentLengthIsHigherThanLimit() {
        Contact contact = ContactBuilder.instance().content("12334567890000").email("a@b.com").buildWithId();
        when(xssSecurityService.cleanTextForXSS(contact.getContent())).thenReturn("12334567890000");
        mockStatic(ContactController.class);
        PowerMockito.when(ContactController.getMaxLength()).thenReturn(10);

        controller.saveContactMessage(contact, request, response);

        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(false));
    }

    @Ignore
    @Test
    public void shouldSendContactMessage() {
        Contact contact = ContactBuilder.instance().content("12345qwer").email("a@b.com").buildWithId();
        when(xssSecurityService.cleanTextForXSS(contact.getContent())).thenReturn("12345qwer");
        mockStatic(ContactController.class);
        PowerMockito.when(ContactController.getMaxLength()).thenReturn(10);

        controller.saveContactMessage(contact, request, response);

        verify(contactService, times(1)).save(contact);
        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(true));
    }

}