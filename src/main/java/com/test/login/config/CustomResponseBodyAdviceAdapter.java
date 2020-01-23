package com.test.login.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.test.login.service.LoggingService;

/**
 * CustomResponseBodyAdviceAdapter Class is design for log the outgoing response
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 */
@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {
	private static final Logger logger = LogManager.getLogger(CustomResponseBodyAdviceAdapter.class);
	@Autowired
	private LoggingService loggingService;

	/**
	 * Whether this component supports the given controller method return type and
	 * the selected HttpMessageConverter type.
	 * 
	 * @param methodParameter the return type aClass the selected converter type
	 * 
	 * @return if beforeBodyWrite should be invoked; false otherwise
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
		return true;
	}

	/**
	 * Invoked after an HttpMessageConverter is selected and just before its write
	 * method is invoked.
	 * 
	 * @param o the body to be written methodParameter the return type of the
	 *          controller method mediaType the content type selected through
	 *          content negotiation aClass the converter type selected to write to
	 *          the response serverHttpRequest the current request
	 *          serverHttpResponse the current response
	 * 
	 * @return the body that was passed in or a modified (possibly new) instance
	 */
	@Override
	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
			Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
			ServerHttpResponse serverHttpResponse) {

		new Thread() {
			@Override
			public void run() {
				super.run();

				if (serverHttpRequest instanceof ServletServerHttpRequest
						&& serverHttpResponse instanceof ServletServerHttpResponse) {

					loggingService.logResponse(((ServletServerHttpRequest) serverHttpRequest).getServletRequest(),
							((ServletServerHttpResponse) serverHttpResponse).getServletResponse(), o);
				}
			}
		}.start();

		return o;
	}
}