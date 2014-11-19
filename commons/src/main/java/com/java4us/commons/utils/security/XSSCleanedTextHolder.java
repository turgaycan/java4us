package com.java4us.commons.utils.security;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class XSSCleanedTextHolder {

	private List<String> errors = new ArrayList<String>();
	private String cleanedText;

	public XSSCleanedTextHolder(List<String> errorList, String cleanedText) {
		this.cleanedText = cleanedText;
		if (errorList != null) {
			CollectionUtils.addAll(this.errors, errorList.toArray());
		}
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getCleanedText() {
		return cleanedText;
	}

	public void setCleanedText(String cleanedText) {
		this.cleanedText = cleanedText;
	}

}
