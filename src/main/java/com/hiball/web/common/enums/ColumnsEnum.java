package com.hiball.web.common.enums;

public enum ColumnsEnum {
	keyValue, dataRetrieveYn,
	/**
	 * playerInfo
	 */
	height, weight, backnumber, birthDate,
	/**
	 * Game Data Elements
	 */
	gameInfoId, gameRecordId, gameDay, gameStime, year, month,  weekDay, weekUnit, startWeekUnit, endWeekUnit, statumsId, stadiumName, gameCnt,
	teamId, teamName, opponentTeamId, opponentTeamName, membersId, membersName, batterMembersId, pitcherMembersId, batterName, pitcherName, name,
	ballSpeed, ballCode, pitchZoneX, pitchZoneY, hitCourseX, hitCourseY, win, lose, draw, save, hold, qs, qsPlus, shutOut,
	completedGame, situationCnt, tpa, playInning, ab, hit, b1b, b2b, b3b, hr, bb, ibb, hbp, rbi, r, so, gdp, fo, go, er,
	err, teamRanking, sf, sg, sb, sh, successSteal, failureSteal, stolenBaseSucRate,
	battingOrder, batterPositionName, exPitchZoneCode,
	coursePCnt, courseCcnt, course1BCnt, course2BCnt, course3BCnt, courseSSCnt, courseLFCnt, courseCFCnt, courseRFCnt,
	coursePRate, courseCRate, course1BRate, course2BRate, course3BRate, courseSSRate, courseLFRate, courseCFRate, courseRFRate,
	inning, beforeOutCount, beforeRunnerState, hitResultCode, battingOrderLocation, runnerExistYn, pitcherType,
	strikeYn, hitTypeCode,
	batterTeamId, pitcherTeamId,
	positionTypeCode, positionName, positionTypeName, rosterTypeCode, rosterTypeName,
	on1bCnt, on2bCnt, on3bCnt, successedStolen2Base, failedStolen2Base, successedStolen3Base, failedStolen3Base, 
	from1bTo2b, from1bTo3b, from1bTohm, from1bToOut,
	from2bTo3b, from2bTohm, from2bToOut,
	from3bTohm, from3bToOut,
	zPitchingCnt, oPitchingCnt, zSwingCnt, oSwingCnt, zContactCnt, oContactCnt, pitchingCnt, swingCnt,
	strikePitchingCnt, contactCnt,
	wildPitchCnt, gameEnterYn, ip,
	analysisValue, analysisFactor,
	aoCnt, poCnt, gdpChanceCnt, gdpSuccRate,
	fieldingResultPositionCode, fieldingResultPositionName,
	addedRunChanceCnt, addedRunCnt, defenseInning, startPitcherCnt,
	/**
	 * league total
	 */
	leagueTotalTpa, leagueTotalR, leagueAvgObp, leagueWsb,
	
	/**
	 * Stats Elements
	 */
	avg, slg, obp, ops, era, whip, foGoRate, bbSoRate, scoringPositionAvg, winningRate, fip,
	sSituationCnt, bSituationCnt,
	sSwingCnt, bSwingCnt, sContactCnt, bContactCnt,
	sSwingRate, bSwingRate, sContactRate, bContactRate,
	firstBallSRate, firstBallBRate, decisionStrikeRate, decisionBallRate, playerWpa,
	stolen2BaseSucRate, stolen3BaseSucRate,
	from1bTo2bRate, from1bTo3bRate, from1bToHrRate, from1bToOutRate, 
	from2bTo3bRate, from2bToHrRate, from2bToOutRate, 
	from3bToHrRate, from3bToOutRate,
	oSwingRate, zSwingRate, swingRate2, oContactRate, zContactRate, contactRate2, zoneRate,
	bbRate, soRate, k9, bb9, hr9, addedRunAllowRate, errRate,
	
	/**
	 * Sabermetrics Stats
	 */
	woba, wrc, wraa, rplus, rminus, leagueAvgWoba, wobaScale, wsb, wrcPlus, lgwSb, babip, isoP, spd, lob,
	/**
	 * GroupingColumn
	 */
	distAccmPitchingCnt, distAccmPitchingCntOrd,
	distBallCode, distBallSpeed, beforeDecidedBallCount, batterRosterTypeCode, battingHand,
	stadium, runnerState, distPitcherScoreGap, batterTeamName, outCount, distBatterScoreGap,
	pitcherTeamName, pitcherRosterTypeCode, pitchingHand, pitchingForm,
	groupingByPichingBallCnt
	/**
	 * 
	 */
}
