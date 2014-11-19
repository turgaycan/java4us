/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain;

import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;
import com.java4us.domain.core.BaseEntity;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author turgay
 */
@Entity
@Table(name = "feed", schema = "java4us")
@SequenceGenerator(name = "feed_id_seq", sequenceName = "feed_id_seq", allocationSize = 1)
public class Feed extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1613079619759952018L;
	
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "feed_id_seq")
	private Long id;
	@Column(name = "link", nullable = false, unique = true)
	private String link;
	@Column(name = "lang")
	private String lang;
	@Column(name = "copyright")
	private boolean copyright;
	@Column(name = "pubdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date pubDate;
	@Column(name = "createdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@OneToMany(mappedBy = "feed")
	@BatchSize(size = 50)
	private List<FeedMessage> entries = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	private Feeder feeder;
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private Category category;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private BaseStatus status;

	public Feed() {
	}

	public Feed(String link, String lang, boolean copyright, BaseStatus status,
			Date pubDate, Date createDate) {
		this.link = link;
		this.lang = lang;
		this.copyright = copyright;
		this.status = status;
		this.pubDate = pubDate;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public boolean getCopyright() {
		return copyright;
	}

	public void setCopyright(boolean copyright) {
		this.copyright = copyright;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public List<FeedMessage> getEntries() {
		return entries;
	}

	public void setEntries(List<FeedMessage> entries) {
		this.entries = entries;
	}

	public Feeder getFeeder() {
		return feeder;
	}

	public void setFeeder(Feeder feeder) {
		this.feeder = feeder;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BaseStatus getStatus() {
		return status;
	}

	public void setStatus(BaseStatus status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
