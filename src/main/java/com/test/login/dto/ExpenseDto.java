package com.test.login.dto;

import java.util.Date;
/**
 * ExpenseDto provides the object in the MMM yyyy format .
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-11
 **/
public class ExpenseDto {
	private Date expenseDate;
	private Double expenseAmount;

	/**
	 * @param expenseDate
	 * @param expenseAmount
	 */
	public ExpenseDto(Date expenseDate, Double expenseAmount) {
		super();
		this.expenseDate = expenseDate;
		this.expenseAmount = expenseAmount;
	}

	/**
	 * @return the expenseDate
	 */
	public Date getExpenseDate() {
		return expenseDate;
	}

	/**
	 * @param expenseDate the expenseDate to set
	 */
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	/**
	 * @return the expenseAmount
	 */
	public Double getExpenseAmount() {
		return expenseAmount;
	}

	/**
	 * @param expenseAmount the expenseAmount to set
	 */
	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	/** 
	 * @return the the object in the string format
	 */
	@Override
	public String toString() {
		return "ExpenseDto [expenseDate=" + expenseDate + ", expenseAmount=" + expenseAmount + "]";
	}

}
