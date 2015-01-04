package com.java4us.commons.service.search;

import com.java4us.commons.dao.search.repositories.FeedMessageSearchRepository;
import com.java4us.documents.criteria.SearchCriteria;
import com.java4us.documents.domain.FeedMessageSearch;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<FeedMessageSearch> search(SearchCriteria criteria) {
        SortBuilder sortBuilder = new FieldSortBuilder(criteria.getSortField()).order(SortOrder.DESC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(new PageRequest(criteria.getPaginationModel().getPageSize(), criteria.getPaginationModel().getCurrentPage()))
                .withSort(sortBuilder)
                .withQuery(matchAllQuery())
                .withFilter(termFilter("title", criteria.getQuery()))
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

    public void index(FeedMessageSearch feedMessageSearch) {
        feedMessageSearchRepository.index(feedMessageSearch);
    }

}
