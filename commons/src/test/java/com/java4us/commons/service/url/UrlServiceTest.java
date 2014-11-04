/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java4us.commons.service.url;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author turgay
 */
@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {
    
    @InjectMocks
    private UrlService service;
    
    @Test
    public void shouldReturnPaginationUrl(){
       String pageUrl =  service.getPaginationUrl("java", 1);
       assertThat(pageUrl, equalTo("java/1"));
    }
}
