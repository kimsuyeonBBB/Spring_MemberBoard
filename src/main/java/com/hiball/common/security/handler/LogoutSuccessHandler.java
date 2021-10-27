package com.hiball.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.hiball.web.util.LoggingUtils;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(LogoutSuccessHandler.class);
    private String authTerminateUrl = "";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	logger.debug("##### onLogoutSuccess #####");
	if (authentication != null) {
	    logger.info("{}", LoggingUtils.makeLoggingInfo(authentication, "Logout"));
	    
	}
	HttpSession session = request.getSession();

	session.invalidate();

	String contextPath = request.getContextPath();
	response.sendRedirect(contextPath + "/login");
    }

    public String getAuthTerminateUrl() {
	return authTerminateUrl;
    }

    public void setAuthTerminateUrl(String authTerminateUrl) {
	this.authTerminateUrl = authTerminateUrl;
    }
}
