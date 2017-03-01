package com.asp.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import validation.ValidEmail;

@Entity
@Table(name="users")
public class Users {
	

	@Column(name="fullname")
	@NotBlank(message="fullname cannot be blank.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String fullname;
	
	@Column(name="email")
	@ValidEmail(message="This does not appear to be a valid email address.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String email;
	@Id
	@Column(name="username")
	@NotBlank(message="Username cannot be blank.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=8, max=15, message="Username must be between 8 and 15 characters long.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\w{8,}$", message="Username can only consist of numbers, letters and the underscore character.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String username;
	@Column(name="password")
	@NotBlank(message="Password cannot be blank.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\S+$", message="Password cannot contain spaces.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=8, max=15, message="Password must be between 8 and 15 characters long.",groups={FormValidationGroup.class})
	private String password;
	@Column(name="authority")
	private String authority;
	@Column(name="enabled")
	private boolean enabled;
	@Column(name="phone")
	@NotBlank(message="This cannot be blank.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="[0-9]+", message="Enter Valid Number.",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=10, max=10, message="Enter Valid Number.",groups={FormValidationGroup.class})
	private String phone;
	@Column(name="carrier")
	private String carrier;
	
	
	public Users() {
		
	}


	public Users(String fullname, String email, String username, String password, String authority, boolean enabled,
			String phone, String carrier) {
		this.fullname = fullname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.authority = authority;
		this.enabled = enabled;
		this.phone = phone;
		this.carrier = carrier;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCarrier() {
		return carrier;
	}


	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}


	@Override
	public String toString() {
		return "Users [fullname=" + fullname + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", authority=" + authority + ", enabled=" + enabled + ", phone=" + phone + ", carrier=" + carrier
				+ "]";
	}


	

	

	
	
	
	
}
