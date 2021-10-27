package com.hiball.web.util;

import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;

import com.hiball.common.security.domain.User;

public class LoggingUtils {
    public static String makeLoggingInfo(User user, String viewId) {
	JSONObject jsonObj = new JSONObject();
   	
	jsonObj.put("userNo", user.getUserNo());
	jsonObj.put("id", user.getUsername());
	jsonObj.put("name", user.getName());
	jsonObj.put("time", System.currentTimeMillis());
	jsonObj.put("viewId", viewId);
   	
	return jsonObj.toJSONString();
    }
    
    public static String makeLoggingInfo(Authentication auth, String viewId) {
	JSONObject jsonObj = new JSONObject();
	User user = (User) auth.getPrincipal();
	
	jsonObj.put("userNo", user.getUserNo());
	jsonObj.put("id", user.getUsername());
	jsonObj.put("name", user.getName());
	jsonObj.put("time", System.currentTimeMillis());
	jsonObj.put("viewId", viewId);
	
	return jsonObj.toJSONString();
    }
    
    public static String makeLoggingInfo(User user, String viewId, JSONObject content) {
	JSONObject jsonObj = new JSONObject();
	
	jsonObj.put("userNo", user.getUserNo());
	jsonObj.put("id", user.getUsername());
	jsonObj.put("name", user.getName());
	jsonObj.put("time", System.currentTimeMillis());
	jsonObj.put("viewId", viewId);
	jsonObj.put("content", content);
	
	return jsonObj.toJSONString();
    }
}
