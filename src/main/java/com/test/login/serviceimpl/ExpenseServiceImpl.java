package com.test.login.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.login.dao.ExpenseDao;
import com.test.login.dao.ExpenseImpl;
import com.test.login.dao.UserDaoImpl;
import com.test.login.dto.ExpenseDto;
import com.test.login.dto.ExpenseDtoResp;
import com.test.login.dto.ExpenseRespDto;
import com.test.login.dto.ExpenseResponseDto;
import com.test.login.entity.Expense;
import com.test.login.entity.User;
import com.test.login.service.ExpenseService;

/**
 * ExpenseService class has implemented to registers the user and login APIs
 * operation methods
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-05
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {
	private static final Logger logger = LogManager.getLogger(ExpenseServiceImpl.class);
	@Autowired
	ExpenseDao expenseDao;
	@Autowired
	ExpenseImpl expenseImpl;
	@Autowired
	UserDaoImpl userDao;

	/**
	 * Registers the expense to be saved to the Database
	 *
	 * @param expense object all details of expense
	 * @return return the the saved ExpenseResponseDto object as a response
	 *
	 */
	@Override
	public ExpenseResponseDto saveExpense(Expense expense) {

		String expenseTitle = expense.getExpenseTitle();
		Long userId = expense.getUserId();
		User user = userDao.findById(userId);

		if (user == null) {
			return new ExpenseResponseDto(expenseTitle + " is not stored.Please enter valid userId..!",
					HttpStatus.BAD_REQUEST.toString(), null);
		}
		//Expense savedExpense = expenseImpl.createExpense(expense);
		Expense savedExpense = expenseDao.save(expense);
		logger.info("data in servise {}", savedExpense);
		if (savedExpense == null) {
			return new ExpenseResponseDto(expenseTitle + " is not stored!.", HttpStatus.BAD_REQUEST.toString(),
					savedExpense);
		}
		return new ExpenseResponseDto(expenseTitle + " is stored successfully!.", HttpStatus.CREATED.toString(),
				savedExpense);

	}

	/**
	 * Retrieves all expenses from the database
	 *
	 * @return ExpenseDtoResp details with expenses total
	 */

	@Override
	public ExpenseDtoResp findAllExpenses() {
		List<ExpenseDto> expenses = expenseImpl.findAllExpences();
		double totalAmount = 0.0;
		List<ExpenseRespDto> expenseList = new ArrayList<>();
		DateFormat formatter = new SimpleDateFormat("MMM yyyy");
		for (ExpenseDto objects : expenses) {
			Double amount = objects.getExpenseAmount();
			totalAmount += amount;

			expenseList.add(new ExpenseRespDto(formatter.format(objects.getExpenseDate()), amount));

		}

		return new ExpenseDtoResp(expenseList, totalAmount);
	}

	/**
	 * Get the expense from the Database by user Id
	 * 
	 * @param uid getting List of expenses as per user Id from the database
	 * @return return List of expenses as per user Id from the database as a
	 *         response
	 */

	@Override
	public ExpenseDtoResp findByUserExpenses(long uid) {
		List<ExpenseDto> expenses = expenseImpl.findbyUserIdExpences(uid);
		double totalAmount = 0.0;
		List<ExpenseRespDto> expenseList = new ArrayList<>();
		DateFormat formatter = new SimpleDateFormat("MMM yyyy");
		for (ExpenseDto objects : expenses) {
			Double amount = objects.getExpenseAmount();
			totalAmount += amount;

			expenseList.add(new ExpenseRespDto(formatter.format(objects.getExpenseDate()), amount));

		}

		return new ExpenseDtoResp(expenseList, totalAmount);
	}

	/**
	 * Update the expense to be saved to the Database
	 * 
	 * @param expense object all details of expense in json format eId expense Id
	 *                for updating the expense
	 * @return return the updated expense object as a response
	 */

	@Override
	public ExpenseResponseDto updateExpense(@Valid Expense expense, long eId) {
		Expense expenseData = null;

		expenseData = expenseImpl.findById(eId);

		if (expenseData == null) {
			return new ExpenseResponseDto("Expense is not present : " + eId + ".Please enter valid Expense Id..!",
					HttpStatus.BAD_REQUEST.toString());
		}
		if (expense.getUserId() != 0) {
			expenseData.setUserId(expense.getUserId());
		}
		if (expense.getExpenseAmount() != 0.0) {
			expenseData.setExpenseAmount(expense.getExpenseAmount());
		}
		if (expense.getExpenseCategory() != null) {
			expenseData.setExpenseCategory(expense.getExpenseCategory());
		}
		if (expense.getExpenseTitle() != null) {
			expenseData.setExpenseTitle(expense.getExpenseTitle());
		}
		if (expense.getExpenseDate() != null) {
			expenseData.setExpenseDate(expense.getExpenseDate());
		}
		if (expense.getExpenseDescription() != null) {
			expenseData.setExpenseDescription(expense.getExpenseDescription());
		}
		return new ExpenseResponseDto(expenseData.getExpenseTitle() + " is updated successfully!.",
				HttpStatus.OK.toString(), expenseDao.save(expenseData));

	}

	/**
	 * delete the expense from the Database
	 *
	 * @param eId expense id for deleting the expense from the database
	 * @return return the expense object has been
	 */

	@Override
	public ExpenseResponseDto deleteExpense(long eId) {
		Expense expenseData = expenseImpl.findById(eId);
		if (expenseData == null) {
			return new ExpenseResponseDto("Expense is not present : " + eId + ".Please enter valid Expense Id..!",
					HttpStatus.BAD_REQUEST.toString());
		}
		//expenseDao.deleteById(eId);
		expenseImpl.deleteExpensebyId(expenseData);

		return new ExpenseResponseDto(expenseData.getExpenseTitle() + " is deleted successfully!.",
				HttpStatus.OK.toString());

	}

	/**
	 * Get the expense from the Database
	 * 
	 * @param eId expense id for getting the expense from the database
	 * @return return the saved expense object as a response
	 */
	@Override
	public ExpenseResponseDto getExpenseById(long eId) {
		Expense expenseData = expenseImpl.findById(eId);
		if (expenseData == null) {
			return new ExpenseResponseDto("Expense is not present : " + eId + ".Please enter valid Expense Id..!",
					HttpStatus.BAD_REQUEST.toString());
		}
		return new ExpenseResponseDto(expenseData.getExpenseTitle() + " expence is retrieved successfully!.",
				HttpStatus.OK.toString(), expenseData);
	}

}
