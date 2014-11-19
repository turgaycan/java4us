package com.java4us.view.admin;

import com.java4us.commons.service.contact.ContactService;
import com.java4us.commons.utils.criteria.ContactSearchCriteria;
import com.java4us.commons.utils.criteria.StatefulSearchCriteria;
import com.java4us.domain.Contact;
import com.java4us.jsf.model.LazyDataModel;
import com.java4us.jsf.model.StatefulLazyDataProdiver;
import com.java4us.jsf.views.BaseView;
import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.Map;

/**
 * Created by turgaycan on 11/15/14.
 */
@ViewScoped
@ManagedBean
public class ContactView extends BaseView implements
        StatefulLazyDataProdiver<Contact> {
    private static final long serialVersionUID = 7291699777464848179L;

    @ManagedProperty(value = "#{contactService}")
    private transient ContactService contactService;

    private ContactSearchCriteria filter;

    private Contact selectedContact;
    private LazyDataModel<Contact> contactList;

    @PostConstruct
    public void init() {
        filter = new ContactSearchCriteria();
        Map<String, Object> requestParameterMap = Faces.getExternalContext().getRequestMap();
        String typeValue = (String) requestParameterMap.get("type");
        filter.setType(typeValue);
        contactList = new LazyDataModel<>(this);
    }

    @Override
    public StatefulSearchCriteria getSearchFilter() {
        return getFilter();
    }

    @Override
    public List<Contact> loadLazyData(int first, int pageSize, String sortField, String sortOrder) {
        return contactService.findPartials(first, pageSize, sortField, sortOrder, filter);
    }

    @Override
    public int getRowCount() {
        return contactService.getRowCount(filter);
    }

    public void onSelect(RowEditEvent event) {
        selectedContact = (Contact) event.getObject();
    }

    public void reset() {
        setFilter(new ContactSearchCriteria());
    }

    public void search() {
        getSearchFilter().clear();
    }

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    public ContactSearchCriteria getFilter() {
        return filter;
    }

    public void setFilter(ContactSearchCriteria filter) {
        this.filter = filter;
    }

    public Contact getSelectedContact() {
        return selectedContact;
    }

    public void setSelectedContact(Contact selectedContact) {
        this.selectedContact = selectedContact;
    }

    public LazyDataModel<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(LazyDataModel<Contact> contactList) {
        this.contactList = contactList;
    }
}
