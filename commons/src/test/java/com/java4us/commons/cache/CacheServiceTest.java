/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.cache;

import com.couchbase.client.CouchbaseClient;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author turgay
 */
@RunWith(MockitoJUnitRunner.class)
public class CacheServiceTest {

    @InjectMocks
    private CacheService service;

    @Mock
    private CouchbaseClient couchbaseClient;

    @Test
    public void shouldTryGetKeyAndValue() throws Exception {
        when(couchbaseClient.get("test")).thenReturn(1234);

        Integer val = service.get("test");

        verify(couchbaseClient, times(1)).get("test");

        assertThat(val, equalTo(1234));
    }
}
