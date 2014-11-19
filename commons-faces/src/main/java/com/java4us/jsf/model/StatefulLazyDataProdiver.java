package com.java4us.jsf.model;

import com.java4us.commons.utils.criteria.StatefulSearchCriteria;

public interface StatefulLazyDataProdiver<T> extends LazyDataProvider<T> {

    StatefulSearchCriteria getSearchFilter();
}
