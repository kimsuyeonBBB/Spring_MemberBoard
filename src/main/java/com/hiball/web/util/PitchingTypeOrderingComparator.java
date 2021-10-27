package com.hiball.web.util;

import java.util.Comparator;

import com.hiball.api.domain.GameRecord;

public class PitchingTypeOrderingComparator implements Comparator<GameRecord> {

	@Override
	public int compare(GameRecord record1, GameRecord record2) {
		int value = 0;
		
		String str1 = record1.getStreakGameCategory();
		String str2 = record2.getStreakGameCategory();
		
		value = str1.compareTo(str2);
		
		return value;
	}
	
}
