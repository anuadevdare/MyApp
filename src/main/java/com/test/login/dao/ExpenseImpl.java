package com.test.login.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.test.login.dto.ExpenseDto;
import com.test.login.entity.Expense;

/**
 * ExpenseImpl provides list of all ExpenseDto using criteria query
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 **/

@Repository
public class ExpenseImpl {
	@PersistenceContext
	private EntityManager em;

	public ExpenseImpl(EntityManager em) {
		this.em = em;
	}

	/**
	 * findAllExpences provides list of all ExpenseDto using criteria query
	 * 
	 * @return list of all ExpenseDto
	 */
	public List<ExpenseDto> findAllExpences() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ExpenseDto> cquery = cb.createQuery(ExpenseDto.class);
		Root<Expense> root = cquery.from(Expense.class);
		cquery.select(cb.construct(ExpenseDto.class, root.<Date>get("expenseDate"),
				cb.sum(root.<Double>get("expenseAmount"))));
		cquery.groupBy((cb.function("YEAR", Integer.class, root.get("expenseDate"))),
				(cb.function("MONTH", Integer.class, root.get("expenseDate"))));
		cquery.orderBy(cb.asc((cb.function("YEAR", Integer.class, root.get("expenseDate")))),
				cb.asc(cb.function("MONTH", Integer.class, root.get("expenseDate"))));

		TypedQuery<ExpenseDto> query = em.createQuery(cquery);
		System.out.println(query.toString());
		try {
			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * findAllExpenses as per the user
	 * 
	 * @param uid user id
	 * @return list of Expenses as per the user ExpenseDto
	 */
	public List<ExpenseDto> findbyUserIdExpences(long uid) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ExpenseDto> cquery = cb.createQuery(ExpenseDto.class);
		Root<Expense> root = cquery.from(Expense.class);
		cquery.select(cb.construct(ExpenseDto.class, root.<Date>get("expenseDate"),
				cb.sum(root.<Double>get("expenseAmount"))));
		cquery.groupBy((cb.function("YEAR", Integer.class, root.get("expenseDate"))),
				(cb.function("MONTH", Integer.class, root.get("expenseDate"))));
		cquery.orderBy(cb.asc((cb.function("YEAR", Integer.class, root.get("expenseDate")))),
				cb.asc(cb.function("MONTH", Integer.class, root.get("expenseDate"))));
		Predicate IdPredicate = cb.equal(root.get("userId"), uid);
		cquery.where(IdPredicate);
		TypedQuery<ExpenseDto> query = em.createQuery(cquery);
		System.out.println(query.toString());
		try {
			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public Expense findById(long id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);

		Root<Expense> expense = cq.from(Expense.class);
		Predicate IdPredicate = cb.equal(expense.get("eid"), id);

		cq.where(IdPredicate);

		TypedQuery<Expense> query = em.createQuery(cq);
		try {
			return query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

	public Expense createExpense(Expense expense) {
		Expense expenseObj = new Expense(expense);
		em.persist(expenseObj);
		return expenseObj;
	}
	public void deleteExpensebyId(Expense expense)
	{
		 em.remove(expense);
	}

}
