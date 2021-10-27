package com.hiball.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hiball.api.domain.GameRecord;

@SuppressWarnings("all")
public class StrongOrWeakRankPoper {
	
	public Map<String, List<GameRecord>> rankMaker(List<GameRecord> selectList, List<GameRecord> allList) {
		Map<String, List<GameRecord>> resultMap = new HashMap<>();
		
		List<GameRecord> highRank = new ArrayList<>();
		List<GameRecord> rowRank = new ArrayList<>();
		
		int selectListSize = selectList.size();
		
		for(int i=0, k=selectListSize-1; i<5 && k>selectListSize-6; i++,k--) {
			GameRecord highRecord = selectList.get(i);
			GameRecord rowRecord = selectList.get(k);
			
			highRank.add(highRecord);
			rowRank.add(rowRecord);
		}
		
		for(GameRecord highRecord : highRank) {
			
			for(GameRecord record : allList) {
				
			}
		}
		
		for(GameRecord rowRecord : rowRank) {
			for(GameRecord record : allList) {
				
			}
		}
		
		return null;
	}
	
}
