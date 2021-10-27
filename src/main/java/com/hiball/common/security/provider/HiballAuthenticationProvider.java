package com.hiball.common.security.provider;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hiball.common.security.domain.User;
import com.hiball.common.security.service.UserService;

public class HiballAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(HiballAuthenticationProvider.class);
    @Autowired
    UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
	String userNm = auth.getName();
	String userPw = (String) auth.getCredentials();
	String encodingPw = "";
	String encodingPw2 = "";
	User user;
	Collection<? extends GrantedAuthority> authorities;

	try {
	    user = userService.loadUserByUsername(userNm);
	    encodingPw = passwordEncoder.encode(userPw);
	    encodingPw2 = passwordEncoder.encode(user.getPassword());
	    logger.debug("########## Username: {}/ Password: {}", userNm, encodingPw);

	    System.out.println("++userPw println--" + userPw);
	    System.out.println("++user.getPassword()--" + user.getPassword());
	    if (!passwordEncoder.matches(userPw, encodingPw2))
		throw new BadCredentialsException("Incorrect password!!");

	    authorities = user.getAuthorities();

	} catch (UsernameNotFoundException e) {
	    logger.debug(e.toString());
	    throw new UsernameNotFoundException(e.getMessage());
	} catch (BadCredentialsException e) {
	    logger.debug(e.toString());
	    throw new BadCredentialsException(e.getMessage());
	} catch (Exception e) {
	    logger.debug(e.toString());
	    throw new RuntimeException(e.getMessage());
	}

	return new UsernamePasswordAuthenticationToken(user, encodingPw, authorities);
    }

    @Override
    public boolean supports(Class<?> clazz) {
	return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz));
    }

}
