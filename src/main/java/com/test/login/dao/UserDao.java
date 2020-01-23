package com.test.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.login.entity.User;

/**
 * JpaRepository provides generic database operation on a repository for a
 * specific type to the UserDao interface .
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-10-30
 **/
public interface UserDao extends JpaRepository<User, Long> {
}
