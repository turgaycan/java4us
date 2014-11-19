package com.java4us.domain.builder;

import com.java4us.domain.Contact;

import java.util.Date;

public class ContactBuilder extends BaseBuilder<Contact, ContactBuilder> {
    private Long id;
    private String email;
    private String content;
    private Date createDate;
    private String type = "question";

    public static ContactBuilder instance(){
        return new ContactBuilder();
    }

    @Override
    protected Contact doBuild() {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setEmail(email);
        contact.setContent(content);
        contact.setCreateDate(createDate);
        contact.setType(type);
        return contact;
    }

    public ContactBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ContactBuilder email(String email) {
        this.email = email;
        return this;
    }

    public ContactBuilder content(String content) {
        this.content = content;
        return this;
    }

    public ContactBuilder type(String type) {
        this.type = type;
        return this;
    }

    public ContactBuilder createDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

}
