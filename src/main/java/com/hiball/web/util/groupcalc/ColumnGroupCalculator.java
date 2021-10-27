package com.hiball.web.util.groupcalc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hiball.api.domain.GameRecord;

public class ColumnGroupCalculator {
	//Don't create instance
	private ColumnGroupCalculator(){}
	
	public static Map<String, Object> groupingCalcByColumn(List<GameRecord> gameRecordList, String[] columns, String[] calcColumns) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Map<String, Object> recordMap = new HashMap<>();
		
		for(GameRecord record : gameRecordList) {
			Class<?> reflectRecord = record.getClass();
			
			String key = "";		
			
			for(String column : columns) {
				key += "/" + settingValueAsString(reflectRecord.getMethod("get"+column).invoke(record));
			}
			
			key = key.substring(1, key.length());
			
			if(!recordMap.containsKey(key)) {
				GameRecord newRecord = new GameRecord();
				newRecord.setGameCnt(0);
				Class<?> newRecordReflection = newRecord.getClass();
				
				for(String calcColumn : calcColumns) {
					Method returnMethod = reflectRecord.getMethod("get" + calcColumn);
					
					Method calcMethod = newRecordReflection.getMethod("set" + calcColumn, returnMethod.getReturnType());
					calcMethod.invoke(newRecord, reflectRecord.getMethod("get"+calcColumn).invoke(record));
				}
				
				recordMap.put(key, newRecord);
				
			} else {
				GameRecord basedRecord = (GameRecord) recordMap.get(key);
				basedRecord.setGameCnt(basedRecord.getGameCnt() + 1);
				
				Class<?> basedRecordReflection = basedRecord.getClass();
				
				for(String calcColumn : calcColumns) {
					Method returnMethod = reflectRecord.getMethod("get" + calcColumn);
					
					Method calcMethod = basedRecordReflection.getMethod("set" + calcColumn, returnMethod.getReturnType());
					
					String returnMethodOfReturnType = returnMethod.getReturnType().toString();
					
					Object basedColumnValue = basedRecordReflection.getMethod("get" + calcColumn).invoke(basedRecord);
					Object newColumnValue = reflectRecord.getMethod("get" + calcColumn).invoke(record);
					
					if(returnMethodOfReturnType.equals("int") || returnMethodOfReturnType.equals("Integer")) {
						Integer basedColumnValueAsInteger = settingValueAsInteger(basedColumnValue);
						Integer newColumnValueAsInteger = settingValueAsInteger(newColumnValue);
						
						calcMethod.invoke(basedRecord, basedColumnValueAsInteger + newColumnValueAsInteger);
					} else if(returnMethodOfReturnType.equals("double") || returnMethodOfReturnType.equals("Double")) {
						Double basedColumnValueAsDouble = settingValueAsDouble(basedColumnValue);
						Double newColumnValueAsDouble = settingValueAsDouble(newColumnValue);
						
						if(calcColumn.equals("PitchingInning")) {
							calcMethod.invoke(basedRecord, calcInninig(basedColumnValueAsDouble, newColumnValueAsDouble, key));
						} else {
							calcMethod.invoke(basedRecord, basedColumnValueAsDouble + newColumnValueAsDouble);
						}
					}
				}
				
				recordMap.put(key, basedRecord);
			}
		}
		
		return recordMap;
	}
	
	private static Double calcInninig(Double basedInning, Double newInning, String key) {
		String basedInningStr = basedInning.toString();
		
		String basedInningValue = basedInningStr.substring(0, basedInningStr.lastIndexOf('.'));
		String basedOutValue = basedInningStr.substring(basedInningStr.lastIndexOf('.') + 1, basedInningStr.length());
		
		String newInningStr = newInning.toString();
		String newInningValue = newInningStr.substring(0, newInningStr.lastIndexOf('.'));
		String newInningOutValue = newInningStr.substring(newInningStr.lastIndexOf('.') + 1, newInningStr.length());
		
		Integer sumInning = Integer.valueOf(basedInningValue) + Integer.valueOf(newInningValue);
		Integer sumOutCount = Integer.valueOf(basedOutValue) + Integer.valueOf(newInningOutValue);
		
		
		if(sumOutCount < 3.0) {
			/*no working*/
		} else if(sumOutCount >= 3.0) {
			if(sumOutCount == 3) {
				sumInning += 1;
				sumOutCount -= 3;
			} else if(sumOutCount == 4) {
				sumInning += 1;
				sumOutCount -= 3;
			} else if(sumOutCount == 5) {
				sumInning += 1;
				sumOutCount -= 3;
			}
		}
		
		String resultValueStr = sumInning + "." + sumOutCount;
		Double resultValue = Double.valueOf(resultValueStr);
		
		return resultValue;
	}
	
	private static String settingValueAsString(Object value) {
		return String.valueOf(value);
	}
	
	private static Double settingValueAsDouble(Object value) {
		return Double.parseDouble(String.valueOf(value));
	}
	
	private static Integer settingValueAsInteger(Object value) {
		return Integer.parseInt(String.valueOf(value));
	}
}
