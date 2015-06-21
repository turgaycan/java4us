package com.java4us.documents.domain;

import com.java4us.domain.common.enums.Category;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.NestedField;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

/**
 * Created by turgaycan on 12/29/14.
 */
@Document(indexName = "feedmessagesearch", type = "feedmessagesearch", shards = 1, replicas = 0, refreshInterval = "-1", indexStoreType = "memory")
public class FeedMessageSearch {

    @Id
    private String id;
    private String title;
    @MultiField(
            mainField = @Field(type = String, index = analyzed),
            otherFields = {
                    @NestedField(dotSuffix = "untouched", type = String, store = true, index = not_analyzed),
                    @NestedField(dotSuffix = "sort", type = String, store = true, indexAnalyzer = "keyword")
            }
    )
    private String link;
    private Integer viewCount;
    private Integer goToLinkCount;
    private Category category;
    private String pubdate;


    public FeedMessageSearch() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setGoToLinkCount(Integer goToLinkCount) {
        this.goToLinkCount = goToLinkCount;
    }

    public Integer getGoToLinkCount() {
        return goToLinkCount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPubdate() {
        return pubdate;
    }
}
