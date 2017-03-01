package com.asp.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import validation.ValidEmail;

@Entity
@Table(name="university")
public class University {
	
	@Id
	@Column(name="email")
	@ValidEmail(message="This does not appear to be a valid email address.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String email;
	
	public University() {

	}

	public University(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
