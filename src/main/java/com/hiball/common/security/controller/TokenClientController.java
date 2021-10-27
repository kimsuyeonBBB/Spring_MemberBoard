package com.hiball.common.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hiball.common.exception.HiBallException;
import com.hiball.common.security.service.HiballClientTokenService;
import com.hiball.site.controller.IndexController;

@Controller
public class TokenClientController {
	private final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	HiballClientTokenService tokenService;
	
	@RequestMapping(value="token_logout", method=RequestMethod.POST) 
	public ModelAndView tokenLogout(HttpServletRequest req, HttpServletResponse res) throws HiBallException {
		String result = "";
		String redirectUri = "";
		ModelAndView mav = new ModelAndView();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = null;
		
		parameterMap.put("clientId", req.getParameter("clientId"));
		parameterMap.put("accessToken", req.getParameter("accessToken"));

		try {
			SecurityContext context = (SecurityContext)req.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			String rootPath = req.getContextPath();
			
			if (context != null) {
				Authentication auth = context.getAuthentication();
				if (auth != null) {
					logger.debug("##### Logout success parameter {} #####", auth.getName());
					parameterMap.put("accessUserId", auth.getName());
					resultMap = tokenService.terminateToken(req, parameterMap);
					req.getSession().invalidate();
					result = "Success";
					redirectUri = rootPath+"/?isTerminated=true";
				} else {
					result = "Failure";
				}
			} else {
				result = "Failue";
			}
			
			mav.setViewName("jsonTypeView");
			mav.addObject("result", result);
			mav.addObject("redirectUri", redirectUri);
			mav.addObject("resultMap", resultMap);
		} catch (Exception e) {
			throw new HiBallException(e.getMessage());
		}
		
		return mav;
	}
}
