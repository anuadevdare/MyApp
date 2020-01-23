package com.test.login.dto;

/**
 * ExpenseRespDto get the use to hold required properties of the expense
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-11
 **/
public class ExpenseRespDto {

	private String expenseDate;
	private Double expenseAmount;

	/**
	 * @param expenseDate
	 * @param expenseAmount
	 */
	public ExpenseRespDto(String expenseDate, Double expenseAmount) {
		super();
		this.expenseDate = expenseDate;
		this.expenseAmount = expenseAmount;
	}

	/**
	 * @return the expenseDate
	 */
	public String getExpenseDate() {
		return expenseDate;
	}

	/**
	 * @param expenseDate the expenseDate to set
	 */
	public void setExpenseDate(String expenseDate) {
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
		return "ExpenseRespDto [expenseDate=" + expenseDate + ", expenseAmount=" + expenseAmount + "]";
	}

}