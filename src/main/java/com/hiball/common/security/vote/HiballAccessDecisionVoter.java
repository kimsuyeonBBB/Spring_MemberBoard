package com.hiball.common.security.vote;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

public class HiballAccessDecisionVoter implements AccessDecisionVoter {
    private static final Logger logger = LoggerFactory.getLogger(HiballAccessDecisionVoter.class);

    @Override
    public int vote(Authentication authentication, Object object, Collection attributes) {
	logger.debug("########## working on access decision manager #########");
	int granted = ACCESS_DENIED;


	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	FilterInvocation invocation = (FilterInvocation)object;
	Iterator iter = attributes.iterator();
	String requestUrl = invocation.getRequestUrl();
	
	ConfigAttribute attribute = (ConfigAttribute)iter.next();
	String configedAuth = attribute.toString();
	
	logger.debug("########## Access URL : {} ", requestUrl);
	logger.debug("########## Attirbutes : {}", attribute);
	logger.debug("####### Configed Role {} #######", attribute);
	
	if (requestUrl.contains("login") || requestUrl.contains("error")) {
	    if ("ROLE_ANONYMOUSE".equals(configedAuth)) {
		granted = ACCESS_GRANTED;
	    }
	} else {
	    for (GrantedAuthority authority : authorities) {
		if (configedAuth.contains(authority.getAuthority())) {
		    granted = ACCESS_GRANTED;
		}
	    }
	}
 	

	return granted;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
	return true;
    }

    @Override
    public boolean supports(Class clazz) {
	return true;
    }

}
