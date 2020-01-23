package com.test.login.config;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.test.login.service.LoggingService;

/**
 * CustomRequestBodyAdviceAdapter Class is design for the log the in coming
 * request
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 */

@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {
	private static final Logger logger = LogManager.getLogger(CustomRequestBodyAdviceAdapter.class);
	@Autowired
	LoggingService loggingService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	/**
	 * Invoked first to determine if this interceptor applies.
	 *
	 * @param methodParameter the method parameter type the target type, not
	 *                        necessarily the same as the method parameter type,
	 *                        aClass the selected converter type
	 * 
	 * @return whether this intercepter should be invoked or not
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Type type,
			Class<? extends HttpMessageConverter<?>> aClass) {
		return true;
	}

	/**
	 * The default implementation returns the body that was passed in.
	 * 
	 * @param body set to the converter Object before the first advice is called
	 *             inputMessage the request parameter the target method parameter
	 *             targetType the target type, not necessarily the same as the
	 *             method parameter type,converterType the converter used to
	 *             deserialize the body
	 * @return the same body or a new instance
	 */
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {

		threadSpawn(body, inputMessage, parameter, targetType, converterType);

		return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
	}

	@Async("threadPoolExecutor")
	public void threadSpawn(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {

		loggingService.logRequest(httpServletRequest, body);

	}
}
