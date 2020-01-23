package com.test.login.config;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.test.login.service.LoggingService;

/**
 * LogInterceptor Class is design for Logging requests for GET methods.The GET
 * methods don’t contain body so we’ll use a HandlerInterceptor for this case.
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

	@Autowired
	private LoggingService loggingService;

	/**
	 * Intercept the execution of a handler. Called after HandlerMapping
	 * determinedan appropriate handler object, but before HandlerAdapter invokes
	 * the handler.
	 * 
	 * @param request servlet container creates an HttpServletRequest,
	 *                responseHttpServletResponse objects and passes it as an
	 *                argument to theservlet's service methodsresponse current HTTP
	 *                responsehandler chosen handler to execute, for type and/or
	 *                instance evaluation
	 * @return modified instance
	 **/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
				&& request.getMethod().equals(HttpMethod.GET.name())) {
			new Thread() {
				@Override
				public void run() {
					loggingService.logRequest(request, null);
				}
			}.start();
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}
}