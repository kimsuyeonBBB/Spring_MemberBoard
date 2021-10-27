package com.hiball.common.security.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.token.TokenService;

public interface HiballClientTokenService extends TokenService {
	public void confirmUserLogin(HttpServletRequest req, Map<String, String> tokenInfoMap);
	public Map<String, Object> terminateToken(HttpServletRequest req, Map<String, Object> parameterMap) throws Exception;
}
