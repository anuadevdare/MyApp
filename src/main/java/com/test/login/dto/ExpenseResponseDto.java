package com.test.login.dto;

import com.test.login.entity.Expense;

/**
 * ExpenseResponseDto provides the required expense response .
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 **/
public class ExpenseResponseDto {

	/**
	 * The Message.
	 */
	private String message;
	/**
	 * The Status.
	 */
	private String status;

	/**
	 * The expence
	 */
	private Expense expence;

	/**
	 * constructor for message and status
	 * 
	 * @param message
	 * @param status
	 */
	public ExpenseResponseDto(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	/**
	 * @param message
	 * @param status
	 * @param expence
	 */

	public ExpenseResponseDto(String message, String status, Expense expence) {
		super();
		this.message = message;
		this.status = status;
		this.expence = expence;
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
	 * @return the expence
	 */
	public Expense getExpence() {
		return expence;
	}

	/**
	 * @param expence the expence to set
	 */
	public void setExpence(Expense expence) {
		this.expence = expence;
	}

	/**
	 * @return the the object in the string format
	 */
	@Override
	public String toString() {
		return "ExpenseResponseDto [message=" + message + ", status=" + status + ", expence=" + expence + "]";
	}

}
