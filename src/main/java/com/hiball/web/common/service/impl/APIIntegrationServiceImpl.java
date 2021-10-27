package com.hiball.web.common.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hiball.common.security.domain.User;
import com.hiball.web.common.excpeiton.HiBallException;
import com.hiball.web.common.param.CommonParam;
import com.hiball.web.common.service.APIIntegrationService;
import com.hiball.web.common.service.AbstractCommonAPIService;
import com.hiball.web.util.LoggingUtils;

@Service("apiIntgrationService")
public class APIIntegrationServiceImpl implements APIIntegrationService {
	@Autowired
	ApplicationContext appCxt;
	@Autowired
	ServletContext servletContext;
	private static Logger logger = LoggerFactory.getLogger(APIIntegrationServiceImpl.class);

	@Override
	public Map<String, Object> integrationService(Object apiParams) throws HiBallException {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		JSONArray paramArr = null;
		Map<String, Object> systemConstantsMap = (Map<String, Object>) servletContext
				.getAttribute("systemConstantsMap");

		if (apiParams != null) {
			paramArr = (JSONArray) apiParams;
			JSONObject param = null;
			for (int i = 0; i < paramArr.size(); i++) {
				long startTime = System.currentTimeMillis();
				param = (JSONObject) paramArr.get(i);

				logger.debug("##### Start Service name: {} / Sub Service name: {} #####", param.get("serviceName"),
						param.get("subServiceName"));
				String serviceName = (String) param.get("serviceName");
				String subService = (String) param.get("subServiceName");
				String sortColumn = (String) param.get("sortColumn");
				JSONObject paramObj = (JSONObject) param.get("parameterSet");

				System.out.println("----" + serviceName);
				System.out.println("----" + subService);
				System.out.println("----" + sortColumn);
				System.out.println("----" + paramObj);

				AbstractCommonAPIService<?> commonService = (AbstractCommonAPIService<?>) appCxt.getBean(serviceName);
				Map<String, String[]> paramMap = convertFromParamObjToParamMap(paramObj);
				logger.debug("##### Parameter Set : {}", paramMap);
				System.out.println("ParamMap" + paramMap);

				if (!"codeService".contentEquals(serviceName)) {
					User user = getUserAuthorization();
					logger.info("{}", LoggingUtils.makeLoggingInfo(user, subService, paramObj));
				}

				Map<String, Object> result = commonService.execute(subService, sortColumn, paramMap);
				System.out.println("+++" + result);
				resultMap.put((String) param.get("returnName"), result.get("resultMap"));
				System.out.println("+++" + resultMap);

				long endTime = System.currentTimeMillis();
				logger.debug("#### End Service/subService: {}/{} --> Exceution time: {} ms", param.get("serviceName"),
						param.get("subServiceName"), (endTime - startTime));
			}
		} else {
			paramArr = new JSONArray();
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> serviceAPICall(Object apiParams) throws HiBallException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> systemConstantsMap = (Map<String, Object>) servletContext
				.getAttribute("systemConstantsMap");
		JSONObject param = null;

		if (apiParams != null) {
			param = (JSONObject) apiParams;
			logger.debug("##### Service: {} #####", param.get("serviceName"));

			String serviceName = (String) param.get("serviceName");
			String subService = (String) param.get("subServiceName");
			JSONObject paramObj = (JSONObject) param.get("parameterSet");

			AbstractCommonAPIService<?> commonService = (AbstractCommonAPIService<?>) appCxt.getBean(serviceName);
			Map<String, String[]> paramMap = convertFromParamObjToParamMap(paramObj);

			commonService.setSummaryTablePostfixMap(convertFromEntrySetToMap(systemConstantsMap));
			resultMap.put((String) param.get("returnName"),
					commonService.execute(subService, "", paramMap).get("resultMap"));
		} else {
			param = new JSONObject();
		}

		return resultMap;
	}

	private Map<String, Object> convertFromEntrySetToMap(Map<String, Object> entrySet) {
		Map<String, Object> result = new HashMap<String, Object>();

		Iterator keys = entrySet.entrySet().iterator();
		while (keys.hasNext()) {
			Map.Entry<Integer, Object> entry = (Map.Entry<Integer, Object>) keys.next();

			result.put(Integer.toString(entry.getKey()), entry.getValue());
		}

		return result;
	}

	private Map<String, String[]> convertFromParamObjToParamMap(JSONObject paramObj) {
		Map<String, String[]> paramMap = new HashMap<String, String[]>();
		Set tmp = paramObj.keySet();
		if (tmp != null) {
			Iterator<String> keySet = (Iterator<String>) tmp.iterator();
			String key = "";
			String[] value = null;
			String keyValue = "";
			while (keySet.hasNext()) {
				key = keySet.next();
				Object val = paramObj.get(key);
				if (val != null) {
					keyValue = String.valueOf(val);
				}

				if (!StringUtils.isEmpty(keyValue)) {
					value = keyValue.split(",");
				} else {
					value = null;
				}
				paramMap.put(key, value);
			}
		}
		return paramMap;
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
}
