package com.java4us.service.user;

import com.java4us.commons.cache.CacheService;
import com.java4us.commons.dao.UserDao;
import com.java4us.commons.model.DeniedUsersModel;
import com.java4us.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by turgaycan on 6/21/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class J4UserServiceTest {

    @InjectMocks
    private J4UserService service;

    @Mock
    private UserDao userDao;

    @Mock
    private CacheService cacheService;

    @Test
    public void shouldReturnModelListFromCache() {
        DeniedUsersModel model = new DeniedUsersModel(Arrays.asList("turgay@can.com", "racip@aksoy.com", "sami@elmunda.com"));
        when(cacheService.get("deniedUsers")).thenReturn(model);

        DeniedUsersModel deniedUsersModel = service.findDeniedUsers();

        List<String> deniedUserList = deniedUsersModel.getDeniedUserList();
        assertThat(deniedUserList.size(), is(3));
        assertThat(deniedUserList, hasItems("turgay@can.com", "racip@aksoy.com", "sami@elmunda.com"));
    }

    @Test
    public void shouldReturnModelListFromDB() {
        when(cacheService.get("deniedUsers")).thenReturn(null);
        when(userDao.findDeniedUsers()).thenReturn(Arrays.asList(new User("turgay@can.com"), new User("racip@aksoy.com"), new User("sami@elmunda.com")));

        DeniedUsersModel deniedUsersModel = service.findDeniedUsers();

        List<String> deniedUserList = deniedUsersModel.getDeniedUserList();
        assertThat(deniedUserList.size(), is(3));
        assertThat(deniedUserList, hasItems("turgay@can.com", "racip@aksoy.com", "sami@elmunda.com"));
    }
}