package com.hiball.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.hiball.web.util.LoggingUtils;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private RequestCache requestCache = new HttpSessionRequestCache();
    
    private String targetUrlParameter;
    private String defaultUrl;
    private boolean useReferer;
    
    public LoginSuccessHandler() {
	targetUrlParameter = "";
	defaultUrl = "/gameList";
	useReferer = false;
    }
    
    public String getTargetUrlParameter() {
	return targetUrlParameter;
    }
    public void setTargetUrlParameter(String targetUrlParameter) {
	this.targetUrlParameter = targetUrlParameter;
    }
    
    public String getDefaultUrl() {
	return defaultUrl;
    }
    public void setDefaultUrl(String defaultUrl) {
	this.defaultUrl = defaultUrl;
    }
    
    public boolean isUseReferer() {
	return useReferer;
    }
    public void setUseReferer(boolean useReferer) {
	this.useReferer = useReferer;
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
	String ctxtPath = req.getContextPath();
	String redirectUrl = ctxtPath+"/memberList";
	logger.debug("##### Successfully Logging and moving {} #####", redirectUrl);
	logger.info("{}", LoggingUtils.makeLoggingInfo(auth, "LogIn"));
	
	resp.sendRedirect(redirectUrl);
    }
    
    private void clearAuthenticationAttributes(HttpServletRequest req) {
	HttpSession session = req.getSession(false);
	
	if(session == null) {
	    return;
	}
    }
    
    private int decideRedirectStrategy(HttpServletRequest req, HttpServletResponse resp) {
	int result = 0;
	SavedRequest savedRequest = requestCache.getRequest(req, resp);
	
	if (!"".contentEquals(targetUrlParameter)) {
	    String targetUrl = req.getParameter(targetUrlParameter);
	    if (StringUtils.hasText(targetUrl)) {
		result = 1;
	    } else {
		if (savedRequest != null) {
		    result = 2;
		} else {
		    String refererUrl = req.getHeader("REFERER");
		    if (useReferer && StringUtils.hasText(refererUrl)) {
			result = 3;
		    } else {
			result = 0;
		    }
		}
	    }
	    return result;
	}
	
	if (savedRequest != null) {
	    result = 2;
	}
	
	String refererUrl = req.getHeader("REFERER");
	if (useReferer && StringUtils.hasText(refererUrl)) {
	    result = 3;
	} else {
	    result = 0;
	}
	
	return result;
    }
    
    private void useTargetUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	SavedRequest savedRequest = requestCache.getRequest(req,  resp);
	if (savedRequest != null) {
	    requestCache.removeRequest(req, resp);
	}
	String targetUrl = req.getParameter(targetUrlParameter);
	redirectStrategy.sendRedirect(req, resp, targetUrl);
    }
    private void useSessionUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	SavedRequest savedRequest = requestCache.getRequest(req, resp);
	String targetUrl = savedRequest.getRedirectUrl();
	redirectStrategy.sendRedirect(req, resp, targetUrl);
    }
    private void useRefererUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	String targetUrl = req.getHeader("REFERER");
	redirectStrategy.sendRedirect(req, resp, targetUrl);
    }
    private void useDefaultUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	redirectStrategy.sendRedirect(req, resp, defaultUrl);
    }
}
