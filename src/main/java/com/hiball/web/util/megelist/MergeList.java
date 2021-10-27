package com.hiball.web.util.megelist;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.convertor.ValueConvertor;

public class MergeList {
	public static List<List<GameRecord>> mergeList(List<GameRecord> playerList, List<GameRecord> wholeList, String groupingColumnName) {
		List<GameRecord> newPlayerList = new ArrayList<>();
		GameRecord emptyRecord = new GameRecord();
		for(int i=0; i<wholeList.size(); i++) {
			GameRecord wholeRecord = wholeList.get(i);
			int sizeCount = 0;
			if(playerList.size() == 0) {
				newPlayerList.add(emptyRecord);
			}else {
				for(int j=0; j<playerList.size(); j++) {
					GameRecord playerRecord = playerList.get(j);
					if(groupingColumnName.equals("groupingByPitchingType")) {
						if(wholeRecord.getPitcherPositionTypeCode() == playerRecord.getPitcherPositionTypeCode() 
								&& wholeRecord.getPitchingForm() == playerRecord.getPitchingForm()
								&& wholeRecord.getPitchingHand().equals(playerRecord.getPitchingHand())) {
							newPlayerList.add(playerRecord);
							break;
						} else {
							sizeCount++;
							if(sizeCount >= playerList.size()){
								newPlayerList.add(emptyRecord);
								break;
							}	
						}
					} else if(groupingColumnName.equals("groupingBySBCount")) {
						if(wholeRecord.getSbCnt().equals(playerRecord.getSbCnt())) {
							newPlayerList.add(playerRecord);
							break;
						} else {
							sizeCount++;
							if(sizeCount >= playerList.size()){
								newPlayerList.add(emptyRecord);
								break;
							}	
						}
					} else if(groupingColumnName.equals("groupingByRunnerState")) {
						if(wholeRecord.getBeforeRunnerState() == playerRecord.getBeforeRunnerState()) {
							newPlayerList.add(playerRecord);
							break;
						} else {
							sizeCount++;
							if(sizeCount >= playerList.size()){
								newPlayerList.add(emptyRecord);
								break;
							}						
						}
					} else if(groupingColumnName.equals("groupingByBallCode")) {
						if(wholeRecord.getBallCode() == playerRecord.getBallCode()) {
							newPlayerList.add(playerRecord);
							break;
						} else {
							sizeCount++;
							if(sizeCount >= playerList.size()){
								newPlayerList.add(emptyRecord);
								break;
							}	
						}
					} else if(groupingColumnName.equals("groupingByBallSpeed")) {
						if(wholeRecord.getBallSpeedType().equals(playerRecord.getBallSpeedType())) {
							newPlayerList.add(playerRecord);
							break;
						} else {
							sizeCount++;
							if(sizeCount >= playerList.size()){
								newPlayerList.add(emptyRecord);
								break;
							}	
						}
					} else if(groupingColumnName.equals("groupingByBattingHand")) {
						if(wholeRecord.getBattingHand().equals(playerRecord.getBattingHand())) {
							newPlayerList.add(playerRecord);
							break;
						} else {
							sizeCount++;
							if(sizeCount >= playerList.size()){
								newPlayerList.add(emptyRecord);
								break;
							}	
						}
					} else if(groupingColumnName.equals("")) {
						if(playerRecord == null) {
							newPlayerList.add(emptyRecord);
						} else {
							newPlayerList.add(playerRecord);
						}
					} else if(groupingColumnName.equals("groupingByBattingType")) {
						if(wholeRecord.getBattingHand() == null || playerRecord.getBattingHand() == null) {
							if(wholeRecord.getBatterRosterTypeCode() == playerRecord.getBatterRosterTypeCode()) {
								newPlayerList.add(playerRecord);
								break;
							} else {
								sizeCount++;
								if(sizeCount >= playerList.size()){
									newPlayerList.add(emptyRecord);
									break;
								}	
							}
						}
						else if(wholeRecord.getBatterRosterTypeCode() == playerRecord.getBatterRosterTypeCode() 
														&& wholeRecord.getBattingHand().equals(playerRecord.getBattingHand())){
								newPlayerList.add(playerRecord);
								break;
						} else {
							sizeCount++;
							if(sizeCount >= playerList.size()){
								newPlayerList.add(emptyRecord);
								break;
							}	
						}
					} else if(groupingColumnName.equals("groupingByInning")) {
						if(wholeRecord.getInning().equals(playerRecord.getInning())) {
							newPlayerList.add(playerRecord);
							break;
						} else {
							sizeCount++;
							if(sizeCount >= playerList.size()) {
								newPlayerList.add(emptyRecord);
								break;
							}
						}
					}
				}
			}
		}
		 
		List<List<GameRecord>> resultList = new ArrayList<>();
		
		resultList.add(newPlayerList);
		resultList.add(wholeList);
		
		return resultList;
	}
	
	public static List<GameRecord> battingRecordAndRunRecordMergeList(List<GameRecord> basedList, List<GameRecord> targetList) {
		
		for(GameRecord record : basedList) {
			for(GameRecord innerRecord : targetList) {
				if(record.getBatterMembersId() == innerRecord.getBatterMembersId()) {
					record.setSuccessSteal(innerRecord.getSuccessSteal());
					record.setAddStealBase(innerRecord.getAddStealBase());
					record.setAddStealBaseChance(innerRecord.getAddStealBaseChance());
					System.out.println(innerRecord.getSuccessSteal());
					break;
				}
			}
		}
		
		return basedList;
	}

