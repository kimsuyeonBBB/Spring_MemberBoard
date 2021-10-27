package com.hiball.web.util.ordering;

import java.util.Comparator;

import com.hiball.api.domain.GameRecord;

public class AccmBallCntOrderingComparator implements Comparator<GameRecord> {

	@Override
	public int compare(GameRecord preRecord, GameRecord curRecord) {
		int value = 0;
		if(preRecord.getDistAccmPitchingCntOrd() < curRecord.getDistAccmPitchingCntOrd()) {
			value = -1;
		}
		
		return value;
	}
	
}
