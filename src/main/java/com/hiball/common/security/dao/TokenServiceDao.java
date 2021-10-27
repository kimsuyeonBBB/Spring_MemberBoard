package com.hiball.common.security.dao;

import java.util.Map;

import com.hiball.common.security.domain.TokenDomain;

public interface TokenServiceDao {
	public int insertTokenInformation(Map<String, Object> param);
	public TokenDomain selectClientInformation(Map<String, Object> param);
	public TokenDomain selectAccessTokenInformation(Map<String, String> param);
}
