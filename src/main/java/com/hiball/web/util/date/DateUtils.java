package com.hiball.web.util.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hiball.web.common.param.CommonParam;

public class DateUtils {
	public static void setPostfixList(String startDate, String endDate, CommonParam param) {
		List<String> resultList = new ArrayList<>();
		
		if(startDate != null && endDate != null) {
			if(!startDate.equals("") && !endDate.equals("")) {
				int startYear = Integer.valueOf(startDate.substring(0, 4));
				int endYear = Integer.valueOf(endDate.substring(0, 4));
				
				if(startYear != endYear) {
					for(int start = startYear; start <= endYear; start++) {
						if(start==2014) continue; // 2014년 데이터는 없으므로 넣지 않는다.
						if(start==2015) resultList.add("");
						else resultList.add("_" + String.valueOf(start));
					}
				}else {
					if(startYear==2015) resultList.add("");
					else {resultList.add("_"+String.valueOf(startYear));}
				}
			}
		}
		
		if(resultList.size() >= 1) param.setTablePostfixList(resultList);
	}
	
	public static int currentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
}