	//비교 컬럼이 2개인 기록을 merge하는 메소드
	public static List<GameRecord> hittingListAndCountListMerge(List<GameRecord> hittingRecordList
			, List<GameRecord> countRecordList, String gettingColumnName1, String gettingColumnName2) {
		String gettingMethodName1 = "get" + gettingColumnName1;
		String gettingMethodName2 = "get" + gettingColumnName2;
		
		for(GameRecord hittingRecord : hittingRecordList) {
			try{
				Class<?> hittingRecordClazz = hittingRecord.getClass();
				Method hittingRecordMethod1 = hittingRecordClazz.getMethod(gettingMethodName1);
				Object hittingRecordObj1 = hittingRecordMethod1.invoke(hittingRecord);
				String gettingColumn1ReturnValue = hittingRecordMethod1.getReturnType().toString();
				
				Method hittingRecordMethod2 = hittingRecordClazz.getMethod(gettingMethodName2);
				Object hittingRecordObj2 = hittingRecordMethod2.invoke(hittingRecord);
				String gettingColumn2ReturnValue = hittingRecordMethod2.getReturnType().toString();
				
				for(GameRecord countRecord : countRecordList) {
					Class<?> countRecordClazz = countRecord.getClass();
					Method countRecordMethod1 = countRecordClazz.getMethod(gettingMethodName1);
					Object countRecordObj1 = countRecordMethod1.invoke(countRecord);
					
					Method countRecordMethod2 = countRecordClazz.getMethod(gettingMethodName2);
					Object countRecordObj2 = countRecordMethod2.invoke(countRecord);
					
					if(valueEqualCheck(hittingRecordObj1, countRecordObj1, gettingColumn1ReturnValue)) {
						if(valueEqualCheck(hittingRecordObj2, countRecordObj2, gettingColumn2ReturnValue)) {
							hittingRecord.setSituationCnt(countRecord.getbSituationCnt());
							hittingRecord.setGameCnt(countRecord.getGameCnt());
							hittingRecord.setBallPitchingCnt(countRecord.getBallPitchingCnt());
							hittingRecord.setStrikePitchingCnt(countRecord.getStrikePitchingCnt());
							hittingRecord.setTotalSbPitchingCnt(countRecord.getTotalSbPitchingCnt());
							
							break;
						}
					}
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return hittingRecordList;
	}
	
	public static List<GameRecord> hittingListAndCountListMerge(List<GameRecord> hittingRecordList
			, List<GameRecord> countRecordList, String gettingColumnName) {
		String gettingMethodName = "get" + gettingColumnName;
		
		for(GameRecord hittingRecord : hittingRecordList) {
			try {
				Class<?> hittingRecordClazz = hittingRecord.getClass();
				Method hittingRecordMethod = hittingRecordClazz.getMethod(gettingMethodName);
				Object hittingRecordObj = hittingRecordMethod.invoke(hittingRecord);
				String gettingColumnReturnValue = hittingRecordMethod.getReturnType().toString();
				
				for(GameRecord countRecord : countRecordList) {
					Class<?> countRecordClazz = countRecord.getClass();
					Method countRecordMethod = countRecordClazz.getMethod(gettingMethodName);
					Object countRecordObj = countRecordMethod.invoke(countRecord);
					
					if(valueEqualCheck(hittingRecordObj, countRecordObj, gettingColumnReturnValue)) {
						hittingRecord.setDistAccmPitchingCntOrd(countRecord.getDistAccmPitchingCntOrd());
						hittingRecord.setSituationCnt(countRecord.getSituationCnt());
						hittingRecord.setGameCnt(countRecord.getGameCnt());
						hittingRecord.setBallPitchingCnt(countRecord.getBallPitchingCnt());
						hittingRecord.setStrikePitchingCnt(countRecord.getStrikePitchingCnt());
						hittingRecord.setTotalSbPitchingCnt(countRecord.getTotalSbPitchingCnt());
						
						break;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return hittingRecordList;
	}
	
	private static boolean valueEqualCheck(Object obj1, Object obj2, String commonReturnValue) {
		String realReturnName = commonReturnValue.substring(commonReturnValue.lastIndexOf('.') + 1, commonReturnValue.length());
		
		if(realReturnName.equals("int") || realReturnName.equals("Integer")) {
			int obj1Value = ValueConvertor.settingValueAsInteger(obj1);
			int obj2Value = ValueConvertor.settingValueAsInteger(obj2);
			if(obj1Value == obj2Value) {
				return true;
			}
			
		} else if(realReturnName.equals("double") || realReturnName.equals("Double")) {
			double obj1Value = ValueConvertor.settingValueAsDouble(obj1);
			double obj2Value = ValueConvertor.settingValueAsDouble(obj2);
			
			if(obj1Value == obj2Value) {
				return true;
			}
		}
		else if(realReturnName.equals("String")) {
			String obj1Value = ValueConvertor.settingValueAsString(obj1);
			String obj2Value = ValueConvertor.settingValueAsString(obj2);
			
			if(obj1Value.equals(obj2Value)) {
				return true;
			}
		}
		
		return false;
	}
	
}
