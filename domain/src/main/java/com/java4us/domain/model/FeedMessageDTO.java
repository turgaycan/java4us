package com.java4us.domain.model;

import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by turgaycan on 11/23/14.
 */
public class FeedMessageDTO implements Serializable {

    private static final long serialVersionUID = -651057710098219919L;
    private Long id;
    private String title;
    private String link;
    private Date pubDate;
    private Date createDate;
    private Category category;
    private BaseStatus status;


    public FeedMessageDTO(Long id, String title, String link, Date pubDate, Date createDate, Category category, BaseStatus status) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.createDate = createDate;
        this.category = category;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
}
