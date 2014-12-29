package com.java4us.commons.dao.search.repositories;

import com.java4us.documents.domain.FeedMessageSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by turgaycan on 12/28/14.
 */
public interface FeedMessageSearchRepository extends ElasticsearchRepository<FeedMessageSearch, String> {

//    public List<FeedMessageSearch> findByFeedMessages(String query, SearchCriteria criteria, Pageable pageable);
}
