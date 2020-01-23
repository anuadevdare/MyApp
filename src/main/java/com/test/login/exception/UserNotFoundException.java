package com.test.login.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
/**
 *UserNotFoundException through the exception, if the user 
 *not present in the database
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-01
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public UserNotFoundException(String message) {
		super(message);
	}
}