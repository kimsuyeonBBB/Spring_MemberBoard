package com.hiball.common.security.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hiball.common.security.dao.UserServiceDao;
import com.hiball.common.security.domain.User;

@Repository(value = "userServiceDao")
public class UserServiceDaoImpl implements UserServiceDao {
	@Autowired
	SqlSession userSqlSession;
	private final String PREFIX = "com.hiball.security.user.";

	@Override
	public User selectUserInfoByUsername(User param) {
		User result = (User) userSqlSession.selectOne(PREFIX + "selectUserInfoByUsername", param);
		return result;
	}
}
