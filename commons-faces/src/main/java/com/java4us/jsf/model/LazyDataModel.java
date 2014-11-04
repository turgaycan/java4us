/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.jsf.model;

import java.util.List;
import java.util.Map;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author turgay
 * @param <T>
 */
public class LazyDataModel<T> extends org.primefaces.model.LazyDataModel<T> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyDataModel.class);

    private LazyDataProvider<T> provider;
    private int first;
    private int rows;
    private Integer state;

    public LazyDataModel(LazyDataProvider<T> provider) {
        super();
        this.provider = provider;
    }

    @Override
    public List<T> load(int first, int rows, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int liveRowCount = getProvider().getRowCount();
        if (liveRowCount < first + 1) {
            first = 0;
        }
        List<T> result = getProvider().loadLazyData(first, rows, sortField, sortOrder.toString());
        setRows(rows);
        setFirst(first);
        if (result != null && result.size() <= first + rows) {
            setRowCount(liveRowCount);
        } else {
            LOGGER.warn("Irregular lazy data, setting row count to ZERO.");
            int rowCount = 0;
            if (first > 0) {
                rowCount = rows;
                if (result != null) {
                    rowCount += result.size();
                }
            }
            setRowCount(rowCount);
        }
        return result;
    }

    @Override
    public Object getWrappedData() {
        if (super.getWrappedData() == null) {
            setWrappedData(load(0, 10, null, SortOrder.ASCENDING, null));
            RequestContext.getCurrentInstance().update(Faces.getRequestParameter("javax.faces.source"));
        }
        return super.getWrappedData();
    }

    public LazyDataProvider<T> getProvider() {
        return provider;
    }

    public void setProvider(LazyDataProvider<T> provider) {
        this.provider = provider;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (getPageSize() == 0) {
            setPageSize(1);
        }
        super.setRowIndex(rowIndex);
    }

    public int getCurrentPage() {
        return getRows() > 0 ? (int) (getFirst() / getRows()) : 0;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
