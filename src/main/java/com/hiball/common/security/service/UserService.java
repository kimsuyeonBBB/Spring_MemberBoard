package com.hiball.common.security.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hiball.api.domain.CodeDomain;
import com.hiball.api.param.CodeParam;
import com.hiball.api.services.code.dao.CodeDao;
import com.hiball.common.security.dao.UserServiceDao;
import com.hiball.common.security.domain.Role;
import com.hiball.common.security.domain.User;

@Service("userService")
public class UserService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserServiceDao userServiceDao;

	@Override
	public User loadUserByUsername(final String username) throws UsernameNotFoundException {
		logger.debug("########## Loading user informaition by username {} ##########", username);
		User param = new User();
		param.setUsername(username);
		logger.debug(param.toString());
		User user = userServiceDao.selectUserInfoByUsername(param);

		/*
		 * String password =
		 * "$2a$10$qn.ElyfZmB5opIqpYR9IK.G/OH6.gUmXMUHfcX1USquxqUcA7Bn.2";
		 * 
		 * User user = new User(); user.setUsername(username);
		 * user.setPassword(password);
		 * */
		
		 Role role = new Role(); role.setName("ROLE_USER");
		 
		 List<Role> authorities = new ArrayList<Role>(); authorities.add(role);
		 user.setAuthorities(authorities);
		

		// 만약 데이터가 없을 경우 예외 발생
		if (user == null)
			throw new UsernameNotFoundException("Not exist username.");

		return user;
	}
}
