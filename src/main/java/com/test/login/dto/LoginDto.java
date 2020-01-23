package com.test.login.dto;

import java.io.Serializable;

/**
 * LoginDto use for the user login .
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-01
 **/
public class LoginDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emailId;
	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(String emailId, String passward) {
		super();
		this.emailId = emailId;
		this.password = passward;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the getPassword
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the setPassword to set
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the the object in the string format
	 */
	@Override
	public String toString() {
		return "LoginDto [emailId=" + emailId + ", password=" + password + "]";
	}

}
