package com.hiball.web.util.strongorweakutil.impl;

import java.util.ArrayList;
import java.util.List;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.strongorweakutil.AbstractStrongOrWeakRankPoper;

public class BatterStrongOrWeakRankPoper extends AbstractStrongOrWeakRankPoper{
	
	@Override
	protected List<GameRecord> getChoiceHighRank(List<GameRecord> highRank, List<GameRecord> allList) {
		List<GameRecord> resultList = new ArrayList<>();
		
		for(GameRecord highRecord : highRank){
			String batterName = highRecord.getBatterName();
			for(GameRecord record : allList) {
				if(batterName.equals(record.getBatterName())) {
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
			String batterName = rowRecord.getBatterName();
			for(GameRecord record : allList) {
				if(batterName.equals(record.getBatterName())) {
					resultList.add(record);
					break;
				}
			}
		}
		return resultList;
	}
	
}
