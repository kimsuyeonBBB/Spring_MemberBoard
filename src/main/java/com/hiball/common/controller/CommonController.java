package com.hiball.common.controller;

import org.json.simple.JSONObject;

import com.hiball.common.security.domain.User;

public abstract class CommonController {
    public String makeLoggingInfo(User user, String viewId) {
	JSONObject jsonObj = new JSONObject();
	
	jsonObj.put("id", user.getUsername());
	jsonObj.put("time", System.currentTimeMillis());
	jsonObj.put("viewId", viewId);
	
	return jsonObj.toJSONString();
    }
}
