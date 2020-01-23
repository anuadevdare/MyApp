package com.test.login.service;

import javax.validation.Valid;

import com.test.login.dto.ExpenseDtoResp;
import com.test.login.dto.ExpenseResponseDto;
import com.test.login.entity.Expense;
/**
 * ExpenseService interface has declared to create Expense and return all Expenses APIs operation methods
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-05
 */
public interface ExpenseService {
	ExpenseResponseDto saveExpense(Expense expense );
	ExpenseDtoResp findAllExpenses();
	ExpenseDtoResp findByUserExpenses(long userId);
	ExpenseResponseDto updateExpense(@Valid Expense expense, long eId);
	ExpenseResponseDto deleteExpense(long eId);
	ExpenseResponseDto getExpenseById(long eId);
}
