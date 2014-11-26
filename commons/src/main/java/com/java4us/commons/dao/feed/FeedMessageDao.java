/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao.feed;

import com.java4us.commons.dao.core.BaseDao;
import com.java4us.commons.utils.criteria.FeedMessageSearchCriteria;
import com.java4us.commons.utils.criteria.SearchCriteriaUtil;
import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author turgay
 */
@Repository
public class FeedMessageDao extends BaseDao<FeedMessage> {

    public FeedMessageDao() {
        super(FeedMessage.class);
    }

    @SuppressWarnings("unchecked")
    public List<FeedMessage> findNoneProceedMessages() {
        return getCriteria().add(Restrictions.eq("proceed", false)).list();
    }

    @SuppressWarnings("unchecked")
    public List<String> findAllProceedMessagesLinks() {
        return getCurrentSession()
                .createQuery(
                        "SELECT fm.link FROM FeedMessage fm WHERE fm.proceed =:proceed")
                .setParameter("proceed", true).list();
    }

    @SuppressWarnings("unchecked")
    public List<String> findAllJavaProceedMessagesLinks() {
        return getCurrentSession()
                .createQuery(
                        "SELECT fm.link FROM FeedMessage fm WHERE fm.proceed =:proceed AND fm.category =:category")
                .setParameter("proceed", true)
                .setParameter("category", Category.JAVA).list();
    }

    @SuppressWarnings("unchecked")
    public List<String> findAllAndroidProceedMessagesLinks() {
        return getCurrentSession()
                .createQuery(
                        "SELECT fm.link FROM FeedMessage fm WHERE fm.proceed =:proceed AND fm.category =:category")
                .setParameter("proceed", true)
                .setParameter("category", Category.ANDROID).list();
    }

    public List<FeedMessage> findByFeed(Feed feed, Date now, Date oneMonth) {
        return getStatusActiveCriteria().add(Restrictions.eq("feed", feed))
                .add(Restrictions.eq("proceed", true))
                        .addOrder(Order.desc("createDate"))
//                .add(Restrictions.between("createDate", now, oneMonth))
                .list();

    }

    @SuppressWarnings("unchecked")
    public List<FeedMessage> findPartialByFilter(int first, int pageSize,
                                                 String sortField, String sortOrder, FeedMessageSearchCriteria filter) {
        Criteria criteria = preparePartialFilter(first, pageSize, sortField,
                sortOrder, filter);
        criteria.setFirstResult(first);
        criteria.setMaxResults(pageSize);
        return criteria == null ? Collections.EMPTY_LIST : criteria.list();
    }

    private Criteria preparePartialFilter(int first, int pageSize,
                                          String sortField, String sortOrder, FeedMessageSearchCriteria filter) {
        Criteria criteria = getCriteria();
        filter.pushList(criteria);
        return getPreparedCriteria(criteria, filter, sortField, sortOrder);

    }

    private Criteria getPreparedCriteria(Criteria criteria,
                                         FeedMessageSearchCriteria filter, String sortField, String sortOrder) {
        prepareFeedMessageCriteria(criteria, filter);
        setupSortCriteria(criteria, getSortField(sortField),
                getSortOrder(sortOrder));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    public int getRowCountFeedMessageList(FeedMessageSearchCriteria filter) {
        Criteria criteria = SearchCriteriaUtil.restoreCountStateBy(filter,
                getCurrentSession());
        if (criteria == null) {
            criteria = getCriteria();
            filter.pushCount(criteria);
        }
        criteria = prepareFeedMessageCriteria(criteria, filter);
        return getRowCount(criteria);
    }

    private Criteria prepareFeedMessageCriteria(Criteria criteria,
                                                FeedMessageSearchCriteria filter) {
        if (filter == null) {
            return criteria;
        }

        if (filter.getStatus() != null) {
            appendFeedMessageStatusToCriteria(criteria, filter.getStatus());
        }

        if (StringUtils.isNotBlank(filter.getLink())) {
            appendFeedMessageLinkToCriteria(criteria, filter.getLink());
        }

        appendFeedToCriteria(criteria);

        if (StringUtils.isNotBlank(filter.getFeedLink())) {
            appendFeedLinkToCriteria(criteria, filter.getFeedLink());
        }

        appendFeedMessageProceed(criteria, filter.isProceed());

        if (filter.getCategory() != null
                && StringUtils.isNotBlank(filter.getCategory().name())) {
            appendFeedMessageCategoryToFeed(criteria, filter.getCategory());
        }
        return criteria;
    }

    private void appendFeedToCriteria(Criteria criteria) {
        criteria.createAlias("feed", "_feed", JoinType.LEFT_OUTER_JOIN);
    }

    private void appendFeedMessageProceed(Criteria criteria, boolean proceed) {
        criteria.add(Restrictions.eq("proceed", proceed));
    }

    private void appendFeedLinkToCriteria(Criteria criteria, String feedLink) {
        criteria.add(Restrictions.eq("_feed.link", feedLink));
    }

    private void appendFeedMessageCategoryToFeed(Criteria criteria,
                                                 Category category) {
        criteria.add(Restrictions.eq("category", category));
    }

    private void appendFeedMessageLinkToCriteria(Criteria criteria, String link) {
        criteria.add(Restrictions.eq("link", link));
    }

    private void appendFeedMessageStatusToCriteria(Criteria criteria,
                                                   BaseStatus status) {
        criteria.add(Restrictions.eq("status", status));
    }

    private String getSortField(String sortField) {
        return StringUtils.isNotBlank(sortField) ? sortField : "createDate";
    }

    private String getSortOrder(String sortOrder) {
        return StringUtils.isNotBlank(sortOrder) ? sortOrder : "desc";
    }

}
