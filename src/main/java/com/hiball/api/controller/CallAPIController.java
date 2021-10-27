package com.hiball.api.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hiball.web.common.excpeiton.HiBallException;
import com.hiball.web.common.param.SystemConstantsParam;
import com.hiball.web.common.service.APIIntegrationService;
import com.hiball.web.common.service.AbstractCommonAPIService;

@Controller
public class CallAPIController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	ApplicationContext appCxt;
	@Autowired
	APIIntegrationService apiIntegrationService;

	@RequestMapping(value = "apiMultiCall.do", method = RequestMethod.POST)
	public ModelAndView callMultiAPI(HttpServletRequest request) throws HiBallException {
		Map<String, Object> result = null;

		ModelAndView mav = new ModelAndView();

		Object apiParams = request.getAttribute("apiRequestParam");
		System.out.println("^^^^^^^^^^^^^^^^^" + apiParams + "^^^^^^^^^^^^^^^^^^^^");

		setStaticSystemConstants(request);
		result = apiIntegrationService.integrationService(apiParams);
		System.out.println("!!!!!!!!!!!!!!!!!!" + result + "!!!!!!!!!!!!!!!");

		mav.addObject("execute", "success");
		mav.addObject("resultMap", result);
		mav.setViewName("jsonTypeView");

		return mav;
	}

	@RequestMapping(value = "serviceAPICall.do", method = RequestMethod.POST)
	public ModelAndView callServiceAPI(HttpServletRequest request) throws HiBallException {
		Map<String, Object> result = null;
		ModelAndView mav = new ModelAndView();
		Object apiParams = request.getAttribute("apiRequestParam");

		setStaticSystemConstants(request);
		result = apiIntegrationService.serviceAPICall(apiParams);

		mav.addObject("execute", "success");
		mav.addObject("resultMap", result);
		mav.setViewName("jsonTypeView");

		return mav;
	}

	@SuppressWarnings("unchecked")
	private void setStaticSystemConstants(HttpServletRequest request) throws HiBallException {
		Map<String, Object> systemConstantsMap = (Map<String, Object>) servletContext
				.getAttribute("systemConstantsMap");

		if (systemConstantsMap == null) {
			AbstractCommonAPIService<SystemConstantsParam> tmpService = (AbstractCommonAPIService<SystemConstantsParam>) appCxt
					.getBean("systemConstantsService");
			systemConstantsMap = tmpService.execute(null, "", null);

			servletContext.setAttribute("systemConstantsMap", systemConstantsMap);
		}
	}
}
