package com.hiball.web.util.accum;

import java.util.List;

import com.hiball.api.domain.GameRecord;
/**
 * 전체 테이블 누적에는 사용하지 않는다.
 * @author CWH
 *
 */
public class AccumlateUtil {
	public static final String ballTypePitcingCntFactor = "ballTypePitchingCnt";
	
	public static List<GameRecord> selectFactorAccumulate(String accumFactor, List<GameRecord> beforeAccumList) {
		int accumulateValue = 0;
		
		for(GameRecord gameRecord : beforeAccumList) {
			if(accumFactor.equals(ballTypePitcingCntFactor)){
				accumulateValue += gameRecord.getBallTypePitchingCnt();
			}
		}

		for(GameRecord gameRecord : beforeAccumList) {
			if(accumFactor.equals(ballTypePitcingCntFactor)) {
				gameRecord.setTotalBallTypePitchingCnt(accumulateValue);
			}
		}
		
		return beforeAccumList;
	}
}
