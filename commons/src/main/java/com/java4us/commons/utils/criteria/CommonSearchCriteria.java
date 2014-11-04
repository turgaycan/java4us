/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.utils.criteria;

import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;

import java.io.Serializable;

/**
 *
 * @author turgay
 */
public class CommonSearchCriteria extends LazySearchCriteria implements
		Serializable {

	private static final long serialVersionUID = -3748155391347038297L;
	private Integer id;
	private boolean deleted;
	private Category category;
	private BaseStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BaseStatus getStatus() {
		return status;
	}

	public void setStatus(BaseStatus status) {
		this.status = status;
	}

}
