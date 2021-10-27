package com.hiball.web.util.value;

import java.util.HashMap;
import java.util.Map;

public class CodeConvertor {
	private static Map<Object, String> codeNameMap = new HashMap<>();
	
	public static String codeConvert(String code) {
		if(!codeNameMap.containsKey(code)){ 
			synchronized (code) {
				codeNameMap.put(code, customCodeConvert(code));
			}
		}
		
		return codeNameMap.get(code);
	}
	
	static {
		codeNameMap.put("b1b", "1루타");
		codeNameMap.put("b2b", "2루타");
		codeNameMap.put("b3b", "3루타");
		codeNameMap.put("hr", "홈런");
		codeNameMap.put("sb", "도루");
		codeNameMap.put("k", "삼진");
		codeNameMap.put("so", "삼진");
		codeNameMap.put("bb", "볼넷");
		codeNameMap.put("err", "실책");	
		codeNameMap.put("gdp", "병살");
		codeNameMap.put("6604", "1루타");
		codeNameMap.put("6607", "2루타");
		codeNameMap.put("6610", "3루타");
		codeNameMap.put("6613", "홈런");
		codeNameMap.put("tunAroundWinAfter5Inning", "5이닝 이후 역전 시");
		codeNameMap.put("firstGetPoint", "선취득점시");
		codeNameMap.put("5InningOverRead", "5이닝 이상 리드 시");
	}
	
	private static String customCodeConvert(String code) {
		String convertedCode = "";
		
		if(code.contains("AbNoHit")){
			String[] codeArray = code.split("/");
			convertedCode = codeArray[0] + "타수 무안타";
		} else {
			convertedCode = code;
		}
		
		return convertedCode;
	}
}
