package com.hiball.web.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hiball.api.domain.GameRecord;

public class PatternUtils {
	
	public static List<GameRecord> pitchingZoneRank(List<GameRecord> list){
		Map<String, Integer> map = pitchingZoneCount(list);
		List<String> sortList = pitchingZoneKeySortList(map);
		Map<String, Integer> rankingMap = pitchingZoneRank(map, sortList);
		
		for(GameRecord record : list){
			String pitchSbZoneCode = record.getPitchSbZoneCode();
			if(rankingMap.containsKey(pitchSbZoneCode)){
				record.setPitchSbZoneRanking(rankingMap.get(pitchSbZoneCode));
			} else{
				continue;
			}
		}
		
		return list;
	}
	
	public static Map<String, Integer> pitchingZoneCount(List<GameRecord> list){
		Map<String, Integer> map = new HashMap<>();
		for(GameRecord record : list){
			String pitchSbZoneCode = record.getPitchSbZoneCode();
			if(!map.containsKey(pitchSbZoneCode)){
				map.put(pitchSbZoneCode, 1);
			} else {
				map.put(pitchSbZoneCode, map.get(pitchSbZoneCode) + 1);
			}
		}
		
		return map;
	}
	
	private static List<String> pitchingZoneKeySortList(final Map<String, Integer> map){
		List<String> list = new ArrayList<>();
		list.addAll(map.keySet());
		
		Collections.sort(list, new Comparator<Object>(){

			@Override
			public int compare(Object o1, Object o2) {
				Integer value1 = map.get(o1);
				Integer value2 = map.get(o2);
				return ((Comparable<Integer>) value1).compareTo(value2);
			}
			
		});
		
		Collections.reverse(list); //주석시 오름차순
		
		return list;
	}
	
	private static Map<String, Integer> pitchingZoneRank(Map<String, Integer> map, List<String> sortList){
		Integer ranking = 1;
		
		for(String value : sortList){
			map.put(value, ranking++);
		}
			
		return map;
	}
	
}