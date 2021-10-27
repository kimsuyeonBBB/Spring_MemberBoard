package com.hiball.web.util.analysis.viewpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.analysis.TeamViewPoint;
import com.hiball.web.util.sort.TeamViewPointComparator;

public class TeamViewPointConcrete {
	private TeamViewPoint tvp = null;

	public TeamViewPointConcrete(TeamViewPoint tvp) {
		this.tvp = tvp;
	}
	
	public Map<String, Object> analysisViewPoint(List<GameRecord> recordList) {
		Map<String, Integer> initSettingMap = new HashMap<>();
		Map<String, Integer> totalCaseMap = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		for(GameRecord record : recordList) {
			//initsettingMap setting
			tvp.setInitSettingMap(initSettingMap, record);			
		}
		
		Set<String> initMapKeySets = initSettingMap.keySet();
		
		for(String key : initMapKeySets) {
			int value = initSettingMap.get(key);
			String newKey = tvp.totalCaseMapKeyMake(key);
			
			if(!totalCaseMap.containsKey(newKey)) {
				totalCaseMap.put(newKey, value);
			} else {
				totalCaseMap.put(newKey, totalCaseMap.get(newKey) + value);
			}
		}
		
		totalCaseMap = tvp.filter(totalCaseMap);
		
		resultMap.put("winRateMap", analysisCaseMap(totalCaseMap, initSettingMap, "WIN"));
		resultMap.put("loseRateMap", analysisCaseMap(totalCaseMap, initSettingMap, "LOSE"));
		
		return resultMap;
	}
	/**
	 * analysisViewPoint Return Value에 대해서 계산을 진행하는 메소드
	 * @author birdhead
	 */
	private Map<String, String> analysisCaseMap(Map<String, Integer> totalCaseMap, Map<String, Integer> initSettingMap, String winOrLose) {
		Map<String, String> resultMap = new HashMap<>();
		Set<String> totalCaseMapKeySet = totalCaseMap.keySet();
		
		for(String key : totalCaseMapKeySet) {
			int totalCaseCnt = totalCaseMap.get(key);
			StringBuilder resultValueSb = new StringBuilder();
			
			String winKey = key + "/" + "WIN";
			String loseKey = key + "/" + "LOSE";
			int winCnt = 0;
			int loseCnt = 0;
			
			if(initSettingMap.containsKey(winKey)) {
				winCnt = initSettingMap.get(winKey);
			}
			
			if(initSettingMap.containsKey(loseKey)) {
				loseCnt = initSettingMap.get(loseKey);
			}
			
			double rate = 0.0;
			
			if(winOrLose.equals("WIN")) {
				rate = Math.round(((double)winCnt / (double)totalCaseCnt) * 100);
			} else {
				rate = Math.round(((double)loseCnt / (double)totalCaseCnt) * 100);
			}
			
			if(tvp.analysisMapFilter(key, winOrLose, rate)) {
				resultValueSb.setLength(0);
				resultValueSb.append(winCnt + "/" + loseCnt + "/" + totalCaseCnt + "/" + rate);
				
				resultMap.put(key, resultValueSb.toString());
			}
		}
		
		List<GameRecord> sortedList = makeListDataFromMapData(resultMap);
		
		sortedList.sort(new TeamViewPointComparator());
		
		resultMap = makeMapDataFromListData(sortedList);
		
		return resultMap;
	}
	
	private List<GameRecord> makeListDataFromMapData(Map<String, String> mapData) {
		List<GameRecord> resultList = new ArrayList<>();
		GameRecord record = null;
		Set<String> keySets = mapData.keySet();
		
		for(String key : keySets) {
			record = tvp.setGameRecord(key, mapData.get(key));
			resultList.add(record);
		}
		
		return resultList;		
	}
	
	private Map<String, String> makeMapDataFromListData(List<GameRecord> gameRecordList) {
		Map<String, String> resultMap = new LinkedHashMap<>();
		
		for(GameRecord record : gameRecordList) {
			String key = record.getMembersId() + "/" + record.getMembersName() 
							+ "/" + record.getAnalysisFactor() + "/" + record.getAnalysisValue();
			String value = record.getWinCnt() + "/" + record.getLoseCnt() + "/" + record.getTotal()
							+ "/" + record.getWinOrLoseRate();
			
			if(!resultMap.containsKey(key)) {
				resultMap.put(key, value);
			} else {
				resultMap.put(key, resultMap.get(key) + value);
			}
		}
		
		return resultMap;
	}
}
