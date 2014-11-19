package com.java4us.commons.service.security;


import com.java4us.domain.FeedMessage;
import com.java4us.domain.builder.FeedMessageBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class XSSSecurityServiceTest{

    @InjectMocks
    private XSSSecurityService service;

    @Test
    public void shouldCleanTextIfUserExternalUrlAddress(){
        FeedMessage feedMessage = new FeedMessageBuilder().description("123<a href=\"abc.com\" target=\"_blank\" />").buildWithId();
        service.cleanFeedMessageForXSS(feedMessage);

        assertThat(feedMessage.getDescription(), equalTo("123\n<a></a>"));
    }

    @Test
    public void shouldNotCleanJ4URLIsIsExitsInText(){
        FeedMessage feedMessage = new FeedMessageBuilder().description("123<a href=\"java4us.net/java\" target=\"_blank\" />").buildWithId();
        service.cleanFeedMessageForXSS(feedMessage);

        assertThat(feedMessage.getDescription(), equalTo("123\n<a href=\"http://java4us.net/java\"></a>"));
    }

}