package com.test.login.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.test.login.dto.UserListDto;
import com.test.login.entity.User;

/**
 * UserDaoImpl provides User object after successful the user login
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-10-31
 **/
@Repository
public class UserDaoImpl {
	@PersistenceContext
	EntityManager em;

	/**
	 * findByEmailId provides User object after successful the user login
	 * 
	 * @param emailId id of the user
	 * @return User object
	 */
	public User findByEmailId(String emailId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> user = cq.from(User.class);
		Predicate emailIdPredicate = cb.equal(user.get("emailId"), emailId);

		cq.where(emailIdPredicate);

		TypedQuery<User> query = em.createQuery(cq);
		try {
			return query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}
	public User findById(long id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> user = cq.from(User.class);
		Predicate IdPredicate = cb.equal(user.get("uid"), id);

		cq.where(IdPredicate);

		TypedQuery<User> query = em.createQuery(cq);
		try {
			return query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}
	
	//response showing list of Object array instead of json
	/*public List<Object[]> userList()
	{
		CriteriaBuilder cb =em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cquery=cb.createQuery(Object[].class);
		Root<User> root=cquery.from(User.class);
		//cquery.select(cb.array(root.<Long>get("uid"),root.<String>get("firstName"),root.<String>get("lastName"),root.<String>get("emailId")));
		//instead of criteria builders's array method multiselect 
		cquery.multiselect(root.<Long>get("uid"),root.<String>get("firstName"),root.<String>get("lastName"),root.<String>get("emailId")));
		TypedQuery<Object[]> query = em.createQuery(cquery);
		 try {
	            return query.getResultList();
	        } catch (NoResultException nre) {
	            return null;
	        }
	}*/
	public List<UserListDto> userList()
	{
		CriteriaBuilder cb =em.getCriteriaBuilder();
		CriteriaQuery<UserListDto> cquery=cb.createQuery(UserListDto.class);
		Root<User> root=cquery.from(User.class);
		cquery.select(cb.construct(UserListDto.class, root.<Long>get("uid"),root.<String>get("firstName"),root.<String>get("lastName"),root.<String>get("emailId")));
		TypedQuery<UserListDto> query = em.createQuery(cquery);
		 try {
	            return query.getResultList();
	        } catch (NoResultException nre) {
	            return null;
	        }
	}
	


}
