package com.hiball.web.util.judgerate;

import java.util.ArrayList;
import java.util.List;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.value.GroupingColumn;

public class JudgeRateUtils {
	public static List<GameRecord> judgeRateCalculation(List<GameRecord> totalCntList, List<GameRecord> filteringCntList, String groupingColumn) {
		List<GameRecord> resultList = new ArrayList<>();
		
		int listSize = totalCntList.size();
		int filteringListSize = filteringCntList.size();
		
		for(int i=0; i<listSize; i++) {
			GameRecord newRecord = new GameRecord();
			
			for(int j=0; j<filteringListSize; j++) {
				
				if(groupingColumn.equals(GroupingColumn.groupingByRunnerState)) {
					if(totalCntList.get(i).getBeforeRunnerState() == filteringCntList.get(j).getBeforeRunnerState()
							&& totalCntList.get(i).getPitchSbZoneCode().equals(filteringCntList.get(j).getPitchSbZoneCode())) {
						newRecord.setsJudgeCnt(filteringCntList.get(j).getsJudgeCnt());
						newRecord.setbJudgeCnt(filteringCntList.get(j).getbJudgeCnt());
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						resultList.add(newRecord);
						break;
					} else {
						newRecord.setsJudgeCnt(0);
						newRecord.setbJudgeCnt(0);
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						resultList.add(newRecord);
						break;
					}
				} else if(groupingColumn.equals(GroupingColumn.groupingBySBCount)) {
					if(totalCntList.get(i).getSbCnt().equals(filteringCntList.get(j).getSbCnt()) 
							&& totalCntList.get(i).getPitchSbZoneCode().equals(filteringCntList.get(j).getPitchSbZoneCode())) {
						newRecord.setsJudgeCnt(filteringCntList.get(j).getsJudgeCnt());
						newRecord.setbJudgeCnt(filteringCntList.get(j).getbJudgeCnt());
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						resultList.add(newRecord);
						break;
					} else {
						newRecord.setsJudgeCnt(0);
						newRecord.setbJudgeCnt(0);
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						resultList.add(newRecord);
						break;
					}
				} else if(groupingColumn.equals(GroupingColumn.groupingByBallCode)) {
					if(totalCntList.get(i).getBallCode() == filteringCntList.get(j).getBallCode() 
							&& totalCntList.get(i).getPitchSbZoneCode().equals(filteringCntList.get(j).getPitchSbZoneCode())) {
						newRecord.setsJudgeCnt(filteringCntList.get(j).getsJudgeCnt());
						newRecord.setbJudgeCnt(filteringCntList.get(j).getbJudgeCnt());
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						resultList.add(newRecord);
						break;
					} else {
						newRecord.setsJudgeCnt(0);
						newRecord.setbJudgeCnt(0);
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						resultList.add(newRecord);
						break;
					}
				} else if(groupingColumn.equals(GroupingColumn.groupingByBallSpeed)) {
					if(totalCntList.get(i).getBallSpeedType().equals(filteringCntList.get(j).getBallSpeedType()) 
							&& totalCntList.get(i).getPitchSbZoneCode().equals(filteringCntList.get(j).getPitchSbZoneCode())) {
						newRecord.setsJudgeCnt(filteringCntList.get(j).getsJudgeCnt());
						newRecord.setbJudgeCnt(filteringCntList.get(j).getbJudgeCnt());
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						System.out.println(filteringCntList.get(j).getsJudgeCnt());
						resultList.add(newRecord);
						break;
					} else {
						newRecord.setsJudgeCnt(0);
						newRecord.setbJudgeCnt(0);
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						resultList.add(newRecord);
						break;
					}
				} else if(groupingColumn.equals(GroupingColumn.groupingByInning)) {
					if(totalCntList.get(i).getInning().equals(filteringCntList.get(j).getInning()) 
							&& totalCntList.get(i).getPitchSbZoneCode().equals(filteringCntList.get(j).getPitchSbZoneCode())) {
						newRecord.setsJudgeCnt(filteringCntList.get(j).getsJudgeCnt());
						newRecord.setbJudgeCnt(filteringCntList.get(j).getbJudgeCnt());
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						resultList.add(newRecord);
						break;
					} else {
						newRecord.setsJudgeCnt(0);
						newRecord.setbJudgeCnt(0);
						newRecord.setPitchSbZoneCode(totalCntList.get(i).getPitchSbZoneCode());
						newRecord.setSbTotalCnt(totalCntList.get(i).getSbTotalCnt());
						resultList.add(newRecord);
						break;
					}
				}
			}
		}
		
		return resultList;
	}
	
}
