package com.hiball.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <1> stadium_name이 summary 변경 되면서 stadium으로 변경되어서 지우려 했지만 기존 service에서 getter/setter를 사용하고 있어서 나둬두고 
 * 		statdium을 추가, service2 작업이 왼료되면 제거
 * @author CWH
 *
 */
public class GameRecord extends GameStatistics implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gameInning;
	private int inningTb; 		
	private int batterPositionTypeCode; 	
	private String batterPositionName; 		
	private String inn1 = "-"; 					
	private String inn2 = "-"; 					
	private String inn3 = "-"; 					
	private String inn4 = "-"; 					
	private String inn5 = "-"; 					
	private String inn6 = "-"; 					
	private String inn7 = "-"; 					
	private String inn8 = "-"; 					
	private String inn9 = "-"; 					
	private String inn10 = "-"; 					
	private String inn11 = "-"; 					
	private String inn12 = "-"; 					
	private int battingOrder; 			
	private int batInningTurn; 			
	private int batPitchSeq; 			
	private int hitTypeCode; 			
	private int hitResultCode; 			
	private String hitResultName; 			
	private int hitCourseCode; 			
	private int hitCoursePositionCode;	
	private int ballCode; 				
	private String ballCodeName; 			
	private int ballSpeed; 
	private String ballSpeedType;
	private int ballCount;
	private int strikeCount;
	private String strikeYn; 		
	private String strikeZoneMatchYn;
	private int pitcherRosterTypeCode;	
	private int batterRosterTypeCode;
	private int pitcherPositionTypeCode;
	private int opponentBatterCnt; 		
	private String pitcherResultName;
	private int pitchZoneCode;
	private String exPitchZoneCode;
	private int pitchZoneX;  
	private int pitchZoneY; 
	private int hitcoursePositionCode; 	
	private int hitCourseX; 	
	private int hitCourseY; 	
	private String hrDistance; 			
	private int runnerState; 			
	private int beforeRunnerState;
	private int battedBallTypeCode; 	
	private String batterTeamName; 		
	private String pitcherTeamName;
	private String catcherTeamName;
	private String teamName;
	private int positionTypeCode;	
	private String positionName;		
	private int rosterTypeCode;	
	private String rosterTypeName;	
	private String pitchingHand = ""; 			
	private String battingHand;	 			
	private int pitchingForm;
	private String pitchingFormName;
	private List<GameRecord> records;
	private String referee; 			
	private String refereeName;		
	private String refereePosition;	
	private int winCnt;
	private int loseCnt;
	private int drawCnt;
	private int saveCnt;
	private int holdCnt;
	private int fastball;
	private int curve;
	private int slide;
	private int etcBall;
	private BigDecimal gameBehind = new BigDecimal(0);
	private String pitchingBallCnt;
	private int before_out_state;
	private String abCategory;
	private String startDate;
	private String endDate;
	private String pitchSbZoneCode;
	private int pitchSbZoneRanking;
	private int pkCnt;
	private int runnerDieCnt;
	private String sbCnt;
	private String battingOrderLocation;
	private int battingTurn;
	private String batInningTurnName;
	private int stadiumsId;
	private String stadiumName;
	private String stadium;
	private int weekDay;
	private int pitchingPositionTypeCode;
	private String battingPositionTypeName;
	private String inning;
	private String scoreGapCategory;
	private int beforeScoreGap;
	private int month;
	private int maxBallSpeed;
	private int minBallSpeed;
	private BigDecimal avgBallSpeed;
	private int beforeOutCount;
	private String restDayGap;
	private int remainderBase;
	private String scoreCode;
	private int streakGameCnt;
	private String streakGameCategory;
	private String accumPitchingBallCnt;
	private BigDecimal maxWpa;
	private BigDecimal minWpa;
	private BigDecimal plusWpaSum;
	private BigDecimal minusWpaSum;	
	private String gameStime;
	private int situationCnt;
	private String analysisFactor;
	private int analysisValue;
	private String winOrLose;
	private String direction;
	private String positionTypeName;
	private int accumtPitchingBallCnt;
	private double winOrLoseRate;
	private String battingOrderCategory;
	private String inningCategory;
	private GameRecord relationRecord;
	private String successStealCategory;
	private int pitcherCnt;
	private String beforeDecidedBallCount;
	private String distBallSpeed;
	private int distBallCode;
	private String distAccmPitchingCnt;
	private int distAccmPitchingCntOrd;
	private String distPaSeq;
	private String distBatterScoreGap;
	private String distPitcherScoreGap;
	private String winOrLoseStreak;
	private int streakCheck;
	private String lasted10GamesResult;
	private int teamRanking;
	private int awayR;
	private int homeR;
	private int winDrawLose = -100;
	private String realData = "Y";
	private String spectatorCnt;
	private String positionChange;
	private String runnerExistYn;
	private String pitcherType;
	private int backnumber;
	private String gameEnterYn;
	private int fieldingResultPositionCode;
	private String fieldingResultPositionName;
	
	public int getInningTb() {
		return inningTb;
	}
	public void setInningTb(int inningTb) {
		this.inningTb = inningTb;
	}
	public int getBatterPositionTypeCode() {
		return batterPositionTypeCode;
	}
	public void setBatterPositionTypeCode(int batterPositionTypeCode) {
		this.batterPositionTypeCode = batterPositionTypeCode;
	}
	public String getBatterPositionName() {
		return batterPositionName;
	}
	public void setBatterPositionName(String batterPositionName) {
		this.batterPositionName = batterPositionName;
	}
	public String getInn1() {
		return inn1;
	}
	public void setInn1(String inn1) {
		this.inn1 = inn1;
	}
	public String getInn2() {
		return inn2;
	}
	public void setInn2(String inn2) {
		this.inn2 = inn2;
	}
	public String getInn3() {
		return inn3;
	}
	public void setInn3(String inn3) {
		this.inn3 = inn3;
	}
	public String getInn4() {
		return inn4;
	}
	public void setInn4(String inn4) {
		this.inn4 = inn4;
	}
	public String getInn5() {
		return inn5;
	}
	public void setInn5(String inn5) {
		this.inn5 = inn5;
	}
	public String getInn6() {
		return inn6;
	}
	public void setInn6(String inn6) {
		this.inn6 = inn6;
	}
	public String getInn7() {
		return inn7;
	}
	public void setInn7(String inn7) {
		this.inn7 = inn7;
	}
	public String getInn8() {
		return inn8;
	}
	public void setInn8(String inn8) {
		this.inn8 = inn8;
	}
	public String getInn9() {
		return inn9;
	}
	public void setInn9(String inn9) {
		this.inn9 = inn9;
	}
	public String getInn10() {
		return inn10;
	}
	public void setInn10(String inn10) {
		this.inn10 = inn10;
	}
	public String getInn11() {
		return inn11;
	}
	public void setInn11(String inn11) {
		this.inn11 = inn11;
	}
	public String getInn12() {
		return inn12;
	}
	public void setInn12(String inn12) {
		this.inn12 = inn12;
	}
	public int getBattingOrder() {
		return battingOrder;
	}
	public void setBattingOrder(int battingOrder) {
		this.battingOrder = battingOrder;
	}
	public int getBatInningTurn() {
		return batInningTurn;
	}
	public void setBatInningTurn(int batInningTurn) {
		this.batInningTurn = batInningTurn;
	}
	public int getBatPitchSeq() {
		return batPitchSeq;
	}
	public void setBatPitchSeq(int batPitchSeq) {
		this.batPitchSeq = batPitchSeq;
	}
	public int getHitTypeCode() {
		return hitTypeCode;
	}
	public void setHitTypeCode(int hitTypeCode) {
		this.hitTypeCode = hitTypeCode;
	}
	public int getHitResultCode() {
		return hitResultCode;
	}
	public void setHitResultCode(int hitResultCode) {
		this.hitResultCode = hitResultCode;
	}
	public String getHitResultName() {
		return hitResultName;
	}
	public void setHitResultName(String hitResultName) {
		this.hitResultName = hitResultName;
	}
	public int getHitCourseCode() {
		return hitCourseCode;
	}
	public void setHitCourseCode(int hitCourseCode) {
		this.hitCourseCode = hitCourseCode;
	}
	public int getHitCoursePositionCode() {
		return hitCoursePositionCode;
	}
	public void setHitCoursePositionCode(int hitCoursePositionCode) {
		this.hitCoursePositionCode = hitCoursePositionCode;
	}
	public int getBallCode() {
		return ballCode;
	}
	public void setBallCode(int ballCode) {
		this.ballCode = ballCode;
	}
	public String getBallCodeName() {
		return ballCodeName;
	}
	public void setBallCodeName(String ballCodeName) {
		this.ballCodeName = ballCodeName;
	}
	public int getBallSpeed() {
		return ballSpeed;
	}
	public void setBallSpeed(int ballSpeed) {
		this.ballSpeed = ballSpeed;
	}
	public String getBallSpeedType() {
		return ballSpeedType;
	}
	public void setBallSpeedType(String ballSpeedType) {
		this.ballSpeedType = ballSpeedType;
	}
	public String getStrikeYn() {
		return strikeYn;
	}
	public void setStrikeYn(String strikeYn) {
		this.strikeYn = strikeYn;
	}
	public int getPitcherRosterTypeCode() {
		return pitcherRosterTypeCode;
	}
	public void setPitcherRosterTypeCode(int pitcherRosterTypeCode) {
		this.pitcherRosterTypeCode = pitcherRosterTypeCode;
	}
	public int getPitcherPositionTypeCode() {
		return pitcherPositionTypeCode;
	}
	public void setPitcherPositionTypeCode(int pitcherPositionTypeCode) {
		this.pitcherPositionTypeCode = pitcherPositionTypeCode;
	}
	public int getOpponentBatterCnt() {
		return opponentBatterCnt;
	}
	public void setOpponentBatterCnt(int opponentBatterCnt) {
		this.opponentBatterCnt = opponentBatterCnt;
	}
	public String getPitcherResultName() {
		return pitcherResultName;
	}
	public void setPitcherResultName(String pitcherResultName) {
		this.pitcherResultName = pitcherResultName;
	}
	public int getPitchZoneCode() {
		return pitchZoneCode;
	}
	public void setPitchZoneCode(int pitchZoneCode) {
		this.pitchZoneCode = pitchZoneCode;
	}
	public void setHitcoursePositionCode(int hitcoursePositionCode) {
		this.hitcoursePositionCode = hitcoursePositionCode;
	}
	
	public int getPitchZoneX() {
		return pitchZoneX;
	}
	public void setPitchZoneX(int pitchZoneX) {
		this.pitchZoneX = pitchZoneX;
	}
	public int getPitchZoneY() {
		return pitchZoneY;
	}
	public void setPitchZoneY(int pitchZoneY) {
		this.pitchZoneY = pitchZoneY;
	}
	public int getHitCourseX() {
		return hitCourseX;
	}
	public void setHitCourseX(int hitCourseX) {
		this.hitCourseX = hitCourseX;
	}
	public int getHitCourseY() {
		return hitCourseY;
	}
	public void setHitCourseY(int hitCourseY) {
		this.hitCourseY = hitCourseY;
	}
	public String getHrDistance() {
		return hrDistance;
	}
	public void setHrDistance(String hrDistance) {
		this.hrDistance = hrDistance;
	}
	public int getRunnerState() {
		return runnerState;
	}
	public void setRunnerState(int runnerState) {
		this.runnerState = runnerState;
	}
	public int getBattedBallTypeCode() {
		return battedBallTypeCode;
	}
	public void setBattedBallTypeCode(int battedBallTypeCode) {
		this.battedBallTypeCode = battedBallTypeCode;
	}
	public String getBatterTeamName() {
		return batterTeamName;
	}
	public void setBatterTeamName(String batterTeamName) {
		this.batterTeamName = batterTeamName;
	}
	public String getPitcherTeamName() {
		return pitcherTeamName;
	}
	public void setPitcherTeamName(String pitcherTeamName) {
		this.pitcherTeamName = pitcherTeamName;
	}
	public int getPositionTypeCode() {
		return positionTypeCode;
	}
	public void setPositionTypeCode(int positionTypeCode) {
		this.positionTypeCode = positionTypeCode;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public int getRosterTypeCode() {
		return rosterTypeCode;
	}
	public void setRosterTypeCode(int rosterTypeCode) {
		this.rosterTypeCode = rosterTypeCode;
	}
	public String getRosterTypeName() {
		return rosterTypeName;
	}
	public void setRosterTypeName(String rosterTypeName) {
		this.rosterTypeName = rosterTypeName;
	}
	public String getPitchingHand() {
		return pitchingHand;
	}
	public void setPitchingHand(String pitchingHand) {
		this.pitchingHand = pitchingHand;
	}
	public String getBattingHand() {
		return battingHand;
	}
	public void setBattingHand(String battingHand) {
		this.battingHand = battingHand;
	}
	public int getPitchingForm() {
		return pitchingForm;
	}
	public void setPitchingForm(int pitchingForm) {
		this.pitchingForm = pitchingForm;
	}
	public String getPitchingFormName() {
		return pitchingFormName;
	}
	public void setPitchingFormName(String pitchingFormName) {
		this.pitchingFormName = pitchingFormName;
	}
	public List<GameRecord> getRecords() {
		return records;
	}
	public void setRecords(List<GameRecord> records) {
		this.records = records;
	}
	public String getReferee() {
		return referee;
	}
	public void setReferee(String referee) {
		this.referee = referee;
	}
	public String getRefereeName() {
		return refereeName;
	}
	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}
	public String getRefereePosition() {
		return refereePosition;
	}
	public void setRefereePosition(String refereePosition) {
		this.refereePosition = refereePosition;
	} 

	public String getGameBehind() {
		return gameBehind.toString();
	}
	public void setGameBehind(BigDecimal gameBehind) {
		this.gameBehind = gameBehind;
	}
	public int getBallCount() {
		return ballCount;
	}
	public void setBallCount(int ballCount) {
		this.ballCount = ballCount;
	}
	public int getStrikeCount() {
		return strikeCount;
	}
	public void setStrikeCount(int strikeCount) {
		this.strikeCount = strikeCount;
	}
	public int getWinCnt() {
		return winCnt;
	}
	public void setWinCnt(int winCnt) {
		this.winCnt = winCnt;
	}
	public int getLoseCnt() {
		return loseCnt;
	}
	public void setLoseCnt(int loseCnt) {
		this.loseCnt = loseCnt;
	}
	public int getDrawCnt() {
		return drawCnt;
	}
	public void setDrawCnt(int drawCnt) {
		this.drawCnt = drawCnt;
	}
	public int getSaveCnt() {
		return saveCnt;
	}
	public void setSaveCnt(int saveCnt) {
		this.saveCnt = saveCnt;
	}
	public int getHoldCnt() {
		return holdCnt;
	}
	public void setHoldCnt(int holdCnt) {
		this.holdCnt = holdCnt;
	}
	public String getExPitchZoneCode() {
		return exPitchZoneCode;
	}
	public void setExPitchZoneCode(String exPitchZoneCode) {
		this.exPitchZoneCode = exPitchZoneCode;
	}
	public String getStrikeZoneMatchYn() {
		return strikeZoneMatchYn;
	}
	public void setStrikeZoneMatchYn(String strikeZoneMatchYn) {
		this.strikeZoneMatchYn = strikeZoneMatchYn;
	}
	public int getFastball() {
		return fastball;
	}
	public void setFastball(int fastBall) {
		this.fastball = fastBall;
	}
	public int getCurve() {
		return curve;
	}
	public void setCurve(int curve) {
		this.curve = curve;
	}
	public int getSlide() {
		return slide;
	}
	public void setSlide(int slide) {
		this.slide = slide;
	}
	public int getEtcBall() {
		return etcBall;
	}
	public void setEtcBall(int etcBall) {
		this.etcBall = etcBall;
	}
	public int getGameInning() {
		return gameInning;
	}
	public void setGameInning(int gameInnning) {
		this.gameInning = gameInnning;
	}
	public int getBeforeRunnerState() {
		return beforeRunnerState;
	}
	public void setBeforeRunnerState(int beforeRunnerState) {
		this.beforeRunnerState = beforeRunnerState;
	}
	public String getPitchingBallCnt() {
		return pitchingBallCnt;
	}
	public void setPitchingBallCnt(String pitchingBallCnt) {
		this.pitchingBallCnt = pitchingBallCnt;
	}
	public String getAbCategory() {
		return abCategory;
	}
	public void setAbCategory(String abCategory) {
		this.abCategory = abCategory;
	}
	public int getBefore_out_state() {
		return before_out_state;
	}
	public void setBefore_out_state(int before_out_state) {
		this.before_out_state = before_out_state;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPitchSbZoneCode() {
		return pitchSbZoneCode;
	}
	public void setPitchSbZoneCode(String pitchSbZoneCode) {
		this.pitchSbZoneCode = pitchSbZoneCode;
	}
	public int getPitchSbZoneRanking() {
		return pitchSbZoneRanking;
	}
	public void setPitchSbZoneRanking(int pitchSbZoneRanking) {
		this.pitchSbZoneRanking = pitchSbZoneRanking;
	}
	public int getPkCnt() {
		return pkCnt;
	}
	public void setPkCnt(int pkCnt) {
		this.pkCnt = pkCnt;
	}
	public int getRunnerDieCnt() {
		return runnerDieCnt;
	}
	public void setRunnerDieCnt(int runnerDieCnt) {
		this.runnerDieCnt = runnerDieCnt;
	}
	public String getSbCnt() {
		return sbCnt;
	}
	public void setSbCnt(String sbCnt) {
		this.sbCnt = sbCnt;
	}
	public String getBattingOrderLocation() {
		return battingOrderLocation;
	}
	public void setBattingOrderLocation(String battingOrderLocation) {
		this.battingOrderLocation = battingOrderLocation;
	}
	public int getBattingTurn() {
		return battingTurn;
	}
	public void setBattingTurn(int battingTurn) {
		this.battingTurn = battingTurn;
	}
	public String getBatInningTurnName() {
		return batInningTurnName;
	}
	public void setBatInningTurnName(String batInningTurnName) {
		this.batInningTurnName = batInningTurnName;
	}
	public String getStadiumName() {
		return stadiumName;
	}
	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}
	public int getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}
	public int getPitchingPositionTypeCode() {
		return pitchingPositionTypeCode;
	}
	public void setPitchingPositionTypeCode(int pitchingPositionTypeCode) {
		this.pitchingPositionTypeCode = pitchingPositionTypeCode;
	}
	public String getBattingPositionTypeName() {
		return battingPositionTypeName;
	}
	public void setBattingPositionTypeName(String battingPositionTypeName) {
		this.battingPositionTypeName = battingPositionTypeName;
	}
	public String getInning() {
		return inning;
	}
	public void setInning(String inning) {
		this.inning = inning;
	}
	public String getScoreGapCategory() {
		return scoreGapCategory;
	}
	public void setScoreGapCategory(String scoreGapCategory) {
		this.scoreGapCategory = scoreGapCategory;
	}
	public int getBeforeScoreGap() {
		return beforeScoreGap;
	}
	public void setBeforeScoreGap(int beforeScoreGap) {
		this.beforeScoreGap = beforeScoreGap;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
//	public int getsSwingCnt() {
//		return sSwingCnt;
//	}
//	public void setsSwingCnt(int sSwingCnt) {
//		this.sSwingCnt = sSwingCnt;
//	}
//	public int getsContactCnt() {
//		return sContactCnt;
//	}
//	public void setsContactCnt(int sContactCnt) {
//		this.sContactCnt = sContactCnt;
//	}
//	public int getbContactCnt() {
//		return bContactCnt;
//	}
//	public void setbContactCnt(int bContactCnt) {
//		this.bContactCnt = bContactCnt;
//	}
//	public int getbSituationCnt() {
//		return bSituationCnt;
//	}
//	public void setbSituationCnt(int bSituationCnt) {
//		this.bSituationCnt = bSituationCnt;
//	}
//	public int getFirstBallCnt() {
//		return firstBallCnt;
//	}
//	public void setFirstBallCnt(int firstBallCnt) {
//		this.firstBallCnt = firstBallCnt;
//	}
//	public int getFirstBallContactCnt() {
//		return firstBallContactCnt;
//	}
//	public void setFirstBallContactCnt(int firstBallContactCnt) {
//		this.firstBallContactCnt = firstBallContactCnt;
//	}
//	public int getsSituationCnt() {
//		return sSituationCnt;
//	}
//	public void setsSituationCnt(int sSituationCnt) {
//		this.sSituationCnt = sSituationCnt;
//	}
	public int getBeforeOutCount() {
		return beforeOutCount;
	}
	public void setBeforeOutCount(int beforeOutCount) {
		this.beforeOutCount = beforeOutCount;
	}
	public String getRestDayGap() {
		return restDayGap;
	}
	public void setRestDayGap(String restDayGap) {
		this.restDayGap = restDayGap;
	}
	public int getRemainderBase() {
		return remainderBase;
	}
	public void setRemainderBase(int remainderBase) {
		this.remainderBase = remainderBase;
	}
	public String getScoreCode() {
		return scoreCode;
	}
	public void setScoreCode(String scoreCode) {
		this.scoreCode = scoreCode;
	}
	public int getStreakGameCnt() {
		return streakGameCnt;
	}
	public void setStreakGameCnt(int streakGameCnt) {
		this.streakGameCnt = streakGameCnt;
	}
	public String getStreakGameCategory() {
		return streakGameCategory;
	}
	public void setStreakGameCategory(String streakGameCategory) {
		this.streakGameCategory = streakGameCategory;
	}
	public int getMaxBallSpeed() {
		return maxBallSpeed;
	}
	public void setMaxBallSpeed(int maxBallSpeed) {
		this.maxBallSpeed = maxBallSpeed;
	}
	public int getMinBallSpeed() {
		return minBallSpeed;
	}
	public void setMinBallSpeed(int minBallSpeed) {
		this.minBallSpeed = minBallSpeed;
	}
	public BigDecimal getAvgBallSpeed() {
		return avgBallSpeed;
	}
	public void setAvgBallSpeed(BigDecimal avgBallSpeed) {
		this.avgBallSpeed = avgBallSpeed;
	}
	public String getAccumPitchingBallCnt() {
		return accumPitchingBallCnt;
	}
	public void setAccumPitchingBallCnt(String accumPitchingBallCnt) {
		this.accumPitchingBallCnt = accumPitchingBallCnt;
	}
	public BigDecimal getMaxWpa() {
		return maxWpa;
	}
	public void setMaxWpa(BigDecimal maxWpa) {
		this.maxWpa = maxWpa;
	}
	public BigDecimal getMinWpa() {
		return minWpa;
	}
	public void setMinWpa(BigDecimal minWpa) {
		this.minWpa = minWpa;
	}
	public BigDecimal getPlusWpaSum() {
		return plusWpaSum;
	}
	public void setPlusWpaSum(BigDecimal plusWpaSum) {
		this.plusWpaSum = plusWpaSum;
	}
	public BigDecimal getMinusWpaSum() {
		return minusWpaSum;
	}
	public void setMinusWpaSum(BigDecimal minusWpaSum) {
		this.minusWpaSum = minusWpaSum;
	}
	public int getBatterRosterTypeCode() {
		return batterRosterTypeCode;
	}
	public void setBatterRosterTypeCode(int batterRosterTypeCode) {
		this.batterRosterTypeCode = batterRosterTypeCode;
	}
	public int getStadiumsId() {
		return stadiumsId;
	}
	public void setStadiumsId(int stadiumsId) {
		this.stadiumsId = stadiumsId;
	}
	public String getGameStime() {
		return gameStime;
	}
	public void setGameStime(String gameStime) {
		this.gameStime = gameStime;
	}
	public int getSituationCnt() {
		return situationCnt;
	}
	public void setSituationCnt(int situationCnt) {
		this.situationCnt = situationCnt;
	}
	public String getAnalysisFactor() {
		return analysisFactor;
	}
	public void setAnalysisFactor(String analysisFactor) {
		this.analysisFactor = analysisFactor;
	}
	public int getAnalysisValue() {
		return analysisValue;
	}
	public void setAnalysisValue(int analysisValue) {
		this.analysisValue = analysisValue;
	}
	public String getWinOrLose() {
		return winOrLose;
	}
	public void setWinOrLose(String winOrLose) {
		this.winOrLose = winOrLose;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getPositionTypeName() {
		return positionTypeName;
	}
	public void setPositionTypeName(String positionTypeName) {
		this.positionTypeName = positionTypeName;
	}
	public int getAccumtPitchingBallCnt() {
		return accumtPitchingBallCnt;
	}
	public void setAccumtPitchingBallCnt(int accumtPitchingBallCnt) {
		this.accumtPitchingBallCnt = accumtPitchingBallCnt;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public double getWinOrLoseRate() {
		return winOrLoseRate;
	}
	public void setWinOrLoseRate(double winOrLoseRate) {
		this.winOrLoseRate = winOrLoseRate;
	}
	public String getBattingOrderCategory() {
		return battingOrderCategory;
	}
	public void setBattingOrderCategory(String battingOrderCategory) {
		this.battingOrderCategory = battingOrderCategory;
	}
	public String getInningCategory() {
		return inningCategory;
	}
	public void setInningCategory(String inningCategory) {
		this.inningCategory = inningCategory;
	}
	public int getEr() {
		return super.getEr();
	}
	public void setEr(int er) {
		super.setEr(er);
	}
	public GameRecord getRelationRecord() {
		return relationRecord;
	}
	public void setRelationRecord(GameRecord relationRecord) {
		this.relationRecord = relationRecord;
	}
	public String getCatcherTeamName() {
		return catcherTeamName;
	}
	public void setCatcherTeamName(String catcherTeamName) {
		this.catcherTeamName = catcherTeamName;
	}
	public String getSuccessStealCategory() {
		return successStealCategory;
	}
	public void setSuccessStealCategory(String successStealCategory) {
		this.successStealCategory = successStealCategory;
	}
	public int getPitcherCnt() {
		return pitcherCnt;
	}
	public void setPitcherCnt(int pitcherCnt) {
		this.pitcherCnt = pitcherCnt;
	}
	public String getDistBallSpeed() {
		return distBallSpeed;
	}
	public void setDistBallSpeed(String distBallSpeed) {
		this.distBallSpeed = distBallSpeed;
	}
	public int getDistBallCode() {
		return distBallCode;
	}
	public void setDistBallCode(int distBallCode) {
		this.distBallCode = distBallCode;
	}
	public String getDistAccmPitchingCnt() {
		return distAccmPitchingCnt;
	}
	public void setDistAccmPitchingCnt(String distAccmPitchingCnt) {
		this.distAccmPitchingCnt = distAccmPitchingCnt;
	}
	public String getDistPaSeq() {
		return distPaSeq;
	}
	public void setDistPaSeq(String distPaSeq) {
		this.distPaSeq = distPaSeq;
	}
	public String getBeforeDecidedBallCount() {
		return beforeDecidedBallCount;
	}
	public void setBeforeDecidedBallCount(String beforeDecidedBallCount) {
		this.beforeDecidedBallCount = beforeDecidedBallCount;
	}
	public String getStadium() {
		return stadium;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	public String getWinOrLoseStreak() {
		return winOrLoseStreak;
	}
	public void setWinOrLoseStreak(String winOrLoseStreak) {
		this.winOrLoseStreak = winOrLoseStreak;
	}
	public int getStreakCheck() {
		return streakCheck;
	}
	public void setStreakCheck(int streakCheck) {
		this.streakCheck = streakCheck;
	}
	public String getLasted10GamesResult() {
		return lasted10GamesResult;
	}
	public void setLasted10GamesResult(String lasted10GamesResult) {
		this.lasted10GamesResult = lasted10GamesResult;
	}
	public int getTeamRanking() {
		return teamRanking;
	}
	public void setTeamRanking(int teamRanking) {
		this.teamRanking = teamRanking;
	}
	public int getAwayR() {
		return awayR;
	}
	public void setAwayR(int awayR) {
		this.awayR = awayR;
	}
	public int getHomeR() {
		return homeR;
	}
	public void setHomeR(int homeR) {
		this.homeR = homeR;
	}
	public int getWinDrawLose() {
		return winDrawLose;
	}
	public void setWinDrawLose(int winDrawLose) {
		this.winDrawLose = winDrawLose;
	}
	public String getRealData() {
		return realData;
	}
	public void setRealData(String realData) {
		this.realData = realData;
	}
	public String getDistBatterScoreGap() {
		return distBatterScoreGap;
	}
	public void setDistBatterScoreGap(String distBatterScoreGap) {
		this.distBatterScoreGap = distBatterScoreGap;
	}
	public String getDistPitcherScoreGap() {
		return distPitcherScoreGap;
	}
	public void setDistPitcherScoreGap(String distPitcherScoreGap) {
		this.distPitcherScoreGap = distPitcherScoreGap;
	}
	public String getSpectatorCnt() {
		return spectatorCnt;
	}
	public void setSpectatorCnt(String spectatorCnt) {
		this.spectatorCnt = spectatorCnt;
	}
	public String getPositionChange() {
		return positionChange;
	}
	public void setPositionChange(String positionChange) {
		this.positionChange = positionChange;
	}
	public int getDistAccmPitchingCntOrd() {
		return distAccmPitchingCntOrd;
	}
	public void setDistAccmPitchingCntOrd(int distAccmPitchingCntOrd) {
		this.distAccmPitchingCntOrd = distAccmPitchingCntOrd;
	}
	public String getRunnerExistYn() {
		return runnerExistYn;
	}
	public void setRunnerExistYn(String runnerExistYn) {
		this.runnerExistYn = runnerExistYn;
	}
	public String getPitcherType() {
		return pitcherType;
	}
	public void setPitcherType(String pitcherType) {
		this.pitcherType = pitcherType;
	}
	public int getBacknumber() {
		return backnumber;
	}
	public void setBacknumber(int backnumber) {
		this.backnumber = backnumber;
	}
	public String getGameEnterYn() {
		return gameEnterYn;
	}
	public void setGameEnterYn(String gameEnterYn) {
		this.gameEnterYn = gameEnterYn;
	}
	public int getFieldingResultPositionCode() {
		return fieldingResultPositionCode;
	}
	public void setFieldingResultPositionCode(int fieldingResultPositionCode) {
		this.fieldingResultPositionCode = fieldingResultPositionCode;
	}
	public String getFieldingResultPositionName() {
		return fieldingResultPositionName;
	}
	public void setFieldingResultPositionName(String fieldingResultPositionName) {
		this.fieldingResultPositionName = fieldingResultPositionName;
	}
	
}
