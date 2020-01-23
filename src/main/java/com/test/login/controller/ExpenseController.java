package com.test.login.controller;

import javax.validation.Valid;
/**
 * ExpenseController is a controller to call save expense and return all expense  APIs for expense that is
 * called by URL starting with "/expense"
 *
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-10-30
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.login.dto.ExpenseDtoResp;
import com.test.login.dto.ExpenseResponseDto;
import com.test.login.entity.Expense;
import com.test.login.service.ExpenseService;

/**
 * ExpenseController is a controller to create Expense and return all Expenses
 * APIs from Expense Master that is called by URL starting with "/expense"
 *
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 */
@RestController
@RequestMapping(value = "/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	/**
	 * Registers the expense to be saved to the Database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * expenses(http://localhost:8082/expense/save) through the post method
	 * 
	 * @param expense object all details of expense in json format
	 * @return return the the saved expense object as a response
	 */
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ExpenseResponseDto saveExpense(@Valid @RequestBody Expense expense) {
		return expenseService.saveExpense(expense);

	}

	/**
	 * Retrieves all expenses from the database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * expenses(http://localhost:8082/expense/allexpences) through the get method
	 * 
	 * @return all expenses details with expenses total in the json format
	 */
	@GetMapping(path = "/allexpences")
	public ExpenseDtoResp allExpences() {
		return expenseService.findAllExpenses();
	}

	/**
	 * Update the expense to be saved to the Database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * expenses(http://localhost:8082/expense/update/{eId}) through the post method
	 * 
	 * @param expense object all details of expense in json format eId expense Id
	 *                for updating the expense
	 * @return return the updated expense object as a response
	 */
	@PutMapping(path = "/update/{eId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ExpenseResponseDto updateExpense(@Valid @RequestBody Expense expense, @PathVariable long eId) {
		return expenseService.updateExpense(expense, eId);

	}

	/**
	 * delete the expense from the Database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * expenses(http://localhost:8082/expense/delete{eid}) through the post method
	 * 
	 * @param eId expense id for deleting the expense from the database
	 * @return return the expense object has been
	 */
	@DeleteMapping(path = "/delete/{eId}")
	public ExpenseResponseDto deleteExpense(@PathVariable long eId) {
		return expenseService.deleteExpense(eId);

	}

	/**
	 * Get the expense from the Database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * expenses(http://localhost:8082/expense/getexpense/{eId}) through the get
	 * method
	 * 
	 * @param eId expense id for getting the expense from the database
	 * @return return the saved expense object as a response
	 */
	@GetMapping(path = "/getexpense/{eId}")
	public ExpenseResponseDto getExpenseById(@PathVariable long eId) {
		return expenseService.getExpenseById(eId);

	}

	/**
	 * Get the expense from the Database by user Id
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * expenses(http://localhost:8082/expense/getexpensebyuser/{uId}) through the
	 * get method
	 * 
	 * @param uId getting List of expenses as per user Id from the database
	 * @return return List of expenses as per user Id from the database as a
	 *         response
	 */
	@GetMapping(path = "/getexpensebyuser/{uId}")
	public ExpenseDtoResp findByUserExpenses(@PathVariable long uId) {
		return expenseService.findByUserExpenses(uId);

	}
}
