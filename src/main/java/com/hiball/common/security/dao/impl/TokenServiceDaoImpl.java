package com.hiball.common.security.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hiball.common.security.dao.TokenServiceDao;
import com.hiball.common.security.domain.TokenDomain;

@Repository ("tokenServiceDao")
public class TokenServiceDaoImpl implements TokenServiceDao {
	private String NAMESPACE = "com.hiball.security.token.";
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertTokenInformation(Map<String, Object> param) {
		int result = sqlSession.insert(NAMESPACE+"insertClientAccessTokenInformation");
		return result;
	}

	@Override
	public TokenDomain selectClientInformation(Map<String, Object> param) {
		TokenDomain result = sqlSession.selectOne(NAMESPACE+"selectClientInformation", param); 
		return result;
	}

	@Override
	public TokenDomain selectAccessTokenInformation(Map<String, String> param) {
		TokenDomain result = sqlSession.selectOne(NAMESPACE+"selectAccessTokenInformation", param);
		return result;
	}

}
