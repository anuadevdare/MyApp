package com.test.login.service;

import javax.servlet.http.HttpServletRequest;
/**
 * ExpenseService interface has declared to log request and response APIs operation methods
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-07
 */
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
@Service
public interface LoggingService {
    void logRequest(HttpServletRequest httpServletRequest, Object body);
    void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
}