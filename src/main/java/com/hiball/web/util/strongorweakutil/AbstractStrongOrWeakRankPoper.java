package com.hiball.web.util.strongorweakutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.common.enums.ColumnsEnum;

public abstract class AbstractStrongOrWeakRankPoper {
	/**
	 * 타자의 대결에서 시즌성적과 대결성적 구하는 메소드
	 * @param selectList
	 * @param allList
	 * @return
	 */
	public Map<String, Object> rankMaker(List<GameRecord> selectList, List<GameRecord> allList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		List<GameRecord> highRank = new ArrayList<>();
		List<GameRecord> rowRank = new ArrayList<>();
		
		int selectListSize = selectList.size();
		if(selectListSize > 0) {
			int presentIndex = (int)selectListSize / 2;
			
			if(presentIndex > 5) {
				presentIndex = 5;
			}
		
			for(int i=0, k=selectListSize-1; i<presentIndex && k>selectListSize-presentIndex-1; i++,k--) {
				GameRecord highRecord = selectList.get(i); //해당 선수랑 붙었던 기록에서 꺼낸 record
				GameRecord rowRecord = selectList.get(k); //해당 선수랑 붙었던 기록에서 꺼낸 record
				
				highRank.add(highRecord);
				rowRank.add(rowRecord);
			}
			
			List<GameRecord> selectListFromHighRank = getChoiceHighRank(highRank, allList);
			List<GameRecord> selectListFromRowRank = getChoiceRowRank(rowRank, allList);
			List<List<GameRecord>> highList = new ArrayList<>();
			List<List<GameRecord>> rowList = new ArrayList<>();
			
			highList.add(highRank);
			highList.add(selectListFromHighRank);
			
			rowList.add(rowRank);
			rowList.add(selectListFromRowRank);
			
			resultMap.put("high", highList);
			resultMap.put("row", rowList);
		} 
		return resultMap;
	}
	
	/**
	 * 전체 기록 구하는 로직 빠진 메소드
	 * @param recordList
	 * @return
	 */
	public Map<String, Object> rankMaker(List<Map<ColumnsEnum, Object>> recordList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		List<Map<ColumnsEnum, Object>> highRank = new ArrayList<>();
		List<Map<ColumnsEnum, Object>> rowRank = new ArrayList<>();
		
		int recordListSize = recordList.size();
		
		if(recordListSize > 0) {
			int presentIndex = (int)recordListSize / 2;
			
			if(presentIndex > 5) {
				presentIndex = 5;
			}
		
			for(int i=0, k=recordListSize-1; i<presentIndex && k>recordListSize-presentIndex-1; i++,k--) {
				Map<ColumnsEnum, Object> highRecord = recordList.get(i); //해당 선수랑 붙었던 기록에서 꺼낸 record
				Map<ColumnsEnum, Object> rowRecord = recordList.get(k); //해당 선수랑 붙었던 기록에서 꺼낸 record
				
				highRank.add(highRecord);
				rowRank.add(rowRecord);
			}
		}

		List<List<Map<ColumnsEnum, Object>>> highList = new ArrayList<>();
		List<List<Map<ColumnsEnum, Object>>> rowList = new ArrayList<>();
		
		highList.add(highRank);
		rowList.add(rowRank);
		
		resultMap.put("high", highList);
		resultMap.put("row", rowList);
		
		return resultMap;
	}
	
	protected abstract List<GameRecord> getChoiceHighRank(List<GameRecord> highRank, List<GameRecord> allList);
	
	protected abstract List<GameRecord> getChoiceRowRank(List<GameRecord> rowRank, List<GameRecord> allList);
}
