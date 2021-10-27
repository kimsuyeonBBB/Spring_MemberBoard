package com.hiball.web.util.analysis;

import java.util.Map;

import com.hiball.api.domain.GameRecord;

public interface TeamViewPoint {
	public void setInitSettingMap(Map<String, Integer> initSettingMap, GameRecord record);
	public String totalCaseMapKeyMake(String key);
	public Map<String, Integer> filter(Map<String, Integer> totalCaseMap);
	public boolean analysisMapFilter(String key, String winOrLose, double rate);
	public GameRecord setGameRecord(String key, String Value);
}
