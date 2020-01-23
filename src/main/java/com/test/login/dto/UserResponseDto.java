package com.test.login.dto;

import com.test.login.entity.User;
/**
 * UserResponseDto provides the required user response .
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-01
 **/

public class UserResponseDto {

	/**
	 * The Message.
	 */
	private String message;
	/**
	 * The Status.
	 */
	private String status;
	/**
	 * The user dto user.
	 */
	private User user;

	/**
	 *  Default constructor 
	 */
	public UserResponseDto() {
		super();
	}

	
	/**
	 * @param message
	 * @param status
	 */
	public UserResponseDto(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}


	/**
	 * @param message
	 * @param status
	 * @param user
	 */
	public UserResponseDto(String message, String status, User user) {
		super();
		this.message = message;
		this.status = status;
		this.user = user;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/** 
	 * @return the the object in the string format
	 */
	@Override
	public String toString() {
		return "ResponseDto [message=" + message + ", status=" + status + ", user=" + user + "]";
	}

}
