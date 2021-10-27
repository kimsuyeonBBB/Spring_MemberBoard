package com.hiball.site.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hiball.common.controller.CommonController;
import com.hiball.common.security.domain.User;

@Controller
public class IndexController extends CommonController {
    private final Logger logger = LoggerFactory.getLogger(IndexController.class);
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest req) {
		logger.debug("########## Root page {} ##########", req.getContextPath());
		ModelAndView mav = new ModelAndView();
		User user = getUserAuthorization();
		// logger.info("{}", LoggingUtils.makeLoggingInfo(user, "main"));

		mav.addObject("user", user);
		mav.setViewName("index");

		return mav;
	}
    
    @RequestMapping(value="memberList", method=RequestMethod.GET)
	public ModelAndView memberList(HttpServletRequest req) {
		logger.debug("##### Member List{} #####");
		ModelAndView mav = new ModelAndView();
		User user = getUserAuthorization();
		
		mav.addObject("user",user);
		mav.setViewName("index");
		return mav;
	}
    
    @RequestMapping(value="memberAdd", method= {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView memberAdd(HttpServletRequest req) {
    	logger.debug("##### Member Add{} #####");
    	ModelAndView mav = new ModelAndView();

    	mav.setViewName("index");
    	return mav;
    }
    
    @RequestMapping(value="memberUpdate", method=RequestMethod.GET)
    public ModelAndView memberUpdate(HttpServletRequest req) {
    	logger.debug("##### Member Update{} #####");
    	ModelAndView mav = new ModelAndView();
    	User user = getUserAuthorization();
		
		mav.addObject("user",user);
    	mav.setViewName("index");
    	return mav;
    }
    
    @RequestMapping(value="boardList", method=RequestMethod.GET)
    public ModelAndView boardList(HttpServletRequest req) {
    	logger.debug("##### Board List{} #####");
    	ModelAndView mav = new ModelAndView();
    	User user = getUserAuthorization();
    	
    	mav.addObject("user",user);
    	mav.setViewName("index");
    	return mav;
    }
    
    @RequestMapping(value="boardAdd", method=RequestMethod.GET)
    public ModelAndView boardAdd(HttpServletRequest req) {
    	logger.debug("##### Board Add{} #####");
    	ModelAndView mav = new ModelAndView();
    	User user = getUserAuthorization();
    	
    	mav.addObject("user",user);
    	mav.setViewName("index");
    	return mav;
    }
    
    @RequestMapping(value="boardUpdate",method=RequestMethod.GET)
    public ModelAndView boardUpdate(HttpServletRequest req) {
    	logger.debug("##### Board Update{} #####");
    	ModelAndView mav = new ModelAndView();
    	User user = getUserAuthorization();
    	
    	mav.addObject("user",user);
    	mav.setViewName("index");
    	return mav;
    }
    
    @RequestMapping(value="findId", method=RequestMethod.GET)
    public ModelAndView findId(HttpServletRequest req) {
    	logger.debug("##### Find Id{} #####");
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("index");
    	return mav;
    }
    
    @RequestMapping(value="findPw", method=RequestMethod.GET)
    public ModelAndView findPw(HttpServletRequest req) {
    	logger.debug("##### Find Password{} #####");
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("index");
    	return mav;
    }
    
    private User getUserAuthorization() {
    	User user = null;
    	SecurityContext sc = SecurityContextHolder.getContext();
    	Authentication auth = sc.getAuthentication();
    	
    	if (auth != null) {
    		user = (User) auth.getPrincipal();
    	}
    	
    	return user;
    }
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
    	logger.debug("########## Login page ##########");
    	return "newLogin";
    }
    
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String error() {
    	logger.debug("########## Error page ##########");
    	return "error";
    }
    

}
