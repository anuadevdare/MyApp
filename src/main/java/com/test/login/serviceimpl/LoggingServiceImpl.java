package com.test.login.serviceimpl;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.test.login.service.LoggingService;

/**
 * ExpenseService class has implemented to log request and response APIs
 * operation methods
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 */

@Service("loggingService")
public class LoggingServiceImpl implements LoggingService {
	private final static Object lock = new Object();
	private static final Logger logger = LogManager.getLogger(LoggingServiceImpl.class);

	/**
	 * To logging required request details
	 *
	 * @param httpServletRequest To get the HTTP request headers Object To get the
	 *                           HTTP request body
	 * @param body               request body
	 */
	@Override
	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		synchronized (lock) {
			StringBuilder stringBuilder = new StringBuilder();
			Map<String, String> parameters = buildParametersMap(httpServletRequest);

			stringBuilder.append(
					"\n********************************** REQUEST ********************************************\n");
			stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] \n");
			stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] \n");
			stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] \n");

			if (!parameters.isEmpty()) {
				stringBuilder.append("parameters=[").append(parameters).append("] \n");
			}

			if (body != null) {
				stringBuilder.append("body=[" + body + "]\n");
			}
			stringBuilder.append(
					"\n***************************************************************************************\n");

			logger.info(stringBuilder.toString());
		}

	}

	/**
	 * To logging required Response details
	 *
	 * @param httpServletRequest To get the HTTP request headers httpServletResponse
	 *                           To get the HTTP response headers body To get the
	 *                           HTTP request body
	 *
	 */

	@Override
	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object body) {
		synchronized (lock) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(
					"\n********************************** RESPONSE ********************************************\n");
			stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] \n");
			stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] \n");
			stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] \n");
			stringBuilder.append("responseBody=[").append(body).append("] \n");
			stringBuilder.append(
					"\n***************************************************************************************\n");
			logger.info(stringBuilder.toString());
		}

	}

	/**
	 * To getting parameters details from the request
	 *
	 * @param httpServletRequest To get the HTTP request
	 * @return the parameter map
	 *
	 */

	private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
		Map<String, String> resultMap = new HashMap<>();
		Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = httpServletRequest.getParameter(key);
			resultMap.put(key, value);
		}

		return resultMap;
	}

	/**
	 * To getting request body from the request
	 *
	 * @param request httpServletRequest To get the HTTP request
	 * @return the request header map
	 *
	 */
	private Map<String, String> buildHeadersMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

	/**
	 * To getting response headers from the response
	 *
	 * @param response To get the HTTP response headers
	 * @return the response headers map
	 *
	 */
	private Map<String, String> buildHeadersMap(HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();

		Collection<String> headerNames = response.getHeaderNames();
		for (String header : headerNames) {
			map.put(header, response.getHeader(header));
		}

		return map;
	}
}