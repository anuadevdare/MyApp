package com.test.login.dto;

public class UserListDto {
	private long uid;

	private String firstName;

	private String lastName;

	private String emailId;

	public UserListDto() {
		super();
	}

	/**
	 * @param uid
	 * @param firstName
	 * @param lastName
	 * @param emailId
	 */
	public UserListDto(long uid, String firstName, String lastName, String emailId) {
		super();
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	/**
	 * @return the uid
	 */
	public long getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(long uid) {
		this.uid = uid;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@Override
	public String toString() {
		return "UserListDto [uid=" + uid + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + "]";
	}
	
	

}
