package com.test.login.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;

public class UpadateUserDTO {

	@Column(name = "FIRST_NAME", unique = false, nullable = true, length = 100)
	private String firstName;
	@Column(name = "LAST_NAME", unique = false, nullable = true, length = 100)
	private String lastName;
	@Column(name = "EMAIL_ID", unique = true, nullable = true, length = 100)
	@Email(message = "Email id  should be valid!")
	private String emailId;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
