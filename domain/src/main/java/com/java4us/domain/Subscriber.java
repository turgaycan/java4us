/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain;

import com.java4us.domain.core.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author turgay
 */
@Entity
@Table(name = "subscriber", schema = "java4us")
@SequenceGenerator(name = "subscriber_id_seq", sequenceName = "subscriber_id_seq", allocationSize = 1)
public class Subscriber extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5018510556613059234L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "subscriber_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Basic(optional = false)
    @Column(name = "surname", length = 100, nullable = false)
    private String surname;
    @Basic(optional = false)
    @Column(name = "email", unique = true, length = 250, nullable = false)
    private String email;
    @Column(name = "createdate", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "allowtomail")
    private boolean allowtomail;

    public Subscriber() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isAllowtomail() {
        return allowtomail;
    }

    public void setAllowtomail(boolean allowtomail) {
        this.allowtomail = allowtomail;
    }

    public String fullName() {
        return name + " " + surname;
    }
}
