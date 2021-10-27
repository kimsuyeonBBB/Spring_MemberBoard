package com.hiball.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hiball.api.domain.GameRecord;

public class PitchingTypeUtil {
	public static final String[] pitchingType = {"groupingByPitchingHand", "groupingByPitchingForm"};
	public static final String[] pitchingOnlyHandNFormType = {"groupingByOnlyPitchingHand", "groupingByOnlyPitchingForm"};
	
	public static List<GameRecord> recordOfGameStreak(List<GameRecord> recordTmpList) {
		Map<String, GameRecord> recordMap = new HashMap<>();
		Map<String, Integer> streakMap = new HashMap<>();
		
		recordMap.put("1게임", new GameRecord());
		recordMap.put("2게임", new GameRecord());
		recordMap.put("3게임", new GameRecord());
		recordMap.put("4게임", new GameRecord());
		recordMap.put("5게임이상", new GameRecord());
		
		streakMap.put("streakGameCnt", 1);
		//this is category of gameCnt
		streakMap.put("1게임", 0);
		streakMap.put("2게임", 0);
		streakMap.put("3게임", 0);
		streakMap.put("4게임", 0);
		streakMap.put("5게임이상", 0);
		
		for(GameRecord record : recordTmpList) {
			if(record.getStreakGameCnt() == 1) {
				streakMap.put("streakGameCnt", streakMap.get("streakGameCnt") + record.getStreakGameCnt());
				int streakValue = streakMap.get("streakGameCnt");
				
				if(streakValue < 5) {
					GameRecord resultRecord = recordMap.get(streakValue + "게임");
					
					gameRecordSetMethod(resultRecord, record);
					
					recordMap.put(streakValue+"게임", resultRecord);
					streakMap.put(streakValue+"게임", streakMap.get(streakValue + "게임") + 1); //increment gameCnt
				} else if(streakValue >= 5){
					GameRecord resultRecord = recordMap.get("5게임이상");
					
					gameRecordSetMethod(resultRecord, record);
					
					recordMap.put("5게임이상", resultRecord);
					streakMap.put("5게임이상", streakMap.get("5게임이상") + 1); //increment gameCnt
				}
				
			}else if(record.getStreakGameCnt() == 0) {
				streakMap.put("streakGameCnt", 1);
				int streakValue = streakMap.get("streakGameCnt");
				
				GameRecord resultRecord = recordMap.get(streakValue + "게임");
				
				gameRecordSetMethod(resultRecord, record);
				
				recordMap.put(streakValue+"게임", resultRecord);
				streakMap.put(streakValue+"게임", streakMap.get(streakValue + "게임") + 1); 
			}
		}
		
		List<GameRecord> resultList = makeList(recordMap, streakMap);
		
		return resultList;
	}
	
	private static List<GameRecord> makeList(Map<String, GameRecord> recordMap, Map<String, Integer> streakMap) {
		List<GameRecord> resultList = new ArrayList<>();
		Set<String> keySet = recordMap.keySet();
		
		for(String str : keySet){
			System.out.println(str);
			GameRecord record = recordMap.get(str);
			Integer gameCnt = streakMap.get(str);
			
			record.setGameCnt(gameCnt);
			record.setStreakGameCategory(str);
			
			resultList.add(record);
		}
		
		return resultList;
	}
	
	
	private static void gameRecordSetMethod(GameRecord resultRecord, GameRecord record) {
		resultRecord.setB1b(record.getB1b() + resultRecord.getB1b());
		resultRecord.setB2b(record.getB2b() + resultRecord.getB2b());
		resultRecord.setB3b(record.getB3b() + resultRecord.getB3b());
		resultRecord.setHr(record.getHr() + resultRecord.getHr());
		resultRecord.setOutCount(record.getOutCount() + resultRecord.getOutCount());
		resultRecord.setEr(record.getEr() + resultRecord.getEr());
	}
}
