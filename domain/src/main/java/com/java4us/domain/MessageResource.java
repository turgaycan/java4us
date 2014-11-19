/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain;

import com.java4us.domain.core.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author turgay
 */
@Entity
@Table(name = "message_resource", schema = "java4us")
@SequenceGenerator(name = "message_resource_id_seq", sequenceName = "message_resource_id_seq", allocationSize = 1)
public class MessageResource extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5893661464822206181L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "message_resource_id_seq")
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@Column(name = "category", length = 100, nullable = false)
	private String category;
	@Basic(optional = false)
	@Column(name = "key", length = 100, unique = true, nullable = false)
	private String key;
	@Basic(optional = false)
	@Column(name = "trvalue", length = 2048, nullable = false)
	private String trValue;
	@Basic(optional = false)
	@Column(name = "envalue", length = 2048, nullable = false)
	private String enValue;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTrValue() {
		return trValue;
	}

	public void setTrValue(String trValue) {
		this.trValue = trValue;
	}

	public String getEnValue() {
		return enValue;
	}

	public void setEnValue(String enValue) {
		this.enValue = enValue;
	}

}
