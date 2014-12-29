package com.java4us.commons.service.search;

import com.java4us.commons.dao.search.repositories.FeedMessageSearchRepository;
import com.java4us.documents.criteria.SearchCriteria;
import com.java4us.documents.domain.FeedMessageSearch;
import com.java4us.domain.common.enums.Category;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.elasticsearch.index.query.FilterBuilders.boolFilter;
import static org.elasticsearch.index.query.FilterBuilders.termFilter;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * Created by turgaycan on 12/24/14.
 */
@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private FeedMessageSearchRepository feedMessageSearchRepository;


    public Page<FeedMessageSearch> search(Category category, String query, SearchCriteria criteria) {
        SortBuilder sortBuilder = new FieldSortBuilder(criteria.getSortField());
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withSort(sortBuilder)
                .withQuery(matchAllQuery())
                .withFilter(boolFilter().must(termFilter("id", 123L)))
                .build();
        Page<FeedMessageSearch> feedMessagePage =
                elasticsearchTemplate.queryForPage(searchQuery, FeedMessageSearch.class);
        return feedMessagePage;
    }

    public void bulkIndexFeedMessages(List<FeedMessageSearch> feedMessageSearchList){
        LOGGER.info("Bulk index started..");
        feedMessageSearchRepository.save(feedMessageSearchList);
        LOGGER.info("Bulk index finished..");
    }

}
