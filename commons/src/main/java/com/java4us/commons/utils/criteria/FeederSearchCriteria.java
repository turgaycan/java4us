package com.java4us.commons.utils.criteria;

import org.apache.commons.lang3.StringUtils;

import com.java4us.domain.common.enums.FeederStatus;

public class FeederSearchCriteria extends CommonSearchCriteria {

	private static final long serialVersionUID = 8075073740687897593L;
	private String domain;
	private String email;
	private FeederStatus feederStatus;
	
	public boolean isSearchable(){
		return feederStatus != null || StringUtils.isNotBlank(email) || StringUtils.isNotBlank(domain);
 	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FeederStatus getFeederStatus() {
		return feederStatus;
	}

	public void setFeederStatus(FeederStatus feederStatus) {
		this.feederStatus = feederStatus;
	}

}
