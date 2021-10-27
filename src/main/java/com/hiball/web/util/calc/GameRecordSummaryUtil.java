package com.hiball.web.util.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hiball.api.domain.GameRecord;

public abstract class GameRecordSummaryUtil {
	public static List<GameRecord> calcPitcherWPA(List<GameRecord> pitcherWPAList) {
		List<GameRecord> resultList = new ArrayList<>();
		int listSize = pitcherWPAList.size();
		int prePitcherMembersId = 0;
		int prePitcherTeamId = 0;
		String prePitcherName = "";
		String prePitcherTeamName = "";
		BigDecimal firstBeforeWe = null;
		BigDecimal lastAfterWe = null;
		
		int outCount = 0;
		int hit = 0;
		int hr = 0;
		int bb = 0;
		int so = 0;
		int er = 0;
		
		
		for(int i=0; i<listSize; i++) {
			GameRecord pitcherRecord = pitcherWPAList.get(i);
			//초기 값 할당 if
			if(prePitcherMembersId == 0) {
				prePitcherTeamId = pitcherRecord.getPitcherTeamId();
				prePitcherTeamName = pitcherRecord.getPitcherTeamName();
				prePitcherMembersId = pitcherRecord.getPitcherMembersId();
				prePitcherName = pitcherRecord.getPitcherName();
				
				outCount = (pitcherRecord.getOutCount() - pitcherRecord.getBeforeOutCount());
				hit = pitcherRecord.getB1b() 
						+ pitcherRecord.getB2b()
						+ pitcherRecord.getB3b()
						+ pitcherRecord.getHr();
				hr = pitcherRecord.getHr();
				bb = pitcherRecord.getBb();
				so = pitcherRecord.getSo();
				er = pitcherRecord.getEr();
				
				firstBeforeWe = pitcherRecord.getBeforeWe();
				
				continue;				
			}
			int curPitcherMembersId = pitcherRecord.getPitcherMembersId();
			
			if(prePitcherMembersId != curPitcherMembersId) {
				String curPitcherName = pitcherRecord.getPitcherName();
				int curPitcherTeamId = pitcherRecord.getPitcherTeamId();
				String curPitcherTeamName = pitcherRecord.getPitcherTeamName();
				
				setPitcherWPAList(resultList
									, prePitcherTeamId, prePitcherTeamName
									, prePitcherMembersId, prePitcherName
									, outCount, hit, hr, bb, so, er
									, firstBeforeWe, lastAfterWe);
				
				prePitcherMembersId = curPitcherMembersId;
				prePitcherName = curPitcherName;
				prePitcherTeamId = curPitcherTeamId;
				prePitcherTeamName = curPitcherTeamName;
				firstBeforeWe = pitcherRecord.getBeforeWe();
				
				outCount = 0;
				hit = 0;
				bb = 0;
				hr = 0;
				so = 0;
				er = 0;
				
				continue;
			}
			
			if(listSize == i + 1) {
				lastAfterWe = pitcherRecord.getAfterWe();
				String curPitcherName = pitcherRecord.getPitcherName();
				
				setPitcherWPAList(resultList
						, prePitcherTeamId, prePitcherTeamName
						, prePitcherMembersId, prePitcherName
						, outCount, hit, hr, bb, so, er
						, firstBeforeWe, lastAfterWe);
				
				prePitcherMembersId = curPitcherMembersId;
				prePitcherName = curPitcherName;
				firstBeforeWe = pitcherRecord.getBeforeWe();
				outCount = 0;
				hit = 0;
				bb = 0;
				hr = 0;
				so = 0;
				er = 0;
			}
			
			
			lastAfterWe = pitcherRecord.getAfterWe();
			outCount += (pitcherRecord.getOutCount() - pitcherRecord.getBeforeOutCount());
			hit += pitcherRecord.getB1b()
					+ pitcherRecord.getB2b()
					+ pitcherRecord.getB3b()
					+ pitcherRecord.getHr();
			hr += pitcherRecord.getHr();
			bb += pitcherRecord.getBb();
			so += pitcherRecord.getSo();
			er += pitcherRecord.getEr();
		}
		
		return resultList;
	}
	
	private static void setPitcherWPAList(List<GameRecord> resultList
									, int pitcherTeamId, String pitcherTeamName
									, int pitcherMembersId, String pitcherName
									, int outCount, int hit, int hr, int bb, int so, int er
									, BigDecimal beforeWe, BigDecimal afterWe) {
		GameRecord resultRecord = new GameRecord();
		
		if(beforeWe == null) beforeWe = new BigDecimal(0);
		if(afterWe == null)  afterWe = new BigDecimal(0);
		
		resultRecord.setTeamId(pitcherTeamId);
		resultRecord.setTeamName(pitcherTeamName);
		resultRecord.setPitcherMembersId(pitcherMembersId);
		resultRecord.setPitcherName(pitcherName);
		resultRecord.setOutCount(outCount);
		resultRecord.setHit(hit);
		resultRecord.setHr(hr);
		resultRecord.setBb(bb);
		resultRecord.setK(so);
		resultRecord.setEr(er);
		resultRecord.setBeforeWe(beforeWe);
		resultRecord.setAfterWe(afterWe);
		
		resultRecord.setPlayerWpa(afterWe.subtract(beforeWe).multiply(new BigDecimal(-1)));
		
		resultList.add(resultRecord);
	}
	
	public static List<GameRecord> calcPositionChangeRecord(List<GameRecord> batterSummaryRecordList) {
		int initCount = 0;
		GameRecord preRecord = batterSummaryRecordList.get(0);
		String resultStr = "";
		int innerForCount = 0;
		
		String lastedPositionChangeName = "";
		
		for(Iterator<GameRecord> iterator = batterSummaryRecordList.iterator();	iterator.hasNext(); ){
			GameRecord curRecord = iterator.next();
			
			if(initCount == 0) {
				initCount++;
				continue;
			}
			
			if(curRecord.getBatterMembersId() == preRecord.getBatterMembersId()) {
				if(curRecord.getRosterTypeCode() == 5101) {
					if(innerForCount++ < 1) {
						resultStr += preRecord.getBatterPositionName() + "/" + curRecord.getPositionChange();
						lastedPositionChangeName = curRecord.getPositionChange();
					}
				}
				
				iterator.remove();
				continue;
			} else {
				if(!resultStr.isEmpty()) {
					String positionChangeName = "";
					String[] resultStrArray = resultStr.split("/");
					for(String str : resultStrArray) {
						positionChangeName += str.substring(0, 1);
					}
					
					preRecord.setBatterPositionName(positionChangeName);
					resultStr = "";
					innerForCount = 0;
				}
				
				if(preRecord.getPositionChange() == null) {
					lastedPositionChangeName = preRecord.getBatterPositionName();
				}
			}
			
			if(curRecord.getBatterPositionName().equals("교")) {
				if(preRecord.getBatterPositionName().split("/").length >= 2) {
					curRecord.setBatterPositionName(preRecord.getBatterPositionName());
				} else {
					curRecord.setBatterPositionName(lastedPositionChangeName + "/" + curRecord.getBatterPositionName());
					lastedPositionChangeName = "";
				}
			}
			
			preRecord = curRecord;
		}
		
		
		return batterSummaryRecordList;
	}
}
