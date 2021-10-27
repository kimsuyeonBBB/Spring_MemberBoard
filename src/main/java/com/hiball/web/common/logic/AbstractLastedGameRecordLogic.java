package com.hiball.web.common.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.excpeiton.HiBallException;
import com.hiball.web.common.param.CommonParam;
import com.hiball.web.common.service.AbstractServiceLogic;

/**
 * Batter, Pitcher, TeamBatting, TeamPitching에 관련된 최근 N경기에 대한 
 * 기록을 조회하는 공통로직 구현 부분~
 * 해당 클래스를 각 서비스에서 확장하여 사용
 * @author birdhead
 *
 * @param <P>
 */
public abstract class AbstractLastedGameRecordLogic<P extends CommonParam> extends AbstractServiceLogic<P> {
	
	@Override
	public final Object execute(P param) throws HiBallException {
		List<Map<ColumnsEnum, Object>> resultList = new ArrayList<>();
		int lastedGameCnt = param.getLastedGameCnt();
		
		int nGameRecordSize = 0;
		int yearDes = 0;
		
		boolean loopCheck = true;
		while(loopCheck) {
			lastedGameCnt -= nGameRecordSize;
			param.setLastedGameCnt(lastedGameCnt);
			param.setYear(param.getYear() - yearDes++);
			List<Map<ColumnsEnum, Object>> nGameRecords = Lists.reverse(retrieveLastedGameRecordList(param));
			nGameRecordSize = nGameRecords.size();
			
			resultList.addAll(0, nGameRecords);
			
			if(lastedGameCnt == nGameRecordSize || yearDes == 2
					|| param.getYear() == 2014) loopCheck = false;
			else {
				String gameDay = param.getGameDay();
				if(gameDay != null) {
					gameDay = param.getYear() - yearDes + "-12-31";
					param.setGameDay(gameDay);
				}
			}
		}
		return resultList;
	}
	
	abstract protected List<Map<ColumnsEnum, Object>> retrieveLastedGameRecordList(P param);
}
