package com.java4us.documents.domain.builder;

import com.java4us.domain.common.enums.Category;
import com.java4us.documents.domain.FeedMessageSearch;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.util.Date;

/**
 * Created by turgaycan on 12/28/14.
 */
public class FeedMessageSearchBuilder {

    private FeedMessageSearch feedMessageSearch;

    public FeedMessageSearchBuilder(){
        feedMessageSearch = new FeedMessageSearch();
    }

    public FeedMessageSearch build() {
        return feedMessageSearch;
    }

    public FeedMessageSearchBuilder id(String id) {
        feedMessageSearch.setId(id);
        return this;
    }

    public FeedMessageSearchBuilder title(String title) {
        feedMessageSearch.setTitle(title);
        return this;
    }

    public FeedMessageSearchBuilder link(String link) {
        feedMessageSearch.setLink(link);
        return this;
    }

    public FeedMessageSearchBuilder viewCount(int viewCount) {
        feedMessageSearch.setViewCount(viewCount);
        return this;
    }

    public FeedMessageSearchBuilder gotoLinkCount(int gotoLinkCount) {
        feedMessageSearch.setGoToLinkCount(gotoLinkCount);
        return this;
    }

    public FeedMessageSearchBuilder category(Category category) {
        feedMessageSearch.setCategory(category);
        return this;
    }

    public FeedMessageSearchBuilder pubDate(Date pubDate) {
        feedMessageSearch.setPubdate(pubDate.toString());
        return this;
    }


    public IndexQuery buildIndex() {
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(feedMessageSearch.getId());
        indexQuery.setObject(feedMessageSearch);
        return indexQuery;
    }
}
