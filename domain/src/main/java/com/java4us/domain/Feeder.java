/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain;

import com.java4us.domain.core.BaseEntity;
import com.java4us.domain.common.enums.FeederStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.BatchSize;

/**
 *
 * @author turgay
 */
@Entity
@Table(name = "feeder", schema = "java4us")
@SequenceGenerator(name = "feeder_id_seq", sequenceName = "feeder_id_seq", allocationSize = 1)
public class Feeder extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -604482708575029600L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "feeder_id_seq")	
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "domain", nullable = false, unique = true)
	private String domain;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "email", nullable = false)
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private FeederStatus status;
	@Column(name = "createdate", nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date createDate;
	@OneToMany(mappedBy = "feeder", cascade = { CascadeType.ALL })
	@BatchSize(size = 50)
	private List<Feed> feeds = new ArrayList<>();

	public Feeder() {
	}

	public Feeder(Long id) {
		this.id = id;
	}

	public Feeder(String domain, String name, String surname, String email,
			FeederStatus status, Date createDate) {
		this.domain = domain;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.status = status;
		this.createDate = createDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	public FeederStatus getStatus() {
		return status;
	}

	public void setStatus(FeederStatus status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Feeder)) {
			return false;
		}
		Feeder other = (Feeder) object;
		return (this.id != null || other.id == null)
				&& (this.id == null || this.id.equals(other.id));
	}

	@Override
	public String toString() {
		return "com.java4us.domain.Subscriber[ id=" + id + " ]";
	}

}
