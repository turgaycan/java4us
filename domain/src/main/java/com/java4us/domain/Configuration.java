/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain;

import com.java4us.domain.core.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author turgay
 */
@Entity
@Table(name = "configuration", schema = "java4us")
@SequenceGenerator(name = "configuration_id_seq", sequenceName = "configuration_id_seq", allocationSize = 1)
public class Configuration extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 8375886582702705923L;

	public enum ConfigCategory {

        Batch("Batch");

        private final String name;

        private ConfigCategory(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator ="configuration_id_seq")
    private Long id;
    @Column(name = "category", length = 250, nullable = false)
    @Enumerated(EnumType.STRING)
    private ConfigCategory category;
    @Column(name = "key", length = 250, unique = true, nullable = false)
    private String key;
    @Column(name = "value", length = 250, nullable = false)
    private String value;

    public Configuration() {
    }

    public Configuration(ConfigCategory category, String key, String value) {
        this.category = category;
        this.key = key;
        this.value = value;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ConfigCategory getCategory() {
        return category;
    }

    public void setCategory(ConfigCategory category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
