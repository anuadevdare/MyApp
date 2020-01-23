package com.test.login.dto;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Length;

public class ChangePassword {
	private String emailId;
	private String oldPassword;
	@Column(name = "PASSWORD", unique = false, nullable = false, length = 100)
	@Length(min = 5, message = "password must have at least 5 charater!")
	private String newPassword;
	private String confNewPassword;
	@Column(name = "PASSWORD_HINT", unique = false, nullable = true, length = 100)
	private String passwordHint;

	/**
	 * All parameter constructor 
	 * @param emailId
	 * @param oldPassword
	 * @param newPassword
	 * @param confNewPassword
	 * @param passwordHint
	 */
	public ChangePassword(String emailId, String oldPassword, String newPassword, String confNewPassword,
			String passwordHint) {
		super();
		this.emailId = emailId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confNewPassword = confNewPassword;
		this.passwordHint = passwordHint;
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
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the confNewPassword
	 */
	public String getConfNewPassword() {
		return confNewPassword;
	}

	/**
	 * @param confNewPassword the confNewPassword to set
	 */
	public void setConfNewPassword(String confNewPassword) {
		this.confNewPassword = confNewPassword;
	}

	/**
	 * @return the passwordHint
	 */
	public String getPasswordHint() {
		return passwordHint;
	}

	/**
	 * @param passwordHint the passwordHint to set
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

}
