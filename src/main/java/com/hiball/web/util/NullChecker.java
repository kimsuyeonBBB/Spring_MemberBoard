package com.hiball.web.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hiball.api.domain.GameRecord;

public abstract class NullChecker {
	private static final Logger logger = LoggerFactory.getLogger(NullChecker.class);
	
	
	@SuppressWarnings("unused")
	public static boolean setNullListSetting(List<GameRecord> resultList) {
		if(resultList.size() == 0) {
			resultList.add(new GameRecord());
			
			logger.info(" ");
			logger.info("***********************  Result List Size 0 *********************** ");
			logger.info(" ");
			
			return true;
		} else if(resultList == null){
			resultList = new ArrayList<>();
			resultList.add(new GameRecord());
			
			logger.info(" ");
			logger.info("***********************  Result List Null *********************** ");
			logger.info(" ");
			
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("unused")
	public static List<GameRecord> setNullListSettingNReturn(List<GameRecord> resultList) {
		if(resultList.size() == 0) {
			resultList.add(new GameRecord());
			
			logger.info(" ");
			logger.info("***********************  Result List Size 0 *********************** ");
			logger.info(" ");
			
			return resultList;
		} else if(resultList == null){
			resultList = new ArrayList<>();
			resultList.add(new GameRecord());
			
			logger.info(" ");
			logger.info("***********************  Result List Null *********************** ");
			logger.info(" ");
			
			return resultList;
		}
		
		return resultList ;
	}
	
	
	public static boolean setNullGameRecordSetting(GameRecord resultRecord) {
		if(resultRecord == null) {
			resultRecord = new GameRecord();
			
			logger.info(" ");
			logger.info("*********************** Result Record Null *********************** ");
			logger.info(" ");
			
			return true;
		}
		
		return false;
	}
	
	public static GameRecord checkGameRecordNReturnRecord(GameRecord resultRecord) {
		
		if(resultRecord == null) {
			resultRecord = new GameRecord();
		}
		
		return resultRecord;
	}
	
	public static GameRecord checkListNReturnRecord(List<GameRecord> resultList) {
		GameRecord resultRecord = null;
		
		if(resultList.size() == 0 || resultList == null) {
			logger.info(" ");
			logger.info("*********************** Result Size 0 And Record Null *********************** ");
			logger.info(" ");
			resultRecord = new GameRecord();
		} else {
			resultRecord = resultList.get(0);
		}
		
		return resultRecord;
	}
	
	public static List<GameRecord> checkListNReturnList(List<GameRecord> resultList) {
		if(resultList != null) {
			if(resultList.size() == 0) {

				logger.info(" ");
				logger.info("***********************  Result List Size 0 *********************** ");
				logger.info(" ");
				
				resultList.add(new GameRecord());
			}
		} else if(resultList == null) {
			
			logger.info(" ");
			logger.info("***********************  Result List Null *********************** ");
			logger.info(" ");
			
			resultList = new ArrayList<>();
			resultList.add(new GameRecord());
		}
		
		return resultList;
	}
	
}
