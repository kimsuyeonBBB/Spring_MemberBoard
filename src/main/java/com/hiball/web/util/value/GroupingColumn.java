package com.hiball.web.util.value;

import java.util.HashMap;
import java.util.Map;

public class GroupingColumn {
	public static final String groupingByRunnerState = "groupingByRunnerState";
	public static final String groupingBySBCount = "groupingBySBCount";
	public static final String groupingByPitchingType = "groupingByPitchingType";
	public static final String groupingByBallCode = "groupingByBallCode";
	public static final String groupingByBallSpeed = "groupingByBallSpeed";
	public static final String groupingByInning = "groupingByInning";
	public static final String groupingByPichingBallCnt = "groupingByPichingBallCnt";
	public static final String groupingByBatterOppTeam = "groupingByBatterOppTeam";
	public static final String groupingByOppPitcherTeam = "groupingByOppPitcherTeam";
	public static final String groupingByPitchingHand = "groupingByPitchingHand";
	public static final String groupingByPitchingForm = "groupingByPitchingForm";
	public static final String groupingByStealCategory = "groupingByStealCategory";
	public static final String groupingByTeamPitcher = "groupingByTeamPitcher";
	public static final String groupingBySbCnt = "groupingBySbCnt";
	public static final String groupingByBatterScoreGap = "groupingByBatterScoreGap";
	public static final String groupingByPitcherScoreGap = "groupingByPitcherScoreGap";
	public static final String groupingByOutCount = "groupingByOutCount";
	public static final String groupingByTpa = "groupingByTpa";
	public static final String groupingByOppBatterTeam = "groupingByOppBatterTeam";
	public static final String groupingByStadium = "groupingByStadium";
	public static final String groupingByWeekDay = "groupingByWeekDay";
	public static final String groupingByBattingType = "groupingByBattingType";
	public static final String groupingByBattingOrderLocation = "groupingByBattingOrderLocation";
	public static final String groupingByRunnerExistYn = "groupingByRunnerExistYn";
	public static final String groupingByPitcherType = "groupingByPitcherType";
	
	public static Map<String, String> groupingColumnMatchingMap = new HashMap<>();
	
	//ord column이 있는 것은 두번째로 
	static {
		groupingColumnMatchingMap.put(groupingByBallCode, "DistBallCode");
		groupingColumnMatchingMap.put(groupingByBallSpeed, "DistBallSpeed");
//		groupingColumnMatchingMap.put(groupingByPichingBallCnt, "DistAccmPitchingCnt/DistAccmPitchingCntOrd");
		groupingColumnMatchingMap.put(groupingByPichingBallCnt, "DistAccmPitchingCnt");
		groupingColumnMatchingMap.put(groupingBySBCount, "BeforeDecidedBallCount");
		groupingColumnMatchingMap.put(groupingByBattingType, "BatterRosterTypeCode/BattingHand");
		groupingColumnMatchingMap.put(groupingByStadium, "Stadium");
		groupingColumnMatchingMap.put(groupingByRunnerState, "RunnerState");
		groupingColumnMatchingMap.put(groupingByPitcherScoreGap, "DistPitcherScoreGap");
		groupingColumnMatchingMap.put(groupingByOppBatterTeam, "BatterTeamName");
		groupingColumnMatchingMap.put(groupingByOutCount, "beforeOutCount");
		groupingColumnMatchingMap.put(groupingByWeekDay, "WeekDay");
		groupingColumnMatchingMap.put(groupingByBatterScoreGap, "DistBatterScoreGap");
		groupingColumnMatchingMap.put(groupingByPitcherScoreGap, "DistPitcherScoreGap");
		groupingColumnMatchingMap.put(groupingByOppPitcherTeam, "PitcherTeamName");
		groupingColumnMatchingMap.put(groupingByOppBatterTeam, "BatterTeamName");
		groupingColumnMatchingMap.put(groupingByPitchingHand, "PitcherRosterTypeCode/PitchingHand");
		groupingColumnMatchingMap.put(groupingByPitchingForm, "PitcherRosterTypeCode/PitchingForm");
		groupingColumnMatchingMap.put(groupingByPitchingType, "PitcherRosterTypeCode/PitchingHand/PitchingForm");
		groupingColumnMatchingMap.put(groupingByBattingOrderLocation, "battingOrderLocation");
		groupingColumnMatchingMap.put(groupingByRunnerExistYn, "RunnerExistYn");	
		groupingColumnMatchingMap.put(groupingByPitcherType, "PitcherType");
	}
	
	public static boolean countInfoGroupingColumn(String groupingColumn) {
		if(groupingColumn.equals(groupingByBallCode) 
				|| groupingColumn.equals(groupingByBallSpeed)
				|| groupingColumn.equals(groupingByPichingBallCnt)
				|| groupingColumn.equals(groupingBySBCount)
				|| groupingColumn.equals(groupingByBattingType)
				|| groupingColumn.equals(groupingByStadium)
				|| groupingColumn.equals(groupingByRunnerState)
				|| groupingColumn.equals(groupingByPitcherScoreGap)
				|| groupingColumn.equals(groupingByBatterScoreGap)
				|| groupingColumn.equals(groupingByOppBatterTeam)
				|| groupingColumn.equals(groupingByOppPitcherTeam)
				|| groupingColumn.equals(groupingByOutCount)
				|| groupingColumn.equals(groupingByWeekDay)
				|| groupingColumn.equals(groupingByPitchingType)) return true;
		
		else return false;
	}
	
	public static final String[] groupingByPitchingTypeArray = {groupingByPitchingHand, groupingByPitchingForm};
}
