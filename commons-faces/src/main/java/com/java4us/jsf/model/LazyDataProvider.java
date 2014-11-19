/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java4us.jsf.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author turgay
 */
public interface LazyDataProvider<T> extends Serializable {
    List<T> loadLazyData(int first, int pageSize, String sortField, String sortOrder);

    int getRowCount();
}
