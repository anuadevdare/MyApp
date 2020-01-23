package com.test.login.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Expense class has a special relationship with the database, directly interact
 * with the 'testmydatabase' database and 'Expense' table
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 */
@Entity
public class Expense implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long eid;
	@Column(name = "EXPENSE_TITLE", unique = false, nullable = false, length = 25)
	private String expenseTitle;
	@Column(name = "EXPENSE_CATEGORY", unique = false, nullable = false, length = 25)
	private String expenseCategory;
	@Column(name = "EXPENSE_DESCRIPTION", unique = false, nullable = true, length = 50)
	private String expenseDescription;
	@Column(name = "EXPENSE_AMOUNT", unique = false, nullable = false, length = 10)
	private double expenseAmount;

	@Column(name = "USER_ID", nullable = false)
	private long userId;
	@Basic
	@Temporal(TemporalType.DATE)
	private Date expenseDate;

	/**
	 * 
	 */
	public Expense() {
		super();
	}

	/**
	 * @param eid
	 * @param expenseTitle
	 * @param expenseCategory
	 * @param expenseDescription
	 * @param expenseAmount
	 * @param userId
	 * @param expenseDate
	 */
	public Expense(Long eid, String expenseTitle, String expenseCategory, String expenseDescription,
			double expenseAmount, long userId, Date expenseDate) {
		super();
		this.eid = eid;
		this.expenseTitle = expenseTitle;
		this.expenseCategory = expenseCategory;
		this.expenseDescription = expenseDescription;
		this.expenseAmount = expenseAmount;
		this.userId = userId;
		this.expenseDate = expenseDate;
	}

	/**
	 * @param expenseTitle
	 * @param expenseCategory
	 * @param expenseDescription
	 * @param expenseAmount
	 * @param userId
	 * @param expenseDate
	 */
	public Expense(String expenseTitle, String expenseCategory, String expenseDescription, double expenseAmount,
			long userId, Date expenseDate) {
		super();
		this.expenseTitle = expenseTitle;
		this.expenseCategory = expenseCategory;
		this.expenseDescription = expenseDescription;
		this.expenseAmount = expenseAmount;
		this.userId = userId;
		this.expenseDate = expenseDate;
	}

	public Expense(Expense expense) {
		this.eid = expense.getEid();
		this.expenseTitle = expense.getExpenseTitle();
		this.expenseDescription = expense.getExpenseDescription();
		this.expenseCategory = expense.getExpenseCategory();
		this.expenseDate = expense.getExpenseDate();
		this.expenseAmount = expense.getExpenseAmount();
		this.userId = expense.getUserId();
	}

	/**
	 * @return the eid
	 */
	public Long getEid() {
		return eid;
	}

	/**
	 * @param eid the eid to set
	 */
	public void setEid(Long eid) {
		this.eid = eid;
	}

	/**
	 * @return the expenseTitle
	 */
	public String getExpenseTitle() {
		return expenseTitle;
	}

	/**
	 * @param expenseTitle the expenseTitle to set
	 */
	public void setExpenseTitle(String expenseTitle) {
		this.expenseTitle = expenseTitle;
	}

	/**
	 * @return the expenseCategory
	 */
	public String getExpenseCategory() {
		return expenseCategory;
	}

	/**
	 * @param expenseCategory the expenseCategory to set
	 */
	public void setExpenseCategory(String expenseCategory) {
		this.expenseCategory = expenseCategory;
	}

	/**
	 * @return the expenseDescription
	 */
	public String getExpenseDescription() {
		return expenseDescription;
	}

	/**
	 * @param expenseDescription the expenseDescription to set
	 */
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	/**
	 * @return the expenseAmount
	 */
	public double getExpenseAmount() {
		return expenseAmount;
	}

	/**
	 * @param expenseAmount the expenseAmount to set
	 */
	public void setExpenseAmount(double expenseAmount) {
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
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Expense [eid=" + eid + ", expenseTitle=" + expenseTitle + ", expenseCategory=" + expenseCategory
				+ ", expenseDescription=" + expenseDescription + ", expenseAmount=" + expenseAmount + ", userId="
				+ userId + ", expenseDate=" + expenseDate + "]";
	}

}
