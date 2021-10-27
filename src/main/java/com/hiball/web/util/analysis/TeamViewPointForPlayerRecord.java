package com.hiball.web.util.analysis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.value.CodeConvertor;

public class TeamViewPointForPlayerRecord implements TeamViewPoint {

	@Override
	public void setInitSettingMap(Map<String, Integer> initSettingMap, GameRecord record) {
		if(record.getAnalysisFactor().equals("b1b") && record.getAnalysisValue() <= 1) return;
		
		String newKey = record.getBatterMembersId() + "/"  + record.getBatterName() + "/" + CodeConvertor.codeConvert(record.getAnalysisFactor())
						+ "/" + record.getAnalysisValue() + "/" + record.getWinOrLose();
		if(!initSettingMap.containsKey(newKey)) {
			initSettingMap.put(newKey, 1);
		} else {
			initSettingMap.put(newKey, initSettingMap.get(newKey) + 1);
		}
	}

	@Override
	public String totalCaseMapKeyMake(String key) {
		String[] keyArray = key.split("/");
		String newKey = keyArray[0] + "/" + keyArray[1] + "/" + keyArray[2] + "/" + keyArray[3];
		
		return newKey;
	}

	@Override
	public Map<String, Integer> filter(Map<String, Integer> totalCaseMap) {
		Map<String, Integer> filteringSuccessedMap = new HashMap<>();
		
		Set<String> keySet = totalCaseMap.keySet();
		
		for(String key : keySet) {
			int checkValue = totalCaseMap.get(key);
			String realKey = key.split("/")[2];
			
			if(realKey.equals("실책")) {
				filteringSuccessedMap.put(key, totalCaseMap.get(key));
			}else {
				if(checkValue >= 5) {
					filteringSuccessedMap.put(key, totalCaseMap.get(key));
				}
			}
		}
		
		return filteringSuccessedMap;
	}

	@Override
	public boolean analysisMapFilter(String key, String winOrLose, double rate) {		
		boolean returnValue = false;
		String code = key.split("/")[2];
		
		if(rate > 50.0) {
			returnValue = true;
		}
		
		if(winOrLose.equals("WIN")) {
			if(code.equals("1루타") || code.equals("2루타") || code.equals("3루타") || code.equals("홈런") || code.equals("도루") || code.equals("실책")
					|| code.equals("삼진") || code.equals("볼넷")) {
				if(rate > 50.0) {
					returnValue = true;
				}
			} else {
				returnValue = false;
			}
		} else if(winOrLose.equals("LOSE")) {
			if(code.contains("무안타") || code.contains("삼진") || code.equals("실책")) {
				if(rate > 50.0) {
					returnValue = true;
				}
			} else {
				returnValue =false;
			}
		}
		
		return returnValue;
	}
	
	/**
	 * token : "/"
	 * key dataSet : batterMembersId / batterName / analysisFactor
	 * value dataSet : winCnt / loseCnt/ totalCnt / winRate
	 */
	@Override
	public GameRecord setGameRecord(String key, String value) {
		GameRecord targetRecord = new GameRecord();
		
		String[] keyArray = key.split("/");
		String[] valueArray = value.split("/");
		
		targetRecord.setMembersId(Integer.valueOf(keyArray[0]));
		targetRecord.setMembersName(keyArray[1]);
		targetRecord.setAnalysisFactor(keyArray[2]);
		targetRecord.setAnalysisValue(Integer.valueOf(keyArray[3]));
		targetRecord.setWinCnt(Integer.valueOf(valueArray[0]));
		targetRecord.setLoseCnt(Integer.valueOf(valueArray[1]));
		targetRecord.setTotal(Integer.valueOf(valueArray[2]));
		targetRecord.setWinOrLoseRate(Double.valueOf(valueArray[3]));
		
		return targetRecord;
		
	}

}
