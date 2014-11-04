package com.java4us.commons.utils.criteria;

public class SubscriberSearchCriteria extends CommonSearchCriteria {

	private static final long serialVersionUID = -5714800286922148741L;
	private String name;
	private String surname;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
