/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain;

import com.java4us.domain.core.BaseEntity;
import com.java4us.domain.common.enums.UserType;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author turgay
 */
@Entity
@Table(name = "user_roles", schema = "java4us")
@SequenceGenerator(name = "user_roles_id_seq", sequenceName = "user_roles_id_seq", allocationSize = 1)
public class UserRoles extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2581586561626494966L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_roles_id_seq")
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserType role;
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User userId;

	public UserRoles() {
	}

	public UserRoles(Long id) {
		this.id = id;
	}

	public UserRoles(Long id, UserType role) {
		this.id = id;
		this.role = role;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
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
		if (!(object instanceof UserRoles)) {
			return false;
		}
		UserRoles other = (UserRoles) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.java4us.domain.UserRoles[ id=" + id + " ]";
	}
}
