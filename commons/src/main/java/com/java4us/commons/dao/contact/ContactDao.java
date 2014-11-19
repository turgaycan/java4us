package com.java4us.commons.dao.contact;

import com.java4us.commons.dao.core.BaseDao;
import com.java4us.commons.utils.criteria.ContactSearchCriteria;
import com.java4us.commons.utils.criteria.SearchCriteriaUtil;
import com.java4us.domain.Contact;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ContactDao extends BaseDao<Contact> {


    public ContactDao() {
        super(Contact.class);
    }

    public List<Contact> findPartialByFilter(int first, int pageSize,
                                             String sortField, String sortOrder, ContactSearchCriteria filter) {
        Criteria criteria = preparePartialFilter(sortField,
                sortOrder, filter);
        criteria.setFirstResult(first);
        criteria.setMaxResults(pageSize);
        return criteria == null ? Collections.EMPTY_LIST : criteria.list();
    }

    public int getRowCount(ContactSearchCriteria filter) {
        Criteria criteria = getCriteria();
        if (criteria == null) {
            criteria = getCriteria();
            filter.pushCount(criteria);
        }
        addParamsToCriteria(criteria, filter);
        return getRowCount(criteria);
    }

    private void addParamsToCriteria(Criteria criteria, ContactSearchCriteria filter) {
        if (StringUtils.isNotBlank(filter.getEmail())) {
            criteria.add(Restrictions.eq("email", filter.getEmail()));
        }
        if (StringUtils.isNotBlank(filter.getType())) {
            criteria.add(Restrictions.eq("type", filter.getType()));
        }
    }

    private Criteria preparePartialFilter(
            String sortField, String sortOrder, ContactSearchCriteria filter) {
        Criteria criteria = SearchCriteriaUtil.restoreCountStateBy(filter,
                getCurrentSession());
        if (criteria == null) {
            criteria = getCriteria();
            filter.pushCount(criteria);
        }

        if (criteria != null) {
            addParamsToCriteria(criteria, filter);
            setupSortCriteria(criteria, getSortField(sortField),
                    getSortOrder(sortOrder));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria;
        }
        return null;
    }

    private String getSortField(String sortField) {
        return StringUtils.isNotBlank(sortField) ? sortField : "createDate";
    }

    private String getSortOrder(String sortOrder) {
        return StringUtils.isNotBlank(sortOrder) ? sortOrder : "desc";
    }

}
