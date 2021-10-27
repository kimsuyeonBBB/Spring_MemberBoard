package com.hiball.web.util.value;

import java.util.ArrayList;
import java.util.List;

public class TeamListUtil {
	public static final List<Integer> teamList = new ArrayList<>();
	public static final List<Integer> teamList_2014 = new ArrayList<>();
	static {
		teamList.add(1001);
		teamList.add(2001);
		teamList.add(3001);
		teamList.add(4001);
		teamList.add(5001);
		teamList.add(6001);
		teamList.add(7001);
		teamList.add(8001);
		teamList.add(9001);
		teamList.add(10001);
		
		
		teamList_2014.add(1001);
		teamList_2014.add(2001);
		teamList_2014.add(3001);
		teamList_2014.add(4001);
		teamList_2014.add(5001);
		teamList_2014.add(6001);
		teamList_2014.add(7001);
		teamList_2014.add(8001);
		teamList_2014.add(9001);
	}
	
	public static List<Integer> getTeamList(int year) {
		List<Integer> returnList = null;
		if(year <= 2014) {
			returnList = teamList_2014;
		} else if(year >= 2015 ) {
			returnList = teamList;
		}
		
		return returnList;
	}
}
