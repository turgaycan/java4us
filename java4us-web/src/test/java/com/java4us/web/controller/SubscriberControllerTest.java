package com.java4us.web.controller;

import com.java4us.commons.service.member.SubscriberService;
import com.java4us.service.json.JsonWriterService;
import com.java4us.web.controller.subscriber.SubscriberController;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubscriberControllerTest{

    @InjectMocks
    private SubscriberController controller;

    @Mock
    private JsonWriterService jsonWriterService;

    @Mock
    private SubscriberService subscriberService;

    @Captor
    private ArgumentCaptor<Map<String, Object>> captor;

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();

    @Test
    public void shouldNotAddNewSubscriberIfFirstNameIsEmpty(){
        request.addParameter("firstname", "");

        controller.addNewSubscriber(request, response);

        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(false));
    }

    @Test
    public void shouldNotAddNewSubscriberIflastNameIsEmpty(){
        request.addParameter("firstname", "turgay");
        request.addParameter("lastname", "");

        controller.addNewSubscriber(request, response);

        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(false));
    }

    @Test
    public void shouldNotAddNewSubscriberIfEmailIsEmpty(){
        request.addParameter("firstname", "turgay");
        request.addParameter("lastname", "can");
        request.addParameter("email", "");
        controller.addNewSubscriber(request, response);

        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(false));
    }

    @Test
    public void shouldNotAddNewSubscriberIfEmailIsNotValid(){
        request.addParameter("firstname", "turgay");
        request.addParameter("lastname", "can");
        request.addParameter("email", "asfasfa");
        when(subscriberService.isProperEmail("asfasfa")).thenReturn(true);

        controller.addNewSubscriber(request, response);

        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("isAlreadyExists"), equalTo(true));
    }

    @Test
    public void shouldNotAddNewSubscriberIfEmailIsValidButRegisteredPreviously(){
        request.addParameter("firstname", "turgay");
        request.addParameter("lastname", "can");
        request.addParameter("email", "abc@a.com");
        when(subscriberService.isProperEmail("abc@a.com")).thenReturn(false);

        controller.addNewSubscriber(request, response);

        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("isAlreadyExists"), equalTo(true));
    }

    @Test
    public void shouldAddNewSubscriber(){
        request.addParameter("firstname", "turgay");
        request.addParameter("lastname", "can");
        request.addParameter("email", "abc@a.com");

        when(subscriberService.isProperEmail("abc@a.com")).thenReturn(true);

        controller.addNewSubscriber(request, response);

        verify(subscriberService, times(1)).save(anyObject());
        verify(jsonWriterService).writeResponse(
                (HttpServletResponse) anyObject(), captor.capture());
        Map<String, Object> value = captor.getValue();
        assertThat((Boolean) value.get("success"), equalTo(true));
    }

}