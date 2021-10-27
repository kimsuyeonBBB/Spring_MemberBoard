package com.hiball.web.util.sort;

import java.util.Comparator;

import com.hiball.api.domain.GameRecord;

public class TeamViewPointComparator implements Comparator<GameRecord> {

	@Override
	public int compare(GameRecord preRecord, GameRecord curRecord) {
		int value = 0;
			
		value = preRecord.getMembersName().compareTo(curRecord.getMembersName());
		if(preRecord.getMembersName().equals(curRecord.getMembersName())) {
			value = preRecord.getAnalysisFactor().compareTo(curRecord.getAnalysisFactor());
			
			if(preRecord.getAnalysisFactor().equals(curRecord.getAnalysisFactor())) {
				if(preRecord.getAnalysisValue() <= curRecord.getAnalysisValue()) {
					value = 1;
				}
			}
		} 
		
		return value;
	}
	
}
