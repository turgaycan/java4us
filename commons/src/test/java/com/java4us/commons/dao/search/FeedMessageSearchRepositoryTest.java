package com.java4us.commons.dao.search;

import com.java4us.commons.dao.core.AbstractElasticSearchTest;
import com.java4us.commons.dao.search.repositories.FeedMessageSearchRepository;
import com.java4us.commons.utils.Clock;
import com.java4us.documents.domain.FeedMessageSearch;
import com.java4us.documents.domain.builder.FeedMessageSearchBuilder;
import com.java4us.domain.builder.utils.TestDateUtils;
import com.java4us.domain.common.enums.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class FeedMessageSearchRepositoryTest extends AbstractElasticSearchTest{

    @Resource
    private FeedMessageSearchRepository feedMessageSearchRepository;

    @Before
    public void emptyData() {
        feedMessageSearchRepository.deleteAll();
    }

    @Test
    public void shouldIndexSingleFeedMessageSearchDocument() {
        Clock.freeze();
        Clock.setTime(TestDateUtils.toDate("10-10-2010"));
        FeedMessageSearch feedMessageSearch = new FeedMessageSearchBuilder().id("12345").title("Difference between servlet and applicationContext xml in Spring framewok").category(Category.ANDROID)
                .link("http://www.java4us.net/difference-between-servlet-and-applicationcontext-xml-in-spring-framewok-r15873817").pubDate(TestDateUtils.toDate("10-10-2010"))
                .viewCount(1).gotoLinkCount(10).build();
        //Indexing using sampleArticleRepository
        feedMessageSearchRepository.save(feedMessageSearch);
        //lets try to search same record in elasticsearch
        FeedMessageSearch indexedFeedMessageSearch = feedMessageSearchRepository.findOne(feedMessageSearch.getId());
        assertThat(indexedFeedMessageSearch, is(notNullValue()));
        assertThat(indexedFeedMessageSearch.getId(), is(feedMessageSearch.getId()));
        Clock.unfreeze();
    }
}