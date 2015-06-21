package com.java4us.documents.criteria;

import com.java4us.domain.common.enums.Category;
import com.java4us.domain.model.PaginationModel;

import java.io.Serializable;

/**
 * Created by turgaycan on 12/28/14.
 */
public class SearchCriteria implements Serializable {

    private static final long serialVersionUID = 4393718253497738137L;
    private String sortField;

    private String sortType;

    private Category category;

    private String query;

    private PaginationModel paginationModel;

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public PaginationModel getPaginationModel() {
        return paginationModel;
    }

    public void setPaginationModel(PaginationModel paginationModel) {
        this.paginationModel = paginationModel;
    }
}
