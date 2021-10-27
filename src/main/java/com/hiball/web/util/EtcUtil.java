package com.hiball.web.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class EtcUtil {
	@SuppressWarnings("unchecked")
	public static List<String> getSeasionPostfixsForRange (Map<String, Object> yearMap, int range) {
		List<String> latestYearSet = new ArrayList<String>();
		String key = "";
		Map<String, String> valueMap = null;
		String value = "";
		String year = "";
		String postfix = "";
		
		if (yearMap.size() < range || range == 0) {
			range = yearMap.size();
		}
		
		Iterator<String> keySet = yearMap.keySet().iterator();
		
		while (keySet.hasNext()) {
			key = keySet.next();
			valueMap = (Map<String, String>)yearMap.get(key);
			value = valueMap.get("postfix");
			if (StringUtils.isBlank(value)) {
				year = key;
				break;
			}
		}
		
		for (int i=0; i<range; i++) {
			if (i == 0) {
				latestYearSet.add("0");
			} else {
				year = Integer.toString(Integer.parseInt(year) - i);
				valueMap = (Map<String, String>)yearMap.get(year);
				if (valueMap != null) {
					postfix = valueMap.get("postfix");
					latestYearSet.add(postfix);
				} else {
					i--;
				}
			}
		}
		
		return latestYearSet;
	}
}
