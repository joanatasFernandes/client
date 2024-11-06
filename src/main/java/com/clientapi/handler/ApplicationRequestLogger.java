package com.clientapi.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class ApplicationRequestLogger implements BaseAppHandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(ApplicationRequestLogger.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logRequestDetails(request);
        return true;
    }

    private void logRequestDetails(HttpServletRequest request) throws IOException {
        StringBuilder logMessage = new StringBuilder("Request received");
        logMessage.append("Request URL: ").append(request.getRequestURL()).append("\n");
        logMessage.append("Request Method: ").append(request.getMethod()).append("\n");
        logMessage.append("Request Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logMessage.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
        }
        logger.info(logMessage.toString());
    }
}
