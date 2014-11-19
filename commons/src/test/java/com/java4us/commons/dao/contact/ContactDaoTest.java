package com.java4us.commons.dao.contact;

import com.java4us.commons.dao.core.AbstractDataAccessTest;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.criteria.ContactSearchCriteria;
import com.java4us.domain.Contact;
import com.java4us.domain.builder.ContactBuilder;
import com.java4us.domain.builder.utils.TestDateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

public class ContactDaoTest extends AbstractDataAccessTest {

    @Autowired
    private ContactDao dao;

    private ContactSearchCriteria filter = new ContactSearchCriteria();

    @Test
    public void shouldReturnContactListByCreateDate() {
        Clock.freeze();
        Clock.setTime(TestDateUtils.toDate("10-10-2010"));
        Contact contact1 = ContactBuilder.instance().id(1L).deleted(true).content("tısss").email("abc@a.com").createDate(TestDateUtils.toDate("09-10-2010")).persist(getSession());
        Contact contact2 = ContactBuilder.instance().id(2L).content("tısss").email("abc@a.com").createDate(TestDateUtils.toDate("07-10-2010")).persist(getSession());
        Contact contact3 = ContactBuilder.instance().id(3L).content("tısss").email("abc@a.com").createDate(TestDateUtils.toDate("06-10-2010")).persist(getSession());
        Contact contact4 = ContactBuilder.instance().id(4L).content("tısss").email("abc@a.com").createDate(TestDateUtils.toDate("08-10-2010")).persist(getSession());

        flushAndClear();

        List<Contact> contacts = dao.findPartialByFilter(0, 10, "", "", filter);

        assertThat(contacts.size(), equalTo(3));
        assertThat(contacts, hasItems(contact2, contact3, contact4));
        assertThat(contacts.get(0), equalTo(contact4));
        assertThat(contacts.get(1), equalTo(contact2));
        assertThat(contacts.get(2), equalTo(contact3));

        Clock.unfreeze();
    }
}