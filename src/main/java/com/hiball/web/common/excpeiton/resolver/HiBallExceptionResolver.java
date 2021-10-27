package com.hiball.web.common.excpeiton.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.hiball.web.common.excpeiton.HiBallErrorInfo;
import com.hiball.web.common.excpeiton.HiBallException;


public class HiBallExceptionResolver implements HandlerExceptionResolver {
	private String view = "";
	
	public void setView(String view) {
		this.view = view;
	}

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exp) {
		ModelAndView mav = new ModelAndView();
		HiBallErrorInfo errInfo = null;
		
		mav.setViewName(view);
		mav.addObject("execute", "failure");
		
		if(exp!=null) {
			String errorCode = "4000";
			String errorMessage = "Current error is't defined";
			
			if(exp instanceof BadSqlGrammarException) {
				errorCode = "4001";
				errorMessage = "Current error is SQLError";
			} else if(exp instanceof MyBatisSystemException) {
				errorCode = "4002";
				errorMessage = "Current error is DBSystemError";
			} else if(exp instanceof IllegalArgumentException) {
				errorCode = "4003";
				errorMessage = "Current error is ElementMatchingError. " + exp.getMessage();
			} else if(exp instanceof JsonMappingException) {
				errorCode = "4004";
				errorMessage = "Current error is JsonMappingError";
			} else if(exp instanceof NullPointerException) {
				errorCode = "4005";
				errorMessage = "Current error is NullPointError";
			} else if(exp instanceof HiBallException) {
				errorCode = "4006";
				errorMessage = exp.getMessage();
			} 
			
			errInfo = new HiBallErrorInfo(errorCode, errorMessage);
		}
		
		mav.addObject("resultMap", errInfo);
		
		return mav;
	}
}
