package com.hiball.web.util.analysis.viewpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.value.CodeConvertor;

public class AnalysisTeamViewPointUtil {	
	
	public static Map<String, Object> analysisViewPoint(List<GameRecord> recordList, String viewPointType) {
		Map<String, Integer> initSettingMap = new HashMap<>();
		Map<String, Integer> totalCaseMap = new HashMap<>();
		Map<String, Integer> totalCaseFilterMap = new HashMap<>();
		
		Map<String, Object> resultMap = new HashMap<>();
		
		String key = "";
		int value = 1;
		
		for(GameRecord record : recordList) {
			
			if(viewPointType.equals("playerViewPoint")) {
				if(record.getAnalysisValue() <= 1) continue;
				
				key = record.getBatterMembersId() + "/" + record.getBatterName() + "/" 
							+ CodeConvertor.codeConvert(record.getAnalysisFactor()) + "/" + record.getAnalysisValue() + "/" + record.getWinOrLose();
			} else if(viewPointType.equals("teamViewPoint")) {
				key = record.getBatterTeamId() + "/" + record.getBatterTeamName() + "/"
							+ record.getAnalysisFactor() + "/" + record.getWinOrLose(); 
			}
	
		
			if(!initSettingMap.containsKey(key)) {
				initSettingMap.put(key, value);
			} else {
				initSettingMap.put(key, initSettingMap.get(key)+1);
			}
			
		}
		
		Set<String> keySets = initSettingMap.keySet();
		
		for(String keySet : keySets) {
			value = initSettingMap.get(keySet);
			
			String[] keySetArray = keySet.split("/");
			
			if(viewPointType.equals("playerViewPoint")) {
				key = keySetArray[0] + "/" + keySetArray[1] + "/" + keySetArray[2] + "/" + keySetArray[3];
			} else if(viewPointType.equals("teamViewPoint")) {
				
			}
			
			
			
			if(!totalCaseMap.containsKey(key)) {
				totalCaseMap.put(key, value);
			} else {
				totalCaseMap.put(key, totalCaseMap.get(key) + value);
			}
		}
		
		Set<String> totalKeySets = totalCaseMap.keySet();
		
		for(String totalKeySet : totalKeySets) {
			int checkValue = totalCaseMap.get(totalKeySet);
			
			if(checkValue >= 10) {
				totalCaseFilterMap.put(totalKeySet, totalCaseMap.get(totalKeySet));
			}
		}
		
		resultMap.put("winRateMap", analysisCaseMap(totalCaseFilterMap, initSettingMap, "WIN"));
		resultMap.put("loseRateMap", analysisCaseMap(totalCaseFilterMap, initSettingMap, "LOSE"));
		
		return resultMap;
		
	}
		
	private static Map<String, String> analysisCaseMap(Map<String, Integer> totalCaseMap, Map<String, Integer> caseMap, String winOrLose) {
		Map<String, String> resultMap = new HashMap<>();
		Set<String> totalCaseMapKeySet = totalCaseMap.keySet();
		
		for(String key : totalCaseMapKeySet) {
			int totalCaseCnt = totalCaseMap.get(key);
			StringBuilder resultValueSb = new StringBuilder();
			
			String winKey = key+"/"+"WIN";
			String loseKey = key+"/"+"LOSE";
			int winCnt = 0;
			int loseCnt = 0;
			
			if(caseMap.containsKey(winKey)) {
				winCnt = caseMap.get(winKey);
			} else {
				winCnt = 0;
			}
			
			if(caseMap.containsKey(loseKey)) {
				loseCnt = caseMap.get(loseKey);
			} else {
				loseCnt = 0;
			}
			
			double rate = 0.0;
			
			if(winOrLose.equals("WIN")) {
				rate = Math.round(((double)winCnt / (double)totalCaseCnt) * 100);
			} else {
				rate = Math.round(((double)loseCnt / (double)totalCaseCnt) * 100);
			}
			
			if(rate > 50.0) {
				resultValueSb.setLength(0);
				resultValueSb.append(winCnt + "/" + loseCnt + "/" + totalCaseCnt + "/" + rate);
				resultMap.put(key, resultValueSb.toString());
			}	
		}
		
		return resultMap;
		
	}
	
	

}
