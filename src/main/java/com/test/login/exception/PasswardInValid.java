package com.test.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PasswardInValid through the exception, if the user entered invalid password
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-05
 */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PasswardInValid extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public PasswardInValid(String message) {
		super(message);
	}
}