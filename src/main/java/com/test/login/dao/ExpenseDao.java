package com.test.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.login.entity.Expense;
/**
 * JpaRepository provides generic database operation on a repository for a specific
 * type to the ExpenseDao interface .
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 **/
public interface ExpenseDao extends JpaRepository<Expense, Long>{
	
}
