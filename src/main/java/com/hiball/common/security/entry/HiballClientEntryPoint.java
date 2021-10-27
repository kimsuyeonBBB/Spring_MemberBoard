package com.hiball.common.security.entry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.hiball.common.security.service.HiballClientTokenService;
import com.mysql.cj.util.StringUtils;


public class HiballClientEntryPoint extends LoginUrlAuthenticationEntryPoint {
	private static Logger logger = LoggerFactory.getLogger(HiballClientEntryPoint.class);
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String authUrl = "";
	private String clientId = "";
	private String secureKey = "";
	@Autowired
	HiballClientTokenService tokenService;
	
	public HiballClientEntryPoint(final String loginFormUrl) {
		super(loginFormUrl);
	}
	
	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authEx) throws IOException, ServletException {
		logger.debug("##### Client Entry Point #####");
		String redirectUrl = "";
		Map<String, String> tokenInfoMap = null;
		
		String action = req.getServletPath();
		logger.debug("##### Client servlet action : {} #####", action);
		
		tokenInfoMap = extractTokenInformation(req, res);
		
		if ("/".equals(action) || action.contains("intro")) {
			configUserInfo(res);
			
			if (StringUtils.isNullOrEmpty(tokenInfoMap.get("token"))) {
				redirectUrl = "/intro";
			} else {
				redirectUrl = "/tokenLogin?accessUserId="+tokenInfoMap.get("accessUserId")+"&accessToken="+tokenInfoMap.get("token");
			}
		} else if(action.contains("tokenLogin")){
			configUserToken(req, res, tokenInfoMap);
			tokenService.confirmUserLogin(req, tokenInfoMap);
			redirectUrl = "/intro";
		}
		
		redirectStrategy.sendRedirect(req, res, redirectUrl);
	}
	
	private Map<String, String> extractTokenInformation(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> tokenInfoMap = new HashMap<String, String>();
		
		tokenInfoMap.put("token", req.getParameter("accessToken"));
		tokenInfoMap.put("accessUserId", req.getParameter("accessUserId"));
		tokenInfoMap.put("redirectUri", req.getParameter("redirectUri"));
		tokenInfoMap.put("isTerminated", req.getParameter("isTerminated"));
		String isTerminated = req.getParameter("isTerminated");

		if (!StringUtils.isNullOrEmpty(isTerminated) && Boolean.valueOf(isTerminated)) {
			removeConfigedToken(res);
		}
		
		return tokenInfoMap;
	}

	private void configUserToken(HttpServletRequest req, HttpServletResponse resp, Map<String, String> tokenInfoMap) {
		JSONObject tokenInfo = new JSONObject();
		tokenInfo.put("access-token", tokenInfoMap.get("token"));
		tokenInfo.put("accessUserId", tokenInfoMap.get("accessUserId"));
		Cookie tokenCookie = new Cookie("hiball_token", tokenInfo.toString());
		tokenCookie.setPath("/");
		
		resp.addCookie(tokenCookie);
	}
	
	private void configUserInfo(HttpServletResponse resp) {
		JSONObject userInfo = new JSONObject();
		userInfo.put("clientId", clientId);
		userInfo.put("secureKey", secureKey);
		userInfo.put("authUrl", authUrl);
		Cookie userCookie = new Cookie("client_info", userInfo.toString());
		resp.addCookie(userCookie);
	}
	
	private void removeConfigedToken(HttpServletResponse res) {
		Cookie emptyToken = new Cookie("hiball_token", null);
		emptyToken.setPath("/");
		res.addCookie(emptyToken);
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecureKey() {
		return secureKey;
	}

	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}
}
