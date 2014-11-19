package com.java4us.domain;

import com.java4us.domain.core.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "contact", schema = "java4us")
@SequenceGenerator(name = "contact_id_seq", sequenceName = "contact_id_seq", allocationSize = 1)
public class Contact extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2394201645492976044L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "contact_id_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "content", length = 4000, nullable = false)
    private String content;
    @Column(name = "type", length = 50, nullable = false)
    private String type;
    @Column(name = "createdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    public Contact() {
    }

    public Contact(String email, String content, String type) {
        this.email = email;
        this.content = content;
        this.type = type;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
