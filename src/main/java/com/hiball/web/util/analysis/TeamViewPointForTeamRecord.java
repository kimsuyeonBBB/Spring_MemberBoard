package com.hiball.web.util.analysis;

import java.util.Map;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.value.CodeConvertor;

public class TeamViewPointForTeamRecord implements TeamViewPoint {

	@Override
	public void setInitSettingMap(Map<String, Integer> initSettingMap, GameRecord record) {
		String newKey = record.getBatterTeamId() + "/" + record.getBatterTeamName() + "/" + CodeConvertor.codeConvert(record.getAnalysisFactor()) + "/"
				 		+ record.getWinOrLose();
		
		if(!initSettingMap.containsKey(newKey)) {
			initSettingMap.put(newKey, record.getAnalysisValue());
		} else {
			initSettingMap.put(newKey, initSettingMap.get(newKey) + record.getAnalysisValue());
		}
	}

	/**
	 * setInitSettingMap에서 만들어진 Key값의 WinOrLose 속성을 제외한 것으로 새로운 키를 만드는 작업을 하는 메소드
	 */
	@Override
	public String totalCaseMapKeyMake(String key) {
		String[] keyArray = key.split("/");
		
		String newKey = keyArray[0] + "/" + keyArray[1] + "/" + keyArray[2];
		
		return newKey;
	}

	@Override
	public Map<String, Integer> filter(Map<String, Integer> totalCaseMap) {
		return totalCaseMap;
	}

	@Override
	public boolean analysisMapFilter(String key, String winOrLose, double rate) {
		return true;
	}

	@Override
	public GameRecord setGameRecord(String key, String value) {
		GameRecord targetRecord = new GameRecord();
		
		String[] keyArray = key.split("/");
		String[] valueArray = value.split("/");
		
		targetRecord.setMembersId(Integer.valueOf(keyArray[0]));
		targetRecord.setMembersName(keyArray[1]);
		targetRecord.setAnalysisFactor(keyArray[2]);
		targetRecord.setWinCnt(Integer.valueOf(valueArray[0]));
		targetRecord.setLoseCnt(Integer.valueOf(valueArray[1]));
		targetRecord.setTotal(Integer.valueOf(valueArray[2]));
		targetRecord.setWinOrLoseRate(Double.valueOf(valueArray[3]));
		
		return targetRecord;
	}

}
