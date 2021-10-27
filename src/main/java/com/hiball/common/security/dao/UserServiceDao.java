package com.hiball.common.security.dao;

import com.hiball.common.security.domain.User;

public interface UserServiceDao {
	public User selectUserInfoByUsername(User param);
}
