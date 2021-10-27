package com.hiball.web.util.convertor;

import java.util.Map;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public abstract class ValueConvertor {
	
	public static String settingValueAsString(Object value) {
		return String.valueOf(value);
	}
	
	public static Integer settingValueAsInteger(Object value) {
		if(value == null) {
			return new Integer(0);
		}
		return Integer.parseInt(String.valueOf(value));
	}
	
	public static Double settingValueAsDouble(Object value) {
		if(value == null) {
			return new Double(0.0);
		}
		return Double.parseDouble(String.valueOf(value));
	}
	
	public static <Key, Value> Multimap<Key, Value> mapConvert(Map<Key, Value> map) {
		return Multimaps.forMap(map);
	}
}
