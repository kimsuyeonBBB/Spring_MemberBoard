package com.hiball.web.util;

import java.util.Comparator;

import com.hiball.api.domain.GameRecord;

public class EnvironmentOrderingComparator implements Comparator<GameRecord> {
	private String orderingFactor;
		
	public EnvironmentOrderingComparator(String orderingFactor) {
		this.orderingFactor = orderingFactor;
	}
	
	@Override
	public int compare(GameRecord record1, GameRecord record2) {
		
		int value = 0;
		
		if("gameDay".equals(orderingFactor)) {
			
			String gameDay1 = record1.getGameDay();
			String gameDay2 = record2.getGameDay();
			if(gameDay1 == null && gameDay2 == null) return 0;
			else {
				value = gameDay1.compareTo(gameDay2); // 오름차
			}
		}
		
		
		
		return value;
	}
	
	
}
