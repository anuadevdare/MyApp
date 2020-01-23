package com.test.login.dto;

import java.util.List;

/**
 * ExpenseDto provides all the expenses list with the expenses total .
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-11
 **/
public class ExpenseDtoResp {

	private List<ExpenseRespDto> expenseDto;
	private double expenseTotlaAmount;

	/**
	 * @param expenseDto         set List of ExpenseRespDto
	 * @param expenseTotlaAmount set total amount of expenses
	 */
	public ExpenseDtoResp(List<ExpenseRespDto> expenseDto, double expenseTotlaAmount) {
		super();
		this.expenseDto = expenseDto;
		this.expenseTotlaAmount = expenseTotlaAmount;
	}

	/**
	 * @return the expenseDto
	 */
	public List<ExpenseRespDto> getExpenseDto() {
		return expenseDto;
	}

	/**
	 * @param expenseDto the expenseDto to set
	 */
	public void setExpenseDto(List<ExpenseRespDto> expenseDto) {
		this.expenseDto = expenseDto;
	}

	/**
	 * @return the expenseTotlaAmount
	 */
	public double getExpenseTotlaAmount() {
		return expenseTotlaAmount;
	}

	/**
	 * @param expenseTotlaAmount the expenseTotlaAmount to set
	 */
	public void setExpenseTotlaAmount(double expenseTotlaAmount) {
		this.expenseTotlaAmount = expenseTotlaAmount;
	}

	/**
	 * @return the the object in the string format
	 */
	@Override
	public String toString() {
		return "ExpenseDtoResp [expenseDto=" + expenseDto.toString() + ", expenseTotlaAmount=" + expenseTotlaAmount
				+ "]";
	}

}
