package com.java4us.commons.service.contact;

import com.java4us.commons.dao.contact.ContactDao;
import com.java4us.commons.utils.criteria.ContactSearchCriteria;
import com.java4us.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by turgaycan on 11/15/14.
 */
@Service
public class ContactService {

    @Autowired
    private ContactDao contactDao;

    @Transactional(readOnly = true)
    public List<Contact> findPartials(int first, int size, String sortField, String sortOrder, ContactSearchCriteria filter) {
        return contactDao.findPartialByFilter(first, size, sortField, sortOrder, filter);
    }

    @Transactional
    public void save(Contact contact){
        contactDao.persist(contact);
    }

    @Transactional(readOnly = true)
    public int getRowCount(ContactSearchCriteria filter){
        return contactDao.getRowCount(filter);
    }
}
