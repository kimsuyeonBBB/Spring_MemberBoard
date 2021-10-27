package com.hiball.web.util.strongorweakutil.impl;

import java.util.ArrayList;
import java.util.List;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.strongorweakutil.AbstractStrongOrWeakRankPoper;

public class PitcherStrongOrWeakRankPoper extends AbstractStrongOrWeakRankPoper {

	@Override
	protected List<GameRecord> getChoiceHighRank(List<GameRecord> highRank, List<GameRecord> allList) {
		List<GameRecord> resultList = new ArrayList<>();
		
		for(GameRecord highRecord : highRank){
			String pitcherName = highRecord.getPitcherName();
			for(GameRecord record : allList) {
				if(pitcherName.equals(record.getPitcherName())) {
					resultList.add(record);
					break;
				}
			}
		}
		return resultList;
	}

	@Override
	protected List<GameRecord> getChoiceRowRank(List<GameRecord> rowRank, List<GameRecord> allList) {
		List<GameRecord> resultList = new ArrayList<>();
		
		for(GameRecord rowRecord : rowRank) {
			String pitcherName = rowRecord.getPitcherName();
			for(GameRecord record : allList) {
				if(pitcherName.equals(record.getPitcherName())) {
					resultList.add(record);
					break;
				}
			}
		}
		return resultList;
	}

}
